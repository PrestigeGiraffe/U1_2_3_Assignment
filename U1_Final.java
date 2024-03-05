import java.util.Scanner;

public class U1_Final {
    public static void main(String[] args) {
        // declaring variables
        Scanner read = new Scanner(System.in);
        double[] side = {0, 0, 0};
        String triangleType;

        // prompting the user to enter sides of their triangle
        System.out.println("Welcome to the triangle determiner!");
        for (int i = 1; i <= 3; i++) {
            System.out.println("Please enter the side length of your triangle ("+ i +"/3): ");
            side[i-1] = read.nextDouble();
        }
        read.close();

        // determining triangle type
        if (side[0] == side[1] && side[1] == side[2] && side[2] == side[0]) {
            triangleType = "Equilateral";
        }
        else if (side[0] == side[1] || side[1] == side[2] || side[2] == side[0]) {
            triangleType = "Isosceles";
        }
        else {
            triangleType = "Scalene";
        }
    }
}