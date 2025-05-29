package org.example.sanwichapp_gui.Components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class ButtonsPane extends FlowPane {
    private Button addSandwichBtn;
    private Button addDrinksBtn;
    private Button addChipsBtn;
    private Button signatureBtn;

    public ButtonsPane() {
        setHgap(20);
        setVgap(20);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        addSandwichBtn = createImageButton("Add Sandwich", "/images/sandwich1.jpeg");
        addDrinksBtn = createImageButton("Add Drinks", "/images/Drinks.jpeg");
        addChipsBtn = createImageButton("Add Chips", "/images/chips.jpeg");
        signatureBtn = createImageButton("Signature Sandwiches", "/images/Sign.jpeg");

        getChildren().addAll(addSandwichBtn, addDrinksBtn, addChipsBtn, signatureBtn);
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

    // getters for buttons to add event handlers in HomePage
    public Button getAddSandwichBtn() { return addSandwichBtn; }
    public Button getAddDrinksBtn() { return addDrinksBtn; }
    public Button getAddChipsBtn() { return addChipsBtn; }
    public Button getSignatureBtn() { return signatureBtn; }
}

