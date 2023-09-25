package paintPack;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class rectangle extends figureGeometrique {

	private Color color;
	private Point p1;
	private Point p2;
	private int stroke;

	rectangle() {
	};

	rectangle(Point p1, Color color, int stroke) {
		this.p1 = p1;
		this.p2 = p1;
		this.color = color;
		this.stroke = stroke;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}

	public void drawFigure(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		g2d.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.0f));
		if (p2.x > p1.x && p2.y > p1.y)

			g.drawRect(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
		else if (p2.x < p1.x && p2.y < p1.y)
			g.drawRect(p2.x, p2.y, p1.x - p2.x, p1.y - p2.y);
		else if (p2.x > p1.x && p2.y < p1.y)
			g.drawRect(p1.x, p2.y, p2.x - p1.x, p1.y - p2.y);
		else if (p2.x < p1.x && p2.y > p1.y)
			g.drawRect(p2.x, p1.y, p1.x - p2.x, p2.y - p1.y);
	};
}
