package com.agronova;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

public class FertilizerManagement {

    public void show(Stage stage) {

        // ================= TOP BAR =================

        Label logo = new Label("🌿 AgroNova");

        logo.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        26
                )
        );

        logo.setTextFill(Color.WHITE);

        Label subtitle =
                new Label("Fertilizer Management");

        subtitle.setFont(
                Font.font("Arial", 16)
        );

        subtitle.setTextFill(Color.WHITE);

        HBox topBar =
                new HBox(20, logo, subtitle);

        topBar.setAlignment(
                Pos.CENTER_LEFT
        );

        topBar.setPadding(
                new Insets(18, 25, 18, 25)
        );

        topBar.setStyle(
                "-fx-background-color: linear-gradient(to right, #1b5e20, #43a047);"
        );

        // ================= TITLE =================

        Label title =
                new Label("Fertilizer Management 🧪");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        title.setTextFill(
                Color.web("#1b5e20")
        );

        Label description =
                new Label(
                        "Add and manage fertilizer information"
                );

        description.setFont(
                Font.font("Arial", 15)
        );

        description.setTextFill(
                Color.DARKGRAY
        );

        // ================= TABLE =================

        TableView<Map<String, Object>> fertilizerTable =
                new TableView<>();

        // ================= FIELDS =================

        Label nameLabel =
                new Label("Fertilizer Name");

        TextField nameField =
                new TextField();

        nameField.setPromptText(
                "Enter fertilizer name"
        );

        nameField.setPrefWidth(300);

        Label typeLabel =
                new Label("Fertilizer Type");

        TextField typeField =
                new TextField();

        typeField.setPromptText(
                "Example: Organic / Chemical"
        );

        typeField.setPrefWidth(300);

        Label quantityLabel =
                new Label("Quantity");

        TextField quantityField =
                new TextField();

        quantityField.setPromptText(
                "Example: 50 kg"
        );

        quantityField.setPrefWidth(300);

        // ================= BUTTONS =================

        Button addButton =
                new Button("ADD FERTILIZER");

        addButton.setPrefWidth(150);
        addButton.setPrefHeight(40);

        addButton.setStyle(
                "-fx-background-color: #2e7d32;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        Button updateButton =
                new Button("UPDATE");

        updateButton.setPrefWidth(120);
        updateButton.setPrefHeight(40);

        updateButton.setStyle(
                "-fx-background-color: #1565c0;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        Button deleteButton =
                new Button("DELETE");

        deleteButton.setPrefWidth(120);
        deleteButton.setPrefHeight(40);

        deleteButton.setStyle(
                "-fx-background-color: #c62828;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        // ================= ADD =================

        addButton.setOnAction(event -> {

            String name =
                    nameField.getText().trim();

            String type =
                    typeField.getText().trim();

            String quantity =
                    quantityField.getText().trim();

            if (name.isEmpty()
                    || type.isEmpty()
                    || quantity.isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Missing Information",
                        "Please fill all fertilizer details."
                );

                return;
            }

            try {

                Firestore db =
                        FirestoreClient.getFirestore();

                Map<String, Object> fertilizerData =
                        new HashMap<>();

                fertilizerData.put(
                        "name",
                        name
                );

                fertilizerData.put(
                        "type",
                        type
                );

                fertilizerData.put(
                        "quantity",
                        quantity
                );

                db.collection("fertilizers")
                        .add(fertilizerData);

                loadFertilizers(
                        fertilizerTable
                );

                clearFields(
                        nameField,
                        typeField,
                        quantityField
                );

                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Success",
                        "Fertilizer added successfully!"
                );

            } catch (Exception ex) {

                ex.printStackTrace();

                showAlert(
                        Alert.AlertType.ERROR,
                        "Error",
                        "Failed to add fertilizer."
                );
            }
        });

        // ================= UPDATE =================

        updateButton.setOnAction(event -> {

            Map<String, Object> selectedFertilizer =
                    fertilizerTable
                            .getSelectionModel()
                            .getSelectedItem();

            if (selectedFertilizer == null) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Select Fertilizer",
                        "Please select a fertilizer from the table."
                );

                return;
            }

            String name =
                    nameField.getText().trim();

            String type =
                    typeField.getText().trim();

            String quantity =
                    quantityField.getText().trim();

            if (name.isEmpty()
                    || type.isEmpty()
                    || quantity.isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Missing Information",
                        "Please fill all fields."
                );

                return;
            }

            try {

                Firestore db =
                        FirestoreClient.getFirestore();

                String originalName =
                        String.valueOf(
                                selectedFertilizer.get("name")
                        );

                var documents =
                        db.collection("fertilizers")
                                .whereEqualTo(
                                        "name",
                                        originalName
                                )
                                .get()
                                .get()
                                .getDocuments();

                if (!documents.isEmpty()) {

                    String documentId =
                            documents.get(0).getId();

                    Map<String, Object> updatedData =
                            new HashMap<>();

                    updatedData.put(
                            "name",
                            name
                    );

                    updatedData.put(
                            "type",
                            type
                    );

                    updatedData.put(
                            "quantity",
                            quantity
                    );

                    db.collection("fertilizers")
                            .document(documentId)
                            .set(updatedData);

                    loadFertilizers(
                            fertilizerTable
                    );

                    clearFields(
                            nameField,
                            typeField,
                            quantityField
                    );

                    showAlert(
                            Alert.AlertType.INFORMATION,
                            "Updated",
                            "Fertilizer updated successfully!"
                    );

                } else {

                    showAlert(
                            Alert.AlertType.WARNING,
                            "Not Found",
                            "Fertilizer record not found."
                    );
                }

            } catch (Exception ex) {

                ex.printStackTrace();

                showAlert(
                        Alert.AlertType.ERROR,
                        "Error",
                        "Failed to update fertilizer."
                );
            }
        });

        // ================= DELETE =================

        deleteButton.setOnAction(event -> {

            Map<String, Object> selectedFertilizer =
                    fertilizerTable
                            .getSelectionModel()
                            .getSelectedItem();

            if (selectedFertilizer == null) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Select Fertilizer",
                        "Please select a fertilizer to delete."
                );

                return;
            }

            Alert alert =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            alert.setTitle(
                    "Delete Fertilizer"
            );

            alert.setHeaderText(
                    "Delete Fertilizer Record"
            );

            alert.setContentText(
                    "Are you sure you want to delete this fertilizer?"
            );

            Optional<ButtonType> result =
                    alert.showAndWait();

            if (result.isEmpty()
                    || result.get() != ButtonType.OK) {

                return;
            }

            try {

                Firestore db =
                        FirestoreClient.getFirestore();

                String fertilizerName =
                        String.valueOf(
                                selectedFertilizer.get("name")
                        );

                var documents =
                        db.collection("fertilizers")
                                .whereEqualTo(
                                        "name",
                                        fertilizerName
                                )
                                .get()
                                .get()
                                .getDocuments();

                if (!documents.isEmpty()) {

                    String documentId =
                            documents.get(0).getId();

                    db.collection("fertilizers")
                            .document(documentId)
                            .delete();

                    loadFertilizers(
                            fertilizerTable
                    );

                    clearFields(
                            nameField,
                            typeField,
                            quantityField
                    );

                    showAlert(
                            Alert.AlertType.INFORMATION,
                            "Deleted",
                            "Fertilizer deleted successfully!"
                    );

                } else {

                    showAlert(
                            Alert.AlertType.WARNING,
                            "Not Found",
                            "Fertilizer record not found."
                    );
                }

            } catch (Exception ex) {

                ex.printStackTrace();

                showAlert(
                        Alert.AlertType.ERROR,
                        "Error",
                        "Failed to delete fertilizer."
                );
            }
        });

        // ================= FORM =================

        GridPane form =
                new GridPane();

        form.setHgap(15);
        form.setVgap(15);

        form.add(
                nameLabel,
                0,
                0
        );

        form.add(
                nameField,
                1,
                0
        );

        form.add(
                typeLabel,
                2,
                0
        );

        form.add(
                typeField,
                3,
                0
        );

        form.add(
                quantityLabel,
                0,
                1
        );

        form.add(
                quantityField,
                1,
                1
        );

        HBox buttonBox =
                new HBox(15);

        buttonBox.setAlignment(
                Pos.CENTER_LEFT
        );

        buttonBox.getChildren().addAll(
                addButton,
                updateButton,
                deleteButton
        );

        form.add(
                buttonBox,
                1,
                2,
                3,
                1
        );

        form.setPadding(
                new Insets(20)
        );

        form.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: #c8e6c9;" +
                "-fx-border-radius: 15;" +
                "-fx-border-width: 1;"
        );

        // ================= TABLE COLUMNS =================

        TableColumn<Map<String, Object>, String>
                nameColumn =
                new TableColumn<>("Fertilizer Name");

        TableColumn<Map<String, Object>, String>
                typeColumn =
                new TableColumn<>("Fertilizer Type");

        TableColumn<Map<String, Object>, String>
                quantityColumn =
                new TableColumn<>("Quantity");

        // ================= CELL VALUES =================

        nameColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.valueOf(
                                        data.getValue()
                                                .get("name")
                                )
                        )
        );

        typeColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.valueOf(
                                        data.getValue()
                                                .get("type")
                                )
                        )
        );

        quantityColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.valueOf(
                                        data.getValue()
                                                .get("quantity")
                                )
                        )
        );

        fertilizerTable.getColumns().addAll(
                nameColumn,
                typeColumn,
                quantityColumn
        );
