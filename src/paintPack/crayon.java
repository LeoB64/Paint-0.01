package paintPack;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Vector;

public class crayon extends figureGeometrique {

	private Vector<Point> vecPoint = new Vector<Point>();
	private Color color;
	private int stroke;

	crayon() {
	};

	crayon(Point p1, Color color, int stroke) {
		vecPoint.add(p1);
		vecPoint.add(p1);
		this.color = color;
		this.stroke = stroke;
	}

	public void setP2(Point p2) {
		vecPoint.add(p2);
	}

	public void drawFigure(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		g2d.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.0f));

		for (int i = 0; i < vecPoint.size() - 1; ++i)
			g2d.drawLine(vecPoint.elementAt(i).x, vecPoint.elementAt(i).y, vecPoint.elementAt(i + 1).x,
					vecPoint.elementAt(i + 1).y);
	}
}
