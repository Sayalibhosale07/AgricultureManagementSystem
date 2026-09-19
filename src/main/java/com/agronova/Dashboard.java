package com.agronova;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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

    root.getStyleClass().add("dashboard");

    // ================= TOP BAR =================

    HBox topBar = new HBox();

    topBar.getStyleClass().add("top-bar");

    topBar.setAlignment(Pos.CENTER_LEFT);
    topBar.setSpacing(18);
    topBar.setPadding(
        new Insets(14, 25, 14, 25)
    );

    Label logo = new Label("🌿");

    logo.setFont(
        Font.font("Arial", 28)
    );

    Label brand = new Label("AgroNova");

    brand.setFont(
        Font.font(
            "Arial",
            FontWeight.BOLD,
            24
        )
    );

    brand.setTextFill(Color.WHITE);

    Label subtitle = new Label(
        "Agriculture Management System"
    );

    subtitle.setFont(
        Font.font("Arial", 13)
    );

    subtitle.setTextFill(
        Color.web("#d8f3dc")
    );

    VBox brandBox = new VBox(2);

    brandBox.getChildren().addAll(
        brand,
        subtitle
    );

    Region topSpacer = new Region();

    HBox.setHgrow(
        topSpacer,
        Priority.ALWAYS
    );

    Label userIcon = new Label("👤");

    userIcon.setFont(
        Font.font("Arial", 22)
    );

    Label userText = new Label("Admin");

    userText.setFont(
        Font.font(
            "Arial",
            FontWeight.BOLD,
            14
        )
    );

    userText.setTextFill(Color.WHITE);

    HBox userBox = new HBox(8);

    userBox.setAlignment(
        Pos.CENTER
    );

    userBox.getChildren().addAll(
        userIcon,
        userText
    );

    topBar.getChildren().addAll(
        logo,
        brandBox,
        topSpacer,
        userBox
    );

    // ================= SIDEBAR =================

    VBox sidebar = new VBox(10);

    sidebar.getStyleClass().add("sidebar");

    sidebar.setPadding(
        new Insets(25, 15, 20, 15)
    );

    sidebar.setPrefWidth(235);

    Label menuTitle = new Label(
        "MAIN MENU" );
        menuTitle.getStyleClass().add("menu-title");

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

    // ================= MENU BUTTONS =================

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

        CropManagement cropManagement =
            new CropManagement();

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

        Reports reportsScreen =
            new Reports();

        reportsScreen.show(stage);
    });


    Button profile =
        createMenuButton("👤  Profile / Settings");

    profile.setOnAction(event -> {

        ProfileSettings profileSettings =
            new ProfileSettings();

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

    center.getStyleClass().add(
        "dashboard-center"
    );
    ScrollPane scrollPane = new ScrollPane();

scrollPane.setContent(center);
scrollPane.setFitToWidth(true);
scrollPane.setHbarPolicy(
    ScrollPane.ScrollBarPolicy.NEVER
);
scrollPane.setVbarPolicy(
    ScrollPane.ScrollBarPolicy.AS_NEEDED
);

scrollPane.getStyleClass().add(
    "dashboard-scroll"
);

    center.setPadding(
        new Insets(30, 35, 30, 35)
    );

    // ================= WELCOME =================

    Label welcome = new Label(
        "Welcome to AgroNova 🌾"
    );

    welcome.getStyleClass().add(
        "dashboard-title"
    );

    Label message = new Label(
        "Manage your agricultural activities from one place."
    );

    message.getStyleClass().add(
        "dashboard-subtitle"
    );

    VBox welcomeBox = new VBox(6);
    welcomeBox.getStyleClass().add("dashboard-welcome");

    welcomeBox.getChildren().addAll(
        welcome,
        message
    );

    // ================= OVERVIEW =================

    Label overviewTitle =
        new Label("Dashboard Overview");

    overviewTitle.getStyleClass().add(
        "section-title"
    );

    // ================= STATISTICS =================

    HBox statistics = new HBox(18);

    statistics.setAlignment(
        Pos.CENTER_LEFT
    );

    statistics.getChildren().addAll(

        createStatCard(
            "👨‍🌾",
            "Farmers",
            "25"
        ),

        createStatCard(
            "🌱",
            "Crops",
            "18"
        ),

        createStatCard(
            "🚜",
            "Equipment",
            "12"
        ),

        createStatCard(
            "🛒",
            "Market Items",
            "20"
        )
    );

    // ================= QUICK MANAGEMENT =================

    Label quickTitle =
        new Label("Quick Management");

    quickTitle.getStyleClass().add(
        "section-title"
    );

    GridPane cards = new GridPane();

    cards.setHgap(18);
    cards.setVgap(18);

    VBox farmerCard = createCard("👨‍🌾",
    "Farmer Management",
    "Add and manage farmer information"
);

farmerCard.setOnMouseClicked(event -> {

    FarmerManagement farmerManagement =
        new FarmerManagement();

    farmerManagement.show(stage);});

cards.add(
    farmerCard,
    0, 0);
    VBox cropCard = createCard(
    "🌱",
    "Crop Management",
    "Manage crops and cultivation details"
);

cropCard.setOnMouseClicked(event -> {

    CropManagement cropManagement =
        new CropManagement();

    cropManagement.show(stage);
});

cards.add(
    cropCard,
    1, 0
);

   VBox fertilizerCard = createCard(
    "🧪",
    "Fertilizer Management",
    "Manage fertilizer records"
);

fertilizerCard.setOnMouseClicked(event -> {

    FertilizerManagement fertilizerManagement =
        new FertilizerManagement();

    fertilizerManagement.show(stage);
});

cards.add(
    fertilizerCard,
    0, 1
);

   VBox equipmentCard = createCard(
    "🚜",
    "Equipment Management",
    "Manage agricultural equipment"
);

equipmentCard.setOnMouseClicked(event -> {

    EquipmentManagement equipmentManagement =
        new EquipmentManagement();

    equipmentManagement.show(stage);
});

cards.add(
    equipmentCard,
    1, 1
);

   VBox marketCard = createCard(
    "🛒",
    "Market Management",
    "Manage crop market information"
);

marketCard.setOnMouseClicked(event -> {

    MarketManagement marketManagement =
        new MarketManagement();

    marketManagement.show(stage);
});

cards.add(
    marketCard,
    2, 0
);

    VBox reportsCard = createCard(
    "📊",
    "Reports",
    "View agricultural reports"
);

reportsCard.setOnMouseClicked(event -> {

    Reports reportsScreen =
        new Reports();

    reportsScreen.show(stage);
});

cards.add(
    reportsCard,
    2, 1
);
    center.getChildren().addAll(
        welcomeBox,
        overviewTitle,
        statistics,
        quickTitle,
        cards
    );

    // ================= ROOT =================

    root.setTop(topBar);
    root.setLeft(sidebar);
    root.setCenter(scrollPane);

    // ================= SCENE =================
Scene scene =
    new Scene(root, 1200, 750);

scene.getStylesheets().add(
    getClass().getResource("/style.css").toExternalForm()
);

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

    VBox card = new VBox(5);

    card.getStyleClass().add(
        "stat-card"
    );

    card.setPrefSize(
        185,
        105
    );

    card.setAlignment(
        Pos.CENTER_LEFT
    );

    Label iconLabel =
        new Label(icon);
        iconLabel.getStyleClass().add("stat-icon");
        

    iconLabel.setFont(
        Font.font(
            "Arial",
            23
        )
    );

    Label titleLabel =
        new Label(title);

    titleLabel.setFont(
        Font.font(
            "Arial",
            13
        )
    );

    titleLabel.setTextFill(
        Color.web("#607d64")
    );

    Label valueLabel =
        new Label(value);

    valueLabel.setFont(
        Font.font(
            "Arial",
            FontWeight.BOLD,
            23
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

    card.getStyleClass().add(
        "management-card"
    );

    card.setPrefSize(
        225,
        130
    );

    Label iconLabel =
        new Label(icon);
        iconLabel.getStyleClass().add("management-icon");

    iconLabel.setFont(
        Font.font(
            "Arial",
            28
        )
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
        Font.font(
            "Arial",
            12
        )
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

private Button createMenuButton(String text) {

    Button button = new Button(text);

    button.getStyleClass().add("menu-button");

    button.setPrefWidth(205);
    button.setPrefHeight(44);

    return button;
}
}