package br.edu.infnet.ReceitaFacil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class ReceitaFacilApplication {
	public static void main(String[] args) throws IOException {
		String GOOGLE_APPLICATION_CREDENTIALS = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
		ServiceAccountCredentials credentials = ServiceAccountCredentials
                .fromStream(new ByteArrayInputStream(GOOGLE_APPLICATION_CREDENTIALS.getBytes(StandardCharsets.UTF_8)));
		
        FirebaseOptions options = FirebaseOptions.builder()
			.setCredentials(credentials)
			.build();

		FirebaseApp.initializeApp(options);

		SpringApplication.run(ReceitaFacilApplication.class, args);
	}

}
