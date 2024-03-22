import java.util.Scanner;

public class U1_Final {
    public static void main(String[] args) {
        // declaring and initializing variables
        Scanner read = new Scanner(System.in);
        double a = 0, b = 0, c = 0, perimeter, area, s;
        String triangleType, loopInput; // loop input will store the user's input for whether or not they want to enter another triangle
        boolean continueLoop = true, validInput = true;

        System.out.println("Welcome to TriangulateIT!"); // welcome message for when program is first run

        while (continueLoop) {
            validInput = true; // sets valid input to true when for when the user enters a second set if their first set was invalid
            // prompting the user to enter sides of their triangle
            System.out.println("Please enter the side length of your triangle (3 inputs): ");
            a = read.nextDouble();
            b = read.nextDouble();
            c = read.nextDouble();
            read.nextLine(); // prevents any further next() or nextLine() call from accidentally consuming the newline from nextDouble() above

            // seeing if the user enters valid numbers
            if (a <= 0 || b <= 0 || c <= 0) {
                System.out.println("Please enter positive values.");
                System.out.println("******************************");
                validInput = false;
            }
            // determining if the sides given can form a triangle
            else if (a + b <= c || b + c <= a || a + c <= b) { // else if statement instead of a whole new if statement to prevent it from printing "cannot form a triangle" with negative numbers
                System.out.println(a + ", " + b + ", and " + c + " cannot form a triangle.");
                System.out.println("******************************");
                validInput = false;
            }

            if (validInput) { // checks if the user's input is valid, if it is then do the calculations and output the information, if it's not then skip this entire section and ask the user if they want to enter another set
                 // determining triangle type
                if (a == b && b == c && c == a) { // if all sides are equal
                    triangleType = "Equilateral";
                } else if (a == b || b == c || c == a) { // if any two sides are equal
                    triangleType = "Isosceles";
                } else { // any other outcome
                    triangleType = "Scalene";
                }

                // calculating perimeter
                perimeter = a + b + c;

                // calculating area
                s = perimeter / 2;
                area = Math.sqrt(s * (s - a) * (s - b) * (s - c));

                // outputting results to user
                System.out.println(a + ", " + b + ", and " + c + " forms a(n) " + triangleType + " triangle.");
                System.out.println("Perimeter = " + perimeter);
                System.out.println("Area = " + area);
                System.out.println("******************************");
            }

            // prompt the user if they want to enter another set of triangle, breaks out of the loop when they enter either 'y' or 'n'
            while (true) { 
                System.out.print("Do you want to enter another set? (Y/N): ");
                loopInput = read.nextLine();

                if (loopInput.equalsIgnoreCase("y")) {
                    System.out.print("\033[H\033[2J"); // clears the terminal
                    break;
                } else if (loopInput.equalsIgnoreCase("n")) {
                    System.out.println("Thank you for using this program, see you next time!");
                    continueLoop = false;
                    break;
                } else { // if the user does not enter either 'y' or 'n', then it will tell them what they inputted is invalid and not break out of the loop
                    System.out.println("'" + loopInput + "' is an invalid input");
                }
            }

        }
        read.close();
    }
}