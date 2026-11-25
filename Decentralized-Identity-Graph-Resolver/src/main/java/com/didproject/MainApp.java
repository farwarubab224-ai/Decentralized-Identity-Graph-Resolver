
package com.didproject;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        // Main background
        Database.initialize();
        StackPane root = new StackPane();

        root.setBackground(new Background(
            new BackgroundFill(
                new LinearGradient(
                    0, 0, 1, 1, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, Color.web("#080F20")),
                    new Stop(1, Color.web("#171332"))
                ),
                CornerRadii.EMPTY,
                null
            )
        ));

        // Decorative identity network
        Pane network = new Pane();
        network.setMouseTransparent(true);

        double[][] points = {
            {100, 120}, {250, 200}, {420, 100},
            {600, 190}, {760, 100}, {850, 300},
            {650, 430}, {400, 350}, {180, 450}
        };

        int[][] connections = {
            {0, 1}, {1, 2}, {2, 3}, {3, 4},
            {4, 5}, {5, 6}, {6, 7}, {7, 8},
            {8, 0}, {1, 7}, {2, 7}, {3, 6}
        };

        for (int[] connection : connections) {
            int a = connection[0];
            int b = connection[1];

            Line line = new Line(
                points[a][0], points[a][1],
                points[b][0], points[b][1]
            );

            line.setStroke(Color.web("#5865F2", 0.35));
            line.setStrokeWidth(1.3);

            network.getChildren().add(line);
        }

        for (double[] point : points) {

            Circle glow = new Circle(
                point[0], point[1], 13,
                Color.web("#8B5CF6", 0.12)
            );

            Circle node = new Circle(
                point[0], point[1], 5,
                Color.web("#38BDF8")
            );

            node.setEffect(new DropShadow(
                15, Color.web("#38BDF8")
            ));

            network.getChildren().addAll(glow, node);
        }

        // Main content
        VBox content = new VBox(22);
        content.setAlignment(Pos.CENTER);
        content.setMaxWidth(700);

        Label badge = new Label("✦  DECENTRALIZED TECHNOLOGY");
        badge.setStyle(
            "-fx-text-fill: #38BDF8;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-color: #172B45;" +
            "-fx-background-radius: 20;" +
            "-fx-padding: 10 20 10 20;"
        );

        Label title = new Label(
            "Decentralized\nIdentity Graph Resolver"
        );

        title.setFont(Font.font(
            "Arial", FontWeight.BOLD, 42
        ));

        title.setTextFill(Color.WHITE);
        title.setAlignment(Pos.CENTER);
        title.setTextAlignment(
            javafx.scene.text.TextAlignment.CENTER
        );

        Label subtitle = new Label(
            "Connect. Discover. Verify."
        );

        subtitle.setFont(Font.font("Arial", 20));
        subtitle.setTextFill(Color.web("#38BDF8"));

        Label description = new Label(
            "Explore the connections between digital identities.\n" +
            "Discover relationships through an intelligent identity graph."
        );

        description.setFont(Font.font("Arial", 15));
        description.setTextFill(Color.web("#B7C2D8"));
        description.setAlignment(Pos.CENTER);
        description.setTextAlignment(
            javafx.scene.text.TextAlignment.CENTER
        );

        // Get Started button
        Button startButton = new Button("Get Started  →");

        startButton.setFont(Font.font(
            "Arial", FontWeight.BOLD, 16
        ));

        startButton.setTextFill(Color.WHITE);

        startButton.setStyle(
            "-fx-background-color: linear-gradient(to right, #6D5DFB, #2687E8);" +
            "-fx-background-radius: 30;" +
            "-fx-padding: 15 38 15 38;" +
            "-fx-cursor: hand;"
        );

        startButton.setEffect(new DropShadow(
            18, Color.web("#655BFF", 0.5)
        ));

       startButton.setOnAction(event -> {
            showRegistration(stage);
      });

        Label footer = new Label(
            "SECURE  •  CONNECTED  •  DECENTRALIZED"
        );

        footer.setTextFill(Color.web("#7786A5"));
        footer.setFont(Font.font("Arial", 11));

        content.getChildren().addAll(
            badge, title, subtitle,
            description, startButton, footer
        );

        root.getChildren().addAll(network, content);

        Scene scene = new Scene(root, 1000, 650);

        stage.setTitle("Decentralized Identity Graph Resolver");
        stage.setMinWidth(800);
        stage.setMinHeight(550);
        stage.setScene(scene);
        stage.show();
    }
     
