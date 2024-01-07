package br.edu.infnet.ReceitaFacil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class ReceitaServiceTest {
/* 
    @Test
    public void ReceitaFacil_RecebeReceitasDoFirebase_True() throws InterruptedException, ExecutionException, IOException {
        // Arrange
		ClassLoader classLoader = ReceitaServiceTest.class.getClassLoader();
		File file = new File(Objects.requireNonNull(classLoader.getResource("google-services.json")).getFile());
		FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
        FirebaseOptions options = FirebaseOptions.builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
			.build();
		FirebaseApp.initializeApp(options);		

        // Act
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = dbFirestore.collection("Receitas").orderBy("nome").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        // Assert
        Assertions.assertThat(documents.size()).isNotNull();
        Assertions.assertThat(documents.size()).isEqualTo(13);
    }
*/
}
