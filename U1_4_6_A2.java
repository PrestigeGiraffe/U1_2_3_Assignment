public class U1_4_6_A2 {
    public static void main(String[] args) {
        System.out.print("*");
        for(int i = 1; i<=9; i++) {
            if (i % 3 == 0) {
                System.out.print(i + "c");
            }
            else if (i % 3 == 1) {
                System.out.print(i + "a");
            }
            else {
                System.out.print(i + "b");
            }
        }
        System.out.print("#");
    }
}