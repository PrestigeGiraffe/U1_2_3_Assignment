import java.util.Scanner;
import java.lang.Math;

public class U1_Final {
    public static void main(String[] args) {
        // declaring and initializing variables
        Scanner read = new Scanner(System.in);
        double a = 0, b = 0, c = 0, perimeter, area;
        String triangleType, loopInput;
        boolean continueLoop = true;

        while (continueLoop) {
            // prompting the user to enter sides of their triangle
            System.out.println("Welcome to the triangle determiner!");
            System.out.print("\nPlease enter the side length of your triangle (1/3): ");
            a = read.nextDouble();
            System.out.print("\nPlease enter the side length of your triangle (2/3): ");
            b = read.nextDouble();
            System.out.print("\nPlease enter the side length of your triangle (3/3): ");
            c = read.nextDouble();

            // seeing if the user enters valid numbers
            if (a <= 0 || b <= 0 || c <= 0) {
                System.out.println("Please enter positive values.");
                continue;
            }

            // determining if the sides given can form a triangle
            if (a + b > c && b + c > a && a + c > b) {} 
            else {
                System.out.println(a+", "+b+", and "+c+" cannot form a triangle.");
                System.out.println("******************************");
                continue;
            }


            // determining triangle type
            if (a == b && b == c && c == a) { // if all sides are equal
                triangleType = "Equilateral";
            }
            else if (a == b || b == c || c == a) { // if any two sides are equal
                triangleType = "Isosceles";
            }
            else { // any other out come
                triangleType = "Scalene";
            }

            // calculating perimeter
            perimeter = a + b + c;

            // calculating area
            double s = (a + b + c) / 2;
            area = Math.sqrt(s * (s-a) * (s-b) * (s-c));

            // outputting results to user
            System.out.println(a + ", " + b + ", and " + c + " forms a " + triangleType + " triangle");
            System.out.println("Perimeter = " + perimeter);
            System.out.println("Area = " + area);

            // loop to prompt the user if they want to enter another set of triangle, breaks out of the loop when they enter either 'y' or 'n'
            while (true) {
                System.out.print("Do you want to enter another set of sides? (Y/N): ");
                loopInput = read.nextLine(); 

                if (loopInput.equalsIgnoreCase("y")) {
                    break;
                }
                else if (loopInput.equalsIgnoreCase("n")) {
                    continueLoop = false;
                    break;
                }
                else { // if the user does not enter either 'y' or 'n', then it will tell them what they inputted is invalid and not break out of the loop
                    System.out.println("\'" + loopInput + "\' is an invalid input");
                }
            }
        }
        read.close();
    }
}