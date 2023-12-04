/**
 * Author: Allen Ma
 */
import java.awt.Graphics;
import java.awt.Rectangle;
public class Racket {
    private static final int WIDTH = 10, HEIGHT = 60;
    private Pong game;
    private int up, down;
    private int x;
    private int y, ya;
    // Constructor for initializing the racket's position and controls
    public Racket(Pong game, int up, int down, int x) {
        this.game = game;
        this.x = x;
        y = game.getHeight() / 2;
        this.up = up;
        this.down = down;
    }

    // Method to update the racket's position based on user input
    public void update() {
        if (y > 0 && y < game.getHeight() - HEIGHT - 29)
            y += ya;
        else if (y == 0)
            y++;
        else if (y == game.getHeight() - HEIGHT - 29)
            y--;
    }

    // Method to handle key press events for racket movement
    public void pressed(int keyCode) {
        if (keyCode == up)
            ya = -1;
        else if (keyCode == down)
            ya = 1;
    }

    // Method to handle key release events
    public void released(int keyCode) {
        if (keyCode == up || keyCode == down)
            ya = 0;
    }

    // Method to get the bounding box of the racket for collision detection
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    // Method to render the racket on the screen
    public void paint(Graphics g) {
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}
