/*
 * Purpose: Gives the user 5 options: generate 10 random numbers in an array, display the array, quit the program, swap the numbers in the array, and shuffle the numbers in the array.
 * Author(s): Johnson Yep & Mr. Han (copied and pasted example 4 from U3-1-Methods note)
 */

import java.util.Scanner;

public class U3_1_2_A1 {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);


        int[] numbers = new int[10];
        
        String option = "0";
        while(true){
            System.out.println("1) Generate");
            System.out.println("2) Display");
            System.out.println("3) Swap");
            System.out.println("4) Shuffle");
            System.out.println("5) Quit");
            System.out.print("Choose one of the options: ");
            option = read.next();

            System.out.println(""); // adds a space between choosing the options and the actual function of each option

            if (option.equals("1")) {
                generate(numbers);
                System.out.println("The numbers have been generated.");
            }
            else if (option.equals("2")) {
                System.out.println("Here are the ten random numbers: ");
                display(numbers);
                System.out.println("");
            }
            else if(option.equals("3")) {
                // display starting numbers
                System.out.print("Starting Array: "); 
                display(numbers);
                
                // prompt user on what numbers they want to swap
                System.out.println("\nWhich two positions do you want to swap?: ");
                int pos1 = read.nextInt();
                int pos2 = read.nextInt();
                boolean canSwap = swap(pos1, pos2, numbers);

                // printing if swap was successful or not
                if (canSwap) {
                    System.out.println("Swap completed successfully.");
                }
                else {
                    System.out.println("Swap failed.");
                }

                // print the array again after it has been swapped
                System.out.print("End Array: ");
                display(numbers);
            }
            else if (option.equals("4")) {
                // display starting numbers
                System.out.print("Starting Array: ");
                display(numbers);

                shuffle(numbers);
                System.out.println("\nThe array has been shuffled.");
                System.out.print("End Array: ");
                display(numbers);
            }
            else if(option.equals("5")) {
                System.out.println("Exiting program.");
                break;
            }
            else {
                System.out.println("Invalid input.");
            }

            System.out.println("\n"); // spaces out for new input
        }
        read.close();
    }

    //Method to generate random numbers. The parameter has a different
    static void generate(int[] x){
        for (int i = 0; i < x.length; i++) {
            x[i] = (int)(Math.random()*100 + 1);
        }
    }

    //Method to display the random numbers in the array being passed.
    static void display(int[] numbers){
        for (int n : numbers) {
            System.out.print(n + " ");
        }
    }

    static boolean swap(int pos1, int pos2, int[] array) {
        if (pos1 < array.length && pos1 > 0 && pos2 <= array.length && pos2 > 0) { // checks the swap the values that the user entered are within bounds of the array
            // -1 from every position since the array starts at 0 and the user will start at 1
            int holder = array[pos1-1];
            array[pos1-1] = array[pos2-1];
            array[pos2-1] = holder;
            return true;
        }
        return false; // return false if the if statement does not go through
    }

    static void shuffle(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randomPlace = (int)Math.floor(Math.random()*array.length); // math.floor the array length because index starts at 0
            int holder = array[i];
            array[i] = array[randomPlace];
            array[randomPlace] = holder;
        }
    }
}