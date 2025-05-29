package org.example.sanwichapp_gui.Classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Chips extends Items {

    public Chips(String name, double price,int quantity) {
        super(name, price,quantity);
    }
    public Chips() {
        super(); // required for Jackson
    }
}
