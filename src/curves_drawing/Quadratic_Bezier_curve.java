/**
 * Parametric equations and methods for drawing a Quadratic Bézier curve based on given control points:
 * - Control Point P0: (x0, y0)
 * - Control Point P1: (x1, y1)
 * - Control Point P2: (x2, y2)
 * 
 * The Quadratic Bézier curve equation is:
 * B(t) = (1-t)^2 * P0 + 2 * (1-t) * t * P1 + t^2 * P2
 * 
 * where t varies from 0 to 1.
 */
package curves_drawing;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Draws a Quadratic Bézier curve using specified control points P0, P1, P2.
 * 
 * @author Sourashis Das
 */
public class Quadratic_Bezier_curve extends Draw {
	Point p0, p1, p2; // Control points P0, P1, P2

	/**
	 * Draws a Quadratic Bézier curve on the specified panel using the given control
	 * points.
	 * 
	 * @param drawPanel The panel where the Bézier curve will be drawn.
	 * @param dataPanel The panel containing input fields for Bézier curve control
	 *                  points.
	 * @param data      The label or description of the Bézier curve to be drawn.
	 */
	public void draw_curve(MyPanel drawPanel, JPanel dataPanel, String data) {
		super.draw_curve(drawPanel, dataPanel, data);

		// Input fields for control points P0, P1, P2
		JTextField x0_Field = new JTextField(10);
		JTextField y0_Field = new JTextField(10);
		JTextField x1_Field = new JTextField(10);
		JTextField y1_Field = new JTextField(10);
		JTextField x2_Field = new JTextField(10);
		JTextField y2_Field = new JTextField(10);

		JTextField[] textFields = {x0_Field,y0_Field,x1_Field,y1_Field,x2_Field,y2_Field};
		addNavigation(textFields);

		// Button to initiate drawing
		JButton getButton = new JButton("Draw " + data);
		getButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graphics g = drawPanel.getGraphics();
				g.clearRect(0, 0, drawPanel.getWidth(), drawPanel.getHeight());

				// Parse input control points
				p0 = new Point(Integer.parseInt(x0_Field.getText()),
						drawPanel.height - Integer.parseInt(y0_Field.getText()));
				p1 = new Point(Integer.parseInt(x1_Field.getText()),
						drawPanel.height - Integer.parseInt(y1_Field.getText()));
				p2 = new Point(Integer.parseInt(x2_Field.getText()),
						drawPanel.height - Integer.parseInt(y2_Field.getText()));

				// Draw control points
				drawControlPoint(g, p0);
				drawControlPoint(g, p1);
				drawControlPoint(g, p2);

				// Draw dotted lines between control points for visualization
				drawDottedLine(g, p0, p1);
				drawDottedLine(g, p1, p2);

				// Draw Quadratic Bézier curve
				drawQuadraticBezier(g, p0, p1, p2);
			}
		});

		// Add components to dataPanel for user input
		dataPanel.add(new JLabel("P0 X: "));
		dataPanel.add(x0_Field);
		dataPanel.add(new JLabel("P0 Y: "));
		dataPanel.add(y0_Field);
		dataPanel.add(new JLabel("P1 X: "));
		dataPanel.add(x1_Field);
		dataPanel.add(new JLabel("P1 Y: "));
		dataPanel.add(y1_Field);
		dataPanel.add(new JLabel("P2 X: "));
		dataPanel.add(x2_Field);
		dataPanel.add(new JLabel("P2 Y: "));
		dataPanel.add(y2_Field);
		dataPanel.add(getButton);

		dataPanel.revalidate(); // Refresh panel layout
	}

	/**
	 * Draws a dotted line between two points on the graphics context.
	 * 
	 * @param g  The graphics context to draw on.
	 * @param p1 The starting point of the line.
	 * @param p2 The ending point of the line.
	 */
	private void drawDottedLine(Graphics g, Point p1, Point p2) {
		int dx = p2.x - p1.x;
		int dy = p2.y - p1.y;
		int steps = Math.max(Math.abs(dx), Math.abs(dy));
		double xIncrement = (double) dx / steps;
		double yIncrement = (double) dy / steps;
		double x = p1.x;
		double y = p1.y;
		boolean draw = false;

		for (int i = 0; i <= steps; i++) {
			if (draw) {
				g.drawLine((int) x, (int) y, (int) x, (int) y);
			}
			draw = !draw;
			x += xIncrement;
			y += yIncrement;
		}
	}

	/**
	 * Draws the Quadratic Bézier curve on the graphics context.
	 * 
	 * @param g  The graphics context to draw on.
	 * @param p0 Control point P0.
	 * @param p1 Control point P1.
	 * @param p2 Control point P2.
	 */
	private void drawQuadraticBezier(Graphics g, Point p0, Point p1, Point p2) {
		double t = 0.0;
		double step = 0.01; // Step size for drawing the curve

		Point prevPoint = p0;

		while (t <= 1) {
			double x = (1 - t) * (1 - t) * p0.x + 2 * (1 - t) * t * p1.x + t * t * p2.x;
			double y = (1 - t) * (1 - t) * p0.y + 2 * (1 - t) * t * p1.y + t * t * p2.y;

			Point currentPoint = new Point((int) x, (int) y);

			g.drawLine(prevPoint.x, prevPoint.y, currentPoint.x, currentPoint.y);

			prevPoint = currentPoint;
			t += step;

			try {
				Thread.sleep(5); // Delay for visualization
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Draws a filled oval representing a control point on the graphics context.
	 * 
	 * @param g The graphics context to draw on.
	 * @param p The coordinates of the control point.
	 */
	private void drawControlPoint(Graphics g, Point p) {
		int size = 5; // Size of the control point
		g.fillOval(p.x - size / 2, p.y - size / 2, size, size);
	}
}
