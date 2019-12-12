package org.jarvis.jarvis_1.config;

import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FCMConfig
 */
@Configuration
public class FCMConfig {

    @Bean
    public void setFirebase(){
        try{
            FileInputStream serviceAccount = new FileInputStream("C:/Users/SH/FireBaseDB/jarvis-77f82-firebase-adminsdk-2l6dx-9676d850ae.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://jarvis-77f82.firebaseio.com").build();
            FirebaseApp.initializeApp(options, "options");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}