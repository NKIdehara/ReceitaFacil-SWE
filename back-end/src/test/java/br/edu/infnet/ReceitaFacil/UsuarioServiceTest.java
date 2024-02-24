package br.edu.infnet.ReceitaFacil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.GetUsersResult;
import com.google.firebase.auth.ListUsersPage;

import br.edu.infnet.ReceitaFacil.model.Usuario;

public class UsuarioServiceTest {
 
    @Test
    public void ReceitaFacil_TestaConexaoComFirebase_True() throws InterruptedException, ExecutionException, IOException {
        // Arrange
        GoogleCredentials credentials = null;
        try {
            ClassLoader classLoader = UsuarioServiceTest.class.getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("google-services.json")).getFile());
            FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
            credentials = GoogleCredentials.fromStream(serviceAccount);
        } catch (Exception e) {
            String GOOGLE_APPLICATION_CREDENTIALS = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
            credentials = ServiceAccountCredentials.fromStream(new ByteArrayInputStream(GOOGLE_APPLICATION_CREDENTIALS.getBytes(StandardCharsets.UTF_8)));   
        }
        FirebaseOptions options = FirebaseOptions.builder()
            .setCredentials(credentials)
            .build();
		FirebaseApp.initializeApp(options);

        // Act
        GetUsersResult result = FirebaseAuth.getInstance().getUsersAsync(Arrays.asList()).get();        

        // Assert
        Assertions.assertThat(result.getUsers()).isNotNull();
    }
 
    @Test
    public void ReceitaFacil_ContaQuantidadeDeUsuarios_True() throws InterruptedException, ExecutionException, IOException, FirebaseAuthException {
        // Arrange
        int num_usuarios = 8;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        GoogleCredentials credentials = null;
        try {
            ClassLoader classLoader = UsuarioServiceTest.class.getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("google-services.json")).getFile());
            FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
            credentials = GoogleCredentials.fromStream(serviceAccount);
        } catch (Exception e) {
            String GOOGLE_APPLICATION_CREDENTIALS = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
            credentials = ServiceAccountCredentials.fromStream(new ByteArrayInputStream(GOOGLE_APPLICATION_CREDENTIALS.getBytes(StandardCharsets.UTF_8)));   
        }
        FirebaseOptions options = FirebaseOptions.builder()
            .setCredentials(credentials)
            .build();
		FirebaseApp.initializeApp(options);

        // Act
        ApiFuture<ListUsersPage> query = FirebaseAuth.getInstance().listUsersAsync(null);
        ListUsersPage listUsersPage = query.get();
        for (ExportedUserRecord user : listUsersPage.getValues()) {
            Usuario usuario = new Usuario(user.getEmail(), user.getUid());
            usuarios.add(usuario);
        }

        // Assert
        Assertions.assertThat(usuarios.size()).isEqualTo(num_usuarios);
    }
}
