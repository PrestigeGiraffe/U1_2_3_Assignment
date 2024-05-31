/*
 * Purpose: To open and read a file and the prompt the user if they would like to delete that file if the file exists.
 * Author: Johnson Yep
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FileManager {
    public static void main(String[] args) {
        // CREATING OBJECT OF SCANNER CLASS
        Scanner read = new Scanner(System.in);

        // PROMPTING THE USER
        System.out.print("Enter the name of file you would like to open: ");
        String fileName = read.nextLine();

        boolean successful = openFile(fileName);
        if (successful) { // if the file opens successfully then ask the user if they want to delete it
            System.out.println("\nWould you like to delete the file (y/n)?: ");
            String userInput = read.nextLine();
            if (userInput.equalsIgnoreCase("y")) { // if the user enters y, then delete it, and if they enter any other input then do nothing
                deleteFile(fileName);
            }
        }
        read.close();
    }

    static boolean openFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner read = new Scanner(file);

            System.out.printf("%10s%10s\n", "x", "y"); // heading (right align for numbers)
            int i = 1; // counter for how many iterations the loop has gone through
            while(read.hasNextInt()) {
                System.out.printf("%10d", read.nextInt()); // right align numbers
                if (i % 2 == 0) { // every second number/iteration create a new line
                    System.out.println();
                }
                i++;
            }
            read.close();
            return true; // return true if the file was found
        }
        catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        }
        return false; // return false if it was not found
    }
 
    static void deleteFile(String fileName) {
        File file = new File(fileName);
        file.delete(); // this method does not need a try/catch because it only gets called if the file can be opened successfully meaning that the file has to exist
        System.out.println("File has been deleted.");
    }
}