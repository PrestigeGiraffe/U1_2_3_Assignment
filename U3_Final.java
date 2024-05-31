/*
 * Purpose: To ask the user to enter a slope and a y-intercept and output the line form equation. Then ask the user to for a file name and range of x values to create the file.
 * Author: Johnson Yep
 */

import java.util.Scanner;

public class U3_Final {
    public static void main(String[] args) {
        // CREATING OBJECT OF SCANNER CLASS
        Scanner read = new Scanner(System.in);

        // PROMPTING USER FOR THE SLOPE AND Y-INTERCEPT
        System.out.print("Enter the slope: ");
        int slope = read.nextInt();

        System.out.print("Enter the y-intercept: ");
        int yIntercept = read.nextInt();
        
        // CREATING OBJECT OF EQUATION CLASS
        Equation equation = new Equation(slope, yIntercept); // sets its values via the constructor

        // RETURNING LINE FORM EQUATION
        String lineForm = equation.returnLineForm(); // calls the returnLineForm method from the equation class and stores it in a string variable so it can be used in the next line
        System.out.printf("The equation of the line is %s\n", lineForm);

        // CREATING TABLE OF VALUES IN A FILE
        System.out.println("\nCreate a table of values...");
        System.out.print("Enter the min x value: ");
        int minX = read.nextInt();
        System.out.print("Enter the max x value: ");
        int maxX = read.nextInt();
        System.out.print("Enter the file name: ");
        String fileName = read.next();
        
        boolean successful = equation.createTableFile(minX, maxX, fileName); // value to store whether or not the file was successfully made
        if (successful) {
            System.out.println("File was saved successfully.");
        }
        else {
            System.out.println("File was not saved.");
        }

        read.close();
    }
}