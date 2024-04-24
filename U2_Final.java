/*
 * Purpose: To prompt the user to enter information about their students, and then output each students' average, the class average, and the class median. Then it will determine which student has the highest and lowest grade. The information prompted will include: how many students the class has, and each student's information (First name, Last name, Unit 1 grade, Unit 2 grade).
 * Author: Johnson Yep
 */

import java.util.Scanner;

public class U2_Final {
    public static void main(String [] args) {

        // -------------------------------------------------- Part A: Enter & Separate Data --------------------------------------------------

        // declaring variables
        Scanner read = new Scanner(System.in);
        int numOfStudents;
        String userInput;

        // prompting user for how many students they have
        System.out.print("How many students do you have?: ");
        numOfStudents = read.nextInt();
        read.nextLine(); // next int doesn't consume the new line so this finishes it and allows me to use nextLine() without it skipping a line

        // declaring an array based on how many students they have, and 4 elements in each array since it's only storing first and last name and 2 grades
        String[][] students = new String[numOfStudents][4];

        System.out.println("Please enter the information of your students in this format: First Name, Last Name, U1 Grade, U2 Grade:\n");
        for (int i = 0; i < numOfStudents; i++) {
            System.out.printf("Student #%d Information: ", i+1);
            userInput = read.nextLine();

            // splitting the information into separate values
            int numOfWords = 0; // index for which index of the array to store the word
            int counter = 0; // counts how many characters are in the current word/piece of information
            int previousCounter = 0; // determines where the last piece of info left off

            for (int y = 0; y < userInput.length(); y++) { // loops through the user's input
                
                char currentChar = userInput.charAt(y);

                if (currentChar == ',' || currentChar == ' ') { // if the current character is a comma or a space
                    if (userInput.charAt(y-1) == ',' || userInput.charAt(y-1) == ' ') { // if the previous character was also a comma or space then just skip it
                        previousCounter++;
                    }
                    else {
                        students[i][numOfWords] = userInput.substring(previousCounter, previousCounter + counter); // create a substring between where the last word left off & where it left off + the length of the current word
                        previousCounter += (counter + 1); // counter + 1 to skip the current comma or space so it doesn't store something like: ,hi
                        counter = 0; // reset counter so the number of characters in the current word is 0 again
                        numOfWords++; // increase index for next word to be stored in correct index of the array
                    }
                }
                else { // if it's just a normal character then increase counter
                    counter++;
                }

                // if statement to check if there is no comma or space to get the last word and or if the user just enters a single piece of info without any commas or spaces
                if (y == (userInput.length()-1) && counter > 0) { // if it's the last index and there are non space/comma characters that haven't been reset
                    students[i][numOfWords] = userInput.substring(previousCounter, previousCounter + counter); // same concept as the first substring explanation
                }
            }
        }
        read.close();

        // -------------------------------------------------- Part B: Calculate Student Averages --------------------------------------------------
        
        double[] averages = new double[numOfStudents]; // array to store the averages of 2 units for each student

        // calculate and store averages into array
        for (int i = 0; i < numOfStudents; i++) {
            for (int x = 2; x < students[i].length; x++) { // starts at 2 since the first 2 elements are first and last name
                double grade = Double.parseDouble(students[i][x]); // converts information to double so it can be used in calculations
                averages[i] += grade; // adds sum of the 2 units
            }

            averages[i] /= students[i].length - 2; // divide the amount of units there are (-2 because the first 2 indexes are first and last names)
        }
        
        // output information to user
        System.out.printf("\n%-15s %-15s %-15s %s", "Name", "Unit 1", "Unit 2", "Average");
        for (int student = 0; student < numOfStudents; student++) {
            System.out.printf("\n%-15s %-15s %-15s %-15.1f", students[student][0] + " " + students[student][1], students[student][2], students[student][3], averages[student]);
        }

        // -------------------------------------------------- Part C: Calculate Class Average & Class Median --------------------------------------------------

        // calculate class average
        double sumAverage = 0; // variable to store the sum of all the students' averages added together
        for (double studentAverage: averages) {
            sumAverage += studentAverage;
        }
        double classAverage = sumAverage / numOfStudents; // divide the sum of all averages by the amount of students in the class

        // loop to bubble sort the student averages so the median can be found
        boolean requiresSort = true;
        while (requiresSort) { // keeps looping until there are no more sorts required
            requiresSort = false; // sets it to false as soon as loop starts, so if the if statement does not go through at all then the while loop will stop
            for (int i = 0; i < numOfStudents - 1; i++) { // -1 from the number of students since the if statement below checks for i+1 so it would go out of bounds if I did not -1
                if (averages[i] > averages[i+1]) {
                    double holder = averages[i];
                    averages[i] = averages[i+1];
                    averages[i+1] = holder;
                    requiresSort = true;
                } 
            }
        }

        // calculate class median
        double median;
        double half = (numOfStudents-1) / 2.0; // -1 from num of students because index starts at 0, and divide by 2.0 and not 2 because it would do integer division otherwise
        if (numOfStudents % 2 == 0) { // if the amount of students is even then add the 2 medians and divide by 2 to get the true median
            median = (averages[(int)Math.floor(half)] + averages[(int)Math.ceil(half)]) / 2;
        }
        else { // if it's odd then the median is just the middle number
            median = averages[(int)half];
        }

        // output information to user
        System.out.printf("\n\nClass Average: %.1f%%", classAverage);
        System.out.printf("\nClass Median: %.1f%%\n", median);

        // -------------------------------------------------- Part D: Find The Highest And Lowest Grade --------------------------------------------------

        // variables to store information that will be outputted
        double lowestGrade = 100, highestGrade = 0; // lowest grade is set to 100 and highest is set to 0 because the loop below will check if each student's grade is higher or lower than these values, so this ensures the the first grade checked will always be set to lowest or highest
        int highestGradeUnit = 0, lowestGradeUnit = 0;
        String lowestStudent = "", highestStudent = ""; 

        for (int i = 0; i < numOfStudents; i++) {
            for (int unit = 2; unit < students[i].length; unit++) { // start at 2 since indexes 0 and 1 are first and last name and the rest are unit grades
                double currentGrade = Double.parseDouble(students[i][unit]); // converts the current grade into a double so it can be used in calculations
                String currentStudent = students[i][1] + ", " + students[i][0]; // string variable to store the current student's name in this format: Last, First
                int currentUnit = unit - 1; // -1 from the unit because we started at 2 in this loop to account for the first and last name

                // check for lowest
                if (currentGrade <= lowestGrade) { // checks if the current iteration's grade is lower than the last one
                    lowestGrade = currentGrade;
                    lowestStudent = currentStudent;
                    lowestGradeUnit = currentUnit;

                }

                // check for highest
                if (currentGrade >= highestGrade) { // checks if the current iteration is bigger than the previous iteration's highest
                    highestGrade = currentGrade;
                    highestStudent = currentStudent;
                    highestGradeUnit = currentUnit;
                }
            }
        }

        // output to user
        System.out.printf("\n%s has the lowest grade: %.1f%% (Unit %d)", lowestStudent, lowestGrade, lowestGradeUnit);
        System.out.printf("\n%s has the highest grade: %.1f%% (Unit %d)", highestStudent, highestGrade, highestGradeUnit);
    }
}