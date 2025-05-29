package org.example.sanwichapp_gui.Controllers;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckBoxSection {
    public static VBox createCheckBoxGroup(Map<String, String> options) {
        VBox box = new VBox(5);
        if (options != null) {
            for (String key : options.keySet()) {
                CheckBox cb = new CheckBox(key);
                cb.setUserData(key);
                box.getChildren().add(cb);
            }
        }
        return box;
    }

    public static List<String> getSelectedItemsFromGroup(Pane container) {
        List<String> selectedItems = new ArrayList<>();
        for (Node node : container.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox cb = (CheckBox) node;
                if (cb.isSelected()) {
                    selectedItems.add(cb.getText());
                }
            }
        }
        return selectedItems;
    }
}
