/**
 * Parametric equations for drawing an ellipse based on given parameters:
 * - Semi-major axis length: a
 * - Semi-minor axis length: b
 * - Center coordinates: (x0, y0)
 * 
 * The parametric equations for the ellipse are:
 * x(t) = x0 + a * cos(t)
 * y(t) = y0 + b * sin(t)
 * 
 * where t varies from 0 to 2π (full ellipse).
 */
package curves_drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Draws a parametric ellipse using specified parameters: semi-major axis (a),
 * semi-minor axis (b), and center (x0, y0).
 * 
 * @author Sourashis Das
 */
public class Parametric_Ellipse extends Draw {
	int a, b, x0, y0;

	/**
	 * Draws a parametric ellipse on the specified panel using the given parameters.
	 * 
	 * @param drawPanel The panel where the ellipse will be drawn.
	 * @param dataPanel The panel containing input fields for ellipse parameters.
	 * @param data      The label or description of the ellipse to be drawn.
	 */
	public void draw_curve(MyPanel drawPanel, JPanel dataPanel, String data) {
		super.draw_curve(drawPanel, dataPanel, data);

		// Input fields for ellipse parameters
		JTextField a_Field = new JTextField(10);
		JTextField b_Field = new JTextField(10);
		JTextField x0_Field = new JTextField(10);
		JTextField y0_Field = new JTextField(10);
		
		JTextField[] textFields = {a_Field,b_Field,x0_Field,y0_Field,};
		addNavigation(textFields);

		// Button to initiate drawing
		JButton getButton = new JButton("Draw " + data);
		getButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graphics2D g = (Graphics2D) drawPanel.getGraphics();
				g.clearRect(0, 0, 1600, 1600);
				g.setColor(Color.BLACK);

				// Parse input parameters
				a = Integer.parseInt(a_Field.getText());
				b = Integer.parseInt(b_Field.getText());
				x0 = Integer.parseInt(x0_Field.getText());
				y0 = Integer.parseInt(y0_Field.getText());
				y0 = drawPanel.height - y0;

				// Draw ellipse points using parametric equations
				double x, y;
				for (double angle = 0; angle <= Math.TAU; angle += 0.002) {
					x = a * Math.cos(angle);
					y = b * Math.sin(angle);
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
		dataPanel.add(new JLabel("semi-major axis (a): "));
		dataPanel.add(a_Field);
		dataPanel.add(new JLabel("semi-minor axis (b): "));
		dataPanel.add(b_Field);
		dataPanel.add(new JLabel("Center X: "));
		dataPanel.add(x0_Field);
		dataPanel.add(new JLabel("Center Y: "));
		dataPanel.add(y0_Field);
		dataPanel.add(getButton);

		dataPanel.revalidate(); // Refresh panel layout
	}
}
