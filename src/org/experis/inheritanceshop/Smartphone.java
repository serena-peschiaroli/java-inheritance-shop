package org.experis.inheritanceshop;

import java.math.BigDecimal;

public class Smartphone extends Product{
    //Attributi
    private String imei;
    private int memorySize;

    //costrotture
    public Smartphone(String name, String brand, BigDecimal price, BigDecimal vat, int quantity, String imei, int memorySize) {
        super(name, brand, price, vat, quantity);
        this.imei = imei;
        this.memorySize = memorySize;
    }
    //getters & setters
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }
}
