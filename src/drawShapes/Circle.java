package drawShapes;

import java.awt.Graphics;

public class Circle implements DisplayObject{
	
	int x,y,r;
	
	public Circle(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	
	@Override
	public void draw(Graphics g) {
		g.drawOval(x, y, r, r);
		
	}

}
