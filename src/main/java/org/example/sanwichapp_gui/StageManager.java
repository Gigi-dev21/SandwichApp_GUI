package org.example.sanwichapp_gui;

import javafx.stage.Stage;
import org.example.sanwichapp_gui.Classes.Inventory;
import org.example.sanwichapp_gui.Classes.Order;

public class StageManager {
    private static Stage primaryStage;
    private static Order currentOrder;
    private static Inventory inventory;

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }
    public static Stage getStage() {
        return primaryStage;
    }

    public static void setOrder(Order order) {
        currentOrder = order;
    }
    public static Order getOrder() {
        return currentOrder;
    }

    public static void setInventory(Inventory inv) {
        inventory = inv;
    }
    public static Inventory getInventory() {
        return inventory;
    }
}
