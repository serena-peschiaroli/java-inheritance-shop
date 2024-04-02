package org.experis.inheritanceshop;

import java.math.BigDecimal;

public class Televisor extends Product{

    //attributi

    private int screenSize;
    private boolean isSmart;
    //costrutture
    public Televisor(String name, String brand, BigDecimal price, BigDecimal vat, int quantity, int screenSize, boolean isSmart) {
        super(name, brand, price, vat, quantity);
        this.screenSize = screenSize;
        isSmart = this.isSmart;
    }

    //getters and setters


    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    public boolean isSmart() {
        return this.isSmart;
    }

    public void setSmart(boolean smart) {
        isSmart = smart;
    }

    @Override
    public String productDetails() {
        return super.productDetails() + String.format(", Screen Size: %d inch, Smart TV: %s", screenSize, isSmart ? "Yes" : "No");
    }

    //scontistica con override

    // override per ottenere sconto per i televisori
    @Override
    public BigDecimal getDefaultDiscountRate() {
        if (!this.isSmart()) {
            return BigDecimal.valueOf(0.10);
        } else {
            return super.getDefaultDiscountRate();
        }
    }
}
