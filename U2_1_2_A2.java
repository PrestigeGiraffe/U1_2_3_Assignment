/*
 * Program Purpose: To check if a value inputted by the user is a positive integer with no period or comma. If the input is valid, then the square root of the number will be outputted to the user.
 * Author: Johnson Yep
 */

 import java.util.Scanner;

 public class U2_1_2_A2 {
    public static void main(String [] args) {
        // variables
        Scanner read = new Scanner(System.in);
        String userInput;
        boolean validInput = true; // variable to store whether or not what the user inputted is valid or not

        // prompting the user
        System.out.print("Type a positive integer (no commas): ");
        userInput = read.nextLine();
        read.close();

        int inputLength = userInput.length();

        // loop to check for commas, periods, and letters
        for (int i = 0; i < inputLength; i++) { // loops through each character in the string
            if (userInput.charAt(i) > 57 ||  userInput.charAt(i) < 48) { // 48 is ascii value for '0', 57 is ascii value for '9'; this if statement checks if any of the characters in the user's input are not a number from 0-9
                validInput = false;
            }
        }

        if (validInput) {
            double convNum = Double.parseDouble(userInput); // converts the string to a double so it can be used in calculations
            System.out.printf("The square root of %s is %.2f", userInput, Math.sqrt(convNum)); // prints the square root with only 2 decimal places
        }
        else {
            System.out.printf("%s is an invalid input.", userInput);
        }
    }
 }