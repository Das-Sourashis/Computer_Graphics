/**
 * Parametric equations for drawing a line segment based on given endpoints:
 * - Endpoint 1: (x1, y1)
 * - Endpoint 2: (x2, y2)
 * 
 * The parametric equations for the line segment are:
 * x(t) = (1 - t) * x1 + t * x2
 * y(t) = (1 - t) * y1 + t * y2
 * 
 * where t varies from 0 to 1, representing the interpolation between the two endpoints.
 */
package curves_drawing;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Draws a parametric line segment using specified endpoints (x1, y1) and (x2,
 * y2).
 * 
 * @author Sourashis Das
 */
public class Parametric_Line extends Draw {
	int x1, x2, y1, y2;

	/**
	 * Draws a parametric line segment on the specified panel using the given
	 * endpoints.
	 * 
	 * @param drawPanel The panel where the line segment will be drawn.
	 * @param dataPanel The panel containing input fields for line segment
	 *                  parameters.
	 * @param data      The label or description of the line segment to be drawn.
	 */
	public void draw_curve(MyPanel drawPanel, JPanel dataPanel, String data) {
		super.draw_curve(drawPanel, dataPanel, data);

		// Input fields for line segment parameters
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
				Graphics2D g = (Graphics2D) drawPanel.getGraphics();
				g.clearRect(0, 0, 1600, 1600);

				// Parse input parameters
				x1 = Integer.parseInt(x1_Field.getText());
				x2 = Integer.parseInt(x2_Field.getText());
				y1 = Integer.parseInt(y1_Field.getText());
				y2 = Integer.parseInt(y2_Field.getText());
				y1 = drawPanel.height - y1;
				y2 = drawPanel.height - y2;

				// Draw line segment using parametric equations
				double x, y;
				for (double t = 0.001; t <= 1; t += 0.001) {
					x = (1 - t) * x1 + t * x2;
					y = (1 - t) * y1 + t * y2;
					g.drawOval((int) x, (int) y, 1, 1);
					try {
						Thread.sleep(1); // Delay for visualization
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				g.dispose(); // Clean up graphics resources
			}
		});

		// Add components to dataPanel for user input
		dataPanel.add(new JLabel("Endpoint 1 - x1: "));
		dataPanel.add(x1_Field);
		dataPanel.add(new JLabel("Endpoint 1 - y1: "));
		dataPanel.add(y1_Field);
		dataPanel.add(new JLabel("Endpoint 2 - x2: "));
		dataPanel.add(x2_Field);
		dataPanel.add(new JLabel("Endpoint 2 - y2: "));
		dataPanel.add(y2_Field);
		dataPanel.add(getButton);

		dataPanel.revalidate(); // Refresh panel layout
	}
}
