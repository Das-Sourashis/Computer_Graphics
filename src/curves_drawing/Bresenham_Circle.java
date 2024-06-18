package curves_drawing;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Draws a circle using Bresenham's algorithm based on user-provided center and
 * radius.
 * 
 * @author Sourashis Das
 */
public class Bresenham_Circle extends Draw {
	int xCenter, yCenter, radius;

	/**
	 * Draws the Bresenham circle on the drawPanel.
	 * 
	 * @param drawPanel The panel where the circle is drawn.
	 * @param dataPanel The panel for data input
	 * @param data      The string representation of the drawing scheme.
	 */
	public void draw_curve(MyPanel drawPanel, JPanel dataPanel, String data) {
		super.draw_curve(drawPanel, dataPanel, data); // Clear previous drawings

		// Create input fields and button for user interaction
		JTextField xCenterField = new JTextField(10);
		JTextField yCenterField = new JTextField(10);
		JTextField radiusField = new JTextField(10);
		
		JTextField[] textFields = {radiusField,xCenterField,yCenterField};
		addNavigation(textFields);

		JButton getButton = new JButton("Draw " + data);

		// Action listener for the draw button
		getButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graphics g = drawPanel.getGraphics();
				g.clearRect(0, 0, drawPanel.getWidth(), drawPanel.getHeight());

				// Parse user input for center coordinates and radius
				xCenter = Integer.parseInt(xCenterField.getText());
				yCenter = Integer.parseInt(yCenterField.getText());
				radius = Integer.parseInt(radiusField.getText());

				// Adjust yCenter for panel coordinates
				yCenter = drawPanel.height - yCenter;

				// Initialize Bresenham's circle algorithm variables
				int x = 0;
				int y = radius;
				int d = 3 - 2 * radius;

				// Draw initial circle points
				drawCirclePoints(g, xCenter, yCenter, x, y);

				// Bresenham's algorithm loop for drawing the circle
				while (y >= x) {
					x++;
					if (d > 0) {
						y--;
						d = d + 4 * (x - y) + 10;
					} else {
						d = d + 4 * x + 6;
					}
					// Draw points symmetrically around the circle
					drawCirclePoints(g, xCenter, yCenter, x, y);
					try {
						Thread.sleep(10); // Delay for visualization
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
				g.dispose(); // Dispose of the graphics object
			}
		});

		// Add input fields, labels, and button to the dataPanel
		dataPanel.add(new JLabel("Radius: "));
		dataPanel.add(radiusField);
		dataPanel.add(new JLabel("Center X: "));
		dataPanel.add(xCenterField);
		dataPanel.add(new JLabel("Center Y: "));
		dataPanel.add(yCenterField);
		dataPanel.add(getButton);

		dataPanel.revalidate(); // Refresh the dataPanel to reflect changes
	}

	/**
	 * Helper method to draw the points of the circle using Bresenham's algorithm.
	 * 
	 * @param g  The graphics object used for drawing.
	 * @param xc The x-coordinate of the center of the circle.
	 * @param yc The y-coordinate of the center of the circle.
	 * @param x  The current x-coordinate relative to the center.
	 * @param y  The current y-coordinate relative to the center.
	 */
	private void drawCirclePoints(Graphics g, int xc, int yc, int x, int y) {
		// Draw points in all octants to form a circle
		g.drawOval(xc + x, yc + y, 1, 1);
		g.drawOval(xc - x, yc + y, 1, 1);
		g.drawOval(xc + x, yc - y, 1, 1);
		g.drawOval(xc - x, yc - y, 1, 1);
		g.drawOval(xc + y, yc + x, 1, 1);
		g.drawOval(xc - y, yc + x, 1, 1);
		g.drawOval(xc + y, yc - x, 1, 1);
		g.drawOval(xc - y, yc - x, 1, 1);
	}
}
