import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EndButton extends JButton {

    public static final String RESTART = "RESTART";
    public static final String EXIT = "EXIT";

    public EndButton(String text, ActionListener e) {
        //setBounds(830, 50, 300, 100);
        setText(text);
        setForeground(Color.GRAY);
        setFont(new Font("Bauhaus 93", Font.PLAIN, 150));
        //setBounds(550, 170, 600, 180);
        defineBounds(text);
        setVisible(false);
        setEnabled(false);
        addActionListener(e);
        setBackground(Color.BLACK);
        setFocusable(false);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void defineBounds(String text) {
        if (text.equals(RESTART)) {
            setBounds(150, 170, 600, 180);
        }
        else if (text.equals(EXIT)) {
            setBounds(1000, 170, 600, 180);
        }
    }
}
