package com.agronova;

import java.util.concurrent.CompletableFuture;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

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

        logo.setFont(
                Font.font("Arial", FontWeight.BOLD, 26)
        );

        logo.setTextFill(Color.WHITE);

        Label subtitle = new Label(
                "Reports & Statistics"
        );

        subtitle.setFont(
                Font.font("Arial", 16)
        );

        subtitle.setTextFill(Color.WHITE);

        HBox topBar = new HBox(
                20,
                logo,
                subtitle
        );

        topBar.setAlignment(
                Pos.CENTER_LEFT
        );

        topBar.setPadding(
                new Insets(18, 25, 18, 25)
        );

        topBar.setStyle(
                "-fx-background-color: linear-gradient(to right, #1b5e20, #43a047);"
        );

        // =========================
        // TITLE
        // =========================

        Label title = new Label(
                "AgroNova Reports 📊"
        );

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

        Label description = new Label(
                "View agriculture management statistics and reports"
        );

        description.setFont(
                Font.font("Arial", 15)
        );

        description.setTextFill(
                Color.DARKGRAY
        );

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

        reportGrid.setAlignment(
                Pos.CENTER
        );

        reportGrid.add(
                farmerCard,
                0,
                0
        );

        reportGrid.add(
                cropCard,
                1,
                0
        );

        reportGrid.add(
                fertilizerCard,
                2,
                0
        );

        reportGrid.add(
                equipmentCard,
                0,
                1
        );

        reportGrid.add(
                marketCard,
                1,
                1
        );

        // =========================
        // STATISTICS TITLE
        // =========================

        Label statisticsTitle = new Label(
                "Management Statistics 📈"
        );

        statisticsTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24
                )
        );

        statisticsTitle.setTextFill(
                Color.web("#1b5e20")
        );

        // =========================
        // BAR CHART
        // =========================

        CategoryAxis xAxis =
                new CategoryAxis();

        xAxis.setLabel(
                "Management Category"
        );

        NumberAxis yAxis =
                new NumberAxis();

        yAxis.setLabel(
                "Total Records"
        );

        yAxis.setForceZeroInRange(true);

        BarChart<String, Number> barChart =
                new BarChart<>(
                        xAxis,
                        yAxis
                );

        barChart.setTitle(
                "AgroNova Data Overview"
        );

        barChart.setLegendVisible(false);

        barChart.setAnimated(false);

        barChart.setCategoryGap(45);

        barChart.setBarGap(8);

        // =========================
        // LARGE CHART SIZE
        // =========================

        barChart.setMinWidth(600);
        barChart.setPrefWidth(700);

        barChart.setMinHeight(500);
        barChart.setPrefHeight(550);

        barChart.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;" +
                "-fx-padding: 25;" +
                "-fx-border-color: #c8e6c9;" +
                "-fx-border-radius: 18;" +
                "-fx-border-width: 1;"
        );

        // =========================
        // LOAD FIRESTORE COUNTS
        // =========================

        loadReportCounts(
                farmerCount,
                cropCount,
                fertilizerCount,
                equipmentCount,
                marketCount,
                barChart
        );

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
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );

        backButton.setOnAction(event -> {

            Dashboard dashboard =
                    new Dashboard();

            dashboard.show(stage);

        });

        // =========================
        // STATISTICS SECTION
        // =========================

        VBox statisticsSection =
                new VBox(
                        15,
                        statisticsTitle,
                        barChart
                );

        statisticsSection.setAlignment(
                Pos.CENTER
        );

        // =========================
        // MAIN CONTENT
        // =========================

        VBox content =
                new VBox(
                        25,
                        title,
                        description,
                        reportGrid,
                        statisticsSection,
                        backButton
                );

        content.setAlignment(
                Pos.TOP_CENTER
        );

        content.setPadding(
                new Insets(35)
        );

        content.setStyle(
                "-fx-background-color: #f4f8f4;"
        );

        // =========================
        // SCROLL PANE
        // =========================

        ScrollPane scrollPane =
                new ScrollPane();

        scrollPane.setContent(
                content
        );

        scrollPane.setFitToWidth(true);

        scrollPane.setPannable(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background-color: #f4f8f4;" +
                "-fx-border-color: transparent;"
        );

        // =========================
        // ROOT
        // =========================

        BorderPane root =
                new BorderPane();

        root.setTop(topBar);

        root.setCenter(scrollPane);

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
                "AgroNova - Reports & Statistics"
        );

        stage.setMaximized(true);

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

        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(
                Font.font("Arial", 32)
        );

        Label headingLabel =
                new Label(heading);

        headingLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        headingLabel.setTextFill(
                Color.web("#1b5e20")
        );

        countLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        countLabel.setTextFill(
                Color.DARKGRAY
        );

        VBox card =
                new VBox(
                        8,
                        iconLabel,
                        headingLabel,
                        countLabel
                );

        card.setAlignment(
                Pos.CENTER
        );

        card.setPrefSize(
                220,
                140
        );

        card.setPadding(
                new Insets(20)
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 15px;" +
                "-fx-border-color: #c8e6c9;" +
                "-fx-border-radius: 15px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0, 0, 3);"
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
            Label marketCount,
            BarChart<String, Number> barChart
    ) {

        loadCollectionCount(
                "farmers",
                "Total Farmers: ",
                farmerCount,
                "Farmers",
                barChart
        );

        loadCollectionCount(
                "crops",
                "Total Crops: ",
                cropCount,
                "Crops",
                barChart
        );

        loadCollectionCount(
                "fertilizers",
                "Total Fertilizers: ",
                fertilizerCount,
                "Fertilizers",
                barChart
        );

        loadCollectionCount(
                "equipments",
                "Total Equipment: ",
                equipmentCount,
                "Equipment",
                barChart
        );

        loadCollectionCount(
                "marketItems",
                "Total Market Items: ",
                marketCount,
                "Market",
                barChart
        );
    }

    // =========================
    // FIRESTORE COLLECTION COUNT
    // =========================

    private void loadCollectionCount(
            String collectionName,
            String labelText,
            Label countLabel,
            String chartName,
            BarChart<String, Number> barChart
    ) {

        CompletableFuture
                .supplyAsync(() -> {

                    try {

                        QuerySnapshot snapshot =
                                db.collection(
                                        collectionName
                                )
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

                            updateChart(
                                    chartName,
                                    count,
                                    barChart
                            );

                        } else {

                            countLabel.setText(
                                    "Unable to load data"
                            );
                        }

                    });

                });
    }

    // =========================
    // UPDATE BAR CHART
    // =========================

    private void updateChart(
            String category,
            int count,
            BarChart<String, Number> barChart
    ) {

        XYChart.Series<String, Number> series;

        if (barChart.getData().isEmpty()) {

            series =
                    new XYChart.Series<>();

            series.setName(
                    "AgroNova Records"
            );

            barChart.getData().add(
                    series
            );

        } else {

            series =
                    barChart.getData().get(0);
        }

        // Check existing category
        for (
                XYChart.Data<String, Number> data
                : series.getData()
        ) {

            if (
                    data.getXValue()
                            .equals(category)
            ) {

                data.setYValue(count);

                return;
            }
        }

        series.getData().add(
                new XYChart.Data<>(
                        category,
                        count
                )
        );
    }
}