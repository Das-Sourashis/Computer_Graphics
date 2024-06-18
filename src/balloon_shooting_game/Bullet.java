package balloon_shooting_game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

/**
 * The Bullet class represents a bullet object in the balloon shooting game.
 * It handles the bullet's movement, drawing, and collision detection.
 * 
 * @author Sourashis Das
 */
public class Bullet extends Thread implements DisplayObject {

    int h,x, y, sleep_time;
	int w;
    private double x_increment, y_increment;
	double cx;
	double cy;
    double[] x_position;
	double[] y_position;
    private volatile boolean running = true; // Flag to stop the thread safely

    /**
     * Constructs a new Bullet object with the specified parameters.
     *
     * @param x         The initial x-coordinate of the bullet.
     * @param down_y    The y-coordinate where the bullet height ends.
     * @param y         The initial y-coordinate of the bullet.
     * @param angle     The angle at which the bullet is fired.
     * @param center_x  The x-coordinate of the gun's center.
     * @param center_y  The y-coordinate of the gun's center.
     */
    Bullet(int x, int down_y, int y, double angle, double center_x, double center_y) {
        this.x = x;
        this.y = y;
        this.h = down_y - y;
        this.w = h * 2;

        // Calculate the initial position of the bullet vertices
        x_position = new double[]{x, x + w, x + w + w * 0.2, x + w, x};
        y_position = new double[]{y, y, y + h / 2.0, y + h, y + h};

        // Rotate the bullet vertices based on the given angle
        for (int i = 0; i < x_position.length; i++) {
            double temp_x = x_position[i];
            double temp_y = y_position[i];
            x_position[i] = temp_x * Math.cos(angle) - temp_y * Math.sin(angle) + x * (1 - Math.cos(angle)) + y * Math.sin(angle);
            y_position[i] = temp_x * Math.sin(angle) + temp_y * Math.cos(angle) + y * (1 - Math.cos(angle)) - x * Math.sin(angle);
        }

        cx = center_x;
        cy = center_y;

        this.x_increment = 10 * Math.cos(angle);
        this.y_increment = 10 * Math.sin(angle);

        this.sleep_time = 6;
        start();
    }

    /**
     * The main loop for the bullet's movement.
     * Updates the position of the bullet vertices and center point.
     */
    @Override
    public void run() {
        while (running) {
            for (int i = 0; i < x_position.length; i++) {
                x_position[i] += x_increment;
                y_position[i] += y_increment;
            }

            cx += x_increment;
            cy += y_increment;

            try {
                Thread.sleep(sleep_time);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
                e.printStackTrace();
            }
        }
    }

    /**
     * Stops the bullet's movement by setting the running flag to false.
     */
    public void stopBullet() {
        running = false;
    }

    /**
     * Draws the bullet onto the provided Graphics object.
     *
     * @param g The Graphics object used for drawing.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillPolygon(
                Arrays.stream(x_position).mapToInt(d -> (int) d).toArray(),
                Arrays.stream(y_position).mapToInt(d -> (int) d).toArray(),
                x_position.length);
    }
}
