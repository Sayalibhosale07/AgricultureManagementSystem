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

public class FarmerManagement {

    public void show(Stage stage) {

        // ================= ROOT =================

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: #f4f8f4;"
        );

        // ================= TABLE =================

        TableView<Map<String, Object>> farmerTable =
                new TableView<>();

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
                Font.font(
                        "Arial",
                        18
                )
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
                        "Manage farmer and farm information"
                );

        description.setFont(
                Font.font(
                        "Arial",
                        16
                )
        );

        description.setTextFill(
                Color.web("#607d64")
        );

        // ================= FORM =================

        GridPane form = new GridPane();

        form.setHgap(15);
        form.setVgap(15);

        // ---------- Farmer Name ----------

        Label nameLabel =
                new Label("Farmer Name");

        TextField nameField =
                new TextField();

        nameField.setPromptText(
                "Enter farmer name"
        );

        nameField.setPrefWidth(250);

        // ---------- Phone ----------

        Label phoneLabel =
                new Label("Phone Number");

        TextField phoneField =
                new TextField();

        phoneField.setPromptText(
                "Enter phone number"
        );

        // ---------- Email ----------

        Label emailLabel =
                new Label("Email");

        TextField emailField =
                new TextField();

        emailField.setPromptText(
                "Enter email address"
        );

        // ---------- Village ----------

        Label villageLabel =
                new Label("Village");

        TextField villageField =
                new TextField();

        villageField.setPromptText(
                "Enter village"
        );

        // ---------- Farm Location ----------

        Label locationLabel =
                new Label("Farm Location");

        TextField locationField =
                new TextField();

        locationField.setPromptText(
                "Enter farm location"
        );

        // ---------- Farm Area ----------

        Label areaLabel =
                new Label("Farm Area");

        TextField areaField =
                new TextField();

        areaField.setPromptText(
                "Example: 5 acres"
        );

        // ---------- Land Type ----------

        Label landTypeLabel =
                new Label("Land Type");

        TextField landTypeField =
                new TextField();

        landTypeField.setPromptText(
                "Example: Agricultural"
        );

        // ================= ADD BUTTON =================

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

        // ================= UPDATE BUTTON =================

        Button updateButton =
                new Button("UPDATE FARMER");

        updateButton.setPrefWidth(150);
        updateButton.setPrefHeight(40);

        updateButton.setStyle(
                "-fx-background-color: #1565c0;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        // ================= DELETE BUTTON =================

        Button deleteButton =
                new Button("DELETE FARMER");

        deleteButton.setPrefWidth(150);
        deleteButton.setPrefHeight(40);

        deleteButton.setStyle(
                "-fx-background-color: #c62828;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        // ================= ADD FARMER =================

        addButton.setOnAction(event -> {

            String name =
                    nameField.getText().trim();

            String phone =
                    phoneField.getText().trim();

            String email =
                    emailField.getText().trim();

            String village =
                    villageField.getText().trim();

            String farmLocation =
                    locationField.getText().trim();

            String farmArea =
                    areaField.getText().trim();

            String landType =
                    landTypeField.getText().trim();

            // Check empty fields

            if (name.isEmpty()
                    || phone.isEmpty()
                    || email.isEmpty()
                    || village.isEmpty()
                    || farmLocation.isEmpty()
                    || farmArea.isEmpty()
                    || landType.isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Missing Information",
                        "Please fill all farmer and farm details."
                );

                return;
            }

            try {

                Firestore db =
                        FirestoreClient.getFirestore();

                Map<String, Object> farmerData =
                        new HashMap<>();

                farmerData.put(
                        "name",
                        name
                );

                farmerData.put(
                        "phone",
                        phone
                );

                farmerData.put(
                        "email",
                        email
                );

                farmerData.put(
                        "village",
                        village
                );

                farmerData.put(
                        "farmLocation",
                        farmLocation
                );

                farmerData.put(
                        "farmArea",
                        farmArea
                );

                farmerData.put(
                        "landType",
                        landType
                );

                // Save in Firestore

                db.collection("farmers")
                        .add(farmerData);

                System.out.println(
                        "Farmer added successfully!"
                );

                loadFarmers(farmerTable);

                clearFields(
                        nameField,
                        phoneField,
                        emailField,
                        villageField,
                        locationField,
                        areaField,
                        landTypeField
                );

                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Success",
                        "Farmer added successfully!"
                );

            } catch (Exception ex) {

                System.out.println(
                        "Failed to add farmer!"
                );

                ex.printStackTrace();

                showAlert(
                        Alert.AlertType.ERROR,
                        "Error",
                        "Failed to add farmer."
                );
            }
        });

        // ================= UPDATE FARMER =================

        updateButton.setOnAction(event -> {

            Map<String, Object> selectedFarmer =
                    farmerTable
                            .getSelectionModel()
                            .getSelectedItem();

            if (selectedFarmer == null) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Select Farmer",
                        "Please select a farmer from the table."
                );

                return;
            }

            String name =
                    nameField.getText().trim();

            String phone =
                    phoneField.getText().trim();

            String email =
                    emailField.getText().trim();

            String village =
                    villageField.getText().trim();

            String farmLocation =
                    locationField.getText().trim();

            String farmArea =
                    areaField.getText().trim();

            String landType =
                    landTypeField.getText().trim();

            if (name.isEmpty()
                    || phone.isEmpty()
                    || email.isEmpty()
                    || village.isEmpty()
                    || farmLocation.isEmpty()
                    || farmArea.isEmpty()
                    || landType.isEmpty()) {

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
                                selectedFarmer.get("name")
                        );

                var documents =
                        db.collection("farmers")
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
                            "phone",
                            phone
                    );

                    updatedData.put(
                            "email",
                            email
                    );

                    updatedData.put(
                            "village",
                            village
                    );

                    updatedData.put(
                            "farmLocation",
                            farmLocation
                    );

                    updatedData.put(
                            "farmArea",
                            farmArea
                    );

                    updatedData.put(
                            "landType",
                            landType
                    );

                    db.collection("farmers")
                            .document(documentId)
                            .set(updatedData);

                    System.out.println(
                            "Farmer updated successfully!"
                    );

                    loadFarmers(farmerTable);

                    clearFields(
                            nameField,
                            phoneField,
                            emailField,
                            villageField,
                            locationField,
                            areaField,
                            landTypeField
                    );

                    showAlert(
                            Alert.AlertType.INFORMATION,
                            "Updated",
                            "Farmer information updated successfully!"
                    );

                } else {

                    showAlert(
                            Alert.AlertType.WARNING,
                            "Not Found",
                            "Farmer record not found."
                    );
                }

            } catch (Exception ex) {

                System.out.println(
                        "Failed to update farmer!"
                );

                ex.printStackTrace();

                showAlert(
                        Alert.AlertType.ERROR,
                        "Error",
                        "Failed to update farmer."
                );
            }
        });

        // ================= DELETE FARMER =================

        deleteButton.setOnAction(event -> {

            Map<String, Object> selectedFarmer =
                    farmerTable
                            .getSelectionModel()
                            .getSelectedItem();

            if (selectedFarmer == null) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Select Farmer",
                        "Please select a farmer to delete."
                );

                return;
            }

            Alert alert =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            alert.setTitle(
                    "Delete Farmer"
            );

            alert.setHeaderText(
                    "Delete Farmer Record"
            );

            alert.setContentText(
                    "Are you sure you want to delete this farmer?"
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

                String farmerName =
                        String.valueOf(
                                selectedFarmer.get("name")
                        );

                var documents =
                        db.collection("farmers")
                                .whereEqualTo(
                                        "name",
                                        farmerName
                                )
                                .get()
                                .get()
                                .getDocuments();

                if (!documents.isEmpty()) {

                    String documentId =
                            documents.get(0).getId();

                    db.collection("farmers")
                            .document(documentId)
                            .delete();

                    System.out.println(
                            "Farmer deleted successfully!"
                    );

                    loadFarmers(farmerTable);

                    clearFields(
                            nameField,
                            phoneField,
                            emailField,
                            villageField,
                            locationField,
                            areaField,
                            landTypeField
                    );

                    showAlert(
                            Alert.AlertType.INFORMATION,
                            "Deleted",
                            "Farmer deleted successfully!"
                    );

                } else {

                    showAlert(
                            Alert.AlertType.WARNING,
                            "Not Found",
                            "Farmer record not found."
                    );
                }

            } catch (Exception ex) {

                System.out.println(
                        "Failed to delete farmer!"
                );

                ex.printStackTrace();

                showAlert(
                        Alert.AlertType.ERROR,
                        "Error",
                        "Failed to delete farmer."
                );
            }
        });

        // ================= FORM LAYOUT =================

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
                phoneLabel,
                2,
                0
        );

        form.add(
                phoneField,
                3,
                0
        );

        form.add(
                emailLabel,
                0,
                1
        );

        form.add(
                emailField,
                1,
                1
        );

        form.add(
                villageLabel,
                2,
                1
        );

        form.add(
                villageField,
                3,
                1
        );

        form.add(
                locationLabel,
                0,
                2
        );

        form.add(
                locationField,
                1,
                2
        );

        form.add(
                areaLabel,
                2,
                2
        );

        form.add(
                areaField,
                3,
                2
        );

        form.add(
                landTypeLabel,
                0,
                3
        );

        form.add(
                landTypeField,
                1,
                3
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
                4,
                3,
                1
        );

        // ================= TABLE COLUMNS =================

        TableColumn<Map<String, Object>, String>
                nameColumn =
                new TableColumn<>("Farmer Name");

        TableColumn<Map<String, Object>, String>
                phoneColumn =
                new TableColumn<>("Phone");

        TableColumn<Map<String, Object>, String>
                emailColumn =
                new TableColumn<>("Email");

        TableColumn<Map<String, Object>, String>
                villageColumn =
                new TableColumn<>("Village");

        TableColumn<Map<String, Object>, String>
                locationColumn =
                new TableColumn<>("Farm Location");

        TableColumn<Map<String, Object>, String>
                areaColumn =
                new TableColumn<>("Farm Area");

        TableColumn<Map<String, Object>, String>
                landTypeColumn =
                new TableColumn<>("Land Type");

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

        phoneColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.valueOf(
                                        data.getValue()
                                                .get("phone")
                                )
                        )
        );

        emailColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.valueOf(
                                        data.getValue()
                                                .get("email")
                                )
                        )
        );

        villageColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.valueOf(
                                        data.getValue()
                                                .get("village")
                                )
                        )
        );

        locationColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.valueOf(
                                        data.getValue()
                                                .get("farmLocation")
                                )
                        )
        );

        areaColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.valueOf(
                                        data.getValue()
                                                .get("farmArea")
                                )
                        )
        );

        landTypeColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.valueOf(
                                        data.getValue()
                                                .get("landType")
                                )
                        )
        );

        farmerTable.getColumns().addAll(
                nameColumn,
                phoneColumn,
                emailColumn,
                villageColumn,
                locationColumn,
                areaColumn,
                landTypeColumn
        );

        // ================= TABLE WIDTH =================

        nameColumn.setPrefWidth(170);
        phoneColumn.setPrefWidth(140);
        emailColumn.setPrefWidth(200);
        villageColumn.setPrefWidth(150);
        locationColumn.setPrefWidth(180);
        areaColumn.setPrefWidth(130);
        landTypeColumn.setPrefWidth(150);

        farmerTable.setPrefHeight(350);

        // ================= SELECT FARMER =================

        farmerTable.setOnMouseClicked(event -> {

            if (event.getClickCount() == 1) {

                Map<String, Object> selectedFarmer =
                        farmerTable
                                .getSelectionModel()
                                .getSelectedItem();

                if (selectedFarmer != null) {

                    nameField.setText(
                            String.valueOf(
                                    selectedFarmer.get("name")
                            )
                    );

                    phoneField.setText(
                            String.valueOf(
                                    selectedFarmer.get("phone")
                            )
                    );

                    emailField.setText(
                            String.valueOf(
                                    selectedFarmer.get("email")
                            )
                    );

                    villageField.setText(
                            String.valueOf(
                                    selectedFarmer.get("village")
                            )
                    );

                    locationField.setText(
                            String.valueOf(
                                    selectedFarmer.get(
                                            "farmLocation"
                                    )
                            )
                    );

                    areaField.setText(
                            String.valueOf(
                                    selectedFarmer.get(
                                            "farmArea"
                                    )
                            )
                    );

                    landTypeField.setText(
                            String.valueOf(
                                    selectedFarmer.get(
                                            "landType"
                                    )
                            )
                    );
                }
            }
        });

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

        backButton.setOnAction(event -> {

            Dashboard dashboard =
                    new Dashboard();

            dashboard.show(stage);
        });

        // ================= LOAD DATA =================

        loadFarmers(farmerTable);

        // ================= ADD CONTENT =================

        content.getChildren().addAll(
                heading,
                description,
                form,
                farmerTable,
                backButton
        );

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
                "AgroNova - Farmer Management"
        );

        stage.setMaximized(true);

        stage.show();
    }

    // =====================================================
    // LOAD FARMERS FROM FIRESTORE
    // =====================================================

    private void loadFarmers(
            TableView<Map<String, Object>> farmerTable) {

        try {

            Firestore db =
                    FirestoreClient.getFirestore();

            var documents =
                    db.collection("farmers")
                            .get()
                            .get()
                            .getDocuments();

            farmerTable.getItems().clear();

            for (var document : documents) {

                farmerTable.getItems().add(
                        document.getData()
                );
            }

            System.out.println(
                    "Farmers loaded successfully!"
            );

        } catch (Exception ex) {

            System.out.println(
                    "Failed to load farmers!"
            );

            ex.printStackTrace();
        }
    }

    // =====================================================
    // CLEAR FORM FIELDS
    // =====================================================

    private void clearFields(
            TextField nameField,
            TextField phoneField,
            TextField emailField,
            TextField villageField,
            TextField locationField,
            TextField areaField,
            TextField landTypeField) {

        nameField.clear();
        phoneField.clear();
        emailField.clear();
        villageField.clear();
        locationField.clear();
        areaField.clear();
        landTypeField.clear();
    }

    // =====================================================
    // ALERT METHOD
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