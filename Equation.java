/*
 * Purpose: Class that contains values for the slope and y-intercept of a line. Also contains a method to return a line form equation using the slope and y-intercept values and a method to write a table of x and y values based on a range that the user enters into a file that the user can name.
 * Author: Johnson Yep
 */

import java.io.FileWriter;
import java.io.IOException;

public class Equation {
    // CLASS VARIABLES
    int slope;
    int yIntercept;

    public Equation() {} // Empty constructor

    public Equation(int slope, int yIntercept) { // Constructor to set the slope and y-intercept when you create the object
        this.slope = slope;
        this.yIntercept = yIntercept;
    }

    public String returnLineForm() {
        return "y = " + slope + "x + " + yIntercept; // returns "y = mx + b", m being slope and b being yInt
    }

    public boolean createTableFile(int from, int to, String fileName) {
        try { // Tries to run the code in this block and if it errors out then it will catch it and print "Error"
            FileWriter file = new FileWriter(fileName);
            for (int x = from; x <= to; x++) { // loop from the minX to maxX
                file.write(x + " " + (x*slope+yIntercept)); // prints the x value and the corresponding y value
                file.write(System.lineSeparator()); // new line
            }
            file.close();
            return true; // returns true for whether or not the file was saved successfully
        }
        catch(IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return false; // returns false if the try block throws an exception
    }
}