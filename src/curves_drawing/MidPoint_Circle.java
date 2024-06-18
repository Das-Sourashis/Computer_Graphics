package curves_drawing;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Sourashis Das
 */
public class MidPoint_Circle extends Draw {
	int xCenter, yCenter, radius;

	/**
	 * Draws a circle on the specified panel using the Mid-Point Circle drawing
	 * algorithm.
	 * 
	 * @param drawPanel The panel where the circle will be drawn.
	 * @param dataPanel The panel containing input fields for center coordinates and
	 *                  radius.
	 * @param data      The label or description of the circle to be drawn.
	 */
	public void draw_curve(MyPanel drawPanel, JPanel dataPanel, String data) {
		super.draw_curve(drawPanel, dataPanel, data);

		// Input fields for center coordinates and radius
		JTextField xCenterField = new JTextField(10);
		JTextField yCenterField = new JTextField(10);
		JTextField radiusField = new JTextField(10);
		
		JTextField[] textFields = {radiusField,xCenterField,yCenterField};
		addNavigation(textFields);

		// Button to initiate drawing
		JButton getButton = new JButton("Draw " + data);
		getButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graphics g = drawPanel.getGraphics();
				g.clearRect(0, 0, drawPanel.getWidth(), drawPanel.getHeight());

				// Parse input coordinates and radius
				xCenter = Integer.parseInt(xCenterField.getText());
				yCenter = Integer.parseInt(yCenterField.getText());
				radius = Integer.parseInt(radiusField.getText());

				// Adjust y-coordinate (assuming drawPanel's origin is at the top-left corner)
				yCenter = drawPanel.height - yCenter;

				// Initialize variables for Mid-Point Circle algorithm
				int x = 0;
				int y = radius;
				int p = 1 - radius;

				// Draw initial points of the circle
				drawCirclePoints(g, xCenter, yCenter, x, y);

				// Iterate through circle points using Mid-Point Circle algorithm
				while (x <= y) {
					x++;
					if (p < 0) {
						p += 2 * x + 1;
					} else {
						y--;
						p += 2 * (x - y) + 1;
					}
					drawCirclePoints(g, xCenter, yCenter, x, y);
					try {
						Thread.sleep(10); // Delay for visualization
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
				g.dispose(); // Clean up graphics resources
			}
		});

		// Add components to dataPanel for user input
		dataPanel.add(new JLabel("Radius: "));
		dataPanel.add(radiusField);
		dataPanel.add(new JLabel("Center X: "));
		dataPanel.add(xCenterField);
		dataPanel.add(new JLabel("Center Y: "));
		dataPanel.add(yCenterField);
		dataPanel.add(getButton);

		dataPanel.revalidate(); // Refresh panel layout
	}

	/**
	 * Draws the circle points symmetrically around the center.
	 * 
	 * @param g  The graphics object used for drawing.
	 * @param xc The x-coordinate of the circle's center.
	 * @param yc The y-coordinate of the circle's center.
	 * @param x  The current x-offset from the center.
	 * @param y  The current y-offset from the center.
	 */
	private void drawCirclePoints(Graphics g, int xc, int yc, int x, int y) {
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
