/*
 * Purpose: Program that converts alien numbers into regular numbers and vice versa
 * Author: Johnson Yep
 */

 import java.util.Scanner;

 public class U3_1_2_A2 {
    public static void main(String[] args) {
        // declaring variables
        Scanner read = new Scanner(System.in);

        System.out.print("Type an alien number: ");
        String alienNum = read.nextLine();
        int regularNum = alienToRegular(alienNum);
        System.out.printf("%s = %d\n", alienNum, regularNum);

        System.out.print("\nType a regular number: ");
        regularNum = read.nextInt();
        alienNum = regularToAlien(regularNum);
        System.out.printf("%d = %s\n", regularNum, alienNum);
        read.close();
    }

    static int alienToRegular(String alienNumber) {
        int regularNum = 0;

        for (int i = 0; i < alienNumber.length(); i++) {
            // singles out each character starting from the right using charAt, and converts it to an integer and multiplies it by 6 to the power of i
            regularNum += Character.getNumericValue(alienNumber.charAt(alienNumber.length()-1-i))*(Math.pow(6, i)); // -1-i because length is out of bounds since charAt index starts at 0
        }
        
        return regularNum;
    }

    static String regularToAlien(int regularNumber) {
        String alienNumber = "";

        if (regularNumber == 0) { // when it is the last number return nothing
            return "";
        }
        
        alienNumber += regularNumber % 6; // concatenate the remainder of the regular number to the alien number
        regularNumber /= 6; // divide the regular number by 6

        return regularToAlien(regularNumber) + alienNumber; // call this method again, and return what the method returns before the alien number so it returns it in backwards order
    }
 }