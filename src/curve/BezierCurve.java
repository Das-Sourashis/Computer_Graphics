package curve;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * This class provides an interactive panel to draw Bezier curves. Users can
 * click to add control points and use buttons to draw or clear the curve. The
 * Bezier curve is computed and drawn using multiple threads for efficiency.
 * 
 * Author: Sourashis Das
 */
public class BezierCurve extends JPanel {
	private final List<Point> controlPoints = new ArrayList<>(); // List to store control points
	private final JButton drawButton = new JButton("Draw Curve"); // Button to trigger drawing the curve
	private final JButton clearButton = new JButton("Clear Panel"); // Button to clear the panel

	public BezierCurve() {
		// Set the panel size and background color
		setPreferredSize(new Dimension(800, 600));
		setBackground(Color.WHITE);

		// Mouse listener to capture control points on click
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlPoints.add(e.getPoint());
				repaint(); // Repaint to show the new control point
			}
		});

		// Action listener for the draw button to repaint the panel
		drawButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint(); // Repaint to draw the curve
			}
		});

		// Action listener for the clear button to clear the panel
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlPoints.clear(); // Clear control points
				repaint(); // Repaint to clear the panel
			}
		});

		// Add buttons to the panel
		this.add(drawButton);
		this.add(clearButton);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// Set anti-aliasing for better curve quality
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw the control points and lines connecting them
		g2d.setColor(Color.RED);
		for (int i = 0; i < controlPoints.size() - 1; i++) {
			Point p1 = controlPoints.get(i);
			Point p2 = controlPoints.get(i + 1);
			g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
		}

		// Draw small circles around control points
		for (Point p : controlPoints) {
			g2d.fillOval(p.x - 3, p.y - 3, 6, 6);
		}

		// Draw the Bezier curve if there are enough control points
		if (controlPoints.size() > 1) {
			// Calculate points on the Bezier curve
			List<Point> bezierPoints = calculateBezierPoints(controlPoints, 1000); // value of u
			g2d.setColor(Color.BLUE);
			g2d.setStroke(new BasicStroke(1));

			// Draw lines connecting the calculated Bezier points
			for (int i = 0; i < bezierPoints.size() - 1; i++) {
				Point p1 = bezierPoints.get(i);
				Point p2 = bezierPoints.get(i + 1);
				g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
			}
		}
	}

	// Calculate points on the Bezier curve using control points
	private List<Point> calculateBezierPoints(List<Point> controlPoints, int numPoints) {
		int n = controlPoints.size() - 1; // Degree of the Bezier curve
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); // Thread
																												// pool
		List<Future<Point>> futures = new ArrayList<>();

		// Submit tasks to calculate each Bezier point
		for (int i = 0; i <= numPoints; i++) {
			final double t = i / (double) numPoints; // Parameter t in [0, 1]
			Callable<Point> task = () -> {
				double x = 0;
				double y = 0;
				for (int j = 0; j <= n; j++) {
					// Calculate Bernstein polynomial
					double bernsteinPoly = binomialCoefficient(n, j) * Math.pow(1 - t, n - j) * Math.pow(t, j);
					x += bernsteinPoly * controlPoints.get(j).x;
					y += bernsteinPoly * controlPoints.get(j).y;
				}
				return new Point((int) x, (int) y);
			};
			futures.add(executor.submit(task)); // Submit task to executor
		}

		// Collect results from futures
		List<Point> points = new ArrayList<>();
		for (Future<Point> future : futures) {
			try {
				points.add(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		executor.shutdown(); // Shutdown the executor
		return points; // Return calculated points
	}

	// Calculate binomial coefficient
	private int binomialCoefficient(int n, int k) {
		int result = 1;
		for (int i = 1; i <= k; i++) {
			result = result * (n - i + 1) / i;
		}
		return result;
	}

	// Create and show the GUI
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Bezier Curve");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new BezierCurve());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	// Main method to run the application
	public static void main(String[] args) {
		SwingUtilities.invokeLater(BezierCurve::createAndShowGUI);
	}
}
