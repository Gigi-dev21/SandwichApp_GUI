package org.example.sanwichapp_gui.Controllers;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.util.Map;

public class OptionSection {
    public static VBox createRadioOptionsFiltered(Map<String, Map<String, Double>> items, String size, ToggleGroup group, String excludeKey) {
        VBox box = new VBox(5);
        if (items != null) {
            for (String type : items.keySet()) {
                if (type.equalsIgnoreCase(excludeKey)) continue; // Skip excluded key
                Double price = items.get(type).getOrDefault(size, 0.0);
                RadioButton rb = new RadioButton(type + " - $" + String.format("%.2f", price));
                rb.setUserData(type);
                rb.setToggleGroup(group);
                box.getChildren().add(rb);
            }
        }
        RadioButton noneOption = new RadioButton("None");
        noneOption.setUserData("None");
        noneOption.setToggleGroup(group);
        box.getChildren().add(noneOption);
        group.selectToggle(noneOption);

        return box;
    }

}
