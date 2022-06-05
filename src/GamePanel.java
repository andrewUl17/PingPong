import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel() {

        JLabel label = new JLabel();

        label.setBackground(Color.RED);
        label.setBounds(0,0,100,100);
        label.setOpaque(true);
        //label.setText("hi");

        //setLayout(new BorderLayout());
        //setPreferredSize(new Dimension(800,800));
        setBounds(0,0,500,500);
        //setSize(100,100);
        setBackground(Color.BLUE);

        add(label);

    }
}
