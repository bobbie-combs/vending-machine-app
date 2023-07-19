/** This class extends the RuntimeException class and create exception for a customer message to be displayed for write to the log file
 */
package com.techelevator.view;
public class TLogException extends RuntimeException {
    public TLogException(String message) {
        super(message);
    }
}
