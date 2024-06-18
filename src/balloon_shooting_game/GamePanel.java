package balloon_shooting_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

/**
 * The DrawPanel class represents the panel where the game elements are drawn.
 * It handles the game loop, user input, and drawing of objects.
 * 
 * @author Sourashis Das
 */
public class GamePanel extends JPanel implements KeyListener, MouseWheelListener {
	List<DisplayObject> displayBuffer = new ArrayList<>();
	static int score;
	Gun gun = new Gun();
	Score s = new Score();
	Random rd = new Random();
	boolean throughBullet = false;

	/**
	 * Constructs a new DrawPanel with default settings.
	 */
	public GamePanel() {
		setBackground(Color.WHITE);
		displayBuffer.add(s);
		displayBuffer.add(gun);
		addMouseWheelListener(this);
		score = 0;

		// Add a timer to periodically add new balloons to the display buffer
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				displayBuffer.add(new Ballon(rd.nextInt(600) + 800));
			}
		}, 0, 300);

		// Add a timer to handle shooting bullets
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				if (throughBullet) {
					displayBuffer.add(new Bullet((int) (gun.x_array[1] + 2), (int) (gun.y_array[1] + gun.height * 0.8),
							(int) (gun.y_array[1] + gun.height * 0.2), gun.gun_angle, gun.center_x, gun.center_y));
					throughBullet = false;
				}
			}
		}, 0, 150);

		// Add a timer to handle collision detection and removal of objects
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				for (int i = 0; i < displayBuffer.size(); i++) {
					DisplayObject ob = displayBuffer.get(i);
					if (ob instanceof Bullet) {
						Bullet bullet = (Bullet) ob;
						if (bullet.x_position[0] >= MyGUI.frame_width || bullet.y_position[0] < -30
								|| bullet.y_position[0] > MyGUI.frame_height) {
							bullet.stopBullet(); // Stop the bullet thread
							displayBuffer.remove(i);
							continue;
						}
					} else if (ob instanceof Ballon) {
						Ballon ballon = (Ballon) ob;
						if (ballon.y <= -70) {
							ballon.stopBalloon(); // Stop the balloon thread
							displayBuffer.remove(i);
							continue;
						}
						for (int j = 0; j < displayBuffer.size(); j++) {
							DisplayObject ob1 = displayBuffer.get(j);
							if (ob1 instanceof Bullet) {
								Bullet bullet = (Bullet) ob1;
								double distance = Math
										.sqrt(Math.pow(bullet.cx - ballon.x, 2) + Math.pow(bullet.cy - ballon.y, 2));
								if (distance < ballon.r / 2.0 + bullet.h / 2.0) {
									ballon.stopBalloon(); // Stop the balloon thread
									displayBuffer.remove(i);
									score++;
									break;
								}
							}
						}
					}
				}
			}
		}, 0, 10);
	}

	/**
	 * Paints the components of the panel including the game objects.
	 *
	 * @param g The Graphics object used for painting.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		List<DisplayObject> tempBuffer = new ArrayList<>(displayBuffer);
		for (DisplayObject displayObject : tempBuffer) {
			if (displayObject != null) {
				displayObject.draw(g);

			}
		}
		tempBuffer.clear();
	}

	// KeyListener methods
	@Override
	public void keyTyped(KeyEvent e) {
		// Not used
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_UP) {
			if (gun.y >= 70) {
				gun.gunUpDown(true);
			}
		} else if (keyCode == KeyEvent.VK_DOWN) {
			if (gun.y <= 700) {
				gun.gunUpDown(false);
			}
		} else if (keyCode == KeyEvent.VK_SPACE) {
			throughBullet = true;
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			gun.rotate(Math.PI / 20.0);
		} else if (keyCode == KeyEvent.VK_LEFT) {
			gun.rotate(-Math.PI / 20.0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Not used
	}

	// MouseWheelListener method
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
	    int unitsToScroll = e.getUnitsToScroll();
	    double scaleFactor;

	    if (unitsToScroll < 0) {
	        // Zoom in
	        scaleFactor = 1.1;  // Increase by 10%
	    } else {
	        // Zoom out
	        scaleFactor = 1.0 / 1.1;  // Decrease by 10%
	    }

	    gun.scale(scaleFactor, scaleFactor);
	}

}
