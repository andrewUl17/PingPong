import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ball extends JLabel {

    private final int FRAME_WIDTH, FRAME_HEIGHT,
                      DEFAULT_X_LOCATION, DEFAULT_Y_LOCATION,
                      RIGHT_PLAYER_X_LOCATION, LEFT_PLAYER_X_LOCATION, PLAYER_DEFAULT_Y_LOCATION;
    private int speed = 3;
    private int xDirection, yDirection, leftPlayerY, rightPlayerY;
    //private int yDirection;
    //private int rightPlayerY;
    private boolean movingInPositiveWay;
    private int counter = 0; // needed to control speed increasment
    public static final int SIZE = 10;
    //private final int DEFAULT_X_LOCATION, DEFAULT_Y_LOCATION;

    public Ball(int FRAME_WIDTH, int FRAME_HEIGHT,
                 int PLAYER_DEFAULT_Y_LOCATION, int LEFT_PLAYER_X_LOCATION, int RIGHT_PLAYER_X_LOCATION) {

        this.PLAYER_DEFAULT_Y_LOCATION = PLAYER_DEFAULT_Y_LOCATION;
        this.FRAME_WIDTH = FRAME_WIDTH;
        this.FRAME_HEIGHT = FRAME_HEIGHT;
        this.RIGHT_PLAYER_X_LOCATION = RIGHT_PLAYER_X_LOCATION;
        this.LEFT_PLAYER_X_LOCATION = LEFT_PLAYER_X_LOCATION;

        leftPlayerY = PLAYER_DEFAULT_Y_LOCATION;
        rightPlayerY = PLAYER_DEFAULT_Y_LOCATION;
        DEFAULT_X_LOCATION = (FRAME_WIDTH /2) - (SIZE/2);
        DEFAULT_Y_LOCATION = (FRAME_HEIGHT /2) - (SIZE/2);

        setBounds(DEFAULT_X_LOCATION, DEFAULT_Y_LOCATION, SIZE, SIZE);
        setBackground(Color.WHITE);
        setOpaque(true);
        randomDirection();
        updateSpeed();
    }

    public void increaseSpeed(){
        if (counter > 2) {
            speed++;
        }

        if (counter % 3 == 0) {
            speed++;
        }
        counter++;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isMovingInPositiveWay() {
        return movingInPositiveWay;
    }

    public void setLeftPlayerY(int leftPlayerY) {
        this.leftPlayerY = leftPlayerY;
    }

    public void setRightPlayerY(int rightPlayerY) {
        this.rightPlayerY = rightPlayerY;
    }

    public int getXDirection() {
        return xDirection;
    }

    public int getYDirection() {
        return yDirection;
    }

    public void repulseFromEnd() {
        if (getY() >= 1000 || getY() <= 0) {
            yDirection = -yDirection;
        }
    }

    public void repulseFromPlayer() {
        if (getX() >= LEFT_PLAYER_X_LOCATION && getX() <= (LEFT_PLAYER_X_LOCATION + Player.LENGTH)) {
            if (getY() >= leftPlayerY && getY() <= (leftPlayerY + Player.HEIGHT)) {
                xDirection = -xDirection;
                increaseSpeed();
                updateSpeed();
            }
        }

        if (getX() >= RIGHT_PLAYER_X_LOCATION && getX() <= (RIGHT_PLAYER_X_LOCATION + Player.LENGTH)) {
            if (getY() >= rightPlayerY && getY() <= (rightPlayerY + Player.HEIGHT)) {
                xDirection = -xDirection;
                increaseSpeed();
                updateSpeed();
            }
        }
    }

    public void updateSpeed() {
        if (directionIsPositive(xDirection)) {
            xDirection = speed;
            movingInPositiveWay = true;
        }
        else {
            xDirection = -speed;
            movingInPositiveWay = false;
        }

        if (directionIsPositive(yDirection)) {
            yDirection = speed;
            //movingInPositiveWay = false;
        }
        else {
            yDirection = -speed;
            //movingInPositiveWay = false;
        }
    }

    private boolean directionIsPositive(int direction) {
        return direction > 0;
    }

    public void randomDirection() {
        Random random = new Random();

        xDirection = translateRandom(random.nextInt(2));
        yDirection = translateRandom(random.nextInt(2));
        setLocation(845, 495);
        updateSpeed();
    }

    private int translateRandom(int rand) {
        if (rand == 0) {
            return -speed;
        }
        return speed;
    }

}
