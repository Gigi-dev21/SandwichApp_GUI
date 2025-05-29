package org.example.sanwichapp_gui.Components;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.sanwichapp_gui.Classes.Drink;
import org.example.sanwichapp_gui.Classes.Inventory;
import org.example.sanwichapp_gui.Classes.Order;
import org.example.sanwichapp_gui.StageManager;
import java.util.Map;

public class DrinkUI {
    private final Inventory inventory = StageManager.getInventory();
    private final Order order = StageManager.getOrder();

    public void render(VBox container) {
        container.getChildren().clear();

        if (inventory == null || inventory.drinks == null) {
            container.getChildren().add(new Label("No drinks available."));
            return;
        }

        //Container for the drink UI//
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-border-color: lightgray; -fx-border-width: 1;");

        Label header = new Label("Select Your Drink");
        header.setStyle("-fx-font-size: 18px; -fx-underline: true;");

        ComboBox<String> drinkSelector = new ComboBox<>();
        drinkSelector.setPromptText("Choose a drink");
        drinkSelector.getItems().addAll(inventory.drinks.keySet().stream().map(this::capitalize).toList());

        ToggleGroup sizeToggleGroup = new ToggleGroup();
        VBox sizeOptionsBox = new VBox(10);

        drinkSelector.setOnAction(e -> {
            String selectedDrinkKey = uncapitalize(drinkSelector.getValue());
            sizeOptionsBox.getChildren().clear();
            sizeToggleGroup.getToggles().clear();

            Map<String, Double> sizes = inventory.drinks.get(selectedDrinkKey);
            if (sizes != null) {
                for (String size : sizes.keySet()) {
                    double price = sizes.get(size);
                    Drink drinkObj = new Drink(capitalize(selectedDrinkKey), capitalize(size), price, 1);
                    RadioButton sizeOption = new RadioButton(capitalize(size) + " ($" + String.format("%.2f", price) + ")");
                    sizeOption.setUserData(drinkObj);
                    sizeOption.setToggleGroup(sizeToggleGroup);
                    sizeOptionsBox.getChildren().add(sizeOption);
                }
            }
        });


        //Add Drink Button///
        Button addButton = new Button("Add Drink");
        addButton.setOnAction(e -> {
            if (sizeToggleGroup.getSelectedToggle() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a drink size.");
                alert.showAndWait();
                return;
            }

            Drink selectedDrink = (Drink) sizeToggleGroup.getSelectedToggle().getUserData();
            order.addDrink(selectedDrink);
//            updateCheckoutButton(checkoutBtn, order);
//            checkoutBtn.setDisable(false);
            container.getChildren().clear();

            System.out.println("Added: " + selectedDrink);
        });

        layout.getChildren().addAll(header, drinkSelector, sizeOptionsBox, addButton);
        container.getChildren().add(layout);
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).replace("_", " ");
    }

    private String uncapitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toLowerCase() + s.substring(1).replace(" ", "_");
    }
}
