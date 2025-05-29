package org.example.sanwichapp_gui.Classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private String customerName;
    private Order order;
    private double totalAmount;
    private String paymentMethod;
    private long timestamp;

    public Transaction() {}

    public Transaction(String customerName, Order order, double totalAmount, String paymentMethod, long timestamp) {
        this.customerName = customerName;
        this.order = order;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.timestamp = timestamp;
    }

    // Getters and setters

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
