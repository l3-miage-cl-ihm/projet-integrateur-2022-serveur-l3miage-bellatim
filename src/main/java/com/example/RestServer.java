package com.example;

// import java.io.IOException;

// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;

import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
// import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class RestServer {
    public static void main(String[] args) {
        /*
         * System.out.println("go Firebase");
         * try {
         * FirebaseOptions options = FirebaseOptions.builder()
         * .setCredentials(GoogleCredentials.getApplicationDefault())
         * // .setDatabaseUrl("https://<DATABASE_NAME>.firebaseio.com/")
         * .build();
         * 
         * FirebaseApp.initializeApp(options);
         * System.out.println("banco pour Firebase");
         * } catch (IOException e) {
         * System.err.println( "error: " + e.getLocalizedMessage() );
         * }
         */

        // Lancement de Spring Boot
        SpringApplication.run(RestServer.class, args);
    }

}
