package balloon_shooting_game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * The MyGUI class creates the main window for the balloon shooting game. It
 * sets up the game environment and periodically repaints the game panel.
 * 
 * @author Sourashis Das
 */
public class MyGUI extends JFrame implements ActionListener {

	GamePanel gamePanel = new GamePanel(); // The panel where the game is drawn
	static int frame_height, frame_width; // The dimensions of the frame

	/**
	 * Constructs a new MyGUI frame for the game. Initializes the frame properties
	 * and components.
	 */
	public MyGUI() {
		setTitle("Balloon Game");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		initComponents(); // Initialize components
		frame_height = getHeight();
		frame_width = getWidth();

		// Add a listener to handle window resize events
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				frame_height = getHeight();
				frame_width = getWidth();
			}
		});

		// Create a timer to repaint the drawPanel every 15 milliseconds
		Timer t = new Timer(10, this);
		t.start();
	}

	/**
	 * Initializes and adds components to the frame.
	 */
	private void initComponents() {
		add(gamePanel);
	}

	/**
	 * The main method to run the game.
	 * 
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {
		MyGUI gui = new MyGUI(); // Create a new MyGUI instance
		gui.addKeyListener(gui.gamePanel); // Add key listener to the drawPanel
		gui.setVisible(true); // Make the frame visible
	}

	/**
	 * This method is called every time the Timer triggers an action event.
	 * 
	 * @param e The action event triggered by the Timer
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		gamePanel.repaint();
	}
}
