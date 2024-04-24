import java.util.Scanner;

public class U3_1_2_A1 {
    //Example 4
    
    //Method to generate random numbers. The parameter has a different
    //name than the argument.
    static void generate(int[] x){
        for (int i = 0; i < x.length; i++) {
            x[i] = (int)(Math.random()*100 + 1);
        }
    }


    //Method to display the random numbers in the array being passed.
    static void display(int[] numbers){
        for (int n : numbers) {
            System.out.print(n + " ");
        }
    }
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);


        int[] numbers = new int[10];
        
        String option = "0";
        while(true){
            System.out.println("1) Generate");
            System.out.println("2) Display");
            System.out.println("3) Quit");
            System.out.print("Choose one of the options: ");
            option = read.next();


            if (option.equals("1")){
                generate(numbers);
                System.out.println("The numbers have been generated.");
            }
            else if (option.equals("2")){
                System.out.println("Here are the ten random numbers: ");
                display(numbers);
                System.out.println("");
            }
            else if(option.equals("3")){
                System.out.println("Exiting program.");
                break;
            }
            else {
                System.out.println("Invalid input.");
            }
        }
        read.close();
}