import java.util.Scanner;

public class U1_4_6_A1 {
    public static void main(String[] args) {
        // declaring variables
        Scanner read = new Scanner(System.in);
        float x, output;

        // prompting the user
        System.out.print("Please enter the x value of the function: ");
        x = read.nextFloat();
        read.close();

        // calculations
        if (x < -10) {
            output = x * -2;
            System.out.println("f(x) = -2x = " + output);
        }
        else if (x >= -10 && x < 0) {
            output = -x;
            System.out.println("f(x) = -x = " + output);
        }
        else if (x >= 0 && x <= 10) {
            output = x;
            System.out.println("f(x) = x = " + output);
        }
        else {
            output = x * 2;
            System.out.println("f(x) = 2x = " + output);
        }
    }
}