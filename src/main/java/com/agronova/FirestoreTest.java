package com.agronova;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.util.HashMap;
import java.util.Map;

public class FirestoreTest {

    public static void testFirestore() {

        try {

            Firestore db = FirestoreClient.getFirestore();

            Map<String, Object> data = new HashMap<>();

            data.put("name", "AgroNova Test");
            data.put("message", "Firestore connected successfully");

            db.collection("test")
              .add(data);

            System.out.println("Firestore data saved successfully!");

        } catch (Exception e) {

            System.out.println("Firestore data save failed!");
            e.printStackTrace();
        }
    }
}