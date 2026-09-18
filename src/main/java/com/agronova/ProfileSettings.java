package com.agronova;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ProfileSettings {

    public void show(Stage stage) {

        // TOP BAR
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
        topBar.setStyle("-fx-background-color: #2e7d32;");

       
        // PROFILE TITLE
    

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

       
        // USERNAME
        

        Label usernameLabel = new Label("Username");

        TextField username = new TextField();
        username.setPromptText("Enter username");
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
        // SAVE BUTTON
        // ===============================

        Button saveButton = new Button("💾 Save Changes");

        saveButton.setStyle(
                "-fx-background-color: #43a047;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 10 25;" +
                "-fx-background-radius: 8;"
        );

        saveButton.setOnAction(event -> {
            System.out.println("Profile changes saved.");
        });

        // ===============================
        // BACK BUTTON
        // ===============================

        Button backButton = new Button("← Back to Dashboard");

        backButton.setStyle(
                "-fx-background-color: #eeeeee;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 10 20;" +
                "-fx-background-radius: 8;"
        );

        backButton.setOnAction(event -> {
            Dashboard dashboard = new Dashboard();
            dashboard.show(stage);
        });

        HBox buttons = new HBox(15, saveButton, backButton);
        buttons.setAlignment(Pos.CENTER_LEFT);

        // ===============================
        // PROFILE CARD
        // ===============================

        VBox profileCard = new VBox(
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

        profileCard.setPadding(new Insets(30));
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

        VBox center = new VBox(profileCard);
        center.setAlignment(Pos.TOP_CENTER);
        center.setPadding(new Insets(40));

        center.setStyle(
                "-fx-background-color: #f1f8f2;"
        );

        // ===============================
        // MAIN LAYOUT
        // ===============================

        BorderPane root = new BorderPane();

        root.setTop(topBar);
        root.setCenter(center);

        // ===============================
        // SCENE
        // ===============================

        Scene scene = new Scene(root, 1200, 750);

        stage.setScene(scene);
        stage.setTitle("AgroNova - Profile & Settings");
        stage.show();
    }
}