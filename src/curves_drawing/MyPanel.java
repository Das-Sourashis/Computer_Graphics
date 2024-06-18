package curves_drawing;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * This class represents a custom JPanel that tracks its own height and width.
 * It includes a component listener to update these dimensions when the panel is
 * resized.
 * 
 * @author Sourashis Das
 */

public class MyPanel extends JPanel {
	int height, width; // Variables to store height and width of the panel

	/**
	 * Constructor to initialize the MyPanel. It adds a component listener to track
	 * size changes.
	 */
	public MyPanel() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				height = getHeight(); // Update height when component is resized
				width = getWidth(); // Update width when component is resized
			}
		});
	}
}
