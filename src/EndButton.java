import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EndButton extends JButton implements Resizable {

    public static final String RESTART = "RESTART";
    public static final String EXIT = "EXIT";
    private String text;

    private int height, width, xLocationLeft, xLocationRight, yLocation, fontSize, distanceFromEnd;

    public EndButton(String text, ActionListener e, int FRAME_WIDTH, int FRAME_HEIGHT) {
        this.text = text;
        distanceFromEnd = FRAME_HEIGHT / 15;
        fontSize = (FRAME_HEIGHT / 100) * 15;
        height = fontSize + 5;
        width = (FRAME_WIDTH / 17) * 6;
        xLocationLeft = /*(FRAME_WIDTH / 2) - width - */distanceFromEnd;
        xLocationRight = /*(FRAME_WIDTH / 2) + distanceToCentralPoint*/ FRAME_WIDTH - width - distanceFromEnd;
        yLocation = (FRAME_HEIGHT / 4) - (height / 2);

        //setBounds(830, 50, 300, 100);
        setText(text);
        setForeground(Color.GRAY);
        setFont(new Font("Bauhaus 93", Font.PLAIN, fontSize));
        //setBounds(550, 170, 600, 180);
        setSize(width, height); // 600, (150 + 5)
        defineLocation(text);
        //setHorizontalAlignment(JButton.CENTER);
        setVisible(false);
        setEnabled(false);
        addActionListener(e);
        setBackground(Color.BLACK);
        setFocusable(false);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void defineLocation(String text) {
        if (text.equals(RESTART)) {
            setLocation(xLocationLeft, yLocation); // 150, 170
            //setHorizontalAlignment(JButton.RIGHT);
        }
        else if (text.equals(EXIT)) {
            setLocation(xLocationRight, yLocation); // 100, 170
            //setHorizontalAlignment(JButton.RIGHT);
        }
    }

    @Override
    public void updateScale(int frameWidth, int frameHeight) {
        distanceFromEnd = frameHeight / 15;
        fontSize = (frameHeight / 100) * 15;
        height = fontSize + 5;
        width = (frameWidth / 17) * 6;
        xLocationLeft = distanceFromEnd;
        xLocationRight = frameWidth - width - distanceFromEnd;
        yLocation = (frameHeight / 4) - (height / 2);

        defineLocation(text);
    }
}
