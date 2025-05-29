//package org.example.sanwichapp_gui.Components.SignatureSandwich;
//
//import javafx.geometry.Insets;
//import javafx.scene.Node;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import org.example.sanwichapp_gui.Classes.Inventory;
//import org.example.sanwichapp_gui.Classes.Order;
//import org.example.sanwichapp_gui.Classes.SignatureSandwich;
//import org.example.sanwichapp_gui.StageManager;
//
//import java.util.List;
//
//public class SignatureSandwichUI {
//
//    private final Inventory inventory = StageManager.getInventory();
//    private final Order order = StageManager.getOrder();
//    private final List<String> names;
//    private final List<SignatureSandwich> sandwiches;
//
//    public SignatureSandwichUI() {
//        this.names = inventory.signature_sandwiches.keySet().stream().toList();
//        this.sandwiches = inventory.signature_sandwiches.values().stream().toList();
//    }
//
//    public Node getUI() {
//        VBox layout = new VBox(15);
//        layout.setPadding(new Insets(20));
//        layout.setStyle("-fx-background-color: #f4f4f4;");
//
//        Label title = new Label("🥪 Signature Sandwiches");
//        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
//        layout.getChildren().add(title);
//
//        ListView<String> sandwichList = new ListView<>();
//        sandwichList.getItems().addAll(names);
//        layout.getChildren().add(sandwichList);
//
//        Label descriptionLabel = new Label();
//        descriptionLabel.setWrapText(true);
//        descriptionLabel.setStyle("-fx-font-size: 14px;");
//        layout.getChildren().add(descriptionLabel);
//
//        Button customizeBtn = new Button("Customize and Add to Order");
//        customizeBtn.setDisable(true);
//        layout.getChildren().add(customizeBtn);
//
//        sandwichList.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
//            if (newVal.intValue() >= 0) {
//                SignatureSandwich selected = sandwiches.get(newVal.intValue());
//                String name = names.get(newVal.intValue());
//                descriptionLabel.setText("🍞 " + name + ":\n" + getSandwichDetails(selected));
//                customizeBtn.setDisable(false);
//            } else {
//                descriptionLabel.setText("");
//                customizeBtn.setDisable(true);
//            }
////        });
////
//        customizeBtn.setOnAction(e -> {
//            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
//            if (selectedIndex >= 0) {
//                SignatureSandwich selected = sandwiches.get(selectedIndex);
//                CustomizeSandwichUI customizeUI = new CustomizeSandwichUI(selected, names.get(selectedIndex));
//                customizeUI.show();
//            }
//        });
//
//        return layout;
//    }
//
//    private String getSandwichDetails(SignatureSandwich s) {
//        return String.format(
//                "Size & Bread: %s\" %s\nMeat: %s\nCheese: %s\nToppings: %s\nSauces: %s\nSides: %s\nToasted: %s",
//                s.getSize(),
//                s.getBread(),
//                s.getMeat(),
//                s.getCheese(),
//                (s.getToppings() == null || s.getToppings().isEmpty()) ? "none" : String.join(", ", s.getToppings()),
//                (s.getSauces() == null || s.getSauces().isEmpty()) ? "none" : String.join(", ", s.getSauces()),
//                (s.getSides() == null || s.getSides().isEmpty()) ? "none" : String.join(", ", s.getSides()),
//                s.isToasted() ? "Yes" : "No"
//        );
//    }
//}
