/*
 * Purpose: To give parcels a name and price and store it in another class, and then decrease their price by a percentage using a method in the other class
 * Author: Johnson yep
 */

 public class U3_3_4_A2 {
    public static void main(String[] args) {
        // CREATING OBJECTS OF PARCEL, AND GIVING THEM VALUES
        Parcel p1 = new Parcel("Plant", 20);

        Parcel p2 = new Parcel();
        p2.name = "TV";
        p2.price = 600;

        // DECREASING PRICE
        p1.decreasePrice(10);
        p2.decreasePrice(10);

        // OUTPUTTING INFORMATION TO USER
        System.out.printf("%15s%15s\n", "Parcel", "Price");
        System.out.printf("%15s%15d\n", p1.name, p1.price);
        System.out.printf("%15s%15d\n", p2.name, p2.price);
    }
 }
