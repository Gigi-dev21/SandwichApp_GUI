package org.example.sanwichapp_gui;

import javafx.stage.Stage;

public class StageManager {
    private static Stage primaryStage;

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    public static Stage getStage() {
        return primaryStage;
    }
}
