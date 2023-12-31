/**
 * Author: Tafsi Bhuiyan
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
public class PongPanel extends JPanel implements ActionListener, KeyListener {
    private Pong game;
    private Ball ball;
    private Racket player1, player2;
    private int score1, score2;

    // Constructor for initializing the game panel
    public PongPanel(Pong game) {
        setBackground(Color.WHITE);
        this.game = game;
        ball = new Ball(game);
        player1 = new Racket(game, KeyEvent.VK_UP, KeyEvent.VK_DOWN, game.getWidth() - 36);
        player2 = new Racket(game, KeyEvent.VK_W, KeyEvent.VK_S, 20);
        Timer timer = new Timer(5, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }
    public Racket getPlayer(int playerNum) {
        if (playerNum == 1)
            return player1;
        else
            return player2;
    }
    public void increaseScore(int playerNum) {
        if (playerNum == 1)
            score1++;
        else
            score2++;
    }
    public int getScore(int playerNum) {
        if (playerNum == 1)
            return score1;
        else
            return score2;
    }
    // Getters and setters for accessing game elements and scores

    // Method to update the game state (invoked by the game loop)
    private void update() {
        ball.update();
        player1.update();
        player2.update();
    }

    // ActionListener interface method (invoked by the timer)
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    // KeyListener interface methods for handling key events
    public void keyPressed(KeyEvent e) {
        player1.pressed(e.getKeyCode());
        player2.pressed(e.getKeyCode());
    }
    public void keyReleased(KeyEvent e) {
        player1.released(e.getKeyCode());
        player2.released(e.getKeyCode());
    }
    public void keyTyped(KeyEvent e) {
        // No action for keyTyped in this implementation
    }

    // PaintComponent method for rendering the game
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Display the scores at the top of the screen
        g.drawString(game.getPanel().getScore(1) + " : " + game.getPanel().getScore(2), game.getWidth() / 2, 10);
        // Render the ball and rackets
        ball.paint(g);
        player1.paint(g);
        player2.paint(g);
    }
}