private void showRegistration(Stage stage) {

    // Main layout
    VBox form = new VBox(15);
    form.setAlignment(Pos.CENTER);
    form.setMaxWidth(430);
    form.setPadding(new javafx.geometry.Insets(30));

    Label heading = new Label("Create Your Identity");
    heading.setFont(Font.font("Arial", FontWeight.BOLD, 30));
    heading.setTextFill(Color.WHITE);

    Label subtitle = new Label(
        "Register your digital identities"
    );
    subtitle.setTextFill(Color.web("#38BDF8"));
    subtitle.setFont(Font.font("Arial", 15));

    // Input fields
    TextField nameField = new TextField();
    nameField.setPromptText("Enter your full name");

    TextField emailField = new TextField();
    emailField.setPromptText("Enter your email address");

    TextField githubField = new TextField();
    githubField.setPromptText("Enter GitHub username");

    TextField walletField = new TextField();
    walletField.setPromptText("Enter wallet address");

    // Common input styling
    TextField[] fields = {
        nameField, emailField, githubField, walletField
    };

    for (TextField field : fields) {
        field.setPrefHeight(43);
        field.setStyle(
            "-fx-background-color: #1D2940;" +
            "-fx-text-fill: white;" +
            "-fx-prompt-text-fill: #8997B0;" +
            "-fx-background-radius: 8;" +
            "-fx-border-color: #344568;" +
            "-fx-border-radius: 8;" +
            "-fx-font-size: 14px;" +
            "-fx-padding: 10;"
        );
    }

    // Register button
    Button registerButton = new Button("Register Identity");

    registerButton.setPrefWidth(430);
    registerButton.setPrefHeight(45);

    registerButton.setStyle(
        "-fx-background-color: linear-gradient(to right, #6D5DFB, #2687E8);" +
        "-fx-text-fill: white;" +
        "-fx-font-size: 16px;" +
        "-fx-font-weight: bold;" +
        "-fx-background-radius: 10;" +
        "-fx-cursor: hand;"
    );

    // Registration validation
    registerButton.setOnAction(event -> {

        String name = nameField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty() || email.isEmpty()) {

            showMessage(
                "Missing Information",
                "Please enter your name and email."
            );

        } else if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {

            showMessage(
                "Invalid Email",
                "Please enter a valid email address."
            );

        } 
         else {

    boolean saved = Database.saveUser(
        name,
        email,
        githubField.getText().trim(),
        walletField.getText().trim()
    );

    if (saved)if (saved) {
    showDashboard(stage);
} else {
    showMessage(
        "Already Registered",
        "Welcome back! Opening Dashboard..."
    );
    showDashboard(stage);
}
}
    });

    // Back button
    Button backButton = new Button("← Back to Home");

    backButton.setStyle(
        "-fx-background-color: transparent;" +
        "-fx-text-fill: #38BDF8;" +
        "-fx-font-size: 14px;" +
        "-fx-cursor: hand;"
    );

    backButton.setOnAction(event -> start(stage));

    form.getChildren().addAll(
        heading,
        subtitle,
        nameField,
        emailField,
        githubField,
        walletField,
        registerButton,
        backButton
    );

    StackPane root = new StackPane(form);

    root.setBackground(new Background(
        new BackgroundFill(
            Color.web("#10182B"),
            CornerRadii.EMPTY,
            null
        )
    ));

    Scene scene = new Scene(root, 1000, 650);

    stage.setTitle("Register Your Identity");
    stage.setScene(scene);
    stage.show();
}

// Reusable message dialog
private void showMessage(String title, String message) {

    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);

    alert.showAndWait();
}


