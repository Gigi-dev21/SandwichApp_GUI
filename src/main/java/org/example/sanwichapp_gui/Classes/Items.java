package org.example.sanwichapp_gui.Classes;

public abstract class Items {
    protected String name;
    protected double price;
    protected int quantity;

    public Items(String name, double price,int quantity) {
        this.name = name;
        this.price = price;
        this.quantity=quantity;

    }

    public Items() {

    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}


