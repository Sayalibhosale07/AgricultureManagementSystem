package com.agronova;

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

public class Reports {

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
        // REPORT CARDS
        // =========================

        VBox farmerCard = createReportCard(
                "👨‍🌾",
                "Farmer Report",
                "View farmer information"
        );

        VBox cropCard = createReportCard(
                "🌱",
                "Crop Report",
                "View crop information"
        );

        VBox fertilizerCard = createReportCard(
                "🧪",
                "Fertilizer Report",
                "View fertilizer information"
        );

        VBox equipmentCard = createReportCard(
                "🚜",
                "Equipment Report",
                "View equipment information"
        );

        VBox marketCard = createReportCard(
                "🛒",
                "Market Report",
                "View market information"
        );

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
            String description
    ) {

        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font("Arial", 32));

        Label headingLabel = new Label(heading);
        headingLabel.setFont(Font.font("Arial", 18));
        headingLabel.setTextFill(Color.web("#1b5e20"));

        Label descriptionLabel = new Label(description);
        descriptionLabel.setFont(Font.font("Arial", 13));
        descriptionLabel.setTextFill(Color.DARKGRAY);

        VBox card = new VBox(
                8,
                iconLabel,
                headingLabel,
                descriptionLabel
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
}