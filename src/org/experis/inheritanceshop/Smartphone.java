package org.experis.inheritanceshop;

import java.math.BigDecimal;
import java.util.Random;

public class Smartphone extends Product{
    //Attributi
    private String imei;
    private int memorySize;

    //costrotture
    public Smartphone(String name, String brand, BigDecimal price, BigDecimal vat, int quantity, int memorySize) {
        super(name, brand, price, vat, quantity);
        this.imei = generateImei();
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

    //dettagli prodotto
    @Override
    public String productDetails() {
        return super.productDetails() + String.format(", IMEI: %s, Memory Size: %dGB", imei, memorySize);
    }

    public String generateImei(){
        Random random = new Random();
        return "IMEI" + random.nextInt(1000000000);
    }

    //scontistica con override

    @Override
    public BigDecimal getDefaultDiscountRate() {
        if (this.memorySize <= 64) {
            return BigDecimal.valueOf(0.05);
        } else {
            return super.getDefaultDiscountRate();
        }
    }
}

