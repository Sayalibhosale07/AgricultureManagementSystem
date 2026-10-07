package com.agronova;

import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
public class FirebaseConfig {

    public static void initializeFirebase() {

        try {

            String path = System.getProperty("user.home")
                    + "\\Downloads\\serviceAccountKey.json";

            FileInputStream serviceAccount =
                    new FileInputStream(path);

            FirebaseOptions options =
                    new FirebaseOptions.Builder()
                            .setCredentials(
                                    GoogleCredentials.fromStream(serviceAccount)
                            )
                            .build();

            FirebaseApp.initializeApp(options);

            System.out.println("Firebase connected successfully!");

        } catch (Exception e) {

            System.out.println("Firebase connection failed!");
            e.printStackTrace();
        }
    }
    public static Firestore getFirestore() {

    return FirestoreClient.getFirestore(
        FirebaseApp.getInstance()
    );
}
}