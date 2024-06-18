/**
 * DDA (Digital Differential Analyzer) Line Drawing Algorithm
 * 
 * Initialization:
 * Provide the starting (x1, y1) and ending (x2, y2) coordinates of the line.
 * 
 * Calculate the initial values:
 * Determine the differences:
 * dx = x2 - x1
 * dy = y2 - y1
 * 
 * Determine the steps and increments:
 * Determine the number of steps:
 * steps = max(|dx|, |dy|)
 * 
 * Calculate increments for x and y:
 * xIncrement = dx / steps
 * yIncrement = dy / steps
 * 
 * Plotting Points:
 * Start from the starting point (x1, y1) and move towards the ending point (x2, y2).
 * At each step, plot the integer coordinates obtained by rounding off the current coordinates.
 * 
 * Iterative Process:
 * Iterate until reaching the ending point (x2, y2).
 * Update the current coordinates:
 * x += xIncrement
 * y += yIncrement
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
 * Draws a line segment between two points (x1, y1) and (x2, y2) using the DDA
 * algorithm.
 * 
 * @author Sourashis Das
 * 
 */
public class dda_line extends Draw {
	int x1, x2, y1, y2;

	/**
	 * Draws a line segment between two points (x1, y1) and (x2, y2) using the DDA
	 * algorithm.
	 * 
	 * @param drawPanel The panel where the line will be drawn.
	 * @param dataPanel The panel containing input fields for x1, y1, x2, and y2.
	 * @param data      The label or description of the line to be drawn.
	 */
	public void draw_curve(MyPanel drawPanel, JPanel dataPanel, String data) {
		super.draw_curve(drawPanel, dataPanel, data);

		// Input fields for coordinates
		JTextField x1_Field = new JTextField(10);
		JTextField x2_Field = new JTextField(10);
		JTextField y1_Field = new JTextField(10);
		JTextField y2_Field = new JTextField(10);
		
		JTextField[] textFields = {x1_Field,y1_Field,x2_Field,y2_Field};
		addNavigation(textFields);


		// Button to initiate drawing
		JButton getButton = new JButton("Draw " + data);
		getButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graphics g = drawPanel.getGraphics();
				g.clearRect(0, 0, 1600, 1600); // Clear previous drawings

				// Parse input coordinates
				x1 = Integer.parseInt(x1_Field.getText());
				x2 = Integer.parseInt(x2_Field.getText());
				y1 = Integer.parseInt(y1_Field.getText());
				y2 = Integer.parseInt(y2_Field.getText());

				// Adjust y-coordinates (assuming drawPanel's origin is at the top-left corner)
				y1 = drawPanel.height - y1;
				y2 = drawPanel.height - y2;

				// Calculate differences and steps
				int dx = x2 - x1;
				int dy = y2 - y1;
				int steps = Math.max(Math.abs(dx), Math.abs(dy));

				// Increment values
				double xIncrement = (double) dx / steps;
				double yIncrement = (double) dy / steps;

				// Initialize starting point
				double x = x1;
				double y = y1;

				// Draw line segment
				for (int i = 0; i <= steps; i++) {
					g.drawOval((int) x, (int) y, 1, 1); // Plot pixel
					x += xIncrement; // Increment x
					y += yIncrement; // Increment y
					try {
						Thread.sleep(1); // Delay for visual effect
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}

				g.dispose(); // Clean up graphics resources
			}
		});

		// Add components to dataPanel for user input
		dataPanel.add(new JLabel("x1 : "));
		dataPanel.add(x1_Field);
		dataPanel.add(new JLabel("y1 : "));
		dataPanel.add(y1_Field);
		dataPanel.add(new JLabel("x2 : "));
		dataPanel.add(x2_Field);
		dataPanel.add(new JLabel("y2 : "));
		dataPanel.add(y2_Field);
		dataPanel.add(getButton);

		dataPanel.revalidate(); // Refresh panel layout
	}
}
