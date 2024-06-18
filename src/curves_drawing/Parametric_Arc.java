/**
 * Parametric equations for drawing an arc based on given parameters:
 * - Radius: r
 * - Center coordinates: (x0, y0)
 * - Start Angle: θ_start (in radians)
 * - End Angle: θ_end (in radians)
 * 
 * The parametric equations for the arc are:
 * x(t) = x0 + r * cos(t)
 * y(t) = y0 + r * sin(t)
 * 
 * where t varies from θ_start to θ_end.
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
 * Draws a parametric arc using specified parameters: radius, center (x0, y0),
 * start angle, and end angle.
 * 
 * @author Sourashis Das
 */
public class Parametric_Arc extends Draw {
	int r, x0, y0;
	double start_angle, end_angle;

	/**
	 * Draws a parametric arc on the specified panel using the given parameters.
	 * 
	 * @param drawPanel The panel where the arc will be drawn.
	 * @param dataPanel The panel containing input fields for arc parameters.
	 * @param data      The label or description of the arc to be drawn.
	 */
	public void draw_curve(MyPanel drawPanel, JPanel dataPanel, String data) {
		super.draw_curve(drawPanel, dataPanel, data);

		// Input fields for arc parameters
		JTextField r_Field = new JTextField(10);
		JTextField x0_Field = new JTextField(10);
		JTextField y0_Field = new JTextField(10);
		JTextField start_angle_Field = new JTextField(10);
		JTextField end_angle_Field = new JTextField(10);
		
		JTextField[] textFields = {r_Field,x0_Field,y0_Field,start_angle_Field,end_angle_Field};
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
				start_angle = Double.parseDouble(start_angle_Field.getText());
				end_angle = Double.parseDouble(end_angle_Field.getText());
				start_angle = Math.toRadians(start_angle); // Convert degrees to radians
				end_angle = Math.toRadians(end_angle); // Convert degrees to radians

				// Draw arc points using parametric equations
				double x, y;
				for (double angle = start_angle; angle <= end_angle; angle += 0.005) {
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
		dataPanel.add(new JLabel("Arc Radius: "));
		dataPanel.add(r_Field);
		dataPanel.add(new JLabel("Center X: "));
		dataPanel.add(x0_Field);
		dataPanel.add(new JLabel("Center Y: "));
		dataPanel.add(y0_Field);
		dataPanel.add(new JLabel("Start Angle (degrees): "));
		dataPanel.add(start_angle_Field);
		dataPanel.add(new JLabel("End Angle (degrees): "));
		dataPanel.add(end_angle_Field);
		dataPanel.add(getButton);

		dataPanel.revalidate(); // Refresh panel layout
	}
}
