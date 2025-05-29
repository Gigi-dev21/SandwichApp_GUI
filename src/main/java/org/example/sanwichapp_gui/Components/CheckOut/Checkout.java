package org.example.sanwichapp_gui.Components.CheckOut;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.sanwichapp_gui.Classes.*;
import org.example.sanwichapp_gui.Controllers.SaveToFile;
import org.example.sanwichapp_gui.Screens.LoginScreen;
import org.example.sanwichapp_gui.StageManager;

import java.time.Instant;


public class Checkout {
    private final Inventory inventory = StageManager.getInventory();
    private final Order order = StageManager.getOrder();
    private final Stage stage;

    public Checkout(Stage stage) {
        this.stage = stage;

    }

    public void show() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f9f9f9;");

        Label title = new Label("🧾 Order Summary");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2a7a2a;");
        layout.getChildren().add(title);

        // Customer name
        Label customerLabel = new Label("Customer Name: " + (order.getCustomerName() != null ? order.getCustomerName() : "Guest"));
        customerLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        layout.getChildren().add(customerLabel);

        layout.getChildren().add(new Separator());

        // Sandwiches Section
        if (!order.getSandwiches().isEmpty()) {
            Label sandwichesLabel = new Label("🥪 Sandwiches:");
            sandwichesLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #444;");
            layout.getChildren().add(sandwichesLabel);

            for (int i = 0; i < order.getSandwiches().size(); i++) {
                Sandwich s = order.getSandwiches().get(i);

                VBox sandwichBox = new VBox(10);
                sandwichBox.setPadding(new Insets(5));
                sandwichBox.setStyle("-fx-background-color: #e2f0d9; -fx-background-radius: 5;");

                HBox mainLineBox = new HBox(10);
                mainLineBox.setAlignment(Pos.CENTER_LEFT);

                Label mainLine = new Label(String.format("%d) %dx %s\" %s  - $%.2f each",
                        i + 1, s.getQuantity(), s.getSize(), s.getName(), s.calculatePrice(inventory)));
                mainLine.setStyle("-fx-font-weight: bold;");

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Button deleteBtn = new Button("🗑️");
                deleteBtn.setStyle("-fx-background-color: transparent; -fx-font-size: 12px; -fx-padding: 2 5 2 5;");
                deleteBtn.setFocusTraversable(false);
                int indexToRemove = i;
                deleteBtn.setOnAction(e -> {
                    order.getSandwiches().remove(indexToRemove);
                    order.updateTotalCount();
                    show();
                });

                mainLineBox.getChildren().addAll(mainLine, spacer, deleteBtn);
                sandwichBox.getChildren().add(mainLineBox);

                // Details
                HBox details = new HBox(10);
                details.getChildren().add(new Label("Bread: " + s.getBread()));
                details.getChildren().add(new Label("Cheese: " + s.getCheese()));
                sandwichBox.getChildren().add(details);

                if (!s.getToppings().isEmpty())
                    sandwichBox.getChildren().add(new Label("Toppings: " + String.join(", ", s.getToppings())));
                if (!s.getSauces().isEmpty())
                    sandwichBox.getChildren().add(new Label("Sauces: " + String.join(", ", s.getSauces())));
                if (!s.getSides().isEmpty())
                    sandwichBox.getChildren().add(new Label("Sides: " + String.join(", ", s.getSides())));
                if (s.isToasted())
                    sandwichBox.getChildren().add(new Label("Toasted"));

                Label totalLabel = new Label(String.format("➤ Total: $%.2f", s.getTotalPrice(inventory)));
                totalLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2a7a2a;");
                sandwichBox.getChildren().add(totalLabel);

                layout.getChildren().add(sandwichBox);
            }
            layout.getChildren().add(new Separator());
        }

        // Drinks Section
        if (!order.getDrinks().isEmpty()) {
            Label drinksLabel = new Label("🥤 Drinks:");
            drinksLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2a7a2a;");
            layout.getChildren().add(drinksLabel);

            for (int i = 0; i < order.getDrinks().size(); i++) {
                Drink d = order.getDrinks().get(i);

                HBox drinkBox = new HBox(10);
                drinkBox.setAlignment(Pos.CENTER_LEFT);
                drinkBox.setStyle("-fx-background-color: #e2f0d9; -fx-background-radius: 5;");
                drinkBox.setPadding(new Insets(5));

                Label drinkLabel = new Label(String.format("%d) %dx %s %s - $%.2f each = $%.2f",
                        i + 1, d.getQuantity(), d.getSize(), d.getName(), d.getPrice(), d.getTotalPrice()));

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Button deleteBtn = new Button("🗑️");
                deleteBtn.setStyle("-fx-background-color: transparent; -fx-font-size: 12px; -fx-padding: 2 5 2 5;");
                deleteBtn.setFocusTraversable(false);
                int indexToRemove = i;
                deleteBtn.setOnAction(e -> {
                    order.getDrinks().remove(indexToRemove);
                    order.updateTotalCount();
                    show();
                });

                drinkBox.getChildren().addAll(drinkLabel, spacer, deleteBtn);
                layout.getChildren().add(drinkBox);
            }
            layout.getChildren().add(new Separator());
        }

