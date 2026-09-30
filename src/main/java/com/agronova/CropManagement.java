package com.agronova;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CropManagement {

    public void show(Stage stage) {

        // ================= ROOT =================

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: #f4f8f4;"
        );

        // ================= TOP BAR =================

        HBox topBar = new HBox();

        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(
                new Insets(15, 25, 15, 25)
        );
        topBar.setSpacing(20);

        topBar.setStyle(
                "-fx-background-color: linear-gradient(to right, #1b5e20, #43a047);"
        );

        Label logo = new Label("🌿 AgroNova");

        logo.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24
                )
        );

        logo.setTextFill(Color.WHITE);

        Label topTitle =
                new Label("Crop Management");

        topTitle.setFont(
                Font.font(
                        "Arial",
                        16
                )
        );

        topTitle.setTextFill(
                Color.web("#d8f3dc")
        );

        topBar.getChildren().addAll(
                logo,
                topTitle
        );

        // ================= PAGE TITLE =================

        Label title =
                new Label("Crop Management 🌱");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        28
                )
        );

        title.setTextFill(
                Color.web("#1b5e20")
        );

        Label description =
                new Label(
                        "Add and manage crop information"
                );

        description.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        description.setTextFill(
                Color.web("#607d64")
        );

        // ================= INPUT FIELDS =================

        Label cropNameLabel =
                new Label("Crop Name");

        TextField cropName =
                new TextField();

        cropName.setPromptText(
                "Enter crop name"
        );

        cropName.setPrefWidth(200);

        Label seasonLabel =
                new Label("Season");

        TextField season =
                new TextField();

        season.setPromptText(
                "Enter season"
        );

        season.setPrefWidth(200);

        Label areaLabel =
                new Label("Land Area");

        TextField area =
                new TextField();

        area.setPromptText(
                "Enter land area"
        );

        area.setPrefWidth(200);

        // ================= ADD BUTTON =================

        Button addCrop =
                new Button("ADD CROP");

        addCrop.setPrefWidth(125);
        addCrop.setPrefHeight(36);

        addCrop.setStyle(
                "-fx-background-color: #2e7d32;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        // ================= UPDATE BUTTON =================

        Button updateCrop =
                new Button("UPDATE");

        updateCrop.setPrefWidth(125);
        updateCrop.setPrefHeight(36);

        updateCrop.setStyle(
                "-fx-background-color: #1976d2;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        // ================= DELETE BUTTON =================

        Button deleteCrop =
                new Button("DELETE");

        deleteCrop.setPrefWidth(125);
        deleteCrop.setPrefHeight(36);

        deleteCrop.setStyle(
                "-fx-background-color: #d32f2f;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        // ================= FORM =================

        GridPane form =
                new GridPane();

        form.setHgap(10);
        form.setVgap(8);

        form.setPadding(
                new Insets(12)
        );

        form.setAlignment(
                Pos.CENTER
        );

        form.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: #c8e6c9;" +
                "-fx-border-radius: 12px;" +
                "-fx-border-width: 1px;"
        );

        // Row 1

        form.add(
                cropNameLabel,
                0,
                0
        );

        form.add(
                cropName,
                1,
                0
        );

        form.add(
                seasonLabel,
                2,
                0
        );

        form.add(
                season,
                3,
                0
        );

        // Row 2

        form.add(
                areaLabel,
                0,
                1
        );

        form.add(
                area,
                1,
                1
        );

        // Buttons

        HBox actionButtons =
                new HBox(8);

        actionButtons.setAlignment(
                Pos.CENTER
        );

        actionButtons.getChildren().addAll(
                addCrop,
                updateCrop,
                deleteCrop
        );

        form.add(
                actionButtons,
                2,
                1,
                2,
                1
        );

        // ================= CROP TABLE =================

        TableView<Map<String, Object>> cropTable =
                new TableView<>();

        cropTable.setPrefHeight(220);
        cropTable.setMinHeight(220);
        cropTable.setMaxHeight(220);

        cropTable.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #c8e6c9;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );

        // ================= TABLE COLUMNS =================

        TableColumn<Map<String, Object>, String>
                cropNameColumn =
                new TableColumn<>("Crop Name");

        TableColumn<Map<String, Object>, String>
                seasonColumn =
                new TableColumn<>("Season");

        TableColumn<Map<String, Object>, String>
                areaColumn =
                new TableColumn<>("Land Area");

        // ================= CELL VALUES =================

        cropNameColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.valueOf(
                                        data.getValue()
                                                .get("cropName")
                                )
                        )
        );

        seasonColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.valueOf(
                                        data.getValue()
                                                .get("season")
                                )
                        )
        );

        areaColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.valueOf(
                                        data.getValue()
                                                .get("area")
                                )
                        )
        );

        // ================= TABLE WIDTH =================

        cropNameColumn.setPrefWidth(250);
        seasonColumn.setPrefWidth(250);
        areaColumn.setPrefWidth(180);

        cropTable.getColumns().addAll(
                cropNameColumn,
                seasonColumn,
                areaColumn
        );

        cropTable.setMaxWidth(700);

        // ================= SELECTED CROP =================

        final String[] selectedCropName =
                {null};

        // ================= SELECT CROP =================

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

        // ================= ADD CROP =================

        addCrop.setOnAction(event -> {

            if (cropName.getText().isEmpty()
                    || season.getText().isEmpty()
                    || area.getText().isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Missing Information",
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

                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Success",
                        "Crop added successfully!"
                );

            } catch (Exception ex) {

                System.out.println(
                        "Failed to add crop!"
                );

                ex.printStackTrace();

                showAlert(
                        Alert.AlertType.ERROR,
                        "Error",
                        "Failed to add crop."
                );
            }
        });

        // ================= UPDATE CROP =================

        updateCrop.setOnAction(event -> {

            if (selectedCropName[0] == null) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Select Crop",
                        "Please select a crop from the table first."
                );

                return;
            }

            if (cropName.getText().isEmpty()
                    || season.getText().isEmpty()
                    || area.getText().isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Missing Information",
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

                    showAlert(
                            Alert.AlertType.WARNING,
                            "Not Found",
                            "Crop not found."
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

                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Updated",
                        "Crop updated successfully!"
                );

            } catch (Exception ex) {

                System.out.println(
                        "Failed to update crop!"
                );

                ex.printStackTrace();

                showAlert(
                        Alert.AlertType.ERROR,
                        "Error",
                        "Failed to update crop."
                );
            }
        });

        // ================= DELETE CROP =================

        deleteCrop.setOnAction(event -> {

            if (selectedCropName[0] == null) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Select Crop",
                        "Please select a crop from the table first."
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

                    showAlert(
                            Alert.AlertType.WARNING,
                            "Not Found",
                            "Crop not found."
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

                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Deleted",
                        "Crop deleted successfully!"
                );

            } catch (Exception ex) {

                System.out.println(
                        "Failed to delete crop!"
                );

                ex.printStackTrace();

                showAlert(
                        Alert.AlertType.ERROR,
                        "Error",
                        "Failed to delete crop."
                );
            }
        });

        // ================= LOAD CROPS =================

        loadCrops(cropTable);

        // ================= BACK BUTTON =================

        Button backButton =
                new Button(
                        "← Back to Dashboard"
                );

        backButton.setPrefHeight(36);

        backButton.setStyle(
                "-fx-background-color: #dceddc;" +
                "-fx-text-fill: #1b5e20;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        backButton.setOnAction(event -> {

            Dashboard dashboard =
                    new Dashboard();

            dashboard.show(stage);
        });

        // ================= CONTENT =================

        VBox content =
                new VBox(
                        8,
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
                new Insets(15)
        );

        content.setFillWidth(false);

        content.setMaxHeight(650);

        // ================= ROOT =================

        root.setTop(topBar);
        root.setCenter(content);

        // ================= SCENE =================

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

    // =====================================================
    // LOAD CROPS FROM FIRESTORE
    // =====================================================

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

    // =====================================================
    // ALERT
    // =====================================================

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message) {

        Alert alert =
                new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}

