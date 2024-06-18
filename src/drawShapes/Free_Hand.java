package drawShapes;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Free_Hand implements DisplayObject {

    private List<Point> points;

    public Free_Hand(int x1, int y1) {
        points = new ArrayList<>();
        points.add(new Point(x1, y1));
    }

    public void addPoint(int x, int y) {
        points.add(new Point(x, y));
    }

    @Override
    public void draw(Graphics g) {
        if (points.size() < 2) {
            return;
        }
        Point prevPoint = points.get(0);
        for (int i = 1; i < points.size(); i++) {
            Point currentPoint = points.get(i);
            g.drawLine(prevPoint.x, prevPoint.y, currentPoint.x, currentPoint.y);
            prevPoint = currentPoint;
        }
    }

}
