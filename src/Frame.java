import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame implements KeyListener, ActionListener, Resizable {

    Player leftPlayer;
    Player rightPlayer;
    MiddleAxis middleAxis;
    Ball ball;
    GameOverSign gameOverSign;
    ScoreLabel scoreLabel;
    EndButton restartButton;
    EndButton exitButton;
    ImageIcon icon;
    //public static final int CENTRE_LINE = 850;
    private boolean gameIsOver = false;
    private int leftScore = 0;
    private int rightScore = 0;
    private final int WIDTH;
    private final int HEIGHT;
    private final int RIGHT_PLAYER_X_LOCATION;
    private final int LEFT_PLAYER_X_LOCATION;
    private final int PLAYER_DEFAULT_Y_LOCATION;
    private int lastHeight, lastWidth;

    public Frame(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        LEFT_PLAYER_X_LOCATION = 50;
        RIGHT_PLAYER_X_LOCATION = WIDTH - 100;
        PLAYER_DEFAULT_Y_LOCATION = (HEIGHT / 2) - (Player.HEIGHT / 2);
        lastHeight = HEIGHT;
        lastWidth = WIDTH;
        //this.LEFT_PLAYER_X_LOCATION = LEFT_PLAYER_X_LOCATION;
        //this.RIGHT_PLAYER_X_LOCATION = RIGHT_PLAYER_X_LOCATION;

        leftPlayer = new Player(Player.LEFT, WIDTH, HEIGHT);
        rightPlayer = new Player(Player.RIGHT, WIDTH, HEIGHT);
        middleAxis = new MiddleAxis(WIDTH, HEIGHT);
        gameOverSign = new GameOverSign(WIDTH, HEIGHT);
        ball = new Ball(WIDTH, HEIGHT);
        scoreLabel = new ScoreLabel(WIDTH, HEIGHT);
        restartButton = new EndButton(EndButton.RESTART, this, WIDTH, HEIGHT);
        exitButton = new EndButton(EndButton.EXIT, this, WIDTH, HEIGHT);
        icon = new ImageIcon("logo.jpg");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(this.WIDTH, this.HEIGHT); //1080 x 1920 (i should rework it) /used to be: 1700, 1000
        this.getContentPane().setBackground(Color.BLACK);
        setTitle("Ping Pong");
        setIconImage(icon.getImage());
        setResizable(true);
        setVisible(true);

        add(leftPlayer);
        add(rightPlayer);
        add(ball);
        add(middleAxis);
        addKeyListener(this);
        add(gameOverSign);
        add(scoreLabel);
        add(restartButton);
        add(exitButton);

        timer.start();
    }

    private Timer timer = new Timer(1, new ActionListener() { // does following code every millisecond
        @Override
        public void actionPerformed(ActionEvent e) {
            ball.setLocation(ball.getX() + ball.getXDirection(), ball.getY() + ball.getYDirection());
            ball.repulseFromEnd();
            ball.repulseFromPlayer();
            updateScore();
            gameOver();
            updateScale(getWidth(), getHeight());
        }
    });

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameIsOver) { // disables keyboard after game is over
            return;
        }

        int grayZone = WIDTH / 10; // gray zone should be 10% of the frame width

        if (!ball.isMovingInPositiveWay() || ball.getX() < LEFT_PLAYER_X_LOCATION + grayZone) {
            if (e.getKeyCode() == 87) {
                leftPlayer.moveUp();
            } else if (e.getKeyCode() == 83) {
                leftPlayer.moveDown();
            }
            ball.setLeftPlayerY(leftPlayer.getY());
        }

        if (ball.isMovingInPositiveWay() || ball.getX() > (RIGHT_PLAYER_X_LOCATION - grayZone)) {
            if (e.getKeyCode() == 38) {
                rightPlayer.moveUp();
            } else if (e.getKeyCode() == 40) {
                rightPlayer.moveDown();
            }
            ball.setRightPlayerY(rightPlayer.getY());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void updateScore() {
        if (ball.getX() <= 0) {
            rightScore++;
            ball.decreaseSpeed();
            ball.randomDirection();
            scoreLabel.update(leftScore, rightScore);
        }

        if (ball.getX() >= getWidth()) {
            leftScore++;
            ball.decreaseSpeed();
            ball.randomDirection();
            scoreLabel.update(leftScore, rightScore);
        }

    }

    private void gameOver() {
        if (rightScore == 12 || leftScore == 12) {
            ball.setVisible(false);
            gameOverSign.setVisible(true);
            gameIsOver = true;
            timer.stop();
            restartButton.setEnabled(true);
            restartButton.setVisible(true);
            exitButton.setEnabled(true);
            exitButton.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restartButton) {
            leftScore = 0;
            rightScore = 0;
            ball.setSpeed(3);
            ball.randomDirection();
            gameIsOver = false;
            timer.start();
            ball.setVisible(true);
            ball.updateSpeed();
            scoreLabel.update(leftScore, rightScore);
            gameOverSign.setVisible(false);
            restartButton.setVisible(false);
            restartButton.setEnabled(false);
            exitButton.setVisible(false);
            exitButton.setEnabled(false);
        }
        else if (e.getSource() == exitButton) {
            System.exit(0);
        }

    }

    @Override
    public void updateScale(int frameWidth, int frameHeight) {

        if (frameWidth == lastWidth && frameHeight == lastHeight) {
            return;
        }
        lastHeight = frameHeight;
        lastWidth = frameWidth;

        leftPlayer.updateScale(frameWidth, frameHeight);
        rightPlayer.updateScale(frameWidth, frameHeight);
        ball.updateScale(frameWidth, frameHeight);
        scoreLabel.updateScale(frameWidth, frameHeight);
        middleAxis.updateScale(frameWidth, frameHeight);
        gameOverSign.updateScale(frameWidth, frameHeight);
        restartButton.updateScale(frameWidth, frameHeight);
        exitButton.updateScale(frameWidth, frameHeight);
    }

    private class MiddleAxis extends JLabel implements Resizable {
        MiddleAxis(int FRAME_WIDTH, int FRAME_HEIGHT) {
            setBackground(Color.LIGHT_GRAY);
            setBounds(((FRAME_WIDTH / 2) - 5), 0, 10, FRAME_HEIGHT);
            setOpaque(true);
        }

        @Override
        public void updateScale(int frameWidth, int frameHeight) {
            setBounds(((frameWidth / 2) - 5), 0, 10, frameHeight);
        }
    }

    private class GameOverSign extends JLabel implements Resizable {

        private int height, width, xLocation, yLocation, fontSize;

        GameOverSign(int FRAME_WIDTH, int FRAME_HEIGHT) {
            height = FRAME_HEIGHT / 5;
            fontSize = height;
            width = (FRAME_WIDTH / 170) * 103;
            xLocation = (FRAME_WIDTH / 2) - (width / 2) -8;
            yLocation = (FRAME_HEIGHT / 2) - (height / 2);

            setText("GAME OVER");
            setFont(new Font("Bauhaus 93", Font.PLAIN, fontSize));
            setBounds(xLocation, yLocation, width, height); // 326, 370, 1030, 200
            setHorizontalAlignment(JLabel.CENTER);
            setForeground(Color.RED);
            setBackground(Color.BLUE);
            setOpaque(false);
            setVisible(false);

        }

        @Override
        public void updateScale(int frameWidth, int frameHeight) {
            height = frameHeight / 5;
            fontSize = height;
            width = (frameWidth / 170) * 103;
            xLocation = (frameWidth / 2) - (width / 2) -8;
            yLocation = (frameHeight / 2) - (height / 2);

            setBounds(xLocation, yLocation, width, height);
        }
    }
}
