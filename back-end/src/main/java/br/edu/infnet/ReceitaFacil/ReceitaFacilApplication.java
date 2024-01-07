package br.edu.infnet.ReceitaFacil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class ReceitaFacilApplication {
	public static void main(String[] args) throws IOException {

        // try {
        //     // Fetch the environment variables
		// 	String private_key_id = System.getenv("GOOGLE_PRIVATE_KEY_ID");
		// 	String private_key = System.getenv("GOOGLE_PRIVATE_KEY");
		// 	String client_email = System.getenv("GOOGLE_CLIENT_EMAIL");
		// 	String client_id = System.getenv("GOOGLE_CLIENT_ID");
		// 	String client_x509_cert_url = System.getenv("GOOGLE_CLIENT_X509_CERT_URL");

		// 	String serviceAccountJson = String.format(
        //         "{\"type\": \"service_account\", \"project_id\": \"infnet-receitafacil\", \"private_key_id\": \"%s\", \"private_key\": \"%s\", \"client_email\": \"%s\", \"client_id\": \"%s\", \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\", \"token_uri\": \"https://oauth2.googleapis.com/token\", \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\", \"client_x509_cert_url\": \"%s\", \"universe_domain\": \"googleapis.com\"}",
        //         private_key_id, private_key.replace("\\n", "\n"), client_email, client_id, client_x509_cert_url
        //     );

        //     // Create a credentials instance from the JSON string
        //     ServiceAccountCredentials credentials = ServiceAccountCredentials
        //         .fromStream(new ByteArrayInputStream(serviceAccountJson.getBytes(StandardCharsets.UTF_8)));
			
        //     // Print the credentials for demonstration
        //     System.out.println("Credentials: " + credentials);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
		String GOOGLE_APPLICATION_CREDENTIALS = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");

		ServiceAccountCredentials credentials = ServiceAccountCredentials
                .fromStream(new ByteArrayInputStream(GOOGLE_APPLICATION_CREDENTIALS.getBytes(StandardCharsets.UTF_8)));
		

		// ClassLoader classLoader = ReceitaFacilApplication.class.getClassLoader();
		// File file = new File(Objects.requireNonNull(classLoader.getResource("google-services.json")).getFile());
		// FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
        // FirebaseOptions options = FirebaseOptions.builder()
		// 	.setCredentials(GoogleCredentials.fromStream(serviceAccount))
		// 	.build();
        FirebaseOptions options = FirebaseOptions.builder()
			.setCredentials(credentials)
			.build();

		FirebaseApp.initializeApp(options);




		SpringApplication.run(ReceitaFacilApplication.class, args);
	}

}
