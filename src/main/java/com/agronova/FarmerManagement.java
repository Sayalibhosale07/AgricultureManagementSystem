
package com.agronova;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FarmerManagement {

    public void show(Stage stage) {

        BorderPane root = new BorderPane();

        root.setStyle(
            "-fx-background-color: #f4f8f4;"
        );

        // ================= TOP BAR =================

        HBox topBar = new HBox();

        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(
            new Insets(18, 25, 18, 25)
        );

        topBar.setSpacing(20);

        topBar.setStyle(
            "-fx-background-color: #14532d;"
        );

        Label logo =
            new Label("🌿 AgroNova");

        logo.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                25
            )
        );

        logo.setTextFill(Color.WHITE);

        Label title =
            new Label("Farmer Management");

        title.setFont(
            Font.font("Arial", 18)
        );

        title.setTextFill(
            Color.web("#d8f3dc")
        );

        topBar.getChildren().addAll(
            logo,
            title
        );

        // ================= MAIN CONTENT =================

        VBox content = new VBox(20);

        content.setPadding(
            new Insets(30)
        );

        Label heading =
            new Label("Farmer Management 👨‍🌾");

        heading.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                30
            )
        );

        heading.setTextFill(
            Color.web("#1b5e20")
        );

        Label description =
            new Label(
                "Add and manage farmer information"
            );

        description.setFont(
            Font.font("Arial", 16)
        );

        description.setTextFill(
            Color.web("#607d64")
        );

        // ================= FORM =================

        GridPane form = new GridPane();

        form.setHgap(15);
        form.setVgap(15);

        Label nameLabel =
            new Label("Farmer Name");

        TextField nameField =
            new TextField();

        nameField.setPromptText(
            "Enter farmer name"
        );

        nameField.setPrefWidth(300);

        Label phoneLabel =
            new Label("Phone Number");

        TextField phoneField =
            new TextField();

        phoneField.setPromptText(
            "Enter phone number"
        );

        Label villageLabel =
            new Label("Village");

        TextField villageField =
            new TextField();

        villageField.setPromptText(
            "Enter village"
        );

        Button addButton =
            new Button("ADD FARMER");

        addButton.setPrefWidth(150);
        addButton.setPrefHeight(40);

        addButton.setStyle(
            "-fx-background-color: #2e7d32;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );

        form.add(nameLabel, 0, 0);
        form.add(nameField, 1, 0);

        form.add(phoneLabel, 0, 1);
        form.add(phoneField, 1, 1);

        form.add(villageLabel, 0, 2);
        form.add(villageField, 1, 2);

        form.add(addButton, 1, 3);

        // ================= BACK BUTTON =================

        Button backButton =
            new Button("← Back to Dashboard");

        backButton.setPrefHeight(40);

        backButton.setStyle(
            "-fx-background-color: #dceddc;" +
            "-fx-text-fill: #1b5e20;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );

        backButton.setOnAction(
            event -> {
                Dashboard dashboard =
                    new Dashboard();

                dashboard.show(stage);
            }
        );

        content.getChildren().addAll(
            heading,
            description,
            form,
            backButton
        );

        root.setTop(topBar);
        root.setCenter(content);

        Scene scene =
            new Scene(root, 1200, 750);

        stage.setScene(scene);

        stage.setTitle(
            "AgroNova - Farmer Management"
        );

        stage.show();
    }
}
