package org.codevscovid19.threedprintingservice.security;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
@Order(0)
public class FirebaseConfiguration {
    @Value("${org.codevscovid19.threedprintingservice.security.firebase.key.location}")
    private String keyLocation;

    @EventListener(ContextRefreshedEvent.class)
    public void initFirebase() throws IOException {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(Files.newInputStream(Path.of(keyLocation))))
                .setDatabaseUrl("https://d-printing-dev.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(options);
    }
}
