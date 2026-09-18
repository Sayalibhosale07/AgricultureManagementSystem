package com.agronova;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FertilizerManagement {

    public void show(Stage stage) {

        // TOP BAR
        Label logo = new Label("🌿 AgroNova");
        logo.setFont(Font.font("Arial", 26));
        logo.setTextFill(Color.WHITE);

        Label subtitle = new Label("Fertilizer Management");
        subtitle.setFont(Font.font("Arial", 16));
        subtitle.setTextFill(Color.WHITE);

        HBox topBar = new HBox(20, logo, subtitle);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(18, 25, 18, 25));

        topBar.setStyle(
                "-fx-background-color: linear-gradient(to right, #1b5e20, #43a047);"
        );

        // TITLE
        Label title = new Label("Fertilizer Management 🧪");
        title.setFont(Font.font("Arial", 30));
        title.setTextFill(Color.web("#1b5e20"));

        Label description = new Label(
                "Add and manage fertilizer information"
        );
        description.setFont(Font.font("Arial", 15));
        description.setTextFill(Color.DARKGRAY);

        // FERTILIZER NAME
        Label nameLabel = new Label("Fertilizer Name");
        nameLabel.setFont(Font.font("Arial", 15));

        TextField nameField = new TextField();
        nameField.setPromptText("Enter fertilizer name");
        nameField.setPrefWidth(350);

        // TYPE
        Label typeLabel = new Label("Fertilizer Type");
        typeLabel.setFont(Font.font("Arial", 15));

        TextField typeField = new TextField();
        typeField.setPromptText("Example: Organic / Chemical");
        typeField.setPrefWidth(350);

        // QUANTITY
        Label quantityLabel = new Label("Quantity");
        quantityLabel.setFont(Font.font("Arial", 15));

        TextField quantityField = new TextField();
        quantityField.setPromptText("Enter quantity");
        quantityField.setPrefWidth(350);

        // ADD BUTTON
        Button addButton = new Button("ADD FERTILIZER 🧪");

        addButton.setStyle(
                "-fx-background-color: #2e7d32;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 12px 25px;" +
                "-fx-background-radius: 8px;"
        );

        addButton.setOnAction(event -> {

            if (nameField.getText().isEmpty()
                    || typeField.getText().isEmpty()
                    || quantityField.getText().isEmpty()) {

                System.out.println("Please fill all fertilizer details.");

            } else {

                System.out.println(
                        "Fertilizer Added: "
                        + nameField.getText()
                        + " | Type: "
                        + typeField.getText()
                        + " | Quantity: "
                        + quantityField.getText()
                );

                nameField.clear();
                typeField.clear();
                quantityField.clear();
            }
        });

        // FORM
        VBox form = new VBox(
                10,
                nameLabel,
                nameField,
                typeLabel,
                typeField,
                quantityLabel,
                quantityField,
                addButton
        );

        form.setAlignment(Pos.CENTER_LEFT);
        form.setPadding(new Insets(25));

        form.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 15px;" +
                "-fx-border-color: #c8e6c9;" +
                "-fx-border-radius: 15px;" +
                "-fx-border-width: 1px;"
        );

        // BACK BUTTON
        Button backButton = new Button("← Back to Dashboard");

        backButton.setStyle(
                "-fx-background-color: #eeeeee;" +
                "-fx-text-fill: #333333;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 10px 20px;" +
                "-fx-background-radius: 8px;"
        );

        backButton.setOnAction(event -> {

            Dashboard dashboard = new Dashboard();
            dashboard.show(stage);

        });

        // CONTENT
        VBox content = new VBox(
                10,
                title,
                description,
                form,
                backButton
        );

        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(40));

        // ROOT
        BorderPane root = new BorderPane();

        root.setTop(topBar);
        root.setCenter(content);

        root.setStyle(
                "-fx-background-color: #f4f8f4;"
        );

        // SCENE
        Scene scene = new Scene(root, 1200, 750);

        stage.setScene(scene);
        stage.setTitle("AgroNova - Fertilizer Management");
        stage.show();
    }
}