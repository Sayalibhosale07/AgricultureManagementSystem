package com.agronova;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.CompletableFuture;

public class UserManagement {

    private final ObservableList<UserData> userList =
            FXCollections.observableArrayList();

    private TableView<UserData> table;

    public void show(Stage stage) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: #f5f9f5;"
        );

        // ================= TOP BAR =================

        HBox topBar = new HBox(20);

        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(
                new Insets(18, 25, 18, 25)
        );

        topBar.setStyle(
                "-fx-background-color: #174d35;"
        );

        Label title = new Label(
                "👥 AgroNova - User Management"
        );

        title.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;"
        );

        topBar.getChildren().add(title);

        // ================= TABLE =================

        table = new TableView<>();

        table.setItems(userList);

        TableColumn<UserData, String> emailColumn =
                new TableColumn<>("Email");

        emailColumn.setCellValueFactory(
                data -> data.getValue().emailProperty()
        );

        TableColumn<UserData, String> roleColumn =
                new TableColumn<>("Role");

        roleColumn.setCellValueFactory(
                data -> data.getValue().roleProperty()
        );

        TableColumn<UserData, Void> actionColumn =
                new TableColumn<>("Action");

        actionColumn.setCellFactory(column ->

                new TableCell<>() {

                    private final Button deleteButton =
                            new Button("🗑 Delete");

                    {
                        deleteButton.setStyle(
                                "-fx-background-color: #c62828;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 6;" +
                                "-fx-cursor: hand;"
                        );

                        deleteButton.setOnAction(event -> {

                            UserData user =
                                    getTableView()
                                            .getItems()
                                            .get(getIndex());

                            deleteUser(
                                    user.getEmail()
                            );
                        });
                    }

                    @Override
                    protected void updateItem(
                            Void item,
                            boolean empty
                    ) {

                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteButton);
                        }
                    }
                }
        );

        emailColumn.setPrefWidth(400);
        roleColumn.setPrefWidth(200);
        actionColumn.setPrefWidth(180);

        table.getColumns().addAll(
                emailColumn,
                roleColumn,
                actionColumn
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        // ================= BUTTONS =================

        Button refreshButton =
                new Button("🔄 Refresh");

        refreshButton.setStyle(
                "-fx-background-color: #2e7d32;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7;" +
                "-fx-padding: 10 20;" +
                "-fx-cursor: hand;"
        );

        refreshButton.setOnAction(
                event -> loadUsers()
        );

        Button backButton =
                new Button("← Back to Admin Dashboard");

        backButton.setStyle(
                "-fx-background-color: #455a64;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7;" +
                "-fx-padding: 10 20;" +
                "-fx-cursor: hand;"
        );

        backButton.setOnAction(event -> {

            AdminDashboard adminDashboard =
                    new AdminDashboard();

            adminDashboard.show(
                    stage,
                    ""
            );
        });

        HBox buttons = new HBox(
                15,
                refreshButton,
                backButton
        );

        buttons.setAlignment(
                Pos.CENTER_RIGHT
        );

        // ================= CONTENT =================

        VBox content = new VBox(20);

        content.setPadding(
                new Insets(30)
        );

        Label heading = new Label(
                "Registered Users"
        );

        heading.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #174d35;"
        );

        Label description = new Label(
                "View and manage users registered in AgroNova."
        );

        description.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #666666;"
        );

        content.getChildren().addAll(
                heading,
                description,
                table,
                buttons
        );

        root.setTop(topBar);
        root.setCenter(content);

        // ================= SCENE =================

        Scene scene = new Scene(
                root,
                1100,
                700
        );

        stage.setScene(scene);

        stage.setTitle(
                "AgroNova - User Management"
        );

        stage.setMaximized(true);

        stage.show();

        // Load Firestore users
        loadUsers();
    }

    // =====================================================
    // LOAD USERS FROM FIRESTORE
    // =====================================================

    private void loadUsers() {

        userList.clear();

        CompletableFuture
                .runAsync(() -> {

                    try {

                        QuerySnapshot snapshot =
                                FirebaseConfig
                                        .getFirestore()
                                        .collection("users")
                                        .get()
                                        .get();

                        for (
                                DocumentSnapshot document :
                                snapshot.getDocuments()
                        ) {

                            String email =
                                    document.getString("email");

                            String role =
                                    document.getString("role");

                            if (email == null) {
                                email =
                                        document.getId();
                            }

                            if (role == null) {
                                role = "USER";
                            }

                            String finalEmail = email;
                            String finalRole = role;

                            Platform.runLater(() ->

                                    userList.add(
                                            new UserData(
                                                    finalEmail,
                                                    finalRole
                                            )
                                    )
                            );
                        }

                    } catch (Exception e) {

                        e.printStackTrace();

                        Platform.runLater(() ->

                                showAlert(
                                        "Error",
                                        "Unable to load users from Firestore."
                                )
                        );
                    }
                });
    }

    // =====================================================
    // DELETE USER
    // =====================================================

    private void deleteUser(String email) {

        Alert confirmation =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmation.setTitle(
                "Delete User"
        );

        confirmation.setHeaderText(
                "Delete this user?"
        );

        confirmation.setContentText(
                email
        );

        confirmation.showAndWait()
                .ifPresent(response -> {

                    if (
                            response ==
                            ButtonType.OK
                    ) {

                        CompletableFuture
                                .runAsync(() -> {

                                    try {

                                        FirebaseConfig
                                                .getFirestore()
                                                .collection("users")
                                                .document(email)
                                                .delete()
                                                .get();

                                        Platform.runLater(() -> {

                                            userList.removeIf(
                                                    user ->
                                                            user.getEmail()
                                                                    .equals(email)
                                            );

                                            showAlert(
                                                    "Success",
                                                    "User deleted successfully."
                                            );
                                        });

                                    } catch (Exception e) {

                                        e.printStackTrace();

                                        Platform.runLater(() ->

                                                showAlert(
                                                        "Error",
                                                        "Unable to delete user."
                                                )
                                        );
                                    }
                                });
                    }
                });
    }

    // =====================================================
    // ALERT
    // =====================================================

    private void showAlert(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    // =====================================================
    // USER DATA CLASS
    // =====================================================

    public static class UserData {

        private final javafx.beans.property.SimpleStringProperty email;

        private final javafx.beans.property.SimpleStringProperty role;

        public UserData(
                String email,
                String role
        ) {

            this.email =
                    new javafx.beans.property.SimpleStringProperty(
                            email
                    );

            this.role =
                    new javafx.beans.property.SimpleStringProperty(
                            role
                    );
        }

        public String getEmail() {
            return email.get();
        }

        public String getRole() {
            return role.get();
        }

        public javafx.beans.property.SimpleStringProperty
        emailProperty() {
            return email;
        }

        public javafx.beans.property.SimpleStringProperty
        roleProperty() {
            return role;
        }
    }
}