package com.agronova;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage) {

        this.stage = stage;

        showSplashScreen();

        stage.setTitle("AgroNova - Agriculture Management System");
        stage.setWidth(1000);
        stage.setHeight(650);
        stage.setResizable(false);
        stage.show();
    }

   

// ================= SPLASH SCREEN =================

private void showSplashScreen() {

    StackPane root = new StackPane();
    root.getStyleClass().add("splash-page");

    // Decorative circles
    javafx.scene.shape.Circle circle1 =
            new javafx.scene.shape.Circle(420);
    circle1.getStyleClass().add("splash-circle-light");

    javafx.scene.shape.Circle circle2 =
            new javafx.scene.shape.Circle(280);
    circle2.getStyleClass().add("splash-circle-dark");

    // Main content
    VBox content = new VBox(12);
    content.setAlignment(Pos.CENTER);

    // Agriculture logo
    StackPane logoBox = new StackPane();

    javafx.scene.shape.Circle logoCircle =
            new javafx.scene.shape.Circle(58);

    logoCircle.getStyleClass().add("logo-circle");

    Label logo = new Label("🌾");
    logo.getStyleClass().add("splash-logo");

    logoBox.getChildren().addAll(
            logoCircle,
            logo
    );

    // Project name
    Label title = new Label("AgroNova");
    title.getStyleClass().add("splash-title");

    // Tagline
    Label tagline =
            new Label("SMART AGRICULTURE MANAGEMENT");

    tagline.getStyleClass().add("splash-tagline");

    // Description
    Label description =
            new Label("Grow Better • Farm Smarter");

    description.getStyleClass().add("splash-description");

    // Loading text
    Label loading =
            new Label("Loading your farm dashboard...");

    loading.getStyleClass().add("splash-loading");

    // Loading bar
    javafx.scene.control.ProgressBar progress =
            new javafx.scene.control.ProgressBar();

    progress.setPrefWidth(230);
    progress.setProgress(-1);
    progress.getStyleClass().add("splash-progress");

    content.getChildren().addAll(
            logoBox,
            title,
            tagline,
            description,
            loading,
            progress
    );

    root.getChildren().addAll(
            circle1,
            circle2,
            content
    );

    StackPane.setAlignment(
            content,
            Pos.CENTER
    );

    Scene scene = new Scene(root);

    scene.getStylesheets().add(
            getClass()
                    .getResource("/style.css")
                    .toExternalForm()
    );

    stage.setScene(scene);

    // Splash duration
    PauseTransition pause =
            new PauseTransition(
                    Duration.seconds(3)
            );

    pause.setOnFinished(
            event -> showWelcomeScreen()
    );

    pause.play();
}
    // ================= WELCOME SCREEN =================

    private void showWelcomeScreen() {

        StackPane root = new StackPane();

        // Background
        root.setStyle(
            "-fx-background-color: linear-gradient(to bottom right, #dff3df, #8fc98c, #397a3d);"
        );

        // Main Card
        VBox content = new VBox(18);

        content.setAlignment(Pos.CENTER);
        content.setMaxWidth(520);
        content.setPadding(new Insets(45));

        content.setStyle(
            "-fx-background-color: rgba(255,255,255,0.93);" +
            "-fx-background-radius: 30;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 25, 0, 0, 8);"
        );

        // Logo
        Label logo = new Label("🌾");

        logo.setFont(Font.font(55));

        // Title
        Label title = new Label("AgroNova");

        title.setFont(
            Font.font("Arial", FontWeight.BOLD, 44)
        );

        title.setTextFill(
            Color.web("#185c36")
        );

        // Small heading
        Label line = new Label(
            "SMART AGRICULTURE MANAGEMENT"
        );

        line.setFont(
            Font.font("Arial", FontWeight.BOLD, 14)
        );

        line.setTextFill(
            Color.web("#5c8f58")
        );

        // Welcome text
        Label welcome = new Label(
            "Grow Better. Farm Smarter."
        );

        welcome.setFont(
            Font.font("Arial", FontWeight.BOLD, 24)
        );

        welcome.setTextFill(
            Color.web("#254f32")
        );

        // Description
        Label description = new Label(
            "Manage farmers, crops, fertilizers,\n" +
            "equipment and agricultural activities easily."
        );

        description.setFont(
            Font.font("Arial", 15)
        );

        description.setTextAlignment(
            TextAlignment.CENTER
        );

        description.setTextFill(
            Color.web("#526b58")
        );

        // Get Started
        Button getStarted = new Button(
            "GET STARTED   →"
        );

        getStarted.setPrefWidth(240);
        getStarted.setPrefHeight(52);

        getStarted.setStyle(
            "-fx-background-color: #1c6b43;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 28;" +
            "-fx-cursor: hand;"
        );

        getStarted.setOnAction(
            event -> showLoginScreen()
        );

        content.getChildren().addAll(
            logo,
            title,
            line,
            welcome,
            description,
            getStarted
        );
        root.getStyleClass().add("welcome-page");

content.getStyleClass().add("welcome-card");

logo.getStyleClass().add("agro-logo");

title.getStyleClass().add("agro-title");

line.getStyleClass().add("agro-subtitle");

welcome.getStyleClass().add("agro-tagline");

description.getStyleClass().add("agro-description");

getStarted.getStyleClass().add("get-started-button");
        root.getChildren().add(content);
       StackPane.setAlignment(
    content,
    Pos.CENTER
);

        StackPane.setAlignment(
            content,
            Pos.CENTER
        );

        Scene scene = new Scene(root);
        scene.getStylesheets().add(
    getClass().getResource("/style.css").toExternalForm()
);

        stage.setScene(scene);
    }

    // ================= LOGIN SCREEN =================

    private void showLoginScreen() {

        StackPane root = new StackPane();

        root.setStyle(
            "-fx-background-color: linear-gradient(to bottom right, #0b3d2e, #1b6b4a, #8fbc8f);"
        );

        VBox card = new VBox(18);

        card.setAlignment(Pos.CENTER);
        card.setPrefWidth(400);
        card.setMaxWidth(400);
        card.setPadding(new Insets(40));

        card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.96);" +
            "-fx-background-radius: 25;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.30), 25, 0, 0, 8);"
        );

        Label logo = new Label("🌾");
        logo.setFont(Font.font(50));

        Label title = new Label("Welcome Back!");

        title.setFont(
            Font.font("Arial", FontWeight.BOLD, 30)
        );

        title.setTextFill(
            Color.web("#174d35")
        );

        Label subtitle = new Label(
            "Login to your AgroNova account"
        );

        subtitle.setFont(
            Font.font("Arial", 14)
        );

        subtitle.setTextFill(Color.GRAY);

        TextField username = new TextField();

        username.setPromptText("Username");
        username.setPrefHeight(45);

        PasswordField password =
            new PasswordField();

        password.setPromptText("Password");
        password.setPrefHeight(45);

        Button login = new Button("LOGIN  →");

        login.setPrefWidth(300);
        login.setPrefHeight(48);

        login.setStyle(
            "-fx-background-color: #176b45;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 12;" +
            "-fx-cursor: hand;"
        );

        Label newUser = new Label(
            "Don't have an account?"
        );

        newUser.setTextFill(Color.GRAY);

        Button register = new Button(
    "Create Account"
);

