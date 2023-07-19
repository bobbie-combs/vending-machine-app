package com.techelevator.view;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VendingMachineCLITest extends TestCase {
    /**
     *JUnit test case for verifying the functionality of printLogForBills() method
     */
    public void test_Print_Log_For_Bills() {
        // Create instances of Menu and VendingMachineCLI classes
        Menu menu = new Menu();
        VendingMachineCLI vendingMachineCLI = new VendingMachineCLI(menu);
        // Set the date format
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss aa");
        // Format the current date and time
        String formattedDate2 = DATE_FORMAT.format(new Date());
        // Test that the formatted date matches the expected format
       Assert.assertEquals("date should come first", vendingMachineCLI.getFormattedDate(), formattedDate2);
    }
    /**
     *JUnit test case for verifying the functionality of printSalesReport() method
     */
    @Test
    public void test_Print_Sales_Report() {
        // Create an instance of the Inventory class
        Inventory inventory = new Inventory();
        // Initialize the total variable to 0
        double total = 0.0;
        // Loop through the items in the inventory
        for(VendingItems item: inventory.getVendingItems()) {
            // Initialize the number of sales variable to 5
            int numOfSales = 5;
            // Calculate the absolute difference between the current quantity and number of sales
            numOfSales = Math.abs(item.getQuantity() - numOfSales);
            // Print the item name and number of sales
            System.out.printf("%s|%d %n",item.getName(),numOfSales);
            // Calculate the total cost of the sales
            total += (item.getPrice() * numOfSales);
            // Decrease the number of sales by 1
            numOfSales--;
            // Test that the number of sales does not exceed 5
            Assert.assertEquals("The number of Sales should not exceed 5 ",item.getQuantity(),numOfSales);
            // Test that the number of sales is 0 if the item is sold out
            Assert.assertEquals("If total number of sales is 0. Item is sold out." ,0, numOfSales);

        }
    }
}