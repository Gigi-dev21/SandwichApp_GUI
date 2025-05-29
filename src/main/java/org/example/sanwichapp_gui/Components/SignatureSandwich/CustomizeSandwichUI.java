//package org.example.sanwichapp_gui.Components.SignatureSandwich;
//
//import javafx.geometry.Insets;
//import javafx.scene.Node;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import org.example.sanwichapp_gui.Classes.Inventory;
//import org.example.sanwichapp_gui.Classes.Order;
//import org.example.sanwichapp_gui.Classes.Sandwich;
//import org.example.sanwichapp_gui.Classes.SignatureSandwich;
//import org.example.sanwichapp_gui.StageManager;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import javafx.stage.Stage;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CustomizeSandwichUI {
//    Stage stage = StageManager.getStage();
//    Order order = StageManager.getOrder();
//    private final Inventory inventory = StageManager.getInventory();
//    private final SignatureSandwich sandwich;
//    private final String sandwichName;
//
//    public CustomizeSandwichUI( SignatureSandwich sandwich, String sandwichName) {
//        this.sandwich = sandwich;
//        this.sandwichName = sandwichName;
//    }
//
//    public void show() {
//        VBox layout = new VBox(15);
//        layout.setPadding(new Insets(20));
//        layout.setAlignment(Pos.TOP_LEFT);
//
//        Label title = new Label("Customize your " + sandwichName);
//        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
//
//        // Toppings Section
//        Label toppingsLabel = new Label("Toppings:");
//        List<String> allToppings = new ArrayList<>(inventory.regular_toppings.keySet());
//
//        // Use checkboxes for toppings, pre-select existing toppings
//        VBox toppingsBox = new VBox(5);
//        List<CheckBox> toppingChecks = new ArrayList<>();
//        for (String topping : allToppings) {
//            CheckBox cb = new CheckBox(topping);
//            cb.setSelected(sandwich.getToppings().contains(topping));
//            toppingChecks.add(cb);
//            toppingsBox.getChildren().add(cb);
//        }
//
//        // Sauces Section
//        Label saucesLabel = new Label("Sauces:");
//        List<String> allSauces = new ArrayList<>(inventory.sauces.keySet());
//
//        VBox saucesBox = new VBox(5);
//        List<CheckBox> sauceChecks = new ArrayList<>();
//        for (String sauce : allSauces) {
//            CheckBox cb = new CheckBox(sauce);
//            cb.setSelected(sandwich.getSauces().contains(sauce));
//            sauceChecks.add(cb);
//            saucesBox.getChildren().add(cb);
//        }
//
//        // Quantity
//        Label quantityLabel = new Label("Quantity:");
//        Spinner<Integer> quantitySpinner = new Spinner<>(1, 20, 1);
//
//        // Confirm Button
//        Button confirmBtn = new Button("Add to Order");
//        Label statusLabel = new Label();
//        statusLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
//
//        confirmBtn.setOnAction(e -> {
//            // Clear current toppings/sauces and add selected
//            sandwich.getToppings().clear();
//            for (CheckBox cb : toppingChecks) {
//                if (cb.isSelected()) sandwich.getToppings().add(cb.getText());
//            }
//
//            sandwich.getSauces().clear();
//            for (CheckBox cb : sauceChecks) {
//                if (cb.isSelected()) sandwich.getSauces().add(cb.getText());
//            }
//
//            int quantity = quantitySpinner.getValue();
//            Sandwich finalized = sandwich.toSandwich(quantity, sandwichName);
//            order.addSandwich(finalized);
//
//            statusLabel.setText("Added " + quantity + " x " + sandwichName + " to order!");
//        });
//
//        layout.getChildren().addAll(
//                title,
//                toppingsLabel,
//                toppingsBox,
//                saucesLabel,
//                saucesBox,
//                quantityLabel,
//                quantitySpinner,
//                confirmBtn,
//                statusLabel
//        );
//
//        Scene scene = new Scene(layout, 400, 600);
//        stage.setScene(scene);
//        stage.setTitle("Customize Sandwich");
//        stage.show();
//    }
//}
