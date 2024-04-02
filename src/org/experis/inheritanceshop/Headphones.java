package org.experis.inheritanceshop;

import java.math.BigDecimal;

public class Headphones extends Product{

    private String color;
    private boolean isWireless;

    //costruttore


    public Headphones(String name, String brand, BigDecimal price, BigDecimal vat, int quantity, String color, boolean isWireless) {
        super(name, brand, price, vat, quantity);
        this.color = color;
        this.isWireless = isWireless;
    }

    //getters&setters


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isWireless() {
        return isWireless;
    }

    public void setWireless(boolean wireless) {
        isWireless = wireless;
    }
}
