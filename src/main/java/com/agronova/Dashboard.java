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
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Dashboard {

    private Stage stage;

    // ================= DASHBOARD =================

    public void show(Stage stage) {

        this.stage = stage;

        BorderPane root = new BorderPane();

        root.setStyle(
            "-fx-background-color: #f4f8f4;"
        );

        // ================= TOP BAR =================

        HBox topBar = new HBox();

        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setSpacing(20);
        topBar.setPadding(
            new Insets(15, 25, 15, 25)
        );

        topBar.setStyle(
            "-fx-background-color: #14532d;"
        );

        Label logo = new Label("🌿 AgroNova");

        logo.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                25
            )
        );

        logo.setTextFill(Color.WHITE);

        Label title = new Label(
            "Agriculture Management System"
        );

        title.setFont(
            Font.font("Arial", 17)
        );

        title.setTextFill(
            Color.web("#d8f3dc")
        );

        topBar.getChildren().addAll(
            logo,
            title
        );

        // ================= SIDEBAR =================

        VBox sidebar = new VBox(10);

        sidebar.setPadding(
            new Insets(25, 15, 20, 15)
        );

        sidebar.setPrefWidth(235);

        sidebar.setStyle(
            "-fx-background-color: #e8f5e9;"
        );

        Label menuTitle = new Label("MAIN MENU");

        menuTitle.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                13
            )
        );

        menuTitle.setTextFill(
            Color.web("#388e3c")
        );

        Button farmer =
            createMenuButton("👨‍🌾  Farmer Management");
        farmer.setOnAction(event -> {
        FarmerManagement farmerManagement =
        new FarmerManagement();

        farmerManagement.show(stage);
        });
        
        Button crop =
            createMenuButton("🌱  Crop Management");
            crop.setOnAction(event -> {
            CropManagement cropManagement = new CropManagement();
            cropManagement.show(stage);
        });

        Button fertilizer =
            createMenuButton("🧪  Fertilizer Management");
            fertilizer.setOnAction(event -> {
            FertilizerManagement fertilizerManagement =
            new FertilizerManagement();

            fertilizerManagement.show(stage);
        });
        Button equipment =
            createMenuButton("🚜  Equipment Management");
            equipment.setOnAction(event -> {
            EquipmentManagement equipmentManagement =
            new EquipmentManagement();

            equipmentManagement.show(stage);
        });

        Button market =
            createMenuButton("🛒  Market Management");
            market.setOnAction(event -> {
            MarketManagement marketManagement =
            new MarketManagement();

            marketManagement.show(stage);
        });

        Button reports =
            createMenuButton("📊  Reports");
            reports.setOnAction(event -> {
            Reports reportsScreen = new Reports();
            reportsScreen.show(stage);
        });

        Button profile =
            createMenuButton("👤  Profile / Settings");
            profile.setOnAction(event -> {
            ProfileSettings profileSettings = new ProfileSettings();
             profileSettings.show(stage);
        });
        Button logout =
            createMenuButton("🚪  Logout");
           logout.setOnAction(event -> {
           Main main = new Main();
           main.start(stage);
        });

        

        sidebar.getChildren().addAll(
            menuTitle,
            farmer,
            crop,
            fertilizer,
            equipment,
            market,
            reports,
            profile,
            logout
        );

        // ================= CENTER =================

        VBox center = new VBox(20);

        center.setPadding(
            new Insets(30)
        );

        // Welcome heading

        Label welcome = new Label(
            "Welcome to AgroNova 🌾"
        );

        welcome.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                30
            )
        );

        welcome.setTextFill(
            Color.web("#1b5e20")
        );

        Label message = new Label(
            "Manage your agricultural activities from one place."
        );

        message.setFont(
            Font.font("Arial", 16)
        );

        message.setTextFill(
            Color.web("#607d64")
        );

        // ================= STATISTICS =================

        HBox statistics = new HBox(15);

        statistics.getChildren().addAll(
            createStatCard("👨‍🌾", "Farmers", "25"),
            createStatCard("🌱", "Crops", "18"),
            createStatCard("🚜", "Equipment", "12"),
            createStatCard("🛒", "Market Items", "20")
        );

        // ================= SECTION TITLE =================

        Label quickTitle =
            new Label("Quick Management");

        quickTitle.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                21
            )
        );

        quickTitle.setTextFill(
            Color.web("#245c35")
        );

        // ================= MANAGEMENT CARDS =================

        GridPane cards = new GridPane();

        cards.setHgap(18);
        cards.setVgap(18);

        cards.add(
            createCard(
                "👨‍🌾",
                "Farmer Management",
                "Add and manage farmer information"
            ),
            0, 0
        );

        cards.add(
            createCard(
                "🌱",
                "Crop Management",
                "Manage crops and cultivation details"
            ),
            1, 0
        );

        cards.add(
            createCard(
                "🧪",
                "Fertilizer Management",
                "Manage fertilizer records"
            ),
            0, 1
        );

        cards.add(
            createCard(
                "🚜",
                "Equipment Management",
                "Manage agricultural equipment"
            ),
            1, 1
        );

        cards.add(
            createCard(
                "🛒",
                "Market Management",
                "Manage crop market information"
            ),
            2, 0
        );

        cards.add(
            createCard(
                "📊",
                "Reports",
                "View agricultural reports"
            ),
            2, 1
        );

        center.getChildren().addAll(
            welcome,
            message,
            statistics,
            quickTitle,
            cards
        );

        // ================= ROOT =================

        root.setTop(topBar);
        root.setLeft(sidebar);
        root.setCenter(center);

        // ================= SCENE =================

        Scene scene =
            new Scene(root, 1200, 750);

        stage.setScene(scene);

        stage.setTitle(
            "AgroNova - Dashboard"
        );

        stage.show();
    }

    // ================= STAT CARD =================

    private VBox createStatCard(
        String icon,
        String title,
        String value
    ) {

        VBox card = new VBox(6);

        card.setPrefSize(180, 90);

        card.setPadding(
            new Insets(12)
        );

        card.setAlignment(
            Pos.CENTER_LEFT
        );

        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 14;" +
            "-fx-border-color: #d7ead8;" +
            "-fx-border-radius: 14;" +
            "-fx-effect: dropshadow(" +
            "gaussian, rgba(0,0,0,0.10), 8, 0, 0, 3);"
        );

        Label iconLabel =
            new Label(icon);

        iconLabel.setFont(
            Font.font("Arial", 22)
        );

        Label titleLabel =
            new Label(title);

        titleLabel.setTextFill(
            Color.web("#607d64")
        );

        titleLabel.setFont(
            Font.font("Arial", 13)
        );

        Label valueLabel =
            new Label(value);

        valueLabel.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                22
            )
        );

        valueLabel.setTextFill(
            Color.web("#1b5e20")
        );

        card.getChildren().addAll(
            iconLabel,
            titleLabel,
            valueLabel
        );

        return card;
    }

    // ================= MANAGEMENT CARD =================

    private VBox createCard(
        String icon,
        String title,
        String description
    ) {

        VBox card = new VBox(8);

        card.setPrefSize(220, 125);

        card.setPadding(
            new Insets(18)
        );

        card.setAlignment(
            Pos.CENTER_LEFT
        );

        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 16;" +
            "-fx-border-color: #c8e6c9;" +
            "-fx-border-radius: 16;" +
            "-fx-border-width: 1;" +
            "-fx-effect: dropshadow(" +
            "gaussian, rgba(0,0,0,0.12), 10, 0, 0, 3);"
        );

        Label iconLabel =
            new Label(icon);

        iconLabel.setFont(
            Font.font("Arial", 27)
        );

        Label titleLabel =
            new Label(title);

        titleLabel.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                16
            )
        );

        titleLabel.setTextFill(
            Color.web("#1b5e20")
        );

        Label descriptionLabel =
            new Label(description);

        descriptionLabel.setWrapText(true);

        descriptionLabel.setFont(
            Font.font("Arial", 12)
        );

        descriptionLabel.setTextFill(
            Color.web("#607d64")
        );

        card.getChildren().addAll(
            iconLabel,
            titleLabel,
            descriptionLabel
        );

        return card;
    }

    // ================= MENU BUTTON =================

    private Button createMenuButton(
        String text
    ) {

        Button button =
            new Button(text);

        button.setPrefWidth(205);
        button.setPrefHeight(44);

        button.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: #1b5e20;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-background-radius: 10;" +
            "-fx-cursor: hand;"
        );

        return button;
    }
}
