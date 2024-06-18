/**
 * Parametric equations for drawing a circle based on given parameters:
 * - Radius: r
 * - Center coordinates: (x0, y0)
 * 
 * The parametric equations for the circle are:
 * x(t) = x0 + r * cos(t)
 * y(t) = y0 + r * sin(t)
 * 
 * where t varies from 0 to 2π (full circle).
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
 * Draws a parametric circle using specified parameters: radius, center (x0,
 * y0).
 * 
 * @author Sourashis Das
 */
public class Parametric_Circle extends Draw {
	int r, x0, y0;

	/**
	 * Draws a parametric circle on the specified panel using the given parameters.
	 * 
	 * @param drawPanel The panel where the circle will be drawn.
	 * @param dataPanel The panel containing input fields for circle parameters.
	 * @param data      The label or description of the circle to be drawn.
	 */
	public void draw_curve(MyPanel drawPanel, JPanel dataPanel, String data) {
		super.draw_curve(drawPanel, dataPanel, data);

		// Input fields for circle parameters
		JTextField r_Field = new JTextField(10);
		JTextField x0_Field = new JTextField(10);
		JTextField y0_Field = new JTextField(10);
		
		JTextField[] textFields = {r_Field,x0_Field,y0_Field};
		addNavigation(textFields);

		// Button to initiate drawing
		JButton getButton = new JButton("Draw " + data);
		getButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graphics2D g = (Graphics2D) drawPanel.getGraphics();
				g.clearRect(0, 0, 1600, 1600);

				// Parse input parameters
				r = Integer.parseInt(r_Field.getText());
				x0 = Integer.parseInt(x0_Field.getText());
				y0 = Integer.parseInt(y0_Field.getText());
				y0 = drawPanel.height - y0;

				// Draw circle points using parametric equations
				double x, y;
				for (double angle = 0; angle <= Math.TAU; angle += 0.005) {
					x = r * Math.cos(angle);
					y = r * Math.sin(angle);
					g.drawOval(x0 + (int) x, y0 - (int) y, 1, 1);
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
		dataPanel.add(new JLabel("Radius: "));
		dataPanel.add(r_Field);
		dataPanel.add(new JLabel("Center X: "));
		dataPanel.add(x0_Field);
		dataPanel.add(new JLabel("Center Y: "));
		dataPanel.add(y0_Field);
		dataPanel.add(getButton);

		dataPanel.revalidate(); // Refresh panel layout
	}
}
