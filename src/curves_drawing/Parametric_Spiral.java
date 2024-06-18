/**
 * Parametric equations for drawing a spiral based on given parameters:
 * - Center coordinates: (x0, y0)
 * - Angle parameter: angle (in radians)
 * 
 * The parametric equations for the spiral are:
 * x(t) = t * cos(t)
 * y(t) = t * sin(t)
 * 
 * where t varies from 0 to angle.
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
 * Draws a parametric spiral using specified parameters: center (x0, y0) and
 * angle.
 * 
 * @author Sourashis Das
 */
public class Parametric_Spiral extends Draw {
	int x0, y0; // Center coordinates
	double angle; // Angle parameter

	/**
	 * Draws a parametric spiral on the specified panel using the given parameters.
	 * 
	 * @param drawPanel The panel where the spiral will be drawn.
	 * @param dataPanel The panel containing input fields for spiral parameters.
	 * @param data      The label or description of the spiral to be drawn.
	 */
	public void draw_curve(MyPanel drawPanel, JPanel dataPanel, String data) {
		super.draw_curve(drawPanel, dataPanel, data);

		// Input fields for spiral parameters
		JTextField x0_Field = new JTextField(10);
		JTextField y0_Field = new JTextField(10);
		JTextField angle_Field = new JTextField(10);
		
		JTextField[] textFields = {x0_Field,y0_Field,angle_Field};
		addNavigation(textFields);

		// Button to initiate drawing
		JButton getButton = new JButton("Draw " + data);
		getButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graphics2D g = (Graphics2D) drawPanel.getGraphics();
				g.clearRect(0, 0, 1600, 1600);

				// Parse input parameters
				x0 = Integer.parseInt(x0_Field.getText());
				y0 = Integer.parseInt(y0_Field.getText());
				angle = Double.parseDouble(angle_Field.getText());
				y0 = drawPanel.height - y0;

				// Draw spiral using parametric equations
				double x, y;
				for (double t = 0.0000; t <= angle; t += 0.005) {
					x = t * Math.cos(t);
					y = t * Math.sin(t);
					g.drawOval(x0 + (int) x, y0 - (int) y, 1, 1);
//					try {
//						Thread.sleep(0, 1); // Delay for visualization
//					} catch (InterruptedException e1) {
//						e1.printStackTrace();
//					}
				}
				g.dispose(); // Clean up graphics resources
			}
		});

		// Add components to dataPanel for user input
		dataPanel.add(new JLabel("Center X: "));
		dataPanel.add(x0_Field);
		dataPanel.add(new JLabel("Center Y: "));
		dataPanel.add(y0_Field);
		dataPanel.add(new JLabel("Angle (t): "));
		dataPanel.add(angle_Field);
		dataPanel.add(getButton);

		dataPanel.revalidate(); // Refresh panel layout
	}
}