private void showDashboard(Stage stage) {

    Label logo = new Label("DID RESOLVER");
    logo.setStyle("-fx-text-fill: #8c7cff; -fx-font-size: 22px; -fx-font-weight: bold;");

    Label menuTitle = new Label("MAIN MENU");
    menuTitle.setStyle("-fx-text-fill: #7f8ba9; -fx-font-size: 11px;");

    VBox sidebar = new VBox(22);
    sidebar.setPadding(new Insets(30, 20, 30, 20));
    sidebar.setPrefWidth(220);
    sidebar.setStyle("-fx-background-color: #11182e;");

    sidebar.getChildren().addAll(
        logo,
        menuTitle,
        sideItem("▦   Dashboard"),
        sideItem("◈   Identity Management"),
        sideItem("◎   Global Graph"),
        sideItem("⌕   Search Identity"),
        sideItem("⇄   Shortest Path"),
        sideItem("⟳   Cycle Detection"),
        sideItem("✓   Verify Identity"),
        sideItem("⚙   Settings")
    );

    Label heading = new Label("Dashboard Overview");
    heading.setStyle("-fx-text-fill: white; -fx-font-size: 27px; -fx-font-weight: bold;");

    Label welcome = new Label("Welcome to your Decentralized Identity Network");
    welcome.setStyle("-fx-text-fill: #a5b1cc; -fx-font-size: 13px;");

    VBox header = new VBox(8, heading, welcome);

    HBox stats = new HBox(15,
        statCard("Total Users", "00", "#7968e8"),
        statCard("Identity Nodes", "00", "#3299d8"),
        statCard("Connections", "00", "#21bca6"),
        statCard("Graph Status", "Ready", "#d69c43")
    );

    Label featuresTitle = new Label("Identity & Graph Operations");
    featuresTitle.setStyle("-fx-text-fill: white; -fx-font-size: 21px; -fx-font-weight: bold;");

    GridPane features = new GridPane();
    features.setHgap(18);
    features.setVgap(18);

    features.add(featureCard("◈", "Generate DID",
        "Create a unique decentralized identity."), 0, 0);

    features.add(featureCard("＋", "Add Identity",
        "Connect Email, GitHub and Wallet identities."), 1, 0);

    features.add(featureCard("◎", "Global Identity Graph",
        "Explore connections between all users."), 2, 0);

    features.add(featureCard("⌕", "Search User",
        "Find a user through their identity details."), 0, 1);

    features.add(featureCard("⇄", "Shortest Path",
        "Find the shortest connection between identities."), 1, 1);

    features.add(featureCard("⟳", "Cycle Detection",
        "Detect loops and cycles in the identity graph."), 2, 1);

    features.add(featureCard("✓", "Verify Identity",
        "Check and verify identity information."), 0, 2);

    features.add(featureCard("◉", "My Identity Graph",
        "View an individual user's identity network."), 1, 2);

    features.add(featureCard("⚙", "Manage Profile",
        "View and update your profile information."), 2, 2);

    Label activityTitle = new Label("Recent Activity");
    activityTitle.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");

    Label activity = new Label(
        "• Dashboard opened\n" +
        "• Identity network is ready\n" +
        "• No recent graph operations"
    );
    activity.setStyle("-fx-text-fill: #aab6d0; -fx-font-size: 13px;");

    VBox activityBox = new VBox(12, activityTitle, activity);
    activityBox.setPadding(new Insets(20));
    activityBox.setStyle(
        "-fx-background-color: #192342;" +
        "-fx-background-radius: 12;"
    );

    VBox content = new VBox(
        25, header, stats, featuresTitle, features, activityBox
    );
    content.setPadding(new Insets(30));
    content.setStyle("-fx-background-color: #0b1020;");

    ScrollPane scroll = new ScrollPane(content);
    scroll.setFitToWidth(true);
    scroll.setStyle("-fx-background: #0b1020; -fx-background-color: #0b1020;");

    BorderPane root = new BorderPane();
    root.setLeft(sidebar);
    root.setCenter(scroll);
    root.setStyle("-fx-background-color: #0b1020;");

    Scene scene = new Scene(root, 1150, 750);

    stage.setTitle("DID Resolver - Dashboard");
    stage.setScene(scene);
    stage.show();
}

private Label sideItem(String name) {
    Label item = new Label(name);
    item.setStyle(
        "-fx-text-fill: #d4dcf5;" +
        "-fx-font-size: 14px;" +
        "-fx-padding: 8px;" +
        "-fx-cursor: hand;"
    );
    return item;
}

private VBox statCard(String name, String value, String color) {

    Label number = new Label(value);
    number.setStyle(
        "-fx-text-fill: " + color + ";" +
        "-fx-font-size: 27px;" +
        "-fx-font-weight: bold;"
    );

    Label title = new Label(name);
    title.setStyle("-fx-text-fill: #c0cbe2; -fx-font-size: 13px;");

    VBox card = new VBox(12, number, title);
    card.setPadding(new Insets(20));
    card.setPrefWidth(200);
    card.setPrefHeight(105);
    card.setStyle(
        "-fx-background-color: #192342;" +
        "-fx-background-radius: 12;"
    );

    return card;
}

private VBox featureCard(String icon, String title, String description) {

    Label symbol = new Label(icon);
    symbol.setStyle(
        "-fx-text-fill: #9c8cff;" +
        "-fx-font-size: 27px;" +
        "-fx-font-weight: bold;"
    );

    Label name = new Label(title);
    name.setStyle(
        "-fx-text-fill: white;" +
        "-fx-font-size: 16px;" +
        "-fx-font-weight: bold;"
    );

    Label detail = new Label(description);
    detail.setWrapText(true);
    detail.setStyle(
        "-fx-text-fill: #aab6d0;" +
        "-fx-font-size: 12px;"
    );

    VBox card = new VBox(12, symbol, name, detail);
    card.setPadding(new Insets(20));
    card.setPrefSize(220, 145);
    card.setStyle(
        "-fx-background-color: #192342;" +
        "-fx-background-radius: 12;" +
        "-fx-border-color: #303b61;" +
        "-fx-border-radius: 12;"
    );

    return card;
}



    public static void main(String[] args) {
        launch(args);
    }
}