register.setOnAction(e -> {
    CreateAccount createAccount = new CreateAccount();
    createAccount.start(new Stage());
});
        register.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #176b45;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );

        HBox registerBox = new HBox(5);

        registerBox.setAlignment(Pos.CENTER);

        registerBox.getChildren().addAll(
            newUser,
            register
        );

        login.setOnAction(event -> {

            if (!username.getText().isEmpty()
                    && !password.getText().isEmpty()) {

                showDashboard();

            } else {

                subtitle.setText(
                    "Please enter username and password"
                );

                subtitle.setTextFill(Color.RED);
            }
        });

        card.getChildren().addAll(
            logo,
            title,
            subtitle,
            username,
            password,
            login,
            registerBox
        );

        root.getChildren().add(card);

        Scene scene = new Scene(root);

        stage.setScene(scene);
    }

    // ================= DASHBOARD =================

    private void showDashboard() {

        BorderPane root = new BorderPane();

        root.setStyle(
            "-fx-background-color: #f5f9f5;"
        );

        // Top Bar
        HBox topBar = new HBox();

        topBar.setAlignment(
            Pos.CENTER_LEFT
        );

        topBar.setSpacing(20);

        topBar.setStyle(
            "-fx-background-color: #1b5e20;" +
            "-fx-padding: 18;"
        );

        Label logo = new Label(
            "🌾 AgroNova"
        );

        logo.setFont(
            Font.font("Arial", 25)
        );

        logo.setTextFill(Color.WHITE);

        Label dashboardTitle =
            new Label(
                "Agriculture Management Dashboard"
            );

        dashboardTitle.setFont(
            Font.font("Arial", 18)
        );

        dashboardTitle.setTextFill(
            Color.WHITE
        );

        topBar.getChildren().addAll(
            logo,
            dashboardTitle
        );

        // Left Menu
        VBox menu = new VBox(10);

        menu.setStyle(
            "-fx-background-color: #e8f5e9;" +
            "-fx-padding: 20;"
        );

        Button farmer =
            createMenuButton(
                "👨‍🌾 Farmer Management"
            );

        Button crop =
            createMenuButton(
                "🌱 Crop Management"
            );

        Button fertilizer =
            createMenuButton(
                "🧪 Fertilizer Management"
            );

        Button equipment =
            createMenuButton(
                "🚜 Equipment Management"
            );

        Button market =
            createMenuButton(
                "🛒 Market Management"
            );

        Button reports =
            createMenuButton(
                "📊 Reports"
            );

        Button profile =
            createMenuButton(
                "👤 Profile / Settings"
            );

        Button logout =
            createMenuButton(
                "🚪 Logout"
            );

        logout.setOnAction(
            event -> showWelcomeScreen()
        );

        menu.getChildren().addAll(
            farmer,
            crop,
            fertilizer,
            equipment,
            market,
            reports,
            profile,
            logout
        );

        // Center
        VBox center = new VBox(20);

        center.setAlignment(Pos.CENTER);

        Label welcome =
            new Label(
                "Welcome to AgroNova 🌾"
            );

        welcome.setFont(
            Font.font("Arial", 32)
        );

        welcome.setTextFill(
            Color.web("#1b5e20")
        );

        Label message =
            new Label(
                "Manage your agricultural activities easily"
            );

        message.setFont(
            Font.font("Arial", 18)
        );

        message.setTextFill(
            Color.DARKGREEN
        );

        center.getChildren().addAll(
            welcome,
            message
        );

        root.setTop(topBar);
        root.setLeft(menu);
        root.setCenter(center);

        Scene scene = new Scene(root);

        stage.setScene(scene);
    }

    // ================= MENU BUTTON =================

    private Button createMenuButton(
        String text
    ) {

        Button button =
            new Button(text);

        button.setPrefWidth(220);
        button.setPrefHeight(45);

        button.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: #1b5e20;" +
            "-fx-font-size: 14px;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-background-radius: 8;"
        );

        return button;
    }

    // ================= MAIN =================

    public static void main(String[] args) {

        launch(args);
    }
}