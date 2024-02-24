package br.edu.infnet.ReceitaFacil.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.cloud.FirestoreClient;
import br.edu.infnet.ReceitaFacil.model.UsuarioRegistro;

@Service
@SuppressWarnings("null")
public class UsuarioService {
    public List<UsuarioRegistro> getUsuarios() throws InterruptedException, ExecutionException {
        List<UsuarioRegistro> usuarios = new ArrayList<UsuarioRegistro>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<ListUsersPage> query = FirebaseAuth.getInstance().listUsersAsync(null);
        ListUsersPage listUsersPage = query.get();
        for (ExportedUserRecord user : listUsersPage.getValues()) {
            DocumentReference docRef = dbFirestore.collection("Usuarios").document(user.getUid());
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot userData = future.get();
            UsuarioRegistro usuario;
            if(!userData.exists()) {
                usuario = new UsuarioRegistro(user.getUid(), user.getEmail(), "Novo Usuário", Long.valueOf(2), "Sem Endereço");
            } else {
                usuario = new UsuarioRegistro(user.getUid(), user.getEmail(), userData.getString("nome"), userData.getLong("tipoAcesso"), userData.getString("endereco"));
            }
            usuarios.add(usuario);
        }
        return usuarios;
    }

    public UsuarioRegistro getUsuario(String UID) throws InterruptedException, ExecutionException, FirebaseAuthException {
        UsuarioRegistro usuario;
        Firestore dbFirestore = FirestoreClient.getFirestore();
        UserRecord user = FirebaseAuth.getInstance().getUser(UID);
        DocumentReference docRef = dbFirestore.collection("Usuarios").document(UID);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot userData = future.get();
        usuario = new UsuarioRegistro(user.getUid(), user.getEmail(), userData.getString("nome"), userData.getLong("tipoAcesso"), userData.getString("endereco"));
        return usuario;
    }

    public UsuarioRegistro setUsuario(UsuarioRegistro usuario) throws InterruptedException, ExecutionException, FirebaseAuthException {
        String userUID;
        CreateRequest request = new CreateRequest()
            .setEmail(usuario.getEmail())
            .setPassword(usuario.getSenha())
            .setDisplayName(usuario.getNome())
            .setDisabled(false);
        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
        userUID = userRecord.getUid();

        Firestore dbFirestore = FirestoreClient.getFirestore();
        Map<String, Object> novoUsuario = new HashMap<>();
        novoUsuario.put("nome", usuario.getNome());
        novoUsuario.put("endereco", usuario.getEndereco());
        novoUsuario.put("tipoAcesso", usuario.getTipoAcesso());
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("Usuarios").document(userUID).set(novoUsuario);
        if(collectionsApiFuture.isDone()) {
            return usuario;
        }
        return null;
    }

    public void deleteUsuario(String UID) throws FirebaseAuthException, InterruptedException, ExecutionException {        
        Firestore dbFirestore = FirestoreClient.getFirestore();
        FirebaseAuth.getInstance().deleteUser(UID);
        dbFirestore.collection("Usuarios").document(UID).delete();
        ApiFuture<QuerySnapshot> query = dbFirestore.collection("Receitas").whereEqualTo("usuario", UID).get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            dbFirestore.collection("Receitas").document(document.getId()).delete();
        }
    }
}