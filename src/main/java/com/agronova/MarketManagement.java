package com.agronova;

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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MarketManagement {

    private final ObservableList<MarketItem> marketList =
            FXCollections.observableArrayList();

    private TableView<MarketItem> marketTable;

    public void show(Stage stage) {

        // ================= TOP BAR =================

        Label logo = new Label("🌿 AgroNova");
        logo.setFont(Font.font("Arial", 26));
        logo.setTextFill(Color.WHITE);

        Label subtitle = new Label("Market Management");
        subtitle.setFont(Font.font("Arial", 16));
        subtitle.setTextFill(Color.WHITE);

        HBox topBar = new HBox(20, logo, subtitle);

        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(18, 25, 18, 25));

        topBar.setStyle(
                "-fx-background-color: linear-gradient(to right, #1b5e20, #43a047);"
        );

        // ================= TITLE =================

        Label title = new Label("Market Management 🛒");

        title.setFont(Font.font("Arial", 30));
        title.setTextFill(Color.web("#1b5e20"));

        Label description = new Label(
                "Add and manage agricultural market information"
        );

        description.setFont(Font.font("Arial", 15));
        description.setTextFill(Color.DARKGRAY);

        // ================= INPUT FIELDS =================

        Label cropLabel = new Label("Crop / Product Name");

        TextField cropField = new TextField();
        cropField.setPromptText("Example: Wheat");
        cropField.setPrefWidth(300);

        Label quantityLabel = new Label("Quantity");

        TextField quantityField = new TextField();
        quantityField.setPromptText("Example: 50 kg");
        quantityField.setPrefWidth(300);

        Label priceLabel = new Label("Market Price");

        TextField priceField = new TextField();
        priceField.setPromptText("Example: ₹2500");
        priceField.setPrefWidth(300);

        // ================= FORM =================

        GridPane form = new GridPane();

        form.setHgap(15);
        form.setVgap(10);
        form.setPadding(new Insets(20));

        form.add(cropLabel, 0, 0);
        form.add(cropField, 1, 0);

        form.add(quantityLabel, 0, 1);
        form.add(quantityField, 1, 1);

        form.add(priceLabel, 0, 2);
        form.add(priceField, 1, 2);

        form.setAlignment(Pos.CENTER);

        form.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 15px;" +
                "-fx-border-color: #c8e6c9;" +
                "-fx-border-radius: 15px;" +
                "-fx-border-width: 1px;"
        );

        // ================= ADD BUTTON =================

        Button addButton = new Button("ADD MARKET ITEM 🛒");

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

            String crop = cropField.getText().trim();
            String quantity = quantityField.getText().trim();
            String price = priceField.getText().trim();

            if (crop.isEmpty()
                    || quantity.isEmpty()
                    || price.isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Validation",
                        "Please fill all market details."
                );

                return;
            }

            try {

                Firestore db =
                        FirestoreClient.getFirestore();

                Map<String, Object> marketItem =
                        new HashMap<>();

                marketItem.put("crop", crop);
                marketItem.put("quantity", quantity);
                marketItem.put("price", price);

                db.collection("marketItems")
                        .add(marketItem)
                        .get();

                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Success",
                        "Market item added successfully!"
                );

                clearFields(
                        cropField,
                        quantityField,
                        priceField
                );

                loadMarketItems();

            } catch (Exception e) {

                e.printStackTrace();

                showAlert(
                        Alert.AlertType.ERROR,
                        "Error",
                        "Failed to add market item."
                );
            }
        });

        // ================= UPDATE =================

        updateButton.setOnAction(event -> {

            MarketItem selected =
                    marketTable.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Select Item",
                        "Please select a market item from the table."
                );

                return;
            }

            String newCrop = cropField.getText().trim();
            String newQuantity = quantityField.getText().trim();
            String newPrice = priceField.getText().trim();

            if (newCrop.isEmpty()
                    || newQuantity.isEmpty()
                    || newPrice.isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Validation",
                        "Please fill all market details."
                );

                return;
            }

            try {

                Firestore db =
                        FirestoreClient.getFirestore();

                var documents =
                        db.collection("marketItems")
                                .whereEqualTo(
                                        "crop",
                                        selected.getCrop()
                                )
                                .get()
                                .get()
                                .getDocuments();

                if (documents.isEmpty()) {

                    showAlert(
                            Alert.AlertType.ERROR,
                            "Error",
                            "Market item not found."
                    );

                    return;
                }

                String documentId =
                        documents.get(0).getId();

                Map<String, Object> updatedItem =
                        new HashMap<>();

                updatedItem.put("crop", newCrop);
                updatedItem.put("quantity", newQuantity);
                updatedItem.put("price", newPrice);

                db.collection("marketItems")
                        .document(documentId)
                        .set(updatedItem)
                        .get();

                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Success",
                        "Market item updated successfully!"
                );

                clearFields(
                        cropField,
                        quantityField,
                        priceField
                );

                loadMarketItems();

            } catch (Exception e) {

                e.printStackTrace();

                showAlert(
                        Alert.AlertType.ERROR,
                        "Error",
                        "Failed to update market item."
                );
            }
        });

        // ================= DELETE =================

        deleteButton.setOnAction(event -> {

            MarketItem selected =
                    marketTable.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Select Item",
                        "Please select a market item to delete."
                );

                return;
            }

            Alert confirmation =
                    new Alert(Alert.AlertType.CONFIRMATION);

            confirmation.setTitle("Delete Market Item");

            confirmation.setHeaderText(
                    "Delete " + selected.getCrop() + "?"
            );

            confirmation.setContentText(
                    "Are you sure you want to delete this market item?"
            );

            Optional<ButtonType> result =
                    confirmation.showAndWait();

            if (result.isPresent()
                    && result.get() == ButtonType.OK) {

                try {

                    Firestore db =
                            FirestoreClient.getFirestore();

                    var documents =
                            db.collection("marketItems")
                                    .whereEqualTo(
                                            "crop",
                                            selected.getCrop()
                                    )
                                    .get()
                                    .get()
                                    .getDocuments();

                    if (!documents.isEmpty()) {

                        String documentId =
                                documents.get(0).getId();

                        db.collection("marketItems")
                                .document(documentId)
                                .delete()
                                .get();

                        showAlert(
                                Alert.AlertType.INFORMATION,
                                "Success",
                                "Market item deleted successfully!"
                        );

                        clearFields(
                                cropField,
                                quantityField,
                                priceField
                        );

                        loadMarketItems();
                    }

                } catch (Exception e) {

                    e.printStackTrace();

                    showAlert(
                            Alert.AlertType.ERROR,
                            "Error",
                            "Failed to delete market item."
                    );
                }
            }
        });

        // ================= TABLE =================

        marketTable = new TableView<>();

        TableColumn<MarketItem, String> cropColumn =
                new TableColumn<>("Crop / Product");

        cropColumn.setCellValueFactory(
                new PropertyValueFactory<>("crop")
        );

        TableColumn<MarketItem, String> quantityColumn =
                new TableColumn<>("Quantity");

        quantityColumn.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );

        TableColumn<MarketItem, String> priceColumn =
                new TableColumn<>("Market Price");

        priceColumn.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );

        cropColumn.setPrefWidth(250);
        quantityColumn.setPrefWidth(180);
        priceColumn.setPrefWidth(180);

        marketTable.getColumns().addAll(
                cropColumn,
                quantityColumn,
                priceColumn
        );

        marketTable.setItems(marketList);

        marketTable.setPrefHeight(220);
        marketTable.setMinHeight(220);
        marketTable.setMaxHeight(220);

        // ================= ROW SELECTION =================

        marketTable.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (obs, oldSelection, newSelection) -> {

                            if (newSelection != null) {

                                cropField.setText(
                                        newSelection.getCrop()
                                );

                                quantityField.setText(
                                        newSelection.getQuantity()
                                );

                                priceField.setText(
                                        newSelection.getPrice()
                                );
                            }
                        }
                );

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
                marketTable,
                backButton
        );

        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(25));

        content.setFillWidth(false);
        content.setMaxHeight(650);

        // ================= ROOT =================

        BorderPane root =
                new BorderPane();

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
                "AgroNova - Market Management"
        );

        stage.setMaximized(true);
        stage.show();

        loadMarketItems();
    }

    // ================= LOAD MARKET ITEMS =================

    private void loadMarketItems() {

        try {

            Firestore db =
                    FirestoreClient.getFirestore();

            marketList.clear();

            var documents =
                    db.collection("marketItems")
                            .get()
                            .get()
                            .getDocuments();

            for (QueryDocumentSnapshot document :
                    documents) {

                String crop =
                        document.getString("crop");

                String quantity =
                        document.getString("quantity");

                String price =
                        document.getString("price");

                marketList.add(
                        new MarketItem(
                                crop,
                                quantity,
                                price
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= CLEAR FIELDS =================

    private void clearFields(
            TextField cropField,
            TextField quantityField,
            TextField priceField) {

        cropField.clear();
        quantityField.clear();
        priceField.clear();
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

    // ================= MARKET ITEM MODEL =================

    public static class MarketItem {

        private final String crop;
        private final String quantity;
        private final String price;

        public MarketItem(
                String crop,
                String quantity,
                String price) {

            this.crop = crop;
            this.quantity = quantity;
            this.price = price;
        }

        public String getCrop() {
            return crop;
        }

        public String getQuantity() {
            return quantity;
        }

        public String getPrice() {
            return price;
        }
    }
}