package org.experis.inheritanceshop;

import java.math.BigDecimal;

public class Televisors extends Product{

    //attributi

    private int screenSize;
    private boolean IsSmart;
    //costrutture
    public Televisors(String name, String brand, BigDecimal price, BigDecimal vat, int quantity, int screenSize, boolean isSmart) {
        super(name, brand, price, vat, quantity);
        this.screenSize = screenSize;
        IsSmart = isSmart;
    }

    //getters and setters


    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    public boolean isSmart() {
        return IsSmart;
    }

    public void setSmart(boolean smart) {
        IsSmart = smart;
    }
}
