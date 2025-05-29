package org.example.sanwichapp_gui.Components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.sanwichapp_gui.Classes.Inventory;
import org.example.sanwichapp_gui.Classes.Order;
import org.example.sanwichapp_gui.Classes.Sandwich;
import org.example.sanwichapp_gui.Controllers.CheckBoxSection;
import org.example.sanwichapp_gui.Controllers.OptionSection;
import org.example.sanwichapp_gui.Controllers.ToastedOptionSection;
import org.example.sanwichapp_gui.StageManager;


import java.util.List;

public class SandwichUI {
    private ComboBox<String> sizeCombo;
    private final Inventory inventory = StageManager.getInventory();
    private final Order order = StageManager.getOrder();

    // Track groups and option boxes so we can update prices on size change
    private ToggleGroup breadGroup, meatGroup, cheeseGroup;
    private VBox breadBox, meatBox, cheeseBox;


    public void render(VBox container) {
        container.getChildren().clear();

        if (inventory == null) {
            container.getChildren().add(new Label("Failed to load inventory."));
            return;
        }

        Label header = new Label("Customize your sandwich, " + order.getCustomerName() + "!");
        header.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        // Size selection
        HBox sizeBox = new HBox(10);
        sizeBox.setAlignment(Pos.CENTER_LEFT);
        Label sizeLabel = new Label("Select sandwich size:");
        sizeCombo = new ComboBox<>();
        sizeCombo.getItems().addAll("4", "8", "12");
        sizeCombo.setValue("8");
        sizeBox.getChildren().addAll(sizeLabel, sizeCombo);

        // Bread options
        breadGroup = new ToggleGroup();
        breadBox = OptionSection.createRadioOptionsFiltered(inventory.bread, sizeCombo.getValue(), breadGroup, "no-such-bread");


        // Meat options
        meatGroup = new ToggleGroup();
        meatBox = OptionSection.createRadioOptionsFiltered(inventory.meat, sizeCombo.getValue(), meatGroup, "extra meat");


        // Cheese options
        cheeseGroup = new ToggleGroup();
        cheeseBox = OptionSection.createRadioOptionsFiltered(inventory.cheese, sizeCombo.getValue(), cheeseGroup, "extra cheese");


        // Toppings
        VBox toppingBox = CheckBoxSection.createCheckBoxGroup(inventory.regular_toppings);

        // Sides
        VBox sidesBox = CheckBoxSection.createCheckBoxGroup(inventory.sides);

        // Sauces
        VBox sauceBox = CheckBoxSection.createCheckBoxGroup(inventory.sauces);

        // Toasted
        ToggleGroup toastedGroup = new ToggleGroup();
        HBox toastedBox = ToastedOptionSection.createToastedGroup(toastedGroup);


        // Update prices when size changes
        sizeCombo.setOnAction(e -> {
            String selectedSize = sizeCombo.getValue();

            breadBox.getChildren().setAll(
                    OptionSection.createRadioOptionsFiltered(inventory.bread, selectedSize, breadGroup, "no-such-bread").getChildren()
            );
            meatBox.getChildren().setAll(
                    OptionSection.createRadioOptionsFiltered(inventory.meat, selectedSize, meatGroup, "extra meat").getChildren()
            );
            cheeseBox.getChildren().setAll(
                    OptionSection.createRadioOptionsFiltered(inventory.cheese, selectedSize, cheeseGroup, "extra cheese").getChildren()
            );
        });


        // Add Sandwich Button/
        Button submitBtn = new Button("Add Sandwich to Order");
        submitBtn.setStyle("-fx-padding: 8 16 8 16; -fx-margin-left: 80px;");

        submitBtn.setOnAction(e -> {
            String size = sizeCombo.getValue();
            String bread = (String) breadGroup.getSelectedToggle().getUserData();
            String meat = (String) meatGroup.getSelectedToggle().getUserData();
            String cheese = (String) cheeseGroup.getSelectedToggle().getUserData();
            boolean toasted = ((String) toastedGroup.getSelectedToggle().getUserData()).equalsIgnoreCase("Yes");


            // Gather toppings, sauces, and sides
            List<String> toppings = CheckBoxSection.getSelectedItemsFromGroup(toppingBox);
            List<String> sauces = CheckBoxSection.getSelectedItemsFromGroup(sauceBox);
            List<String> sides = CheckBoxSection.getSelectedItemsFromGroup(sidesBox);

            // Set default quantity to 1 (you can add quantity UI later if needed)
            int quantity = 1;

            // Create the Sandwich object
            Sandwich sandwich = new Sandwich(
                    size, bread, meat,
                    cheese,
                    toppings, sauces, sides,
                    toasted, quantity
            );

            // Add to selectedItems (cast it if needed)
            order.addSandwich(sandwich);
//            updateCheckoutButton(checkoutBtn, order);

//            checkoutBtn.setDisable(false);
            System.out.println("Added to order: " + sandwich);
            container.getChildren().clear();
        });

// LEFT COLUMN
        VBox leftColumn = new VBox(10);
        leftColumn.getChildren().addAll(
                new Label("Choose bread:"), breadBox,
                new Label("Choose meat:"), meatBox,
                new Label("Choose cheese:"), cheeseBox
        );

        // RIGHT COLUMN
        VBox rightColumn = new VBox(10);
        rightColumn.getChildren().addAll(
                new Label("Choose toppings:"), toppingBox,
                new Label("Choose sides:"), sidesBox,
                new Label("Choose sauces:"), sauceBox,
                new Label("Toasted?"), toastedBox
        );

       // COMBINE BOTH COLUMNS IN AN HBOX
        HBox formColumns = new HBox(100);
        formColumns.getChildren().addAll(leftColumn, rightColumn);
        HBox.setMargin(leftColumn, new Insets(0, 100, 0, 0));
        HBox.setMargin(rightColumn, new Insets(0, 0, 0, 100));

       // MAIN CONTAINER
        container.getChildren().addAll(
                header,
                sizeBox,
                formColumns,
                submitBtn
        );

    }

    public void updateCheckoutButton(Button checkoutBtn, Order order) {
        checkoutBtn.setText("🛒 " + order.getTotalItemCount());
    }
}