nameColumn.setPrefWidth(250);
typeColumn.setPrefWidth(250);
quantityColumn.setPrefWidth(150);

fertilizerTable.setPrefHeight(220);
fertilizerTable.setMinHeight(220);
fertilizerTable.setMaxHeight(220);
        // ================= SELECT FERTILIZER =================

        fertilizerTable.setOnMouseClicked(event -> {

            if (event.getClickCount() == 1) {

                Map<String, Object> selectedFertilizer =
                        fertilizerTable
                                .getSelectionModel()
                                .getSelectedItem();

                if (selectedFertilizer != null) {

                    nameField.setText(
                            String.valueOf(
                                    selectedFertilizer.get("name")
                            )
                    );

                    typeField.setText(
                            String.valueOf(
                                    selectedFertilizer.get("type")
                            )
                    );

                    quantityField.setText(
                            String.valueOf(
                                    selectedFertilizer.get("quantity")
                            )
                    );
                }
            }
        });

        // ================= BACK BUTTON =================

        Button backButton =
                new Button("← Back to Dashboard");

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

        // ================= CONTENT =================

       VBox content =
        new VBox(
                15,
                title,
                description,
                form,
                fertilizerTable,
                backButton
        );

content.setAlignment(Pos.TOP_CENTER);

content.setPadding(
        new Insets(30)
);
content.setFillWidth(false);

