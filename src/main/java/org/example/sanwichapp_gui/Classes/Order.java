package org.example.sanwichapp_gui.Classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    private String customerName;

    // Use regular ArrayLists here to avoid serialization issues
    private List<Sandwich> sandwiches = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private List<Chips> chips = new ArrayList<>();

    private double tax = 0.07;

    // This property is for UI only, ignore it in serialization
    @JsonIgnore
    private transient IntegerProperty totalItemCount = new SimpleIntegerProperty(0);

    public Order() {
        // Initialize lists
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();

        // Initialize totalItemCount property
        this.totalItemCount = new SimpleIntegerProperty(0);

        // You can update total count manually after loading from JSON
        updateTotalCount();
    }

    public boolean isSignedIn() {
        return customerName != null && !customerName.trim().isEmpty();
    }

    // Add sandwich
    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
        updateTotalCount();
    }

    // Add drink
    public void addDrink(Drink drink) {
        drinks.add(drink);
        updateTotalCount();
    }

    // Add chips
    public void addChips(Chips chip) {
        chips.add(chip);
        updateTotalCount();
    }

    // Getters and setters for lists
    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public List<Chips> getChips() {
        return chips;
    }

    public void setCustomerName(String name) {
        this.customerName = name;
    }

    public String getCustomerName() {
        return customerName;
    }

    // IntegerProperty for total item count (UI binding only)
    @JsonIgnore
    public IntegerProperty totalItemCountProperty() {
        if (totalItemCount == null) {
            totalItemCount = new SimpleIntegerProperty(0);
            updateTotalCount();
        }
        return totalItemCount;
    }

    @JsonIgnore
    public int getTotalItemCount() {
        return totalItemCountProperty().get();
    }

    // Update the total count based on the items and quantities
    public void updateTotalCount() {
        int totalCount = sandwiches.stream().mapToInt(Sandwich::getQuantity).sum()
                + drinks.stream().mapToInt(Drink::getQuantity).sum()
                + chips.stream().mapToInt(Chips::getQuantity).sum();
        totalItemCountProperty().set(totalCount);
    }

    @JsonIgnore
    public double getTotalOfAllDrinkPrice() {
        double total = 0.0;
        for (Drink d : drinks) {
            total += d.getTotalPrice();
        }
        return total;
    }

    @JsonIgnore
    public double getTotalOfAllChipsPrice() {
        double total = 0.0;
        for (Chips c : chips) {
            total += c.getTotalPrice();
        }
        return total;
    }

    public double getTotalSandwichPrice(Inventory inventory) {
        double total = 0.0;
        for (Sandwich s : sandwiches) {
            total += s.getTotalPrice(inventory);
        }
        return total;
    }

    public double getOrderTotal(Inventory inventory) {
        return getTotalSandwichPrice(inventory) + getTotalOfAllDrinkPrice() + getTotalOfAllChipsPrice();
    }

    public void clear() {
        sandwiches.clear();
        drinks.clear();
        chips.clear();
        customerName = null;
        updateTotalCount();
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerName='" + customerName + '\'' +
                ", sandwiches=" + sandwiches +
                ", drinks=" + drinks +
                ", chips=" + chips +
                '}';
    }

    public double getTax() {
        return tax;
    }

    public boolean isEmpty() {
        return sandwiches.isEmpty() && drinks.isEmpty() && chips.isEmpty();
    }
}
