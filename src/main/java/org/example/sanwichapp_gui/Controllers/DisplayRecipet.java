package org.example.sanwichapp_gui.Controllers;


import org.example.sanwichapp_gui.Classes.*;

public class DisplayRecipet {
    private String buildReceipt(Order order, Inventory inventory) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Customer: ").append(order.getCustomerName()).append("\n\n");

        receipt.append("Sandwiches:\n");
        if (order.getSandwiches().isEmpty()) {
            receipt.append("  None\n");
        } else {
            for (Sandwich s : order.getSandwiches()) {
                receipt.append("  - ").append(s.toString()).append(" : $").append(String.format("%.2f", s.getTotalPrice(inventory))).append("\n");
            }
        }

        receipt.append("\nDrinks:\n");
        if (order.getDrinks().isEmpty()) {
            receipt.append("  None\n");
        } else {
            for (Drink d : order.getDrinks()) {
                receipt.append("  - ").append(d.toString()).append(" : $").append(String.format("%.2f", d.getTotalPrice())).append("\n");
            }
        }

        receipt.append("\nChips:\n");
        if (order.getChips().isEmpty()) {
            receipt.append("  None\n");
        } else {
            for (Chips c : order.getChips()) {
                receipt.append("  - ").append(c.toString()).append(" : $").append(String.format("%.2f", c.getTotalPrice())).append("\n");
            }
        }

        double subtotal = order.getOrderTotal(inventory);
        double taxAmount = subtotal * order.getTax();
        double total = subtotal + taxAmount;

        receipt.append("\nSubtotal: $").append(String.format("%.2f", subtotal));
        receipt.append("\nTax (").append(String.format("%.0f", order.getTax() * 100)).append("%): $").append(String.format("%.2f", taxAmount));
        receipt.append("\nTotal: $").append(String.format("%.2f", total));

        return receipt.toString();
    }
}
