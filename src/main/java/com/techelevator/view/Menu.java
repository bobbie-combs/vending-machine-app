/**
 * Validates customer's choice based on the input customer gives and displays menu options
 */
package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {
    private PrintWriter out;
    private Scanner in;

    /**
     * Constructor that takes an InputStream and OutputStream as parameters.
    */
    public Menu(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    /**
     * Constructor created for unit testing
     */
    public Menu() {

    }

    /**
     * Method that displays the options and allows the user to select one.
     * @param options array of options customer can choose from.
     * @return choice that customer selected.
     */
    public Object getChoiceFromOptions(Object[] options) {
        Object choice = null;
        while (choice == null) {
            displayMenuOptions(options);
            choice = getChoiceFromUserInput(options);
        }
        return choice;
    }

    /**
     * Verifies user's choice and if it is a valid option choice
     * @param options amount of options available for customer to choose from
     * @return Object (choice) to the getChoiceFromUserInput
     */
    private Object getChoiceFromUserInput(Object[] options) {
        Object choice = null;
        String userInput = in.nextLine();
        try {
            int selectedOption = Integer.valueOf(userInput);
            if (selectedOption > 0 && selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch (NumberFormatException | NullPointerException e) {
            // eat the exception, an error message will be displayed below since choice will be null
           System.err.println(System.lineSeparator() + " *** " + userInput + " invalid denomination or number " + System.lineSeparator());
        }
        if (choice == null) {
            out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
        }
        return choice;
    }

    /**
     * Displays  to options available for customer to choose from.
     * @param options the array of options displayed to Customer.
     */
    private void displayMenuOptions(Object[] options) {
        out.println();
        for (int i = 0; i < options.length; i++) {
            int optionNum = i + 1;
            out.println(optionNum + ") " + options[i]);
        }
        out.print(System.lineSeparator() + "Please choose an option >>> ");
        out.flush();
    }

    /**
     *Displays user guide and instructions for using the vending machine.
     *Prompts the user to enter either 1 to view the instructions or 2 to skip.
     */
    public void userGuide(){
        System.out.print("HELLO, PLEASE PRESS 1 FOR INSTRUCTIONS OR 2 TO SKIP ");
        Scanner scanner = new Scanner(System.in);
        String instruction;
        instruction = scanner.nextLine();
        if (instruction.equals("1")){
            System.out.println("----->>>>> Follow the steps below <<<<<-----");
            System.out.println("MAIN MENU OPTIONS");
            System.out.println("Step ONE: PRESS 1 TO SEE VENDING MACHINE ITEMS");
            System.out.println("Step TWO: PRESS 2 TO PURCHASE AN ITEM");
            System.out.println("PURCHASE MENU OPTIONS");
            System.out.println("Step THREE: PRESS 1 TO INSERT YOUR MONEY");
            System.out.println("Step FOUR: PRESS 1 OR 2 OR 3 OR 4 TO SELECT THE AMOUNT OF MONEY YOU WANT TO FEED");
            System.out.println("Step FIVE: PRESS 2 TO SEE PRODUCT AND PURCHASE AN ITEM");
            System.out.println("Step SIX: PRESS 1 - 16 TO PURCHASE AN ITEM");
            System.out.println("Step SEVEN: PRESS 3 TO FINISH TRANSACTION AND RETURN TO MAIN MENU");
            System.out.println("MAIN MENU OPTION TO LEAVE VENDING MACHINE");
            System.out.println("LAST STEP: PRESS 3 TO EXIT");
        }else if (instruction.equals("2")){
            System.out.println("----->>>>> Proceeding without user guide <<<<<-----");
        }
    }
    /**
     * Exits the application for customer.
     */
    public void exitApplication() {
        out.flush();
        System.exit(1);
    }
}
