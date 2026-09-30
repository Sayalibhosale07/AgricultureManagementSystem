package com.agronova;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.concurrent.CompletableFuture;

public class Reports {


private Firestore db;

// =========================
// CONSTRUCTOR
// =========================

public Reports() {
    db = FirestoreClient.getFirestore();
}

// =========================
// SHOW REPORTS SCREEN
// =========================

public void show(Stage stage) {

    // =========================
    // TOP BAR
    // =========================

    Label logo = new Label("🌿 AgroNova");
    logo.setFont(Font.font("Arial", 26));
    logo.setTextFill(Color.WHITE);

    Label subtitle = new Label("Reports");
    subtitle.setFont(Font.font("Arial", 16));
    subtitle.setTextFill(Color.WHITE);

    HBox topBar = new HBox(20, logo, subtitle);

    topBar.setAlignment(Pos.CENTER_LEFT);
    topBar.setPadding(new Insets(18, 25, 18, 25));

    topBar.setStyle(
            "-fx-background-color: linear-gradient(to right, #1b5e20, #43a047);"
    );

    // =========================
    // TITLE
    // =========================

    Label title = new Label("AgroNova Reports 📊");

    title.setFont(Font.font("Arial", 30));
    title.setTextFill(Color.web("#1b5e20"));

    Label description = new Label(
            "View agriculture management summaries and reports"
    );

    description.setFont(Font.font("Arial", 15));
    description.setTextFill(Color.DARKGRAY);

    // =========================
    // COUNT LABELS
    // =========================

    Label farmerCount = new Label("Loading...");
    Label cropCount = new Label("Loading...");
    Label fertilizerCount = new Label("Loading...");
    Label equipmentCount = new Label("Loading...");
    Label marketCount = new Label("Loading...");

    // =========================
    // REPORT CARDS
    // =========================

    VBox farmerCard = createReportCard(
            "👨‍🌾",
            "Farmer Report",
            farmerCount
    );

    VBox cropCard = createReportCard(
            "🌱",
            "Crop Report",
            cropCount
    );

    VBox fertilizerCard = createReportCard(
            "🧪",
            "Fertilizer Report",
            fertilizerCount
    );

    VBox equipmentCard = createReportCard(
            "🚜",
            "Equipment Report",
            equipmentCount
    );

    VBox marketCard = createReportCard(
            "🛒",
            "Market Report",
            marketCount
    );

    // =========================
    // REPORT GRID
    // =========================

    GridPane reportGrid = new GridPane();

    reportGrid.setHgap(20);
    reportGrid.setVgap(20);
    reportGrid.setAlignment(Pos.CENTER);

    reportGrid.add(farmerCard, 0, 0);
    reportGrid.add(cropCard, 1, 0);
    reportGrid.add(fertilizerCard, 2, 0);

    reportGrid.add(equipmentCard, 0, 1);
    reportGrid.add(marketCard, 1, 1);

    // =========================
    // LOAD FIRESTORE COUNTS
    // =========================

    loadReportCounts(
            farmerCount,
            cropCount,
            fertilizerCount,
            equipmentCount,
            marketCount
    );

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
    // CONTENT
    // =========================

    VBox content = new VBox(
            15,
            title,
            description,
            reportGrid,
            backButton
    );

    content.setAlignment(Pos.TOP_CENTER);
    content.setPadding(new Insets(35));

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
    stage.setTitle("AgroNova - Reports");
    stage.show();
}

// =========================
// REPORT CARD METHOD
// =========================

private VBox createReportCard(
        String icon,
        String heading,
        Label countLabel
) {

    Label iconLabel = new Label(icon);

    iconLabel.setFont(Font.font("Arial", 32));

    Label headingLabel = new Label(heading);

    headingLabel.setFont(Font.font("Arial", 18));
    headingLabel.setTextFill(Color.web("#1b5e20"));

    countLabel.setFont(Font.font("Arial", 14));
    countLabel.setTextFill(Color.DARKGRAY);

    VBox card = new VBox(
            8,
            iconLabel,
            headingLabel,
            countLabel
    );

    card.setAlignment(Pos.CENTER);

    card.setPrefSize(220, 140);
    card.setPadding(new Insets(20));

    card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 15px;" +
            "-fx-border-color: #c8e6c9;" +
            "-fx-border-radius: 15px;" +
            "-fx-border-width: 1px;"
    );

    return card;
}

// =========================
// LOAD REPORT COUNTS
// =========================

private void loadReportCounts(
        Label farmerCount,
        Label cropCount,
        Label fertilizerCount,
        Label equipmentCount,
        Label marketCount
) {

    // Farmers
    loadCollectionCount(
            "farmers",
            "Total Farmers: ",
            farmerCount
    );

    // Crops
    loadCollectionCount(
            "crops",
            "Total Crops: ",
            cropCount
    );

    // Fertilizers
    loadCollectionCount(
            "fertilizers",
            "Total Fertilizers: ",
            fertilizerCount
    );

    // Equipment
    loadCollectionCount(
            "equipments",
            "Total Equipment: ",
            equipmentCount
    );

    // Market
    loadCollectionCount(
            "marketItems",
            "Total Market Items: ",
            marketCount
    );
}

// =========================
// FIRESTORE COLLECTION COUNT
// =========================

private void loadCollectionCount(
        String collectionName,
        String labelText,
        Label countLabel
) {

    CompletableFuture
            .supplyAsync(() -> {

                try {

                    QuerySnapshot snapshot = db
                            .collection(collectionName)
                            .get()
                            .get();

                    return snapshot.size();

                } catch (Exception e) {

                    e.printStackTrace();

                    return -1;
                }

            })
            .thenAccept(count -> {

                Platform.runLater(() -> {

                    if (count >= 0) {

                        countLabel.setText(
                                labelText + count
                        );

                    } else {

                        countLabel.setText(
                                "Unable to load data"
                        );
                    }

                });

            });
}


}
