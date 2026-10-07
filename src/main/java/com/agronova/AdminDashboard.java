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

public class AdminDashboard {

    public void show(Stage stage, String email) {

        // ================= ROOT =================

        BorderPane root = new BorderPane();

        root.setStyle(
            "-fx-background-color: #f5f9f5;"
        );

        // ================= TOP BAR =================

        HBox topBar = new HBox();

        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setSpacing(25);
        topBar.setPadding(
            new Insets(18, 25, 18, 25)
        );

        topBar.setStyle(
            "-fx-background-color: #174d35;"
        );

        Label logo = new Label("🌾 AgroNova");

        logo.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                25
            )
        );

        logo.setTextFill(Color.WHITE);

        Label title = new Label(
            "Admin Dashboard"
        );

        title.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                19
            )
        );

        title.setTextFill(Color.WHITE);

        topBar.getChildren().addAll(
            logo,
            title
        );

        // ================= LEFT MENU =================

        VBox menu = new VBox(12);

        menu.setPadding(new Insets(20));

        menu.setPrefWidth(260);

        menu.setStyle(
            "-fx-background-color: #e8f5e9;"
        );

        Label menuTitle = new Label(
            "ADMIN MENU"
        );

        menuTitle.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                14
            )
        );

        menuTitle.setTextFill(
            Color.web("#174d35")
        );

        // Buttons

        Button users = createMenuButton(
            "👥 User Management"
        );

        Button reports = createMenuButton(
            "📊 Reports"
        );

        Button farmers = createMenuButton(
            "👨‍🌾 Farmer Management"
        );

        Button crops = createMenuButton(
            "🌱 Crop Management"
        );

        Button fertilizers = createMenuButton(
            "🧪 Fertilizer Management"
        );

        Button equipment = createMenuButton(
            "🚜 Equipment Management"
        );

        Button market = createMenuButton(
            "🛒 Market Management"
        );

        Button logout = createMenuButton(
            "🚪 Logout"
        );

        // ================= BUTTON ACTIONS =================

        users.setOnAction(event -> {

            UserManagement userManagement =
                new UserManagement();

            userManagement.show(stage);
        });

        reports.setOnAction(event -> {

            Reports reportsScreen =
                new Reports();

            reportsScreen.show(stage);
        });

        farmers.setOnAction(event -> {

            FarmerManagement farmerManagement =
                new FarmerManagement();

            farmerManagement.show(stage);
        });

        crops.setOnAction(event -> {

            CropManagement cropManagement =
                new CropManagement();

            cropManagement.show(stage);
        });

        fertilizers.setOnAction(event -> {

            FertilizerManagement fertilizerManagement =
                new FertilizerManagement();

            fertilizerManagement.show(stage);
        });

        equipment.setOnAction(event -> {

            EquipmentManagement equipmentManagement =
                new EquipmentManagement();

            equipmentManagement.show(stage);
        });

        market.setOnAction(event -> {

            MarketManagement marketManagement =
                new MarketManagement();

            marketManagement.show(stage);
        });

        logout.setOnAction(event -> {

            stage.close();
        });

        // ================= MENU =================

        menu.getChildren().addAll(
            menuTitle,
            users,
            reports,
            farmers,
            crops,
            fertilizers,
            equipment,
            market,
            logout
        );

        // ================= CENTER =================

        VBox center = new VBox(20);

        center.setPadding(
            new Insets(35)
        );

        center.setAlignment(
            Pos.TOP_CENTER
        );

        Label welcome = new Label(
            "Welcome, Admin 👑"
        );

        welcome.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                32
            )
        );

        welcome.setTextFill(
            Color.web("#174d35")
        );

        Label emailLabel = new Label(
            "Logged in as: " + email
        );

        emailLabel.setFont(
            Font.font("Arial", 15)
        );

        emailLabel.setTextFill(
            Color.GRAY
        );

        Label message = new Label(
            "Manage AgroNova users and agricultural data"
        );

        message.setFont(
            Font.font(
                "Arial",
                FontWeight.NORMAL,
                17
            )
        );

        message.setTextFill(
            Color.web("#2e7d32")
        );

        // ================= OVERVIEW TITLE =================

        Label overviewTitle = new Label(
            "System Overview"
        );

        overviewTitle.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                22
            )
        );

        overviewTitle.setTextFill(
            Color.web("#174d35")
        );

        // ================= CARDS =================

        GridPane cards = new GridPane();

        cards.setHgap(20);
        cards.setVgap(20);

        cards.setAlignment(
            Pos.CENTER
        );

        VBox userCard = createCard(
            "👥",
            "Users",
            "Manage Users"
        );

        VBox farmerCard = createCard(
            "👨‍🌾",
            "Farmers",
            "Manage Farmers"
        );

        VBox cropCard = createCard(
            "🌱",
            "Crops",
            "Manage Crops"
        );

        VBox fertilizerCard = createCard(
            "🧪",
            "Fertilizers",
            "Manage Fertilizers"
        );

        VBox equipmentCard = createCard(
            "🚜",
            "Equipment",
            "Manage Equipment"
        );

        VBox marketCard = createCard(
            "🛒",
            "Market",
            "Manage Market"
        );

        cards.add(userCard, 0, 0);
        cards.add(farmerCard, 1, 0);
        cards.add(cropCard, 2, 0);

        cards.add(fertilizerCard, 0, 1);
        cards.add(equipmentCard, 1, 1);
        cards.add(marketCard, 2, 1);

        // ================= CENTER CONTENT =================

        center.getChildren().addAll(
            welcome,
            emailLabel,
            message,
            overviewTitle,
            cards
        );

        // ================= ROOT =================

        root.setTop(topBar);
        root.setLeft(menu);
        root.setCenter(center);

        // ================= SCENE =================

        Scene scene = new Scene(
            root,
            1200,
            750
        );

        stage.setScene(scene);

        stage.setTitle(
            "AgroNova - Admin Dashboard"
        );

        stage.setMaximized(true);

        stage.show();
    }

    // =====================================================
    // MENU BUTTON
    // =====================================================

    private Button createMenuButton(
        String text
    ) {

        Button button =
            new Button(text);

        button.setPrefWidth(230);
        button.setPrefHeight(45);

        button.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: #1b5e20;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );

        return button;
    }

    // =====================================================
    // DASHBOARD CARD
    // =====================================================

    private VBox createCard(
        String icon,
        String title,
        String subtitle
    ) {

        Label iconLabel =
            new Label(icon);

        iconLabel.setFont(
            Font.font("Arial", 30)
        );

        Label titleLabel =
            new Label(title);

        titleLabel.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                18
            )
        );

        titleLabel.setTextFill(
            Color.web("#174d35")
        );

        Label subtitleLabel =
            new Label(subtitle);

        subtitleLabel.setFont(
            Font.font(
                "Arial",
                13
            )
        );

        subtitleLabel.setTextFill(
            Color.GRAY
        );

        VBox card =
            new VBox(
                8,
                iconLabel,
                titleLabel,
                subtitleLabel
            );

        card.setAlignment(
            Pos.CENTER
        );

        card.setPrefWidth(220);
        card.setPrefHeight(145);

        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 14;" +
            "-fx-border-color: #c8e6c9;" +
            "-fx-border-radius: 14;" +
            "-fx-border-width: 1;" +
            "-fx-padding: 20;"
        );

        return card;
    }
}