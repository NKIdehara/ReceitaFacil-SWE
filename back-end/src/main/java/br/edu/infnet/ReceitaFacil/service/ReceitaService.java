package br.edu.infnet.ReceitaFacil.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import br.edu.infnet.ReceitaFacil.model.Receita;

@Service
public class ReceitaService {
    public Receita newReceita(Receita receita) {        
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Map<String, Object> novaReceita = new HashMap<>();

        SimpleDateFormat style = new SimpleDateFormat("yyyy-MM-dd");
        String dataReceita = style.format(receita.getDataReceita());

        novaReceita.put("nome", receita.getNome());
        novaReceita.put("ingredientes", receita.getIngredientes());
        novaReceita.put("preparo", receita.getPreparo());
        novaReceita.put("dataReceita", dataReceita);
        novaReceita.put("usuario", receita.getUsuario());
        novaReceita.put("figura", receita.getFigura());

        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("Receitas").document().set(novaReceita);
        if(collectionsApiFuture.isDone()) {
            return receita;
        }
        return null;
    }

    public List<Receita> getReceitas(String userUID) throws InterruptedException, ExecutionException {
        List<Receita> receitas = new ArrayList<Receita>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = dbFirestore.collection("Receitas").whereEqualTo("usuario", userUID).orderBy("nome").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            Receita receita = new Receita(
                document.getId(),
                document.getString("nome"),
                document.getString("ingredientes"),
                document.getString("preparo"),
                Date.valueOf(document.getString("dataReceita")),
                document.getString("usuario"),
                document.getString("figura")
            );
            receitas.add(receita);
        }
        return receitas;
    }

    public List<Receita> getReceitas() throws InterruptedException, ExecutionException {
        List<Receita> receitas = new ArrayList<Receita>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = dbFirestore.collection("Receitas").orderBy("nome").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            Receita receita = new Receita(
                document.getId(),
                document.getString("nome"),
                document.getString("ingredientes"),
                document.getString("preparo"),
                Date.valueOf(document.getString("dataReceita")),
                document.getString("usuario"),
                document.getString("figura")
            );
            receitas.add(receita);
        }
        return receitas;
    }

    @SuppressWarnings("null")
    public void updateReceita(Receita receita) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Map<String, Object> atualizaReceita = new HashMap<>();

        SimpleDateFormat style = new SimpleDateFormat("yyyy-MM-dd");
        String dataReceita = style.format(receita.getDataReceita());

        atualizaReceita.put("nome", receita.getNome());
        atualizaReceita.put("ingredientes", receita.getIngredientes());
        atualizaReceita.put("preparo", receita.getPreparo());
        atualizaReceita.put("dataReceita", dataReceita);
        atualizaReceita.put("usuario", receita.getUsuario());
        atualizaReceita.put("figura", receita.getFigura());

        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("Receitas").document(receita.getId()).update(atualizaReceita);
        WriteResult result = collectionsApiFuture.get();
    }

    @SuppressWarnings("null")
    public void deleteReceita(String id) {        
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("Receitas").document(id).delete();
    }
}