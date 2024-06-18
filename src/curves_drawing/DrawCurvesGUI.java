package curves_drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * GUI application for drawing various curves using different techniques.
 * 
 * @author Sourashis Das
 *
 */
public class DrawCurvesGUI extends JFrame implements ActionListener {
	JComboBox<String> techniques = null; // Dropdown to select drawing technique
	MyPanel drawPanel = new MyPanel(); // Panel where curves are drawn
	JPanel dataPanel = new JPanel(); // Panel for input data
	JPanel northContainerPanel = new JPanel(); // Container panel for top components

	/**
	 * Constructor to initialize the GUI components.
	 */
	public DrawCurvesGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH); // Maximize the window
		setResizable(false);
		setTitle("Draw Curves");
		initComponents(); // Initialize GUI components
	}

	/**
	 * Method to initialize and layout GUI components.
	 */
	private void initComponents() {
		JPanel topPanel = new JPanel(); // Panel for technique selection and buttons
		String list[] = { "DDA_Line", "Bresenham_Line", "Bresenham_Circle", "Mid_Point_Circle",
				"Quadratic_Bézier_curve", "Cubic_Bézier_curve", "Parametric_Line", "Parametric_Circle",
				"Parametric_Arc", "Parametric_Ellipse", "Parametric_Spiral" };
		techniques = new JComboBox<String>(list); // Dropdown with available techniques
		topPanel.add(new JLabel("Technique:"));
		topPanel.add(techniques);
		JButton drawBtn = new JButton("Get Inputs"); // Button to get inputs
		topPanel.add(drawBtn);
		JButton clrBtn = new JButton("Clear"); // Button to clear the drawing
		topPanel.add(clrBtn);
		clrBtn.addActionListener(this); // Register ActionListener for clear button
		drawBtn.addActionListener(this); // Register ActionListener for get inputs button

		northContainerPanel.setLayout(new GridLayout(2, 1)); // Layout for top container panel
		northContainerPanel.add(topPanel); // Add technique selection panel
		northContainerPanel.add(dataPanel); // Add data input panel

		add(drawPanel); // Add drawing panel to the frame
		add(northContainerPanel, BorderLayout.NORTH); // Add top container panel to the frame

		topPanel.setBackground(Color.GREEN); // Set background color for technique panel
		dataPanel.setBackground(Color.YELLOW); // Set background color for data panel
		drawPanel.setBackground(Color.WHITE); // Set background color for drawing panel
	}


	/**
	 * ActionListener implementation for handling button clicks.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Get Inputs")) {
			create(); // Call create method to process inputs
		} else if (e.getActionCommand().equals("Clear")) {
			drawPanel.repaint(); // Clear the drawing panel
		}
	}

	/**
	 * Method to create the selected curve based on the chosen technique.
	 */
	private void create() {
		String scheme = techniques.getSelectedItem().toString(); // Get selected technique
		CurveFactory.draw(drawPanel, dataPanel, scheme); // Use CurveFactory to draw the curve
	}
}
