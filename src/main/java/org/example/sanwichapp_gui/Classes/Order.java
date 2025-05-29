package org.example.sanwichapp_gui.Classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    private String customerName;
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chips> chips;
    private double tax=0.07;



    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
    }

    // Add sandwich
    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    // Add drink
    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    // Add chips
    public void addChips(Chips chip) {
        chips.add(chip);
    }

    // Getters
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

    public int getTotalItemCount() {
        int totalCount = 0;
        totalCount += this.sandwiches.stream().mapToInt(s -> s.getQuantity()).sum();
        totalCount += this.drinks.stream().mapToInt(d -> d.getQuantity()).sum();
        totalCount += this.chips.stream().mapToInt(c -> c.getQuantity()).sum();
        return totalCount;
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
    }


    @Override
    public String toString() {
        return "Order{" +
                "sandwiches=" + sandwiches +
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
