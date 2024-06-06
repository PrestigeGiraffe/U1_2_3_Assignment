/*
 * Purpose: Moves the projectile
 */

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Projectile extends Rectangle {
    private double endX, endY;
    private int speed;

    // Creates a new projectile and stores the values passed into the constructor as class variables
    Projectile(double startX, double startY, double endX, double endY, int size, int speed) {
        super(startX, startY, size, size);
        this.endX = endX;
        this.endY = endY;
        this.speed = speed;
    }

    public void move() {
        double xDist = endX - this.getX();
        double yDist = endY - this.getY();


        
    if (xDist > 0 && yDist > 0) 
        {
            this.setX(this.getX() + speed);
            this.setY(this.getY() + speed);
        }
        else if (xDist < 0 && yDist < 0) 
        {
            this.setX(this.getX() - speed);
            this.setY(this.getY() - speed);
        }

        else if (xDist < 0 && yDist > 0) {
            this.setX(this.getX() - speed);
            this.setY(this.getY() + speed);
        }
        else {
            this.setX(this.getX() + speed);
            this.setY(this.getY() - speed);
        }
        
        this.setFill(Paint.valueOf("Yellow"));
    }
}
