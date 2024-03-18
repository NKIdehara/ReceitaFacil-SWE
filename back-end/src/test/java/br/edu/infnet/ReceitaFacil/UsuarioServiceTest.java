package br.edu.infnet.ReceitaFacil;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import java.io.IOException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
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
    public void ReceitaFacil_QuantidadeDeUsuariosMaiorDoQueZero_True() throws InterruptedException, ExecutionException, IOException, FirebaseAuthException {
        // Arrange
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            ClassLoader classLoader = UsuarioServiceTest.class.getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("google-services.json")).getFile());
            FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
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
            Assertions.assertThat(usuarios.size()).isGreaterThan(0);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
 
    @Test
    public void ReceitaFacil_ConectaComFirebaseAuth_True() throws InterruptedException, ExecutionException, IOException {
        // Arrange
        try {
            ClassLoader classLoader = UsuarioServiceTest.class.getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("google-services.json")).getFile());
            FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();
            FirebaseApp.initializeApp(options);

            // Act
            GetUsersResult result = FirebaseAuth.getInstance().getUsersAsync(Arrays.asList()).get();        

            // Assert
            Assertions.assertThat(result.getUsers()).isNotNull();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}