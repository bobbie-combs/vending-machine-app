package com.techelevator.view;
public abstract class VendingItems {
    private String name;
    private double price;
    private String code;
    private String type;
    private int quantity;

    /**A constructor that sets information of a product in the vending machine.
     * @param code slot code of product
     * @param name product name
     * @param price cost of product
     * @param type the type of product (Gum, Drink, Chip, or Candy)
     * @param quantity amount of a product in vending machine
     */
    public VendingItems(String code, String name, double price, String type, int quantity) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.type = type;
        this.quantity = quantity;
    }
    // getters, setters for vending product information
    public String getName() {
        return name;
    }
    //Getters and Setters methods
    public double getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     *  Creates message to customer after making a purchase based on type.
     * Abstract method required for the Candy, Gums
     * , Drinks, and Chip class
     * @return String message to customer
     */
    public abstract String playSound();
}
