import java.util.Scanner;

public class U1_2_3_A2 {
    public static void main(String[] args) {
        // declaring variables
        Scanner input = new Scanner(System.in);
        int inputtedSeconds, seconds, minutes, hours;

        // prompting the user
        System.out.print("Enter the number of seconds: ");
        inputtedSeconds = input.nextInt();
        input.close();

        // calculations
        seconds = inputtedSeconds;
        hours = seconds / 3600;
        seconds = seconds - (3600 * hours);
        minutes = seconds / 60;
        seconds = seconds - (60 * minutes); 

        // output
        System.out.print(inputtedSeconds+" second(s) = "+hours+" hour(s) "+minutes+" minute(s) "+seconds+" second(s).");
    }
}