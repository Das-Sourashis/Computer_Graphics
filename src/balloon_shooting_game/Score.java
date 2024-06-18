package balloon_shooting_game;

import java.awt.Font;
import java.awt.Graphics;

/**
 * The Score class represents the score display in the game. It implements the
 * DisplayObject interface to draw the score on the screen.
 * 
 * @author Sourashis Das
 */
public class Score implements DisplayObject {

	/**
	 * Draws the current score on the screen.
	 * 
	 * @param g The Graphics object used for drawing.
	 */
	@Override
	public void draw(Graphics g) {
		// Set font for the score display
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		// Draw the score at the specified position
		g.drawString("Your Score is : " + GamePanel.score, 300, 40);
	}
}
