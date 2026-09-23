package com.agronova;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

        topBar.setPadding(
                new Insets(18, 25, 18, 25)
        );

        topBar.setStyle(
                "-fx-background-color: linear-gradient(to right, #1b5e20, #43a047);"
        );

        // =========================
        // PAGE TITLE
        // =========================

        Label title =
                new Label("Crop Management 🌱");

        title.setFont(
                Font.font("Arial", 30)
        );

        title.setTextFill(
                Color.web("#1b5e20")
        );

        Label description =
                new Label(
                        "Add and manage crop information"
                );

        description.setFont(
                Font.font("Arial", 15)
        );

        description.setTextFill(
                Color.DARKGRAY
        );

        // =========================
        // INPUT FIELDS
        // =========================

        Label cropNameLabel =
                new Label("Crop Name");

        cropNameLabel.setFont(
                Font.font("Arial", 15)
        );

        TextField cropName =
                new TextField();

        cropName.setPromptText(
                "Enter crop name"
        );

        cropName.setPrefWidth(350);

        Label seasonLabel =
                new Label("Season");

        seasonLabel.setFont(
                Font.font("Arial", 15)
        );

        TextField season =
                new TextField();

        season.setPromptText(
                "Enter season"
        );

        season.setPrefWidth(350);

        Label areaLabel =
                new Label("Land Area");

        areaLabel.setFont(
                Font.font("Arial", 15)
        );

        TextField area =
                new TextField();

        area.setPromptText(
                "Enter land area"
        );

        area.setPrefWidth(350);

        // =========================
        // ADD CROP BUTTON
        // =========================

        Button addCrop =
                new Button("ADD CROP 🌱");

        addCrop.setStyle(
                "-fx-background-color: #2e7d32;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 12px 25px;" +
                "-fx-background-radius: 8px;"
        );

        // =========================
        // UPDATE BUTTON
        // =========================

        Button updateCrop =
                new Button("UPDATE CROP");

        updateCrop.setStyle(
                "-fx-background-color: #1976d2;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 10px 20px;" +
                "-fx-background-radius: 8px;"
        );

        // =========================
        // DELETE BUTTON
        // =========================

        Button deleteCrop =
                new Button("DELETE CROP");

        deleteCrop.setStyle(
                "-fx-background-color: #d32f2f;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 10px 20px;" +
                "-fx-background-radius: 8px;"
        );

        // =========================
        // UPDATE + DELETE BUTTONS
        // =========================

        HBox actionButtons =
                new HBox(
                        15,
                        updateCrop,
                        deleteCrop
                );

        actionButtons.setAlignment(
                Pos.CENTER_LEFT
        );

        // =========================
        // CROP TABLE
        // =========================

        TableView<Map<String, Object>> cropTable =
                new TableView<>();

        TableColumn<Map<String, Object>, String>
                cropNameColumn =
                new TableColumn<>("Crop Name");

        TableColumn<Map<String, Object>, String>
                seasonColumn =
                new TableColumn<>("Season");

        TableColumn<Map<String, Object>, String>
                areaColumn =
                new TableColumn<>("Land Area");

        // =========================
        // CROP NAME COLUMN
        // =========================

        cropNameColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(
                                data.getValue()
                                        .get("cropName")
                        )
                )
        );

        // =========================
        // SEASON COLUMN
        // =========================

        seasonColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(
                                data.getValue()
                                        .get("season")
                        )
                )
        );

        // =========================
        // AREA COLUMN
        // =========================

        areaColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(
                                data.getValue()
                                        .get("area")
                        )
                )
        );

        // =========================
        // TABLE WIDTH
        // =========================

        cropNameColumn.setPrefWidth(220);

        seasonColumn.setPrefWidth(220);

        areaColumn.setPrefWidth(220);

        cropTable.getColumns().addAll(
                cropNameColumn,
                seasonColumn,
                areaColumn
        );

        cropTable.setMaxWidth(700);

        cropTable.setPrefHeight(300);

        // =========================
        // SELECTED CROP TRACKING
        // =========================

        final String[] selectedCropName =
                { null };

        // =========================
        // SELECT CROP FROM TABLE
        // =========================

        cropTable.setOnMouseClicked(event -> {

            if (event.getClickCount() == 1) {

                Map<String, Object> selectedCrop =
                        cropTable
                                .getSelectionModel()
                                .getSelectedItem();

                if (selectedCrop != null) {

                    selectedCropName[0] =
                            String.valueOf(
                                    selectedCrop.get(
                                            "cropName"
                                    )
                            );

                    cropName.setText(
                            String.valueOf(
                                    selectedCrop.get(
                                            "cropName"
                                    )
                            )
                    );

                    season.setText(
                            String.valueOf(
                                    selectedCrop.get(
                                            "season"
                                    )
                            )
                    );

                    area.setText(
                            String.valueOf(
                                    selectedCrop.get(
                                            "area"
                                    )
                            )
                    );
                }
            }
        });

        // =========================
        // ADD CROP
        // =========================

        addCrop.setOnAction(event -> {

            if (cropName.getText().isEmpty()
                    || season.getText().isEmpty()
                    || area.getText().isEmpty()) {

                System.out.println(
                        "Please fill all crop details."
                );

                return;
            }

            try {

                Firestore db =
                        FirestoreClient.getFirestore();

                Map<String, Object> cropData =
                        new HashMap<>();

                cropData.put(
                        "cropName",
                        cropName.getText()
                );

                cropData.put(
                        "season",
                        season.getText()
                );

                cropData.put(
                        "area",
                        area.getText()
                );

                db.collection("crops")
                        .add(cropData);

                System.out.println(
                        "Crop added successfully!"
                );

                loadCrops(cropTable);

                cropName.clear();

                season.clear();

                area.clear();

                selectedCropName[0] = null;

            } catch (Exception ex) {

                System.out.println(
                        "Failed to add crop!"
                );

                ex.printStackTrace();
            }
        });

        // =========================
        // UPDATE CROP
        // =========================

        updateCrop.setOnAction(event -> {

            if (selectedCropName[0] == null) {

                System.out.println(
                        "Please select a crop first."
                );

                return;
            }

            if (cropName.getText().isEmpty()
                    || season.getText().isEmpty()
                    || area.getText().isEmpty()) {

                System.out.println(
                        "Please fill all crop details."
                );

                return;
            }

            try {

                Firestore db =
                        FirestoreClient.getFirestore();

                var documents =
                        db.collection("crops")
                                .whereEqualTo(
                                        "cropName",
                                        selectedCropName[0]
                                )
                                .get()
                                .get()
                                .getDocuments();

                if (documents.isEmpty()) {

                    System.out.println(
                            "Crop not found!"
                    );

                    return;
                }

                var document =
                        documents.get(0);

                Map<String, Object> updatedCrop =
                        new HashMap<>();

                updatedCrop.put(
                        "cropName",
                        cropName.getText()
                );

                updatedCrop.put(
                        "season",
                        season.getText()
                );

                updatedCrop.put(
                        "area",
                        area.getText()
                );

                document.getReference()
                        .set(updatedCrop);

                System.out.println(
                        "Crop updated successfully!"
                );

                loadCrops(cropTable);

                cropName.clear();

                season.clear();

                area.clear();

                selectedCropName[0] = null;

            } catch (Exception ex) {

                System.out.println(
                        "Failed to update crop!"
                );

                ex.printStackTrace();
            }
        });

        // =========================
        // DELETE CROP
        // =========================

        deleteCrop.setOnAction(event -> {

            if (selectedCropName[0] == null) {

                System.out.println(
                        "Please select a crop first."
                );

                return;
            }

            Alert alert =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            alert.setTitle(
                    "Delete Crop"
            );

            alert.setHeaderText(
                    "Delete Crop"
            );

            alert.setContentText(
                    "Are you sure you want to delete this crop?"
            );

            Optional<ButtonType> result =
                    alert.showAndWait();

            if (result.isEmpty()
                    || result.get()
                    != ButtonType.OK) {

                return;
            }

            try {

                Firestore db =
                        FirestoreClient.getFirestore();

                var documents =
                        db.collection("crops")
                                .whereEqualTo(
                                        "cropName",
                                        selectedCropName[0]
                                )
                                .get()
                                .get()
                                .getDocuments();

                if (documents.isEmpty()) {

                    System.out.println(
                            "Crop not found!"
                    );

                    return;
                }

                documents.get(0)
                        .getReference()
                        .delete();

                System.out.println(
                        "Crop deleted successfully!"
                );

                loadCrops(cropTable);

                cropName.clear();

                season.clear();

                area.clear();

                selectedCropName[0] = null;

            } catch (Exception ex) {

                System.out.println(
                        "Failed to delete crop!"
                );

                ex.printStackTrace();
            }
        });

        // =========================
        // LOAD EXISTING CROPS
        // =========================

        loadCrops(cropTable);

        // =========================
        // BACK BUTTON
        // =========================

        Button backButton =
                new Button(
                        "← Back to Dashboard"
                );

        backButton.setStyle(
                "-fx-background-color: #eeeeee;" +
                "-fx-text-fill: #333333;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 10px 20px;" +
                "-fx-background-radius: 8px;"
        );

        backButton.setOnAction(event -> {

            Dashboard dashboard =
                    new Dashboard();

            dashboard.show(stage);
        });

        // =========================
        // FORM
        // =========================

        VBox form =
                new VBox(
                        10,
                        cropNameLabel,
                        cropName,
                        seasonLabel,
                        season,
                        areaLabel,
                        area,
                        addCrop,
                        actionButtons
                );

        form.setAlignment(
                Pos.CENTER_LEFT
        );

        form.setPadding(
                new Insets(25)
        );

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

        VBox content =
                new VBox(
                        20,
                        title,
                        description,
                        form,
                        cropTable,
                        backButton
                );

        content.setAlignment(
                Pos.TOP_CENTER
        );

        content.setPadding(
                new Insets(40)
        );

        // =========================
        // ROOT
        // =========================

        BorderPane root =
                new BorderPane();

        root.setTop(topBar);

        root.setCenter(content);

        root.setStyle(
                "-fx-background-color: #f4f8f4;"
        );

        // =========================
        // SCENE
        // =========================

        Scene scene =
                new Scene(
                        root,
                        1200,
                        750
                );

        stage.setScene(scene);

        stage.setTitle(
                "AgroNova - Crop Management"
        );

        stage.setMaximized(true);

        stage.show();
    }

    // =========================
    // LOAD CROPS FROM FIRESTORE
    // =========================

    private void loadCrops(
            TableView<Map<String, Object>> cropTable) {

        try {

            Firestore db =
                    FirestoreClient.getFirestore();

            var documents =
                    db.collection("crops")
                            .get()
                            .get()
                            .getDocuments();

            cropTable.getItems().clear();

            for (var document : documents) {

                cropTable.getItems().add(
                        document.getData()
                );
            }

            System.out.println(
                    "Crops loaded successfully!"
            );

        } catch (Exception ex) {

            System.out.println(
                    "Failed to load crops!"
            );

            ex.printStackTrace();
        }
    }
}