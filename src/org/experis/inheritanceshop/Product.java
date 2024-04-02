package org.experis.inheritanceshop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Product {

    //attributi
    private final int id;
    private String name;
    private String brand;
    private BigDecimal price;
    private BigDecimal vat;
    private int quantity;
    private boolean isVisible;
    //costruttore
    public Product(String name, String brand, BigDecimal price, BigDecimal vat, int quantity) {
        this.id = generateRandomId();
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.vat = vat;
        this.quantity = quantity;
        this.isVisible = quantity > 0;
    }

    //metodi
    //ottieni codice id random
    public int generateRandomId() {
        Random random = new Random();
        // genera un numero casuale comresop tra 100000 e 99999999
        return 100000 + random.nextInt(90000000);
    }

    //ottieni prezzo totale
    /* dividi 22% iva x ottenere decimale 0.22, aggiungere 1 x poter moltiplicare prezzo x 1.22
     * NB: con bigDecimal non si possono usare gli operatori */

    public BigDecimal getTotalPrice(){
        //dividere x 100 ed aggiungere 1
        BigDecimal vatRate = this.vat.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP).add(BigDecimal.ONE);
        //ritornare price * 1.22; NB:   set Scale x arrotondare il risultato a due cifre decimali, RM HA ->arrotondato al numero intero superiore più vicino
        return this.price.multiply(vatRate).setScale(2, RoundingMode.HALF_UP);

    }

    //ottenere il nome esteso

    public String getFullName(){
        return String.format("%08d", this.id) + "-" + this.name;
    }

    //getters per accedere i valori delle istanze

    //id
    public int getId() {
        return this.id;
    }
    //name
    public String getName(){
        return this.name;
    }

    public String getBrand() {
        return this.brand;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getVat() {
        return this.vat;
    }

    public boolean getIsVisible() {
        return this.isVisible;
    }


    //setters


    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //metodo per dettagli prodotto:
    public String productDetails() {
        return String.format("Id: %d, Name: %s, Brand: %s, Net Price: €%.2f, VAT: %.0f%%, Total Price: €%.2f",
                id, name, brand, price, vat, getTotalPrice());
    }

    //scontistica

    // sconto di default
    public BigDecimal getDefaultDiscountRate() {
        return BigDecimal.valueOf(0.02);
    }

    // per ottenere il prezzo scontato
    public BigDecimal getDiscountedPrice(boolean hasLoyaltyCard) {
        BigDecimal discountRate = getDefaultDiscountRate();

        //aggiungere lo sconto default se carta fedeltà
        if (hasLoyaltyCard) {
            discountRate = discountRate.add(BigDecimal.valueOf(0.02));
        }

        // calcolo del prezzo scontato
        BigDecimal discountAmount = this.price.multiply(discountRate);
        BigDecimal discountedPrice = this.price.subtract(discountAmount);

        return discountedPrice.setScale(2, RoundingMode.HALF_EVEN);
    }

    // ottenere il prezzo totale scontato
    public BigDecimal getDiscountedTotalPrice(boolean hasLoyaltyCard) {
        BigDecimal discountedPrice = getDiscountedPrice(hasLoyaltyCard);
        BigDecimal vatRate = this.vat.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP).add(BigDecimal.ONE);
        BigDecimal totalPrice = discountedPrice.multiply(vatRate);
        return totalPrice.setScale(2, RoundingMode.HALF_EVEN);
    }

}




