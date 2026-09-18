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

public class CropManagement {

    public void show(Stage stage) {

        // =========================
        // TOP BAR
        // =========================

        Label logo = new Label("🌿 AgroNova");
        logo.setFont(Font.font("Arial", 26));
        logo.setTextFill(Color.WHITE);

        Label subtitle = new Label("Crop Management");
        subtitle.setFont(Font.font("Arial", 16));
        subtitle.setTextFill(Color.WHITE);

        HBox topBar = new HBox(20, logo, subtitle);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(18, 25, 18, 25));
        topBar.setStyle(
                "-fx-background-color: linear-gradient(to right, #1b5e20, #43a047);"
        );

        // =========================
        // PAGE TITLE
        // =========================

        Label title = new Label("Crop Management 🌱");
        title.setFont(Font.font("Arial", 30));
        title.setTextFill(Color.web("#1b5e20"));

        Label description = new Label(
                "Add and manage crop information"
        );
        description.setFont(Font.font("Arial", 15));
        description.setTextFill(Color.DARKGRAY);

        // =========================
        // INPUT FIELDS
        // =========================

        Label cropNameLabel = new Label("Crop Name");
        cropNameLabel.setFont(Font.font("Arial", 15));

        TextField cropName = new TextField();
        cropName.setPromptText("Enter crop name");
        cropName.setPrefWidth(350);

        Label seasonLabel = new Label("Season");
        seasonLabel.setFont(Font.font("Arial", 15));

        TextField season = new TextField();
        season.setPromptText("Enter season");
        season.setPrefWidth(350);

        Label areaLabel = new Label("Land Area");
        areaLabel.setFont(Font.font("Arial", 15));

        TextField area = new TextField();
        area.setPromptText("Enter land area");
        area.setPrefWidth(350);

        // =========================
        // ADD CROP BUTTON
        // =========================

        Button addCrop = new Button("ADD CROP 🌱");

        addCrop.setStyle(
                "-fx-background-color: #2e7d32;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 12px 25px;" +
                "-fx-background-radius: 8px;"
        );

        addCrop.setOnAction(event -> {

            if (cropName.getText().isEmpty()
                    || season.getText().isEmpty()
                    || area.getText().isEmpty()) {

                System.out.println("Please fill all crop details.");

            } else {

                System.out.println(
                        "Crop Added: "
                                + cropName.getText()
                                + " | Season: "
                                + season.getText()
                                + " | Area: "
                                + area.getText()
                );

                cropName.clear();
                season.clear();
                area.clear();
            }
        });

        // =========================
        // BACK BUTTON
        // =========================

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

        // =========================
        // FORM
        // =========================

        VBox form = new VBox(
                10,
                cropNameLabel,
                cropName,
                seasonLabel,
                season,
                areaLabel,
                area,
                addCrop
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

        // =========================
        // MAIN CONTENT
        // =========================

        VBox content = new VBox(
                10,
                title,
                description,
                form,
                backButton
        );

        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(40));

        // =========================
        // ROOT
        // =========================

        BorderPane root = new BorderPane();

        root.setTop(topBar);
        root.setCenter(content);

        root.setStyle(
                "-fx-background-color: #f4f8f4;"
        );

        // =========================
        // SCENE
        // =========================

        Scene scene = new Scene(root, 1200, 750);

        stage.setScene(scene);
        stage.setTitle("AgroNova - Crop Management");
        stage.show();
    }
}