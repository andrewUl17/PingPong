import javax.swing.*;
import java.awt.*;

public class ScoreLabel extends JLabel implements Resizable{

    private String score = "00   00";

    private int HEIGHT;
    private int WIDTH;
    //private final int FONT_SIZE;
    private int X_POSITION;

    public ScoreLabel(int FRAME_WIDTH, int FRAME_HEIGHT) {
        HEIGHT = (FRAME_HEIGHT / 100) * 15;
        WIDTH = 310;
        X_POSITION = (FRAME_WIDTH / 2) - (WIDTH / 2) - 3;

        setText(score);
        setHorizontalAlignment(JLabel.CENTER);
        setFont(new Font("Bauhaus 93", Font.PLAIN, 100));
        setBounds(X_POSITION, 0, WIDTH, HEIGHT); // default bounds: 695, 0, 310, 150
        setForeground(Color.WHITE);
        setBackground(Color.BLUE);
        setOpaque(false);
        setVisible(true);
    }

    public void update(int left, int right) {
        score = passToFormat(left) + "   " + passToFormat(right);
        setText(score);
    }

    private String passToFormat(Integer num) {
        if (num < 10) {
            return "0" + num;
        }
        return num.toString();
    }

    @Override
    public void updateScale(int frameWidth, int frameHeight) {
        HEIGHT = (frameHeight / 100) * 15;
        WIDTH = 310;
        X_POSITION = (frameWidth / 2) - (WIDTH / 2) - 3;

        setBounds(X_POSITION, 0, WIDTH, HEIGHT);
    }
}
