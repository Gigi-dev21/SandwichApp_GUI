package org.example.sanwichapp_gui.Screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.sanwichapp_gui.Classes.Inventory;
import org.example.sanwichapp_gui.Classes.Order;
import org.example.sanwichapp_gui.Components.SandwichUI;
import org.example.sanwichapp_gui.StageManager;


public class HomePage {
    Stage stage = StageManager.getStage();
    Order order= StageManager.getOrder();
    private String userName=order.getCustomerName();


    // Container for sandwich customizer UI
    private VBox customizerContainer = new VBox(10);



    public void show() {
        //Welcome Sign///
        Label welcomeLabel = new Label("Welcome, " + userName + "!");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: green;");
        welcomeLabel.setMaxWidth(Double.MAX_VALUE);
        welcomeLabel.setAlignment(Pos.CENTER);
        order.setCustomerName(userName);

        //Cart Button//
        Button checkoutBtn = new Button("🛒 " + order.getTotalItemCount());
        checkoutBtn.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-background-color: transparent;");
        checkoutBtn.setDisable(order.isEmpty());


        //Creating layout container for the  addsanwich button etcc//
        HBox topBar = new HBox(10, welcomeLabel, checkoutBtn);
        topBar.setPadding(new Insets(10));
        topBar.setAlignment(Pos.CENTER);
        HBox.setHgrow(welcomeLabel, Priority.ALWAYS);

        FlowPane buttonsPane = new FlowPane();
        buttonsPane.setHgap(20);
        buttonsPane.setVgap(20);
        buttonsPane.setPadding(new Insets(20));
        buttonsPane.setAlignment(Pos.CENTER);


        Button addSandwichBtn = createImageButton("Add Sandwich", "/images/sandwich1.jpeg");
        Button addDrinksBtn = createImageButton("Add Drinks", "/images/Drinks.jpeg");
        Button addChipsBtn = createImageButton("Add Chips", "/images/chips.jpeg");
        Button signatureBtn = createImageButton("Signature Sandwiches", "/images/Sign.jpeg");

        buttonsPane.getChildren().addAll(addSandwichBtn, addDrinksBtn, addChipsBtn, signatureBtn);
        /// ///////////////

        addSandwichBtn.setOnAction(e -> {
            customizerContainer.getChildren().clear();
            SandwichUI customizer = new SandwichUI();
            customizer.render(customizerContainer, checkoutBtn);
            customizerContainer.setVisible(true);
            customizerContainer.setManaged(true);
        });

//        addDrinksBtn.setOnAction(e -> {
//            customizerContainer.getChildren().clear();
//            DrinkUI drinkUI = new DrinkUI();
//            Inventory inventory = Inventory.loadFromFile("inventory.json");
//            drinkUI.render(customizerContainer, inventory, order, checkoutBtn);
//            customizerContainer.setVisible(true);
//            customizerContainer.setManaged(true);
//        });
//
//        addChipsBtn.setOnAction(e -> {
//            customizerContainer.getChildren().clear();
//            ChipsUI chipsUI = new ChipsUI();
//            Inventory inventory = Inventory.loadFromFile("inventory.json");
//            chipsUI.render(customizerContainer, inventory, order, checkoutBtn);
//            customizerContainer.setVisible(true);
//            customizerContainer.setManaged(true);
//        });

//        signatureBtn.setOnAction(e -> {
//            customizerContainer.getChildren().clear();
//            SignatureSandwichSelectionUI signatureSandwichSelectionUI= new SandwichCustomizerUI();
//            Inventory inventory = Inventory.loadFromFile("inventory.json");
//            signatureSandwichSelectionUI.re
////            SignatureSandwichSelectionUI selectionUI = new SignatureSandwichSelectionUI(stage, inventory, order);
////            selectionUI.show();
//            customizerContainer.setVisible(true);
//            customizerContainer.setManaged(true);
//        });

        checkoutBtn.setOnAction(e -> {
            if (order.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Your cart is empty!");
                alert.showAndWait();
                return;
            }

            Inventory inventory = Inventory.loadFromFile("inventory.json");

            Stage checkoutStage = new Stage();
//            Checkout checkoutPage = new Checkout(checkoutStage, order, inventory);
//            checkoutPage.show();
        });


        // Style the container for sandwich customizer (border for clarity)
        customizerContainer.setPadding(new Insets(20));
        customizerContainer.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
        customizerContainer.setVisible(false);
        customizerContainer.setManaged(false);


        VBox mainLayout = new VBox(20, topBar, buttonsPane, customizerContainer);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(mainLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Scene scene = new Scene(scrollPane, 1000, 800);
        stage.setScene(scene);
        stage.setTitle("Welcome");
        stage.setResizable(true);
        stage.show();
    }

    private Button createImageButton(String text, String imagePath) {
        ImageView imageView = loadImageView(imagePath, 80, 80);
        Button button = new Button(text, imageView);
        button.setContentDisplay(ContentDisplay.TOP);
        button.setPrefSize(120, 120);
        return button;
    }

    private ImageView loadImageView(String imagePath, double width, double height) {
        Image img;
        try {
            img = new Image(getClass().getResourceAsStream(imagePath));
        } catch (Exception e) {
            System.out.println("Image not found: " + imagePath);
            img = null;
        }
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }


}