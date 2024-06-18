package curves_drawing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Abstract class representing a drawing strategy for various curves.
 * 
 * Author: Sourashis Das
 */
public abstract class Draw {

    JButton getButton;
    double x, y;

    /**
     * Method to draw a curve on the specified drawPanel based on the selected
     * scheme.
     * 
     * @param drawPanel The panel where the curve is drawn.
     * @param dataPanel The panel for data input (not used in current
     *                  implementation).
     * @param scheme    The selected drawing scheme.
     */
    public void draw_curve(MyPanel drawPanel, JPanel dataPanel, String scheme) {
        // Remove all components from dataPanel and drawPanel
        dataPanel.removeAll();
        dataPanel.repaint();
        drawPanel.removeAll();
        dataPanel.revalidate();
        drawPanel.revalidate();
    }

    /**
     * Adds navigation functionality between text fields using left and right arrow keys.
     * 
     * @param fields Array of JTextFields to add navigation to.
     */
    protected void addNavigation(JTextField[] fields) {
        for (int i = 0; i < fields.length; i++) {
            int currentIndex = i;
            fields[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        if (currentIndex > 0) {
                            fields[currentIndex - 1].requestFocus();
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        if (currentIndex < fields.length - 1) {
                            fields[currentIndex + 1].requestFocus();
                        }
                    }
                }
            });
        }
    }
}
