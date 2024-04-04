/*
 * Program Purpose: To prompt the user to enter a set amount of grades, and then calculates and outputs the grades they entered, the average grade, and lowest grade.
 * Author: Johnson Yep
 */

import java.util.Scanner;

public class U2_3_4_A1 {
    public static void main(String[] args) {
        // declaring variable
        Scanner read = new Scanner(System.in);
        int numOfEntries;
        double sum = 0, average, lowest = 0;

        // prompting user for how many grades they want to enter
        System.out.print("Enter the number of grades being entered: ");
        numOfEntries = read.nextInt();

        double[] grades = new double[numOfEntries];

        // loop to prompt user to enter the grades and does calculations
        for (int i = 0; i < numOfEntries; i++) {
            System.out.printf("Enter grade (%d/%d): ", i+1, numOfEntries);
            grades[i] = read.nextDouble();
            sum += grades[i]; // adds up the sum of all of the grades to be divided later to get average
            if (i == 0 || grades[i] < grades[i-1]) { // if it is the loop's first iteration or if the grade is less than the previous grade then set it as the lowest grade
                lowest = grades[i];
            }
        }
        read.close();

        // calculate average
        average = sum / numOfEntries;

        // print output to user
        System.out.print("\nGrades you have entered: ");
        for (double i : grades) {
            System.out.printf("%.2f ", i);
        }

        System.out.printf("\nThe average of the grades: %.2f", average);
        System.out.printf("\nLowest grade: %.2f", lowest);
    }
}