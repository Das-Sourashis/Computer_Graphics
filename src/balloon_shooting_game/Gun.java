package balloon_shooting_game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

/**
 * The Gun class represents the player's gun in the game. It handles scaling,
 * rotation, and drawing of the gun.
 * 
 * @author Sourashis Das
 */
public class Gun implements DisplayObject {
	double x, y, height, width, center_x, center_y, gun_angle;
	double[] x_array, y_array;

	/**
	 * Constructs a new Gun with default settings.
	 */
	public Gun() {
		center_x = 150;
		center_y = 85;
		height = 20;
		width = 100;
		x = center_x - width / 2.0;
		y = center_y - height / 2.0;
		gun_angle = 0;

		// Define gun shape using arrays of x and y coordinates
		x_array = new double[] { x, x + width, x + width, x + width * 0.3, x + width * 0.3, x };
		y_array = new double[] { y, y, y + height, y + height, y + height * 3, y + height * 3 };
	}

	/**
	 * Scales the gun by the specified factors along the x and y axes.
	 * 
	 * @param sx The scaling factor along the x-axis.
	 * @param sy The scaling factor along the y-axis.
	 */
	void scale(double sx, double sy) {
		if ((height > 5 && height < 80) || (height < 5 && sx > 1) || (sx < 1 && height > 80)) {
			for (int i = 0; i < x_array.length; i++) {
				x_array[i] = (x_array[i] * sx + center_x * (1 - sx));
				y_array[i] = (y_array[i] * sy + center_y * (1 - sy));
			}
		}

		// Update width and height after scaling
		width = Math.sqrt(Math.pow((x_array[1] - x_array[0]), 2) + Math.pow(y_array[1] - y_array[0], 2));
		height = Math.sqrt(Math.pow((x_array[2] - x_array[1]), 2) + Math.pow(y_array[2] - y_array[1], 2));
		x = x_array[0];
		y = y_array[0];
	}

	/**
	 * Rotates the gun by the specified angle.
	 * 
	 * @param angle The angle by which to rotate the gun.
	 */
	void rotate(double angle) {
		if ((gun_angle > (-Math.PI / 2) && gun_angle < (Math.PI / 2)) || (gun_angle <= (-Math.PI / 2) && angle > 0)
				|| (gun_angle >= (Math.PI / 2) && angle < 0)) {
			double temp_x, temp_y;
			gun_angle += angle;
			for (int i = 0; i < x_array.length; i++) {
				temp_x = x_array[i];
				temp_y = y_array[i];
				x_array[i] = (temp_x * Math.cos(angle) - temp_y * Math.sin(angle) + center_x * (1 - Math.cos(angle))
						+ center_y * Math.sin(angle));
				y_array[i] = (temp_x * Math.sin(angle) + temp_y * Math.cos(angle) + center_y * (1 - Math.cos(angle))
						- center_x * Math.sin(angle));
			}
			// Update width and height after rotation
			width = Math.sqrt(Math.pow((x_array[1] - x_array[0]), 2) + Math.pow(y_array[1] - y_array[0], 2));
			height = Math.sqrt(Math.pow((x_array[2] - x_array[1]), 2) + Math.pow(y_array[2] - y_array[1], 2));
			x = x_array[0];
			y = y_array[0];
		}
	}

	/**
	 * Moves the gun up or down based on the specified direction.
	 * 
	 * @param direction The direction to move the gun. true for up, false for down.
	 */
	void gunUpDown(boolean direction) {
		if (direction) {
			center_y -= 7;
			for (int i = 0; i < y_array.length; i++) {
				y_array[i] = y_array[i] - 7;
			}
		} else {
			center_y += 7;
			for (int i = 0; i < y_array.length; i++) {
				y_array[i] = y_array[i] + 7;
			}
		}
		y = y_array[0];
	}

	/**
	 * Draws the gun on the specified graphics context.
	 * 
	 * @param g The graphics context on which to draw the gun.
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillPolygon(Arrays.stream(x_array).mapToInt(d -> (int) d).toArray(),
				Arrays.stream(y_array).mapToInt(d -> (int) d).toArray(), x_array.length);
	}
}