content.setMaxHeight(
        650
);
        content.setAlignment(
                Pos.TOP_CENTER
        );

        content.setPadding(
                new Insets(30)
        );

        // ================= ROOT =================

        BorderPane root =
                new BorderPane();

        root.setTop(topBar);
        root.setCenter(content);

        root.setStyle(
                "-fx-background-color: #f4f8f4;"
        );

        // ================= LOAD DATA =================

        loadFertilizers(
                fertilizerTable
        );

        // ================= SCENE =================

        Scene scene =
                new Scene(
                        root,
                        1200,
                        750
                );

        stage.setScene(scene);

        stage.setTitle(
                "AgroNova - Fertilizer Management"
        );

        stage.setMaximized(true);

        stage.show();
    }

    // =====================================================
    // LOAD FERTILIZERS FROM FIRESTORE
    // =====================================================

    private void loadFertilizers(
            TableView<Map<String, Object>> fertilizerTable) {

        try {

            Firestore db =
                    FirestoreClient.getFirestore();

            var documents =
                    db.collection("fertilizers")
                            .get()
                            .get()
                            .getDocuments();

            fertilizerTable.getItems().clear();

            for (var document : documents) {

                fertilizerTable.getItems().add(
                        document.getData()
                );
            }

            System.out.println(
                    "Fertilizers loaded successfully!"
            );

        } catch (Exception ex) {

            System.out.println(
                    "Failed to load fertilizers!"
            );

            ex.printStackTrace();
        }
    }

    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    private void clearFields(
            TextField nameField,
            TextField typeField,
            TextField quantityField) {

        nameField.clear();
        typeField.clear();
        quantityField.clear();
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