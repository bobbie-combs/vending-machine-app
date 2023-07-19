/** VendingMachineCLI is a class that represents a vending machine command line interface.
 It allows a user to interact with the vending machine by selecting options from menus.
 The class contains several static constants that represent the main menu options, the purchase menu
 options, the denominations of money that can be fed into the vending machine, and a string that
 represents the option to give change. The class also contains several static variables that keep
 track of the vending machine inventory, the list of items that have been purchased, the amount of
 money that has been fed into the vending machine, and the current time.
 @author Jayan Desilva, Bobbie Combs, Dawit Oqubaldet
 @version 1.0
 */
package com.techelevator.view;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.Objects;


public class VendingMachineCLI {
    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static  final String MAIN_MENU_OPTION_SALES_REPORT = "4";
    private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT};
    private static  final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static  final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static  final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};
    private static final String[] FEED_MONEY = {"$1.00", "$5.00", "$10.00","$20.00", "Return to Purchase Menu"};
    private static final String GIVE_CHANGE = "GIVE CHANGE";
    private Menu menu;
    private double currentMoney = 0.00;
    private static List<VendingItems> itemsList;
    private static List<VendingItems> availableItemsList;
    private static Inventory inventory = new Inventory(itemsList, availableItemsList);
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss aa");
    private String formattedDate = DATE_FORMAT.format(new Date());


    /**
     * A constructor method that creates an instance of the Menu class
     @param menu the menu object
     */
    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public String getFormattedDate() {
        return this.formattedDate;
    }

    /**
     The main method that runs the vending machine CLI, which displays a menu of options for the user to choose from.
     This method runs indefinitely, until customer selects the exit option.
     */
    public void run() {

        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            // display vending machine items
            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                List<VendingItems> vendingItems = inventory.getAvailableItems();
                inventory.printVendingItems(vendingItems);
                //displays purchase menu options to customer
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                boolean isPurchaseMenu = true;
                //loops through purchase menu until user chooses "Finish Transaction" option
                while (isPurchaseMenu) {
                    choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
                    // do purchase
                    System.out.printf("Current Money Provided: $%.2f%n", currentMoney);
                    // updates amount of money in vending machine based on what customer feeds
                    if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
                        System.out.print("Please enter an amount from following denomination:");
                        String feedMoney = (String) menu.getChoiceFromOptions(FEED_MONEY);
                        //limited options for customer so customer feeds whole dollar amounts
                        while (!Objects.equals(feedMoney, FEED_MONEY[4])) {

                            if (Objects.equals(feedMoney, FEED_MONEY[0])) {
                                double tempBill = Double.parseDouble(FEED_MONEY[0].substring(1));
                                currentMoney += tempBill;
                                printLogForBills(PURCHASE_MENU_OPTION_FEED_MONEY.toUpperCase(), tempBill, currentMoney);
                            } else if (Objects.equals(feedMoney, FEED_MONEY[1])) {
                                double tempBill = Double.parseDouble(FEED_MONEY[1].substring(1));
                                currentMoney += tempBill;
                                printLogForBills(PURCHASE_MENU_OPTION_FEED_MONEY.toUpperCase(), tempBill, currentMoney);
                            } else if (Objects.equals(feedMoney, FEED_MONEY[2])) {
                                double tempBill = Double.parseDouble(FEED_MONEY[2].substring(1));
                                currentMoney += tempBill;
                                printLogForBills(PURCHASE_MENU_OPTION_FEED_MONEY.toUpperCase(), tempBill, currentMoney);
                            } else if (Objects.equals(feedMoney, FEED_MONEY[3])) {
                                double tempBill = Double.parseDouble(FEED_MONEY[3].substring(1));
                                currentMoney += tempBill;
                                printLogForBills(PURCHASE_MENU_OPTION_FEED_MONEY.toUpperCase(), tempBill, currentMoney);
                            } else{
                                System.out.println("The Machine accepts only $1, $5, $10 & $20 bills");

                            }

                            feedMoney = (String) menu.getChoiceFromOptions(FEED_MONEY);
                        }
                        System.out.printf("Current Money Provided: $%.2f%n", currentMoney);

                    } //displays a list of vending machine items to customer and allows them to purchase items and update quantity of item
                    if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
                        // Calls to available items from Inventory class and create an array of available vending items.
                        availableItemsList = inventory.getAvailableItems();
                        VendingItems[] tempArray = availableItemsList.toArray(new VendingItems[0]);
                        System.out.println(" *** Choose your product code from available items *** ");
                        //Creates an array of Strings to pop up the select product menu
                        String[] tempStringArr = new String[availableItemsList.size()];
                        for(int i = 0; i<tempStringArr.length; i++) {
                            tempStringArr[i] = String.format("%-2s | %-18s | %.2f | %d %s",
                                    tempArray[i].getCode(), tempArray[i].getName(),
                                    tempArray[i].getPrice(), tempArray[i].getQuantity(),
                                    tempArray[i].getQuantity()>0 ? "" : "SOLDOUT");
                        }
                        choice = (String) menu.getChoiceFromOptions(tempStringArr);
                        String choiceCode = choice.split("\\|")[0].trim();

                        for (int i = 0; i < availableItemsList.size(); i++) {
                            if (choiceCode.equals(availableItemsList.get(i).getCode()) ) {
                                double price = availableItemsList.get(i).getPrice();
                                int quantity = availableItemsList.get(i).getQuantity();
                                // checks if customer has enough money in the vending machine

                                if (price <= currentMoney && !choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT) && availableItemsList.get(i).getQuantity()>0) {
                                    currentMoney -= price;
                                    availableItemsList.get(i).setQuantity(quantity - 1);
                                    //Calls to the method printSound() to display a message to customer after purchase because on the type of product
                                    printSound(availableItemsList, i);
                                    //add items bought to purchase log file Log.txt
                                    printLogForBills(inventory.getVendingItems().get(i).getName(), price, currentMoney);
                                }
                                else if(availableItemsList.get(i).getQuantity() <= 0) {
                                    System.out.println(availableItemsList.get(i).getName() + " SOLD OUT, Please select another product");

                                } else {
                                    System.out.printf("Your balance is $ %.2f, please add money before proceeding to purchase",currentMoney);
                                }
                                choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT);
                            }

                        }
                    } //returns remaining money in vending machine to customer in smallest amount of quarters, nickels, and dimes, and returns to main menu
                    if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
                        final double QUARTERS = 0.25;
                        final double DIMES = 0.10;
                        final double NICKELS = 0.05;
                        int numOfQuarters = 0;
                        int numOfDimes = 0;
                        int numOfNickels = 0;
                        double refund = currentMoney;
                        int zero = 0;

                        if (refund > zero) {
                            if (refund / QUARTERS >= zero) {
                                numOfQuarters += (refund / QUARTERS);
                                refund -= (QUARTERS * numOfQuarters);
                            }
                            if (refund / DIMES >= zero) {
                                numOfDimes += (refund / DIMES);
                                refund -= (DIMES * numOfDimes);
                            }
                            if (refund / NICKELS >= zero) {
                                numOfNickels += (refund / NICKELS);
                                refund -= (NICKELS * numOfNickels);
                            }
                        }//prints message of change given to customer
                        String changeMessage = String.format("Your change is %d quarters, %d dimes, and %d nickels, totaling $%.2f", numOfQuarters, numOfDimes, numOfNickels, currentMoney);
                        System.out.println(changeMessage);
                        printLogForBills(GIVE_CHANGE, currentMoney, refund); //logs change given
                        currentMoney = 0.0;
                        isPurchaseMenu = false; //exits purchase menu loop
                    }
                }
            }
            else if (choice.equals(MAIN_MENU_OPTION_EXIT)) { //exits application for customer
                menu.exitApplication();
            }
            else { //hidden option to print sales report of the quantity purchased for each item
                choice.equals(MAIN_MENU_OPTION_SALES_REPORT);
                printSalesReport();
            }

        }
    }
    /**
     * Logs events to Log.txt file while using the vending machine such as purchasing products, feeding money, or getting change.
     @param name the type of log added
     @param changeAmount the amount added, given, or spent while using vending machine
     @param newBalance the new balance after the transaction
     */
    public  void printLogForBills(String name, double changeAmount, double newBalance) {
        String moneyAmounts = String.format (" $%.2f $%.2f",changeAmount,newBalance);
        TLog.logTransactions(formattedDate + " " + name + moneyAmounts);
        if (!name.equals(GIVE_CHANGE)) {
            System.out.println("Current Money Provided: " + String.format("$%.2f", newBalance));
        }
    }

    /**
     * Prints Sales Report showing the total money spent by customer
     * and list of products and the quantity bought.
     */
    public  void printSalesReport() {
        double total = 0.0;
        for(VendingItems item: inventory.getVendingItems()) {
            int numOfSales = 5;
            numOfSales = Math.abs(item.getQuantity() - numOfSales);
            System.out.printf("%s|%d %n",item.getName(),numOfSales);
            total += (item.getPrice() * numOfSales);
        }
        System.out.println();
        System.out.printf("**TOTAL SALES** $%.2f %n",total);
    }

    /**
     *  Displays message to customer after making a purchase based on type.
     * Abstract method required for the Candy, Gums
     * , Drinks, and Chip class
     * @return String message to customer
     */
    private static void printSound (List<VendingItems> list, int i) {
        if (list.get(i).getType().equalsIgnoreCase("Chip")) {
            System.out.println(inventory.getVendingItems().get(i).playSound());
        } else if (inventory.getVendingItems().get(i).getType().equalsIgnoreCase("Drink")) {
            System.out.println(inventory.getVendingItems().get(i).playSound());
        } else if (inventory.getVendingItems().get(i).getType().equalsIgnoreCase("Candy")) {
            System.out.println(inventory.getVendingItems().get(i).playSound());
        } else if (inventory.getVendingItems().get(i).getType().equalsIgnoreCase("Gum")) {
            System.out.println(inventory.getVendingItems().get(i).playSound());
        }
    }
    /**
     * The main method that runs the vending machine program.
     *Initializes the menu, creates the inventory list from a file, and runs the CLI.
     *@param args
     */
    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        inventory.createListFromFile();
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        menu.userGuide();
        cli.run();
    }
}
