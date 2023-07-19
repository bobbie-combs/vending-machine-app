package com.techelevator.view;


import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryTest {

    /**
     *JUnit test case for verifying the functionality of createListFromFile()  method
     */
    @Test
    public void test_Create_List_From_File() throws FileNotFoundException {
        // Create an instance of Inventory
        Inventory inventory = new Inventory();
        // Set the filename for the vending machine data
        String filename = "vendingmachine.csv";
        // Create a File object for the expected data file
        File dataFileExpected= new File (filename);

        // Create a File object for the absolute path of the expected data file
        File absoluteDataFileExpected = new File(dataFileExpected.getAbsolutePath());

        // Get the expected vending items list
        List<VendingItems> expectedType = inventory.getVendingItems();
        // Create a new list for provided vending items
        List<Inventory> providedType = new ArrayList<>();
        // Create a new list for testing purposes
         List<VendingItems> listTest = new ArrayList<>();
        // Use a Scanner to read the expected data file
        try(Scanner input = new Scanner(dataFileExpected)) {
            while (input.hasNextLine()) {
                // Read each line and split it into an array
                String line = input.nextLine();
                String[] lineToArr = line.split("\\|");

                // Create a new VendingItem object based on the type of the item
                switch (lineToArr[3]) {
                    case "Chip":
                        listTest.add(new Chips(lineToArr[0], lineToArr[1], Double.parseDouble(lineToArr[2]), lineToArr[3], 5));
                        break;
                    case "Drink":
                        listTest.add(new Drinks(lineToArr[0], lineToArr[1], Double.parseDouble(lineToArr[2]), lineToArr[3], 5));
                        break;
                    case "Candy":
                        listTest.add(new Candy(lineToArr[0], lineToArr[1], Double.parseDouble(lineToArr[2]), lineToArr[3], 5));
                        break;
                    case "Gum":
                        listTest.add(new Gums(lineToArr[0], lineToArr[1], Double.parseDouble(lineToArr[2]), lineToArr[3], 5));
                        break;
                }
                // Assert that the expected vending items list and provided vending items list are equal
                Assert.assertEquals("Please provide right type in the List collection.", expectedType, providedType);
                // Assert that the absolute path of the expected data file matches the inventory's absolute data file
                Assert.assertEquals("Please provide a relative pathname", absoluteDataFileExpected, inventory.getAbsDataFile());
            }
        }
    }
    /**
     *JUnit test case for verifying the functionality of printVendingItems() method
     */
    @Test
    public void test_Print_Vending_Items() {
        // Create an instance of Inventory
        Inventory inventory = new Inventory();
        // Loop through each VendingItem in the inventory
        for (VendingItems item: inventory.getVendingItems()) {
            // Print out the details of the item
            System.out.printf("%-2s | %-18s | $%.2f | Item left: %d %n",item.getCode(), item.getName(), item.getPrice(), item.getQuantity());
            if (item.getQuantity() == 0){
                // If the item is sold out, print a message indicating that it is sold out
                System.out.printf("%-2s | %-18s | $%.2f | Item left: %d SOLD OUT %n%n", item.getCode(), item.getName(), item.getPrice(), 1);
                // Assert that the item quantity is 0
                int actual = item.getQuantity();
                Assert.assertEquals("Item is totally SOLD OUT. Item quantity is zero", 0,actual);
            }
        }
    }
}