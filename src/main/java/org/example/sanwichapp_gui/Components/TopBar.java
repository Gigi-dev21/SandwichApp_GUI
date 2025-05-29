package org.example.sanwichapp_gui.Components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.example.sanwichapp_gui.Classes.Order;

public class TopBar extends HBox {
    private Label welcomeLabel;
    private Button checkoutBtn;

    public TopBar(String userName, Order order) {
        welcomeLabel = new Label("Welcome, " + userName + "!");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: green;");
        welcomeLabel.setMaxWidth(Double.MAX_VALUE);
        welcomeLabel.setAlignment(Pos.CENTER);

        checkoutBtn = new Button("🛒 " + order.getTotalItemCount());
        checkoutBtn.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-background-color: transparent;");
        checkoutBtn.setDisable(order.isEmpty());

        this.getChildren().addAll(welcomeLabel, checkoutBtn);
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        HBox.setHgrow(welcomeLabel, Priority.ALWAYS);
    }

    public Button getCheckoutButton() {
        return checkoutBtn;
    }
}
