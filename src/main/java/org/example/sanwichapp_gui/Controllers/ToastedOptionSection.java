package org.example.sanwichapp_gui.Controllers;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class ToastedOptionSection {
    public static HBox createToastedGroup(ToggleGroup toastedGroup) {
        RadioButton yes = new RadioButton("Yes");
        RadioButton no = new RadioButton("No");
        yes.setUserData("Yes");
        no.setUserData("No");
        yes.setToggleGroup(toastedGroup);
        no.setToggleGroup(toastedGroup);
        no.setSelected(true);
        return new HBox(10, yes, no);
    }
}
