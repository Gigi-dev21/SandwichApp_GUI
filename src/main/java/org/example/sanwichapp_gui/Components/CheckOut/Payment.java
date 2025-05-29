package org.example.sanwichapp_gui.Components.CheckOut;



import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Payment {

    private boolean paymentSuccess = false;

    public boolean showPaymentDialog() {
        Stage paymentStage = new Stage();
        paymentStage.initModality(Modality.APPLICATION_MODAL);
        paymentStage.setTitle("Payment Details");

        // Grid for form fields
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label nameLabel = new Label("Cardholder Name:");
        TextField nameField = new TextField();

        Label cardNumberLabel = new Label("Card Number:");
        TextField cardNumberField = new TextField();

        Label expiryLabel = new Label("Expiration Date (MM/YY):");
        TextField expiryField = new TextField();

        Label cvvLabel = new Label("CVV:");
        TextField cvvField = new TextField();

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        Button submitBtn = new Button("Submit Payment");
        Button cancelBtn = new Button("Cancel");

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);

        grid.add(cardNumberLabel, 0, 1);
        grid.add(cardNumberField, 1, 1);

        grid.add(expiryLabel, 0, 2);
        grid.add(expiryField, 1, 2);

        grid.add(cvvLabel, 0, 3);
        grid.add(cvvField, 1, 3);

        grid.add(messageLabel, 0, 4, 2, 1);

        grid.add(submitBtn, 0, 5);
        grid.add(cancelBtn, 1, 5);

        submitBtn.setOnAction(e -> {
            // Validate inputs
            String name = nameField.getText().trim();
            String cardNum = cardNumberField.getText().trim();
            String expiry = expiryField.getText().trim();
            String cvv = cvvField.getText().trim();

            if (name.isEmpty()) {
                messageLabel.setText("Cardholder name is required.");
                return;
            }
            if (!cardNum.matches("\\d{16}")) {
                messageLabel.setText("Card number must be exactly 16 digits.");
                return;
            }
            if (!expiry.matches("(0[1-9]|1[0-2])/\\d{2}")) {
                messageLabel.setText("Expiration date must be in MM/YY format.");
                return;
            }
            if (!cvv.matches("\\d{3}")) {
                messageLabel.setText("CVV must be exactly 3 digits.");
                return;
            }

            // Simulate payment processing delay
            submitBtn.setDisable(true);
            messageLabel.setStyle("-fx-text-fill: green;");
            messageLabel.setText("Processing payment...");

            // Simple simulation (could use a background thread for real app)
            new Thread(() -> {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                // Back to FX thread:
                javafx.application.Platform.runLater(() -> {
                    messageLabel.setText("Payment approved!");
                    paymentSuccess = true;
                    paymentStage.close();
                });
            }).start();
        });

        cancelBtn.setOnAction(e -> {
            paymentStage.close();
        });

        Scene scene = new Scene(grid);
        paymentStage.setScene(scene);
        paymentStage.showAndWait();

        return paymentSuccess;
    }
}

