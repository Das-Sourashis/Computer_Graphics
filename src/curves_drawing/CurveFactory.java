package curves_drawing;

import javax.swing.JPanel;

/**
 * Factory class to create and draw various curves based on selected schemes.
 * 
 * @author Sourashis Das
 */
public class CurveFactory {

	/**
	 * Method to draw a curve based on the selected scheme.
	 * 
	 * @param drawPanel The panel where the curve is drawn.
	 * @param dataPanel The panel for data input (not used in current
	 *                  implementation).
	 * @param scheme    The selected drawing scheme.
	 */
	public static void draw(MyPanel drawPanel, JPanel dataPanel, String scheme) {
		createCurve(scheme).draw_curve(drawPanel, dataPanel, scheme);
	}

	/**
	 * Method to create an curve based on the selected scheme.
	 * 
	 * @param scheme The selected drawing scheme.
	 * @return The encoder (strategy) instance corresponding to the selected scheme.
	 */
	public static Draw createCurve(String scheme) {
		if (scheme.equals("DDA_Line")) {
			return new dda_line();
		} else if (scheme.equals("Bresenham_Line")) {
			return new Bresenham_Line();
		} else if (scheme.equals("Bresenham_Circle")) {
			return new Bresenham_Circle();
		} else if (scheme.equals("Mid_Point_Circle")) {
			return new MidPoint_Circle();
		} else if (scheme.equals("Quadratic_Bézier_curve")) {
			return new Quadratic_Bezier_curve();
		} else if (scheme.equals("Cubic_Bézier_curve")) {
			return new Cubic_Bezier_curve();
		} else if (scheme.equals("Parametric_Line")) {
			return new Parametric_Line();
		} else if (scheme.equals("Parametric_Circle")) {
			return new Parametric_Circle();
		} else if (scheme.equals("Parametric_Arc")) {
			return new Parametric_Arc();
		} else if (scheme.equals("Parametric_Ellipse")) {
			return new Parametric_Ellipse();
		} else if (scheme.equals("Parametric_Spiral")) {
			return new Parametric_Spiral();
		} else {
			return null; // TO BE DONE for OTHER SCHEMES....
		}
	}
}
