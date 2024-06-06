/*
 * Purpose: Moves the projectile
 */

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Projectile extends Rectangle {
    Point2D startPoint, endPoint;
    private int speed;

    // Creates a new projectile and stores the values passed into the constructor as
    // class variables
    Projectile(double startX, double startY, double endX, double endY, int size, int speed) {
        super(startX, startY, size, size);
        this.startPoint = new Point2D(startX, startY);
        this.endPoint = new Point2D(endX, endY);
        this.speed = speed;
    }

    public void move() {
        double xDist = startPoint.getX() - this.getX();
        double yDist = startPoint.getY() - this.getY();

        if (xDist > 0 && yDist > 0) {
            this.setX(this.getX() + speed);
            this.setY(this.getY() + speed);
        } else if (xDist < 0 && yDist < 0) {
            this.setX(this.getX() - speed);
            this.setY(this.getY() - speed);
        }

        else if (xDist < 0 && yDist > 0) {
            this.setX(this.getX() - speed);
            this.setY(this.getY() + speed);
        } else {
            this.setX(this.getX() + speed);
            this.setY(this.getY() - speed);
        }

        this.setFill(Paint.valueOf("Yellow"));
    }
}
