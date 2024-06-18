/**
 * Bresenham's algorithm calculates the pixels needed to draw a line between two points
 * with minimal computations, ensuring that the resulting line appears smooth and straight.
 * It handles both shallow and steep slopes efficiently by choosing the optimal pixels to
 * plot based on the error term calculated during the process.
 * 
 */

package curves_drawing;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Draws a line using Bresenham's algorithm based on user-provided end points.
 * 
 * @author Sourashis Das
 */
public class Bresenham_Line extends Draw {
	int x1, x2, y1, y2;

	/**
	 * Draws the Bresenham line on the drawPanel.
	 * 
	 * @param drawPanel The panel where the line is drawn.
	 * @param dataPanel The panel for data input.
	 * @param data      The string representation of the drawing scheme.
	 */
	public void draw_curve(MyPanel drawPanel, JPanel dataPanel, String data) {
		super.draw_curve(drawPanel, dataPanel, data); // Clear previous drawings

		// Create input fields and button for user interaction
		JTextField x1_Field = new JTextField(10);
		JTextField x2_Field = new JTextField(10);
		JTextField y1_Field = new JTextField(10);
		JTextField y2_Field = new JTextField(10);
		
		JTextField[] textFields = {x1_Field,y1_Field,x2_Field,y2_Field};
		addNavigation(textFields);

		JButton getButton = new JButton("Draw " + data);

		// Action listener for the draw button
		getButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graphics g = drawPanel.getGraphics();
				g.clearRect(0, 0, drawPanel.getWidth(), drawPanel.getHeight());

				// Parse user input for end point coordinates
				x1 = Integer.parseInt(x1_Field.getText());
				x2 = Integer.parseInt(x2_Field.getText());
				y1 = Integer.parseInt(y1_Field.getText());
				y2 = Integer.parseInt(y2_Field.getText());

				// Adjust y-coordinates for panel coordinates
				y1 = drawPanel.height - y1;
				y2 = drawPanel.height - y2;

				// Calculate differences and setup Bresenham's line algorithm variables
				int dx = Math.abs(x2 - x1);
				int dy = Math.abs(y2 - y1);
				int p = 2 * dy - dx;
				int twoDy = 2 * dy;
				int twoDyMinusDx = 2 * (dy - dx);

				int stepX = (x1 < x2) ? 1 : -1;
				int stepY = (y1 < y2) ? 1 : -1;

				int x = x1;
				int y = y1;

				g.drawOval(x, y, 1, 1); // Draw the initial point

				// Bresenham's algorithm loop for drawing the line
				if (dx > dy) { // Shallow slope case
					while (x != x2) {
						x += stepX;
						if (p < 0) {
							p += twoDy;
						} else {
							y += stepY;
							p += twoDyMinusDx;
						}
						g.drawOval(x, y, 1, 1);
						try {
							Thread.sleep(1); // Delay for visualization
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
				} else { // Steep slope case
					while (y != y2) {
						y += stepY;
						if (p < 0) {
							p += 2 * dx;
						} else {
							x += stepX;
							p += 2 * (dx - dy);
						}
						g.drawOval(x, y, 1, 1);
						try {
							Thread.sleep(1); // Delay for visualization
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
				}
				g.dispose(); // Dispose of the graphics object
			}
		});

		// Add input fields, labels, and button to the dataPanel
		dataPanel.add(new JLabel("x1: "));
		dataPanel.add(x1_Field);
		dataPanel.add(new JLabel("y1: "));
		dataPanel.add(y1_Field);
		dataPanel.add(new JLabel("x2: "));
		dataPanel.add(x2_Field);
		dataPanel.add(new JLabel("y2: "));
		dataPanel.add(y2_Field);
		dataPanel.add(getButton);

		dataPanel.revalidate(); // Refresh the dataPanel to reflect changes
	}
}
