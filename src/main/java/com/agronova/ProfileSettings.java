package com.agronova;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProfileSettings {

    public void show(Stage stage, String loggedInEmail) {

        Firestore db = FirestoreClient.getFirestore();

        // ===============================
        // TOP BAR
        // ===============================

        Label logo = new Label("🌿 AgroNova");

        logo.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label pageTitle = new Label("Profile & Settings");

        pageTitle.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-text-fill: white;"
        );

        HBox topBar = new HBox(30, logo, pageTitle);

        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(15, 25, 15, 25));

        topBar.setStyle(
                "-fx-background-color: #2e7d32;"
        );

        // ===============================
        // PROFILE TITLE
        // ===============================

        Label title = new Label("👤 My Profile");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #2e7d32;"
        );

        Label subtitle = new Label(
                "Manage your AgroNova account information"
        );

        subtitle.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #666666;"
        );

        // ===============================
        // FULL NAME
        // ===============================

        Label usernameLabel = new Label("Full Name");

        TextField username = new TextField();

        username.setPromptText("Enter Full Name");
        username.setMaxWidth(400);

        // ===============================
        // EMAIL
        // ===============================

        Label emailLabel = new Label("Email");

        TextField email = new TextField();

        email.setPromptText("Enter email");
        email.setMaxWidth(400);

        // ===============================
        // PASSWORD
        // ===============================

        Label passwordLabel = new Label("Password");

        PasswordField password = new PasswordField();

        password.setPromptText("Enter new password");
        password.setMaxWidth(400);

        // ===============================
        // LOAD USER DATA
        // ===============================

        try {

            var documents = db.collection("users")
                    .whereEqualTo("email", loggedInEmail)
                    .get()
                    .get()
                    .getDocuments();

            if (!documents.isEmpty()) {

                var userData = documents.get(0).getData();

                username.setText(
                        String.valueOf(
                                userData.getOrDefault(
                                        "fullName",
                                        ""
                                )
                        )
                );

                email.setText(
                        String.valueOf(
                                userData.getOrDefault(
                                        "email",
                                        ""
                                )
                        )
                );
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        // ===============================
        // SAVE BUTTON
        // ===============================

        Button saveButton = new Button(
                "💾 Save Changes"
        );

        saveButton.setStyle(
                "-fx-background-color: #43a047;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 10 25;" +
                "-fx-background-radius: 8;"
        );

        saveButton.setOnAction(event -> {

            String fullName =
                    username.getText().trim();

            String newEmail =
                    email.getText().trim();

            String newPassword =
                    password.getText().trim();

            // ===============================
            // VALIDATION
            // ===============================

            if (fullName.isEmpty()
                    || newEmail.isEmpty()) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.WARNING
                        );

                alert.setTitle("Profile");
                alert.setHeaderText(null);

                alert.setContentText(
                        "Full Name and Email are required."
                );

                alert.showAndWait();

                return;
            }

            try {

                // ===============================
                // FIND LOGGED-IN USER
                // ===============================

                var documents = db.collection("users")
                        .whereEqualTo(
                                "email",
                                loggedInEmail
                        )
                        .get()
                        .get()
                        .getDocuments();

                if (documents.isEmpty()) {

                    Alert alert =
                            new Alert(
                                    Alert.AlertType.ERROR
                            );

                    alert.setTitle("Profile");
                    alert.setHeaderText(null);

                    alert.setContentText(
                            "User profile not found."
                    );

                    alert.showAndWait();

                    return;
                }

                // ===============================
                // UPDATE USER
                // ===============================

                var document =
                        documents.get(0);

                document.getReference().update(
                        "fullName",
                        fullName,
                        "email",
                        newEmail
                );

                // ===============================
                // UPDATE PASSWORD
                // ===============================

                if (!newPassword.isEmpty()) {

                    document.getReference().update(
                            "password",
                            newPassword
                    );
                }

                // ===============================
                // SUCCESS MESSAGE
                // ===============================

                Alert alert =
                        new Alert(
                                Alert.AlertType.INFORMATION
                        );

                alert.setTitle("Profile");

                alert.setHeaderText(null);

                alert.setContentText(
                        "Profile updated successfully! 🌱"
                );

                alert.showAndWait();

            } catch (Exception ex) {

                ex.printStackTrace();

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR
                        );

                alert.setTitle("Profile");

                alert.setHeaderText(null);

                alert.setContentText(
                        "Failed to update profile."
                );

                alert.showAndWait();
            }
        });

        // ===============================
        // BACK BUTTON
        // ===============================

        Button backButton = new Button(
                "← Back to Dashboard"
        );

        backButton.setStyle(
                "-fx-background-color: #eeeeee;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 10 20;" +
                "-fx-background-radius: 8;"
        );

        backButton.setOnAction(event -> {

            Dashboard dashboard =
                    new Dashboard();

            dashboard.show(
                    stage,
                    loggedInEmail
            );
        });

        HBox buttons =
                new HBox(
                        15,
                        saveButton,
                        backButton
                );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        // ===============================
        // PROFILE CARD
        // ===============================

        VBox profileCard =
                new VBox(
                        12,
                        title,
                        subtitle,
                        usernameLabel,
                        username,
                        emailLabel,
                        email,
                        passwordLabel,
                        password,
                        buttons
                );

        profileCard.setPadding(
                new Insets(30)
        );

        profileCard.setMaxWidth(500);

        profileCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: #c8e6c9;" +
                "-fx-border-radius: 15;"
        );

        // ===============================
        // CENTER
        // ===============================

        VBox center =
                new VBox(profileCard);

        center.setAlignment(
                Pos.TOP_CENTER
        );

        center.setPadding(
                new Insets(40)
        );

        center.setStyle(
                "-fx-background-color: #f1f8f2;"
        );

        // ===============================
        // MAIN LAYOUT
        // ===============================

        BorderPane root =
                new BorderPane();

        root.setTop(topBar);
        root.setCenter(center);

        // ===============================
        // SCENE
        // ===============================

        Scene scene =
                new Scene(
                        root,
                        1200,
                        750
                );

        stage.setScene(scene);

        stage.setTitle(
                "AgroNova - Profile & Settings"
        );

        stage.show();
    }
}