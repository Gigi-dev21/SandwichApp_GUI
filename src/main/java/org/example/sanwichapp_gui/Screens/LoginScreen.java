package org.example.sanwichapp_gui.Screens;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.sanwichapp_gui.Classes.Order;
import org.example.sanwichapp_gui.StageManager;

public class LoginScreen {

    public void show() {
        Stage stage = StageManager.getStage();
        Order order= StageManager.getOrder();

        // Welcome title//
        Label title = new Label("Welcome to Delicious Sandwich!");
        title.setFont(new Font("Arial", 28));

        //Enter Name//
        Label prompt = new Label("Enter your name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Your name");
        nameField.setMaxWidth(200);

        //Error label//
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        //Submit button for name//
        Button submitButton = new Button("Submit");

        submitButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            order.setCustomerName(name);
            if (name.isEmpty()) {
                errorLabel.setText("Name cannot be empty.");
            } else {
                HomePage homePage = new HomePage();
                homePage.show();
            }
        });

        VBox layout = new VBox(15, title, prompt, nameField, errorLabel, submitButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 1000, 800);
        stage.setTitle("Delicious Sandwich");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
