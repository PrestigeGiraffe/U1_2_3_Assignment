import java.util.Scanner;

public class U1_4_6_A1 {
    public static void main(String[] args) {
        // declaring variables
        Scanner read = new Scanner(System.in);
        int x;

        // promtping the user
        for (int i = 1; i<=4; i++) { // loop to prompt the user 4 times because there are 4 x values in the piecewise function
            System.out.print("Please enter the x value of the function (" + i + "/4): ");
            x = read.nextInt();
        }
        read.close();
    }
}