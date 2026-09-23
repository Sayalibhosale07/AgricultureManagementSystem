package com.agronova;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EquipmentManagement {

    private final ObservableList<Equipment> equipmentList =
            FXCollections.observableArrayList();

    private TableView<Equipment> equipmentTable;

    public void show(Stage stage) {

        // ================= TOP BAR =================

        Label logo = new Label("🌿 AgroNova");
        logo.setFont(Font.font("Arial", 26));
        logo.setTextFill(Color.WHITE);

        Label subtitle = new Label("Equipment Management");
        subtitle.setFont(Font.font("Arial", 16));
        subtitle.setTextFill(Color.WHITE);

        HBox topBar = new HBox(20, logo, subtitle);

        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(18, 25, 18, 25));

        topBar.setStyle(
                "-fx-background-color: linear-gradient(to right, #1b5e20, #43a047);"
        );

        // ================= TITLE =================

        Label title = new Label("Equipment Management 🚜");

        title.setFont(Font.font("Arial", 30));
        title.setTextFill(Color.web("#1b5e20"));

        Label description = new Label(
                "Add and manage agricultural equipment"
        );

        description.setFont(Font.font("Arial", 15));
        description.setTextFill(Color.DARKGRAY);

        // ================= INPUT FIELDS =================

        Label nameLabel = new Label("Equipment Name");

        TextField nameField = new TextField();
        nameField.setPromptText("Example: Tractor");
        nameField.setPrefWidth(300);

        Label typeLabel = new Label("Equipment Type");

        TextField typeField = new TextField();
        typeField.setPromptText("Example: Machine / Tool");
        typeField.setPrefWidth(300);

        Label quantityLabel = new Label("Quantity");

        TextField quantityField = new TextField();
        quantityField.setPromptText("Example: 2");
        quantityField.setPrefWidth(300);

        // ================= FORM =================

        GridPane form = new GridPane();

        form.setHgap(15);
        form.setVgap(10);
        form.setPadding(new Insets(20));

        form.add(nameLabel, 0, 0);
        form.add(nameField, 1, 0);

        form.add(typeLabel, 0, 1);
        form.add(typeField, 1, 1);

        form.add(quantityLabel, 0, 2);
        form.add(quantityField, 1, 2);

        form.setAlignment(Pos.CENTER);

        form.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 15px;" +
                "-fx-border-color: #c8e6c9;" +
                "-fx-border-radius: 15px;" +
                "-fx-border-width: 1px;"
        );

        // ================= ADD BUTTON =================

        Button addButton = new Button("ADD EQUIPMENT 🚜");

        addButton.setStyle(
                "-fx-background-color: #2e7d32;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 10px 20px;" +
                "-fx-background-radius: 8px;"
        );

        // ================= UPDATE BUTTON =================

        Button updateButton = new Button("UPDATE ✏️");

        updateButton.setStyle(
                "-fx-background-color: #f9a825;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 10px 20px;" +
                "-fx-background-radius: 8px;"
        );

        // ================= DELETE BUTTON =================

        Button deleteButton = new Button("DELETE 🗑️");

        deleteButton.setStyle(
                "-fx-background-color: #c62828;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 10px 20px;" +
                "-fx-background-radius: 8px;"
        );

        HBox buttonBox = new HBox(
                10,
                addButton,
                updateButton,
                deleteButton
        );

        buttonBox.setAlignment(Pos.CENTER);

        // ================= ADD =================

        addButton.setOnAction(event -> {

            String name = nameField.getText().trim();
            String type = typeField.getText().trim();
            String quantity = quantityField.getText().trim();

            if (name.isEmpty()
                    || type.isEmpty()
                    || quantity.isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Validation",
                        "Please fill all equipment details."
                );

                return;
            }

            try {

                Firestore db = FirestoreClient.getFirestore();

                Map<String, Object> equipment = new HashMap<>();

                equipment.put("name", name);
                equipment.put("type", type);
                equipment.put("quantity", quantity);

                db.collection("equipments")
                        .add(equipment)
                        .get();

                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Success",
                        "Equipment added successfully!"
                );

                clearFields(
                        nameField,
                        typeField,
                        quantityField
                );

                loadEquipments();

            } catch (Exception e) {

                e.printStackTrace();

                showAlert(
                        Alert.AlertType.ERROR,
                        "Error",
                        "Failed to add equipment."
                );
            }
        });

        // ================= UPDATE =================

        updateButton.setOnAction(event -> {

            Equipment selected =
                    equipmentTable.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Select Equipment",
                        "Please select an equipment from the table."
                );

                return;
            }

            String newName = nameField.getText().trim();
            String newType = typeField.getText().trim();
            String newQuantity = quantityField.getText().trim();

            if (newName.isEmpty()
                    || newType.isEmpty()
                    || newQuantity.isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Validation",
                        "Please fill all equipment details."
                );

                return;
            }

            try {

                Firestore db = FirestoreClient.getFirestore();

                var documents = db.collection("equipments")
                        .whereEqualTo("name", selected.getName())
                        .get()
                        .get()
                        .getDocuments();

                if (documents.isEmpty()) {

                    showAlert(
                            Alert.AlertType.ERROR,
                            "Error",
                            "Equipment record not found."
                    );

                    return;
                }

                String documentId =
                        documents.get(0).getId();

                Map<String, Object> updatedEquipment =
                        new HashMap<>();

                updatedEquipment.put("name", newName);
                updatedEquipment.put("type", newType);
                updatedEquipment.put("quantity", newQuantity);

                db.collection("equipments")
                        .document(documentId)
                        .set(updatedEquipment)
                        .get();

                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Success",
                        "Equipment updated successfully!"
                );

                clearFields(
                        nameField,
                        typeField,
                        quantityField
                );

                loadEquipments();

            } catch (Exception e) {

                e.printStackTrace();

                showAlert(
                        Alert.AlertType.ERROR,
                        "Error",
                        "Failed to update equipment."
                );
            }
        });

        // ================= DELETE =================

        deleteButton.setOnAction(event -> {

            Equipment selected =
                    equipmentTable.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Select Equipment",
                        "Please select an equipment to delete."
                );

                return;
            }

            Alert confirmation = new Alert(
                    Alert.AlertType.CONFIRMATION
            );

            confirmation.setTitle("Delete Equipment");
            confirmation.setHeaderText(
                    "Delete " + selected.getName() + "?"
            );

            confirmation.setContentText(
                    "Are you sure you want to delete this equipment?"
            );

            Optional<ButtonType> result =
                    confirmation.showAndWait();

            if (result.isPresent()
                    && result.get() == ButtonType.OK) {

                try {

                    Firestore db =
                            FirestoreClient.getFirestore();

                    var documents =
                            db.collection("equipments")
                                    .whereEqualTo(
                                            "name",
                                            selected.getName()
                                    )
                                    .get()
                                    .get()
                                    .getDocuments();

                    if (!documents.isEmpty()) {

                        String documentId =
                                documents.get(0).getId();

                        db.collection("equipments")
                                .document(documentId)
                                .delete()
                                .get();

                        showAlert(
                                Alert.AlertType.INFORMATION,
                                "Success",
                                "Equipment deleted successfully!"
                        );

                        clearFields(
                                nameField,
                                typeField,
                                quantityField
                        );

                        loadEquipments();
                    }

                } catch (Exception e) {

                    e.printStackTrace();

                    showAlert(
                            Alert.AlertType.ERROR,
                            "Error",
                            "Failed to delete equipment."
                    );
                }
            }
        });

        // ================= TABLE =================

        equipmentTable = new TableView<>();

        TableColumn<Equipment, String> nameColumn =
                new TableColumn<>("Equipment Name");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        TableColumn<Equipment, String> typeColumn =
                new TableColumn<>("Equipment Type");

        typeColumn.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );

        TableColumn<Equipment, String> quantityColumn =
                new TableColumn<>("Quantity");

        quantityColumn.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );

        nameColumn.setPrefWidth(250);
        typeColumn.setPrefWidth(250);
        quantityColumn.setPrefWidth(150);

        equipmentTable.getColumns().addAll(
                nameColumn,
                typeColumn,
                quantityColumn
        );

        equipmentTable.setItems(equipmentList);

        equipmentTable.setPrefHeight(220);
        equipmentTable.setMinHeight(220);
        equipmentTable.setMaxHeight(220);

        // ================= ROW SELECTION =================

        equipmentTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {

                    if (newSelection != null) {

                        nameField.setText(
                                newSelection.getName()
                        );

                        typeField.setText(
                                newSelection.getType()
                        );

                        quantityField.setText(
                                newSelection.getQuantity()
                        );
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

        VBox content = new VBox(
                12,
                title,
                description,
                form,
                buttonBox,
                equipmentTable,
                backButton
        );

        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(25));

        content.setFillWidth(false);
        content.setMaxHeight(650);

        // ================= ROOT =================

        BorderPane root = new BorderPane();

        root.setTop(topBar);
        root.setCenter(content);

        root.setStyle(
                "-fx-background-color: #f4f8f4;"
        );

        // ================= SCENE =================

        Scene scene =
                new Scene(root, 1200, 750);

        stage.setScene(scene);

        stage.setTitle(
                "AgroNova - Equipment Management"
        );

        stage.setMaximized(true);
        stage.show();

        // Load existing data
        loadEquipments();
    }

    // ================= LOAD EQUIPMENT =================

    private void loadEquipments() {

        try {

            Firestore db =
                    FirestoreClient.getFirestore();

            equipmentList.clear();

            var documents =
                    db.collection("equipments")
                            .get()
                            .get()
                            .getDocuments();

            for (QueryDocumentSnapshot document : documents) {

                String name =
                        document.getString("name");

                String type =
                        document.getString("type");

                String quantity =
                        document.getString("quantity");

                equipmentList.add(
                        new Equipment(
                                name,
                                type,
                                quantity
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= CLEAR FIELDS =================

    private void clearFields(
            TextField nameField,
            TextField typeField,
            TextField quantityField) {

        nameField.clear();
        typeField.clear();
        quantityField.clear();
    }

    // ================= ALERT =================

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

    // ================= EQUIPMENT MODEL =================

    public static class Equipment {

        private final String name;
        private final String type;
        private final String quantity;

        public Equipment(
                String name,
                String type,
                String quantity) {

            this.name = name;
            this.type = type;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getQuantity() {
            return quantity;
        }
    }
}