/*
 * Purpose: Class that contains variables for the name and price of a parcel, as well as a constructor that allows the user to input the value when creating the object, and a method to decrease the price of the parcel by a certain percent
 * Author: Johnson yep
 */

 public class Parcel {
    // CLASS VARIABLES
    String name;
    int price;

    // CONSTRUCTOR 1
    public Parcel() {}

    // CONSTRUCTOR 2
    public Parcel(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void decreasePrice(double percent) {
        this.price -= this.price*(percent/100); // subtract the price times the percent inputted from the price, /100 so that the user can input a number from 1-100 rather than 0-1
    }
 }