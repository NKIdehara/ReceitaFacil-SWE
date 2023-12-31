package br.edu.infnet.ReceitaFacil.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.cloud.FirestoreClient;

import br.edu.infnet.ReceitaFacil.model.Usuario;

@Service
public class UsuarioService {
    public List<Usuario> getUsuarios() throws InterruptedException, ExecutionException {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<ListUsersPage> query = FirebaseAuth.getInstance().listUsersAsync(null);
        ListUsersPage listUsersPage = query.get();
        for (ExportedUserRecord user : listUsersPage.getValues()) {
            DocumentReference docRef = dbFirestore.collection("Usuarios").document(user.getUid());
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot userData = future.get();
            Usuario usuario;
            if(!userData.exists()) {
                usuario = new Usuario(user.getUid(), user.getEmail(), "Novo Usuário", Long.valueOf(2), "Sem Endereço");
            } else {
                usuario = new Usuario(user.getUid(), user.getEmail(), userData.getString("nome"), userData.getLong("tipoAcesso"), userData.getString("endereco"));
            }
            usuarios.add(usuario);
        }
        return usuarios;
    }    
}