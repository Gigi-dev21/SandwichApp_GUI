package org.example.sanwichapp_gui.Components;



import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.sanwichapp_gui.Classes.Chips;
import org.example.sanwichapp_gui.Classes.Inventory;
import org.example.sanwichapp_gui.Classes.Order;
import org.example.sanwichapp_gui.StageManager;


import java.util.List;
import java.util.Map;

public class ChipsUI {
    private final Inventory inventory = StageManager.getInventory();
    private final Order order = StageManager.getOrder();
    public void render(VBox container) {
        container.getChildren().clear();

        if (inventory == null || inventory.chips == null) {
            container.getChildren().add(new Label("No chips available."));
            return;
        }

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-border-color: lightgray; -fx-border-width: 1;");

        Label header = new Label("Select Your Chips");
        header.setStyle("-fx-font-size: 18px; -fx-underline: true;");

        ToggleGroup chipsToggleGroup = new ToggleGroup();
        VBox chipsOptionsBox = new VBox(10);

        for (Map.Entry<String, Double> chipEntry : inventory.chips.entrySet()) {
            String chipName = chipEntry.getKey();
            double price = chipEntry.getValue();

            RadioButton chipOption = new RadioButton(capitalize(chipName) + " ($" + String.format("%.2f", price) + ")");
            chipOption.setUserData(new Chips(chipName, price, 1));
            chipOption.setToggleGroup(chipsToggleGroup);

            chipsOptionsBox.getChildren().add(chipOption);
        }

        Button addButton = new Button("Add Chips");
        addButton.setOnAction(e -> {
            if (chipsToggleGroup.getSelectedToggle() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a chip.");
                alert.showAndWait();
                return;
            }

            Chips selectedChip = (Chips) chipsToggleGroup.getSelectedToggle().getUserData();

            // Add the Chip object to selected items list
            order.addChips(selectedChip);
//            checkoutBtn.setDisable(false);
//            updateCheckoutButton(checkoutBtn, order);

            container.getChildren().clear();

            System.out.println("Added chip: " + selectedChip);
        });

        layout.getChildren().addAll(header, chipsOptionsBox, addButton);
        container.getChildren().add(layout);
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
