// This class represents a transaction log that writes to the log file based on vending machine events
package com.techelevator.view;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileOutputStream;

public class TLog {
    public static void logTransactions(String message) {
        String destinationFile = "Log.txt";
        File logFile = new File(destinationFile); // creates a new file object with the destination file path
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(logFile,true))) {
            // appends the message to the log file
            pw.println(message);
        } catch (TLogException | FileNotFoundException e) {
            // prints error if it occurs while writing to the log file Log.txt
            System.err.println("Cannot open the file for writing");
        }
    }
}
