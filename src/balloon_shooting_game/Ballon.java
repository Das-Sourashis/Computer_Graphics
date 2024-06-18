package balloon_shooting_game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * The Ballon class represents a balloon in the balloon shooting game. Each
 * balloon is a separate thread that moves upward until it goes out of the
 * visible area.
 * 
 * @author Sourashis Das
 */
public class Ballon extends Thread implements DisplayObject {
	private static final Random rd = new Random();
	int x, y, r; // Balloon's position, diameter, and sleep time between movements
	private int sleep_time;
	private Color color; // Color of the balloon
	private volatile boolean running = true; // Flag to stop the thread safely

	/**
	 * Constructs a new Ballon at the specified x-coordinate.
	 * 
	 * @param x The x-coordinate where the balloon will be created.
	 */
	public Ballon(int x) {
		this.x = x;
		this.y = MyGUI.frame_height; // Start the balloon at the bottom of the frame
		this.r = 50; // Set the balloon radius
		this.sleep_time = rd.nextInt(90) + 10; // Random sleep time between 10 and 100 milliseconds
		this.color = new Color(rd.nextInt(200) + 20, rd.nextInt(200) + 20, rd.nextInt(200) + 20); // Random color
		start(); // Start the balloon thread
	}

	/**
	 * The run method contains the balloon's movement logic. The balloon moves
	 * upward until it goes out of the visible area.
	 */
	@Override
	public void run() {
		while (running) {
			y -= 10; // Move the balloon upward by 10 units

			try {
				Thread.sleep(sleep_time); // Pause the thread for the specified sleep time
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // Restore the interrupted status
				e.printStackTrace();
			}
		}
	}

	/**
	 * Stops the balloon's thread. Sets the running flag to false to terminate the
	 * run loop.
	 */
	public void stopBalloon() {
		running = false;
	}

	/**
	 * Draws the balloon on the provided Graphics object.
	 * 
	 * @param g The Graphics object to draw on.
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(color); // Set the color to the balloon's color
		g.fillOval(x, y, r, r); // Draw the balloon as a filled oval
		g.setColor(Color.black); // Set the color to black for the string
		g.drawLine(x + r / 2, y + r, x + r / 2, y + r / 2 + 60); // Draw the string
	}
}
