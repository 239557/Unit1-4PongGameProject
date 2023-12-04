/**
 * Author: Allen Ma
 */
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JOptionPane;
public class Ball {
    private static final int WIDTH = 30, HEIGHT = 30;
    private Pong game;
    private int x, y, xa = 2, ya = 2;
    // Constructor for initializing the ball's position
    public Ball(Pong game) {
        this.game = game;
        x = game.getWidth() / 2;
        y = game.getHeight() / 2;
    }
    // Method to update the ball's position and handle game logic
    public void update() {
        x += xa;
        y += ya;
        if (x < 0) {
            game.getPanel().increaseScore(2); // Player 2 scores when the ball passes the left boundary
            x = game.getWidth() / 2;
            xa = -xa; // Invert the x-axis velocity
        }
        // Check if the ball hits the right boundary
        else if (x > game.getWidth() - WIDTH - 7) {
            game.getPanel().increaseScore(1); // Player 1 scores when the ball passes the right boundary
            x = game.getWidth() / 2;
            xa = -xa; // Invert the x-axis velocity
        }
        // Check if the ball hits the top or bottom boundary
        else if (y < 0 || y > game.getHeight() - HEIGHT - 29)
            ya = -ya; // Invert the y-axis velocity
        // Check for winning conditions and display a message dialog
        if (game.getPanel().getScore(1) == 5)
            JOptionPane.showMessageDialog(null, "Player 1 wins. Thanks for playing. Hope you enjoyed", "Pong", JOptionPane.PLAIN_MESSAGE);
        else if (game.getPanel().getScore(2) == 5)
            JOptionPane.showMessageDialog(null, "Player 2 wins. Thanks for playing. Hope you enjoyed", "Pong", JOptionPane.PLAIN_MESSAGE);
        // Check for collision with rackets
        checkCollision();
    }
    // Method to check collision with rackets
    public void checkCollision() {
        if (game.getPanel().getPlayer(1).getBounds().intersects(getBounds()) || game.getPanel().getPlayer(2).getBounds().intersects(getBounds()))
            xa = -xa; // Invert the x-axis velocity upon collision
    }
    // Method to get the bounding box of the ball
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
    // Method to render the ball on the screen
    public void paint(Graphics g) {
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}