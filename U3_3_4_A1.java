/*
 * Purpose: Ask the user to input the name and weight of items, and store that information in a separate class and then display it to the user after they finish giving all the information.
 * Author: Johnson yep
 */

import java.util.Scanner;

public class U3_3_4_A1 {
    public static void main(String[] args) {
        // CREATING OBJECT OF SCANNER CLASS
        Scanner read = new Scanner(System.in);
        

        // VARIABLES
        int numOfItems;

        // PROMPTING THE USER
        System.out.print("How many items would you like to enter?: ");
        numOfItems = read.nextInt();
        Item[] item = new Item[numOfItems]; // create an array of the Item class, set the size to how many items the user wants to enter

        for (int i = 0; i < numOfItems; i++) {
            item[i] = new Item(); // create object of item and place it in [i] of the array

            System.out.printf("Please enter the name of item #%d: ", i+1);
            item[i].name = read.next();

            System.out.printf("Please enter the weight of item #%d: ", i+1);
            item[i].weight = read.nextDouble();
        }
        read.close();

        // OUTPUTTING INFORMATION TO USER
        System.out.printf("\n%15s%15s\n", "Item", "Weight");
        for (Item i: item) {
            System.out.printf("%15s%15.1f\n", i.name, i.weight);
        }
    }
}