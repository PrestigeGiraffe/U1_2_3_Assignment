import java.util.Scanner;

public class U1_2_3_A1 {
    public static void main(String[] args) {
        // declaring variables
        Scanner input = new Scanner(System.in);
        double base, height, area;

        // prompting the user with scanner
        System.out.print("Triangle Area Calculator\nPlease enter  the base: ");
        base = input.nextDouble();
        System.out.print("Please enter the height: ");
        height = input.nextDouble();
        input.close();

        // calculations
        area = (base * height) / 2;

        // output to user
        System.out.print("The area of your triangle is: "+area);
    }
}