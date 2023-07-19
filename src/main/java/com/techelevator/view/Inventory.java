/**
 * The Inventory class represents a collection of vending machine items
 read from a CSV file and stored in a List <VendingItems> object.
 */
package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    // the List object that stores all VendingItems from file
    private List <VendingItems> vendingItems = new ArrayList<>();
    private List <VendingItems> availableItems = new ArrayList<>(); //tracks items after each transaction
    private static  File dataFile = new File("vendingmachine.csv");
    private static  File absDataFile = new File(dataFile.getAbsolutePath());

    /**
     *  Constructor created for unit testing
     */
    public Inventory() {}
    /**
     * Constructor use to initialize the vendingItems List object.
     * @param items is a List object containing VendingItems
     */
    public Inventory(List<VendingItems> items, List<VendingItems> availableItems) {
        this.vendingItems = items;
        this.availableItems = availableItems;
    }
    public File getAbsDataFile() {
        return absDataFile;
    }

    /**
     * Returns the vendingItems List object.
     * @return List<VendingItems> the vendingItems List object
     */
    public List<VendingItems> getVendingItems() {
        return vendingItems;
    }
    /**
     * Returns the updated vendingItems List objects after each transaction.
     * @return List<VendingItems> the vendingItems List object
     */

     public List<VendingItems> getAvailableItems() {
        return initializeAvailableitemsList();
    }
    /**
     * Reads vending item data from the file and creates a List of VendingItems.
     */
    public void createListFromFile() {
        vendingItems = new ArrayList<>();
        try (Scanner input = new Scanner(absDataFile)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] lineToArr = line.split("\\|");
                String code = lineToArr[0];
                String name = lineToArr[1];
                double price = Double.parseDouble(lineToArr[2]);
                String type = lineToArr[3];
                int quantity = 5;
                switch (lineToArr[3]) {
                    case "Chip":
                        vendingItems.add(new Chips(code, name, price, type, quantity));
                        break;
                    case "Drink":
                        vendingItems.add(new Drinks(code, name, price, type, quantity));
                        break;
                    case "Candy":
                        vendingItems.add(new Candy(code, name, price, type, quantity));
                        break;
                    case "Gum":
                        vendingItems.add(new Gums(code, name, price, type, quantity));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        }
    }
    /**
     * Prints out the code, name, price, and quantity of each item in the vendingItems list.
     *  If the quantity of an item is zero, it will print "SOLD OUT" for the quantity.
     */
    public void printVendingItems(List<VendingItems> list) {
        for (VendingItems item :list) {
            System.out.printf("%-2s | %-18s | $%.2f | Items left: %d %n", item.getCode(), item.getName(), item.getPrice(), item.getQuantity());
        }
    }

/**
 *  Add elements to a list of VendingItem objects containing the code, name, price, and quantity of each item
 * in the vendingItems list that has a quantity greater than zero. If an item has a quantity
 * of zero, it is labeled as "SOLD OUT".
 * @return list returns a list of Vending Machine items.
 */
    public List<VendingItems> initializeAvailableitemsList() {
        //stores the file source of each vending machine product's information
        List<VendingItems> availableItems = new ArrayList<>();
        availableItems.addAll(vendingItems);
        return availableItems;
    }

}
