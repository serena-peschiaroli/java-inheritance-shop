package org.experis.inheritanceshop;

import java.math.BigDecimal;
import java.util.Scanner;

public class Cart {

    private static Scanner scan = new Scanner(System.in);
    private static Product[] cart;
    public static void main(String[] args) {
        System.out.print("Do you have a loyalty card? (y/n): ");
        String loyaltyCardResponse = scan.nextLine();
        boolean hasLoyaltyCard = loyaltyCardResponse.equalsIgnoreCase("y");
        System.out.println("How many products? ");
        int size = Integer.parseInt(scan.nextLine());
        cart = new Product[size];



        // Loop per la selezione dei prodotti
        for (int i = 0; i < size; i++) {


            System.out.println("Select a product to add to your cart: ");
            System.out.println("1. Smartphone");
            System.out.println("2. Televisor");
            System.out.println("3. Headphones");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scan.nextLine());
            switch (choice) {
                case 1:
                    addToCart(i, "Smartphone");
                    break;
                case 2:
                    addToCart(i, "Televisor");
                    break;
                case 3:
                    addToCart(i, "Headphones");
                    break;
                default:
                    System.out.println("Invalid selection.");
                    // i-- riduzione indice per gestire gli input invalidi, ovvero l'utete riceve un nuovo prompt per un input valido
                    i--;
                    break;
            }
        }

        //mostra contenuto del carrello

        System.out.println("\nYour Cart Contents:");
        for (Product product : cart) {
            if (product != null) {

                System.out.println(product.productDetails());

            }
        }
        //prezzo totale
        System.out.println("Total : " + getCartTotalPrice(hasLoyaltyCard));
    }

    //aggiungi al carrello
    private static void addToCart(int index, String productType) {
        String name, brand;
        BigDecimal price, extraCost = BigDecimal.ZERO;
        int memorySize = 64, screenSize = 32;
        boolean isSmart = false, isWireless = false;

        // attributi comuni
        System.out.print("Add Name: ");
        name = scan.nextLine();
        System.out.print("Add brand: ");
        brand = scan.nextLine();
        //Customizzazioni del prodotto
        switch (productType) {
            case "Smartphone":
                System.out.println("Select memory size:");
                System.out.println("1. 64GB (no extra cost)");
                System.out.println("2. 128GB (+€50)");
                System.out.println("3. 256GB (+€100)");
                int memoryChoice = Integer.parseInt(scan.nextLine());
                memorySize = memoryChoice == 1 ? 64 : memoryChoice == 2 ? 128 : 256;
                extraCost = memoryChoice == 1 ? BigDecimal.ZERO : memoryChoice == 2 ? new BigDecimal("50") : new BigDecimal("100");
                price = new BigDecimal("999.99").add(extraCost);
                cart[index] = new Smartphone(name, brand, price, new BigDecimal("22"), 1, memorySize);
                break;
            case "Televisor":
                System.out.println("Choose screen size:");
                System.out.println("1. 32-inch (€350.00)");
                System.out.println("2. 44-inch (€450.00)");
                int screenChoice = Integer.parseInt(scan.nextLine());
                System.out.println("Smart Tv?");
                System.out.println("1. Yes (+€100.00)");
                System.out.println("2. No");
                int smartChoice = Integer.parseInt(scan.nextLine());
                isSmart = smartChoice == 1;
                extraCost = isSmart ? new BigDecimal("100") : BigDecimal.ZERO;
                screenSize = screenChoice == 1 ? 32 : 44;
                price = screenChoice == 1 ? new BigDecimal("350.00") : new BigDecimal("450.00").add(extraCost);
                Televisor customizedTelevisor = new Televisor(name, brand, price, new BigDecimal("22"), 1, screenSize, isSmart);
                cart[index] = customizedTelevisor;
                System.out.println("Added " + customizedTelevisor.getName() + " " + screenSize + "-inch" + (isSmart ? " Smart TV" : "") + " to your cart.");
                break;
            case "Headphones":
                System.out.println("Choose color:");
                System.out.println("1. Black (no extra cost)");
                System.out.println("2. White (+€25 for wireless feature)");
                int colorChoice = Integer.parseInt(scan.nextLine());
                BigDecimal headphoneExtra = BigDecimal.ZERO;
                isWireless = colorChoice == 2;
                if (isWireless) {
                    headphoneExtra = new BigDecimal("25");
                }
                price = new BigDecimal("100.00").add(headphoneExtra);
                Headphone customizedHeadphone = new Headphone(name, brand, price, new BigDecimal("22"), 1, colorChoice == 2 ? "White" : "Black", isWireless);
                //aggiungi al carrello all'indice specifico
                cart[index] = customizedHeadphone;
                System.out.println("Added " + customizedHeadphone.getName() + " in " + (isWireless ? "White (Wireless)" : "Black") + " to your cart.");

                break;
        }
        //aggiunta del prodotto al carrello
        System.out.println("Added " + name + " to your cart.");
    }

    //mostra prezzo tot del carrello

    public static BigDecimal getCartTotalPrice(boolean hasLoyaltyCard) {
        BigDecimal cartTotalPrice = BigDecimal.ZERO;
        for (Product product : cart) {
            if (product != null) {
                BigDecimal productPrice;
                if (hasLoyaltyCard) {
                     productPrice = product.getDiscountedTotalPrice(true);
                } else {
                    productPrice = product.getTotalPrice();
                }
                cartTotalPrice = cartTotalPrice.add(productPrice);
            }
        }
        return cartTotalPrice;
    }
}
