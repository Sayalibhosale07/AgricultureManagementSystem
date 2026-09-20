
package com.agronova;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
public class FarmerManagement {

    public void show(Stage stage) {

        BorderPane root = new BorderPane();
        TableView<java.util.Map<String, Object>> farmerTable =
        new TableView<>();
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
updateButton.setOnAction(event -> {

    String name = nameField.getText();
    String phone = phoneField.getText();
    String village = villageField.getText();

    if (name.isEmpty() || phone.isEmpty() || village.isEmpty()) {

        System.out.println("Please fill all fields!");
        return;
    }

    java.util.Map<String, Object> selectedFarmer =
            farmerTable.getSelectionModel().getSelectedItem();

    if (selectedFarmer == null) {

        System.out.println("Please select a farmer!");
        return;
    }

    try {

        Firestore db = FirestoreClient.getFirestore();

        String farmerName =
                String.valueOf(selectedFarmer.get("name"));

        var documents = db.collection("farmers")
                .whereEqualTo("name", farmerName)
                .get()
                .get()
                .getDocuments();

        if (!documents.isEmpty()) {

            String documentId =
                    documents.get(0).getId();

            java.util.Map<String, Object> updatedData =
                    new java.util.HashMap<>();

            updatedData.put("name", name);
            updatedData.put("phone", phone);
            updatedData.put("village", village);

            db.collection("farmers")
              .document(documentId)
              .set(updatedData);

            System.out.println(
                "Farmer updated successfully!"
            );

            loadFarmers(farmerTable);

            nameField.clear();
            phoneField.clear();
            villageField.clear();

        } else {

            System.out.println(
                "Farmer not found!"
            );
        }

    } catch (Exception ex) {

        System.out.println(
            "Failed to update farmer!"
        );

        ex.printStackTrace();
    }
});
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
deleteButton.setOnAction(event -> {

    java.util.Map<String, Object> selectedFarmer =
            farmerTable.getSelectionModel().getSelectedItem();

    if (selectedFarmer == null) {

        System.out.println("Please select a farmer!");
        return;
    }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

alert.setTitle("Delete Farmer");
alert.setHeaderText("Delete Farmer");
alert.setContentText(
    "Are you sure you want to delete this farmer?"
);

java.util.Optional<javafx.scene.control.ButtonType> result =
        alert.showAndWait();

if (result.isEmpty()
        || result.get() != javafx.scene.control.ButtonType.OK) {

    return;
}

    try {

        Firestore db = FirestoreClient.getFirestore();

        String farmerName =
                String.valueOf(selectedFarmer.get("name"));

        var documents = db.collection("farmers")
                .whereEqualTo("name", farmerName)
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

            nameField.clear();
            phoneField.clear();
            villageField.clear();

        } else {

            System.out.println(
                "Farmer not found!"
            );
        }

    } catch (Exception ex) {

        System.out.println(
            "Failed to delete farmer!"
        );

        ex.printStackTrace();
    }
});
        addButton.setPrefWidth(150);
        addButton.setPrefHeight(40);

        addButton.setStyle(
            "-fx-background-color: #2e7d32;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );

       addButton.setOnAction(event -> {

    String name = nameField.getText();
    String phone = phoneField.getText();
    String village = villageField.getText();

    // Check empty fields
    if (name.isEmpty() || phone.isEmpty() || village.isEmpty()) {

        System.out.println("Please fill all fields!");
        return;
    }

    try {

        // Connect to Firestore
        Firestore db = FirestoreClient.getFirestore();

        // Create farmer data
        java.util.Map<String, Object> farmerData =
                new java.util.HashMap<>();

        farmerData.put("name", name);
        farmerData.put("phone", phone);
        farmerData.put("village", village);

        // Save data in Firestore
        db.collection("farmers")
          .add(farmerData);

        System.out.println("Farmer added successfully!");
        loadFarmers(farmerTable);

        // Clear fields
        nameField.clear();
        phoneField.clear();
        villageField.clear();

    } catch (Exception ex) {

        System.out.println("Failed to add farmer!");
        ex.printStackTrace();
    }
});

        form.add(nameLabel, 0, 0);
        form.add(nameField, 1, 0);

        form.add(phoneLabel, 0, 1);
        form.add(phoneField, 1, 1);

        form.add(villageLabel, 0, 2);
        form.add(villageField, 1, 2);

        form.add(addButton, 1, 3);
        form.add(updateButton, 2, 3);
        form.add(deleteButton, 3, 3);
       // ================= FARMER TABLE =================

     

       TableColumn<java.util.Map<String, Object>, String> nameColumn =
        new TableColumn<>(" Farmer Name ");

       TableColumn<java.util.Map<String, Object>, String> phoneColumn =
        new TableColumn<>(" Phone Number ");

       TableColumn<java.util.Map<String, Object>, String> villageColumn =
        new TableColumn<>(" Village ");

       nameColumn.setCellValueFactory(
        data -> new javafx.beans.property.SimpleStringProperty(
                String.valueOf(data.getValue().get("name"))
        )
);

phoneColumn.setCellValueFactory(
        data -> new javafx.beans.property.SimpleStringProperty(
                String.valueOf(data.getValue().get("phone"))
        )
);

villageColumn.setCellValueFactory(
        data -> new javafx.beans.property.SimpleStringProperty(
                String.valueOf(data.getValue().get("village"))
        )
);

farmerTable.getColumns().addAll(
        nameColumn,
        phoneColumn,
        villageColumn
);
farmerTable.setOnMouseClicked(event -> {

    if (event.getClickCount() == 1) {

        java.util.Map<String, Object> selectedFarmer =
                farmerTable.getSelectionModel().getSelectedItem();

        if (selectedFarmer != null) {

            nameField.setText(
                String.valueOf(selectedFarmer.get("name"))
            );

            phoneField.setText(
                String.valueOf(selectedFarmer.get("phone"))
            );

            villageField.setText(
                String.valueOf(selectedFarmer.get("village"))
            );
        }
    }
});

farmerTable.setPrefHeight(300);
farmerTable.setOnMouseClicked(event -> {

    if (event.getClickCount() == 1) {

        java.util.Map<String, Object> selectedFarmer =
                farmerTable.getSelectionModel().getSelectedItem();

        if (selectedFarmer != null) {

            nameField.setText(
                String.valueOf(selectedFarmer.get("name"))
            );

            phoneField.setText(
                String.valueOf(selectedFarmer.get("phone"))
            );

            villageField.setText(
                String.valueOf(selectedFarmer.get("village"))
            );
        }
    }
});
nameColumn.setPrefWidth(220);
phoneColumn.setPrefWidth(220);
villageColumn.setPrefWidth(220);

farmerTable.setMaxWidth(700);

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
        loadFarmers(farmerTable);
        content.getChildren().addAll(
            heading,
            description,
            form,
            farmerTable,
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
        stage.setMaximized(true);
        stage.show();
              
    }

    // ================= LOAD FARMERS =================

    private void loadFarmers(
            TableView<java.util.Map<String, Object>> farmerTable) {

        try {

            Firestore db = FirestoreClient.getFirestore();

            var documents = db.collection("farmers")
                    .get()
                    .get()
                    .getDocuments();

            farmerTable.getItems().clear();

            for (var document : documents) {

                farmerTable.getItems().add(
                        document.getData()
                );
            }

            System.out.println("Farmers loaded successfully!");

        } catch (Exception ex) {

            System.out.println("Failed to load farmers!");
            ex.printStackTrace();
        }
    }
}
    
