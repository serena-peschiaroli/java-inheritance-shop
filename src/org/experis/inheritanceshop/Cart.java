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

    /*aggiungi al carrello a seconda della selezione dell'utente
    * */
    private static void addToCart(int index, String productType) {
        Product product = null;

        switch (productType) {
            case "Smartphone":
                product = getSmartphoneDetails();
                break;
            case "Televisor":
                product = getTelevisorDetails();
                break;
            case "Headphones":
                product = getHeadphoneDetails();
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
                return;
        }

        cart[index] = product;
        System.out.println("Added " + product.getName() + " to your cart.");
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

    private static String getInput(String prompt) {
        System.out.print(prompt);
        return scan.nextLine();
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return getIntInput(prompt); // ricorsiva fino a che un input valido non è stato immesso
        }
    }
    private static Smartphone getSmartphoneDetails() {
        String name = getInput("Add Name: ");
        String brand = getInput("Add Brand: ");
        System.out.println("Select memory size:");
        System.out.println("1. 64GB (no extra cost)");
        System.out.println("2. 128GB (+€50)");
        System.out.println("3. 256GB (+€100)");
        int memoryChoice = getIntInput("");
        int memorySize = 64; // valore di default
        BigDecimal extraCost = BigDecimal.ZERO; // valore di default

        switch (memoryChoice) {
            case 2:
                memorySize = 128;
                extraCost = new BigDecimal("50");
                break;
            case 3:
                memorySize = 256;
                extraCost = new BigDecimal("100");
                break;

        }

        BigDecimal price = new BigDecimal("999.99").add(extraCost);
        return new Smartphone(name, brand, price, new BigDecimal("22"), 1, memorySize);
    }

    private static Televisor getTelevisorDetails() {
        String name = getInput("Add Name: ");
        String brand = getInput("Add Brand: ");
        System.out.println("Select screen size:");
        System.out.println("1. 32-inch (€350.00)");
        System.out.println("2. 44-inch (+€150.00)");

        int screenChoice = getIntInput("");
        int screenSize = 32;
        BigDecimal extraCost = BigDecimal.ZERO;

        if (screenChoice == 2) {
            screenSize = 44;
            extraCost = new BigDecimal("150");
        }

        System.out.println("Do you want a Smart TV? (y/n): ");
        String tvChoice = getInput("").trim().toLowerCase();
        boolean isSmart = "y".equals(tvChoice);

        if (isSmart) {
            extraCost = extraCost.add(new BigDecimal("100"));
        }

        // calcolare il prezzo base prima di aggiungere il costo extra
        BigDecimal basePrice = screenSize == 32 ? new BigDecimal("350.00") : new BigDecimal("500.00");
        BigDecimal price = basePrice.add(extraCost);
        return new Televisor(name, brand, price, new BigDecimal("22"), 1, screenSize, isSmart);
    }

    private static Headphone getHeadphoneDetails() {
        String name = getInput("Add Name: ");
        String brand = getInput("Add Brand: ");
        System.out.println("Choose color:");
        System.out.println("1. Black (no extra cost)");
        System.out.println("2. White (+€25 for wireless feature)");

        int colorChoice = getIntInput("");
        boolean isWireless = colorChoice == 2;
        String color = isWireless ? "White" : "Black";
        BigDecimal headphoneExtra = isWireless ? new BigDecimal("25") : BigDecimal.ZERO;

        BigDecimal price = new BigDecimal("100.00").add(headphoneExtra);
        return new Headphone(name, brand, price, new BigDecimal("22"), 1, color, isWireless);
    }





}
