package com.agronova;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
public class CreateAccount extends Application {

    @Override
    public void start(Stage stage) {

        // Main background
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        root.setStyle(
            "-fx-background-color: linear-gradient(to bottom right, #0b3d2e, #1b6b4a, #8fbc8f);"
        );

        // Card
        VBox card = new VBox(14);
        card.setAlignment(Pos.CENTER);
        card.setPrefWidth(430);
        card.setMaxWidth(430);
        card.setPadding(new Insets(35));

        card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.96);" +
            "-fx-background-radius: 25;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.30), 25, 0, 0, 8);"
        );

        // Logo
        Label logo = new Label("🌾");
        logo.setFont(Font.font(45));

        // Title
        Label title = new Label("Create Account");
        title.setFont(
            Font.font("Arial", FontWeight.BOLD, 30)
        );
        title.setTextFill(Color.web("#174d35"));

        // Subtitle
        Label subtitle = new Label(
            "Join AgroNova and manage your farm smarter"
        );
        subtitle.setTextFill(Color.GRAY);
        subtitle.setFont(Font.font("Arial", 13));

        // Full Name
        TextField nameField = new TextField();
        nameField.setPromptText("Full Name");
        nameField.setPrefHeight(42);

        // Email
        TextField emailField = new TextField();
        emailField.setPromptText("Email Address");
        emailField.setPrefHeight(42);

        // Mobile
        TextField mobileField = new TextField();
        mobileField.setPromptText("Mobile Number");
        mobileField.setPrefHeight(42);

        // Password
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setPrefHeight(42);

        // Confirm Password
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");
        confirmPasswordField.setPrefHeight(42);

        // Create button
        Button createButton = new Button("CREATE ACCOUNT");
        createButton.setOnAction(e -> {

    if (nameField.getText().isEmpty()
            || emailField.getText().isEmpty()
            || mobileField.getText().isEmpty()
            || passwordField.getText().isEmpty()
            || confirmPasswordField.getText().isEmpty()) {

        subtitle.setText("Please fill all the fields");
        subtitle.setTextFill(Color.RED);

    } else if (!passwordField.getText()
            .equals(confirmPasswordField.getText())) {

        subtitle.setText("Passwords do not match");
        subtitle.setTextFill(Color.RED);

    } else {

         try {

        Firestore db = FirestoreClient.getFirestore();

        java.util.Map<String, Object> userData =
                new java.util.HashMap<>();

        userData.put("fullName", nameField.getText());
        userData.put("email", emailField.getText());
        userData.put("mobile", mobileField.getText());
        userData.put("password", passwordField.getText());

        db.collection("users")
          .add(userData);

        subtitle.setText("Account created successfully! 🌱");
        subtitle.setTextFill(Color.web("#176b45"));

    } catch (Exception ex) {

        subtitle.setText("Failed to create account!");
        subtitle.setTextFill(Color.RED);

        ex.printStackTrace();
    }
}
    
});
        createButton.setPrefWidth(300);
        createButton.setPrefHeight(45);

        createButton.setStyle(
            "-fx-background-color: #176b45;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 12;" +
            "-fx-cursor: hand;"
        );

        // Back button
        Button backButton = new Button("← Back to Login");

        backButton.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #176b45;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );

        backButton.setOnAction(e -> stage.close());

        // Add everything to card
        card.getChildren().addAll(
            logo,
            title,
            subtitle,
            nameField,
            emailField,
            mobileField,
            passwordField,
            confirmPasswordField,
            createButton,
            backButton
        );

        root.getChildren().add(card);

        Scene scene = new Scene(root, 1000, 650);

        stage.setTitle("AgroNova - Create Account");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}