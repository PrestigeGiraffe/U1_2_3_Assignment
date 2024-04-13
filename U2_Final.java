/*
 * Purpose: 
 * Author: Johnson Yep
 */

import java.util.Scanner;
import java.util.Arrays;

public class U2_Final {
    public static void main(String [] args) {
        // declaring variables
        Scanner read = new Scanner(System.in);
        int numOfStudents;
        String userInput;

        // prompting user for how many students they have
        System.out.print("How many students do you have?: ");
        numOfStudents = read.nextInt();
        read.nextLine(); // next int doesn't consume the new line so this finishes it and allows me to use nextLine() without it skipping a line

        // declaring an array based on how many students they have
        String[][] students = new String[numOfStudents][4];

        System.out.println("Please enter the information of your students in this format: First Name, Last Name, U1 Grade, U2 Grade:");
        for (int i = 0; i < numOfStudents; i++) {
            System.out.printf("\nStudent #%d Information: ", i+1);
            userInput = read.nextLine();
        }

        // splitting the information into separate values
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < userInput.length(); y++) {
                int counter = 0; // counts how many
                int previousCounter = 0; // determines where the last piece of info left off
                char currentChar = userInput.charAt(y);
                System.out.print(currentChar);
                if (currentChar == ',' || currentChar == ' ') { // if the current character is a comma or a space
                    //System.out.print("sep");
                    students[i][x] = userInput.substring(previousCounter, counter);
                    previousCounter = counter;
                    counter = 0;
                }
                else {
                    counter++;
                }
            }
        }
        read.close();

        System.out.print(Arrays.toString(students[0]));
    }
}