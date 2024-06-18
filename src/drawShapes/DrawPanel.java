package drawShapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class DrawPanel extends JPanel implements MouseListener,MouseMotionListener {
	
	List<DisplayObject> displayBuffer = new ArrayList<DisplayObject>();
	DisplayObject cobj = null;
	String scheme;
	int x,y;
	
	public DrawPanel(String scheme) {
		setBackground(Color.WHITE);
		addMouseListener(this);
		addMouseMotionListener(this);
		this.scheme = scheme;
	}
	
	public void getScheme(String scheme) {
		this.scheme = scheme;
	}
	
	public void clear() {
		displayBuffer.clear();
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(DisplayObject ob : displayBuffer) {
			ob.draw(g);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x=e.getX();
		y=e.getY();
		if(scheme.equals("Line")) {
			cobj = new Line(e.getX(), e.getY(), e.getX(), e.getY());
		}else if (scheme.equals("Circle")) {
			cobj = new Circle(e.getX(), e.getY(), 0);
		}else if (scheme.equals("Free Hand")) {
			cobj = new Free_Hand(x, y);
		}
		displayBuffer.add(cobj);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(scheme.equals("Line")) {
			Line line = (Line)cobj;
			line.x2 = e.getX();
			line.y2 = e.getY();
		}else if (scheme.equals("Circle")) {
			Circle c = (Circle)cobj;
			 int m = (int) Math.sqrt((x-e.getX())*(x-e.getX())+(y-e.getY())*(y-e.getY()));		 
			 c.x = x - m;
			 c.y = y - m;
			 c.r = 2*m;
		}else if (scheme.equals("Free Hand")) {
			Free_Hand fh = (Free_Hand)cobj;
			fh.addPoint(e.getX(), e.getY());
		}
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

