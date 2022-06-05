import javax.swing.*;
import java.awt.*;

public class Player extends JLabel {

    public static final int LENGTH = 40;
    public static final int HEIGHT = 200;
    //private final int FRAME_HEIGHT;
    //private final int FRAME_WIDTH;
    public static final int LEFT_PLAYER = 50;
    public static final int RIGHT_PLAYER = 1600;
    //private final int DEFAULT_Y_LOCATION;
    private final int player; // player's X-position
    private final int SPEED = 25;

    public Player(int player, int DEFAULT_Y_LOCATION) {
        this.player = player;
        //this.FRAME_HEIGHT = FRAME_HEIGHT;
        //this.FRAME_WIDTH = FRAME_WIDTH;
        //this.DEFAULT_Y_LOCATION = DEFAULT_Y_LOCATION;
        //LEFT_PLAYER = 50;
        //RIGHT_PLAYER = FRAME_WIDTH - 100;
        //setBounds(player[0], player[1], LENGTH, HEIGHT);
        update(DEFAULT_Y_LOCATION);
    }

    public void update(int y) {
        setBackground(Color.WHITE);
        setOpaque(true);
        setBounds(player, y, LENGTH, HEIGHT);
        setVisible(true);
    }

    /*public void move(int y) {
        setLocation(player, y);
    }*/

    public void moveUp() {
        if (getY() >= 1) {
            setLocation(player, getY() - SPEED);
        }
    }

    public void moveDown() {
        if (getY() < 970 - HEIGHT) {
            setLocation(player, getY() + SPEED);
        }
    }


}
