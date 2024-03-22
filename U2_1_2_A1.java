/*
 * Program Purpose: Calculates the volume and surface area of a circle after the user inputs its radius
 * Author: Johnson Yep
 */

import java.util.Scanner;

public class U2_1_2_A1 {
    public static void main(String [] args) {
        Scanner read = new Scanner(System.in);
        double radius, volume, surfaceArea;

        // prompting user for the radius
        System.out.print("Enter the radius: ");
        radius = read.nextDouble();
        read.close();

        // calculations
        volume = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
        surfaceArea = 4 * Math.PI * Math.pow(radius, 2);

        // outputting answer to user
        System.out.printf("%20s %20s %20s\n", "Radius", "Volume", "Surface Area"); // print the headings
        System.out.printf("%20.1f %20.2f %20.2f", radius, volume, surfaceArea); // output the answers
    }
}