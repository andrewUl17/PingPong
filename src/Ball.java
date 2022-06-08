import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ball extends JLabel implements Resizable {

    private final int FRAME_WIDTH, FRAME_HEIGHT;
    private int speed = 3;
    private int xDirection, yDirection, leftPlayerY, rightPlayerY,
                defaultXLocation, defaultYLocation,
                rightPlayerXLocation, leftPlayerXLocation, actualFrameHeight;
    //private int yDirection;
    //private int rightPlayerY;
    private boolean movingInPositiveWay;
    private int counter = 0; // needed to control speed increasment
    public static final int SIZE = 10;
    //private final int DEFAULT_X_LOCATION, DEFAULT_Y_LOCATION;

    public Ball(int FRAME_WIDTH, int FRAME_HEIGHT) {

        //this.PLAYER_DEFAULT_Y_LOCATION = PLAYER_DEFAULT_Y_LOCATION;
        this.FRAME_WIDTH = FRAME_WIDTH;
        this.FRAME_HEIGHT = FRAME_HEIGHT;
        actualFrameHeight = FRAME_HEIGHT;

        leftPlayerXLocation = 50;
        rightPlayerXLocation = FRAME_WIDTH - 100;
        leftPlayerY = (FRAME_HEIGHT / 2) - (Player.HEIGHT / 2);
        rightPlayerY = (FRAME_HEIGHT / 2) - (Player.HEIGHT / 2);
        defaultXLocation = (FRAME_WIDTH /2) - (SIZE/2);
        defaultYLocation = (FRAME_HEIGHT /2) - (SIZE/2);

        setBounds(defaultXLocation, defaultYLocation, SIZE, SIZE);
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

    public void decreaseSpeed() {
        if (speed > 3) {
            speed--;
        }
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
        if (getY() >= actualFrameHeight || getY() <= 0) {
            yDirection = -yDirection;
        }
    }

    public void repulseFromPlayer() {
        if (getX() >= leftPlayerXLocation && getX() <= (leftPlayerXLocation + Player.LENGTH)) {
            if (getY() >= leftPlayerY && getY() <= (leftPlayerY + Player.HEIGHT)) {
                xDirection = -xDirection;
                increaseSpeed();
                updateSpeed();
            }
        }

        if (getX() >= rightPlayerXLocation && getX() <= (rightPlayerXLocation + Player.LENGTH)) {
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
        setLocation(defaultXLocation, defaultYLocation);
        updateSpeed();
    }

    private int translateRandom(int rand) {
        if (rand == 0) {
            return -speed;
        }
        return speed;
    }

    @Override
    public void updateScale(int frameWidth, int frameHeight) {
        leftPlayerXLocation = 50;
        rightPlayerXLocation = frameWidth - 100;
        leftPlayerY = (frameHeight / 2) - (Player.HEIGHT / 2);
        rightPlayerY = (frameHeight / 2) - (Player.HEIGHT / 2);
        defaultXLocation = (frameWidth /2) - (SIZE/2);
        defaultYLocation = (frameHeight /2) - (SIZE/2);
        actualFrameHeight = frameHeight;

        randomDirection();
    }
}
