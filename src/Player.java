import javax.swing.*;
import java.awt.*;

public class Player extends JLabel implements Resizable {

    public static final int LENGTH = 40;
    public static final int HEIGHT = 200;
    public static final int LEFT_PLAYER = 50;
    public static final int RIGHT_PLAYER = 1600;
    //private final int DEFAULT_Y_LOCATION;
    private int xLocation; // player's X-position
    private int player, actualFrameHeight;
    private final int SPEED = 25;
    public static final int RIGHT = 0;
    public static final int LEFT = 1;

    public Player(int player, int FRAME_WIDTH, int FRAME_HEIGHT) {
        this.player = player;
        //this.FRAME_HEIGHT = FRAME_HEIGHT;
        setSize(LENGTH, HEIGHT);
        defineWhichPlayer(FRAME_WIDTH, FRAME_HEIGHT);
        actualFrameHeight = FRAME_HEIGHT;
        //this.FRAME_HEIGHT = FRAME_HEIGHT;
        //this.FRAME_WIDTH = FRAME_WIDTH;
        //this.DEFAULT_Y_LOCATION = DEFAULT_Y_LOCATION;
        //LEFT_PLAYER = 50;
        //RIGHT_PLAYER = FRAME_WIDTH - 100;
        //setBounds(player[0], player[1], LENGTH, HEIGHT);
        update((FRAME_HEIGHT / 2) - (Player.HEIGHT / 2)); // default y location is calculated
    }

    public void update(int y) {
        setBackground(Color.WHITE);
        setOpaque(true);
        setLocation(xLocation, y);
        setVisible(true);
    }

    public void moveUp() {
        if (getY() >= 1) {
            setLocation(xLocation, getY() - SPEED);
        }
    }

    public void moveDown() {
        if (getY() < actualFrameHeight - HEIGHT) {
            setLocation(xLocation, getY() + SPEED);
        }
    }

    private void defineWhichPlayer(int frameWidth, int frameHeight) {
        if (player == RIGHT) {
            xLocation = frameWidth - 100;
        }
        if (player == LEFT) {
            xLocation = 50;
        }
    }

    @Override
    public void updateScale(int frameWidth, int frameHeight) {
        defineWhichPlayer(frameWidth, frameHeight);
        actualFrameHeight = frameHeight;

        if (getY() < frameHeight){
            update((frameHeight / 2) - (Player.HEIGHT / 2));
        }
    }
}
