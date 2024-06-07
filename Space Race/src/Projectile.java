/*
 * Purpose: Moves the projectile
 */

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Projectile extends Rectangle {
    Point2D startPoint, endPoint;
    private double speedX, speedY;

    // Creates a new projectile and stores the values passed into the constructor as
    // class variables
    Projectile(double startX, double startY, double endX, double endY, int size, int speed) {
        super(startX, startY, size, size);
        this.startPoint = new Point2D(startX, startY);
        this.endPoint = new Point2D(endX, endY);
        
        // Get hypotenuse of the x and y values of the end point to calculate the right speed for the x value to be changed by so they can reach the same point at the same time if the x and y end points are different
        double totalX = endX - startX;
        double totalY = endY - startY;

        double hyp = Math.sqrt(Math.pow(totalX, 2) + Math.pow(totalY, 2)); // ratio for speed calculation
        speedX = (totalX / hyp) * speed;
        speedY = (totalY / hyp) * speed;
        this.setFill(Paint.valueOf("Yellow"));
    }

    public void move() {
        this.setX(this.getX() + speedX);
        this.setY(this.getY() + speedY);
    }
}
