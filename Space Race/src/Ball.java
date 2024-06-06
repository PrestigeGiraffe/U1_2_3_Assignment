import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Ball extends Circle {
    //The horizontal and vertical speeds of the ball.
    private int xSpeed = 2;
    private int ySpeed = 2;


    //Constructor to create the ball at a certain x and y position.
    public Ball(int x, int y, int size, Color c){
        super(x, y, size, c);   //super is the Circle class constructor
    } 


    //Method to move the ball to a new location.
    //Parameters: The width and height of the scene.
    public void move(double w, double h){
        //Change the horizontal speed if the ball is outside the sides.
        if (this.getCenterX() > w || this.getCenterX() < 0) {
            xSpeed = -xSpeed;
        }
        //Change the vertical speed if the ball is outside the bottom/top.
        if (this.getCenterY() > h || this.getCenterY() < 0) {
            ySpeed = -ySpeed;
        }
        //Add the speed amount to the x and y coordinates.
        this.setCenterX(this.getCenterX() + xSpeed);
        this.setCenterY(this.getCenterY() + ySpeed);
    }


    //Method to switch the direction of the ball when it bounces of another ball.
    public void bounce() {
        this.xSpeed = - this.xSpeed;
        this.ySpeed = - this.ySpeed;
    }
}