        // Chips Section
        if (!order.getChips().isEmpty()) {
            Label chipsLabel = new Label("🍟 Chips:");
            chipsLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2a7a2a;");
            layout.getChildren().add(chipsLabel);

            for (int i = 0; i < order.getChips().size(); i++) {
                Chips c = order.getChips().get(i);

                HBox chipBox = new HBox(10);
                chipBox.setAlignment(Pos.CENTER_LEFT);
                chipBox.setPadding(new Insets(5));
                chipBox.setStyle("-fx-background-color: #e2f0d9; -fx-background-radius: 5;");

                Label chipsLabelItem = new Label(String.format("%d) %dx %s - $%.2f each = $%.2f",
                        i + 1, c.getQuantity(), c.getName(), c.getPrice(), c.getTotalPrice()));

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Button deleteBtn = new Button("🗑️");
                deleteBtn.setStyle("-fx-background-color: transparent; -fx-font-size: 12px; -fx-padding: 2 5 2 5;");
                deleteBtn.setFocusTraversable(false);
                int indexToRemove = i;
                deleteBtn.setOnAction(e -> {
                    order.getChips().remove(indexToRemove);
                    order.updateTotalCount();
                    show();
                });

                chipBox.getChildren().addAll(chipsLabelItem, spacer, deleteBtn);
                layout.getChildren().add(chipBox);
            }
            layout.getChildren().add(new Separator());
        }

        // Totals
        double sandwichSubtotal = order.getSandwiches().stream().mapToDouble(s -> s.getTotalPrice(inventory)).sum();
        double drinkSubtotal = order.getDrinks().stream().mapToDouble(Drink::getTotalPrice).sum();
        double chipSubtotal = order.getChips().stream().mapToDouble(Chips::getTotalPrice).sum();

        double subtotal = sandwichSubtotal + drinkSubtotal + chipSubtotal;
        double taxAmount = subtotal * order.getTax();
        double total = subtotal + taxAmount;

        Label subtotalLabel = new Label(String.format("Subtotal: $%.2f", subtotal));
        Label taxLabel = new Label(String.format("Tax (%.0f%%): $%.2f", order.getTax() * 100, taxAmount));
        Label totalLabel = new Label(String.format("Total: $%.2f", total));

        subtotalLabel.setStyle("-fx-font-weight: bold;");
        taxLabel.setStyle("-fx-font-weight: bold;");
        totalLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #2a7a2a;");

        layout.getChildren().addAll(subtotalLabel, taxLabel, totalLabel);

        Transaction transaction = new Transaction(
                order.getCustomerName(),
                order,
                total,
                "Credit Card",
                Instant.now().toEpochMilli()
        );
        // Confirm Button
        Button confirmBtn = new Button("Confirm Order");
        confirmBtn.setStyle("-fx-background-color: #2a7a2a; -fx-text-fill: white; -fx-font-weight: bold;");
        confirmBtn.setOnAction(e -> {
            Payment paymentDialog = new Payment();
            boolean success = paymentDialog.showPaymentDialog();

            if (success) {
                System.out.println("🎉 Payment succeeded! Thank you for your order.");

                Stage thisStage = (Stage) confirmBtn.getScene().getWindow();
                thisStage.close();

                //Save to File
                SaveToFile saveToFile= new SaveToFile();
                saveToFile.saveTransactionAsJson(transaction);

                order.clear();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Order Confirmation");
                alert.setHeaderText("Thank You!");
                alert.setContentText("Your order was placed successfully.");
                alert.showAndWait();

                new LoginScreen().show();
            } else {
                System.out.println("❌ Payment cancelled or failed.");
            }
        });

        layout.setAlignment(Pos.TOP_LEFT);
        layout.getChildren().add(confirmBtn);

        // ScrollPane wraps the whole layout for scroll
        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));

        Scene scene = new Scene(scrollPane, 600, 700);
        stage.setScene(scene);
        stage.setTitle("Checkout");
        stage.show();
    }
}
