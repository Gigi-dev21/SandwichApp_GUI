package org.example.sanwichapp_gui.Classes;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Drink extends Items {
    private String size;
    public Drink() {
        super();  // calls default constructor in Items
    }
    public Drink(String name, String size, double price,int quantity) {
        super(name, price,quantity);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    @Override
    public String toString() {
        return name + " (" + size + ") ($" + price + ")";
    }
}
