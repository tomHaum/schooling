package march14;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import checkers.Piece;

public class TomIsBetterThanMatt extends JPanel {
	List<Rectangle2D> rects = new ArrayList<Rectangle2D>();
	boolean poly = false, rect = false;
	int numOfSides = 3;
	int numOfRect = 10;

	TomIsBetterThanMatt() {

	}

	public void init() {
		double x1 = -1;
		double y1 = 0;
		double x2 = -1;
		double y2 = 0;
		double area = 0;

		int inteveral = 25;
		Rectangle2D a;

		double width = (double) (2.0 / inteveral);
		for (int i = 0; i < inteveral; i++) {
			System.out.println();
			x1 = x2;
			System.out.println("x1 = " + x1);
			x2 += width;
			System.out.println("x2 = " + x2);

			y1 = (double) Math.pow(Math.abs(1 - (x1 * x1)), .5);
			System.out.println("y1 = " + y1);
			y2 = (double) Math.pow(Math.abs(1 - (x2 * x2)), .5);
			System.out.println("y2 = " + y2);
			if (y1 > y2) {
				area += y2 * width;
				a = new Rectangle2D.Double(x1 * 100, 0, width * 100, y2 * 100);
				System.out.println(y2);
			} else {
				area += y1 * width;
				a = new Rectangle2D.Double(x1 * 100, 0, width * 100, y1 * 100);
				System.out.println(y1);
			}
			System.out.println("Area: " + area);
			rects.add(a);

		}
		this.repaint();
	}

	public void paint1(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(150, 100);

		Iterator<Rectangle2D> it = rects.iterator();

		while (it.hasNext()) {
			Rectangle2D a = it.next();
			;
			g2.draw(a);
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.clearRect(-0, 0, 1000, 1000);
		System.out.println(poly + " " + rect);
		if (poly) {
			g2.translate(200, 200);
			g2.drawOval(-100, -100, 200, 200);

			int sides = numOfSides;

			double sideLengthCirc = Math.tan(Math.PI / sides);
			double areaCirc = sideLengthCirc * 1 * sides;
			g2.drawLine((int) (-sideLengthCirc * 100), 100,
					(int) (sideLengthCirc * 100), 100);
			for (int i = 0; i < sides - 1; i++) {

				g2.rotate(2 * Math.PI / sides, 0, 0);
				g2.drawLine((int) (-sideLengthCirc * 100), 100,
						(int) (sideLengthCirc * 100), 100);
			}

			double sideLengthInc = Math.sin(Math.PI / sides);
			double height = Math.cos(Math.PI / sides);
			double areaInc = sideLengthInc * height * sides;
			double area = (areaInc + areaCirc) * .5;
			System.out.println(area);
			g2.drawLine((int) (-sideLengthInc * 100), (int) (height * 100),
					(int) (sideLengthInc * 100), (int) (100 * height));
			for (int i = 0; i < sides - 1; i++) {
				g2.rotate(2 * Math.PI / sides, 0, 0);
				g2.drawLine((int) (-sideLengthInc * 100), (int) (height * 100),
						(int) (sideLengthInc * 100), (int) (100 * height));
			}
		}
		if (rect) {
			g2.translate(150, 100);
			double x1 = -1;
			double y1 = 0;
			double x2 = -1;
			double y2 = 0;
			double area = 0;

			int inteveral = numOfRect;
			Rectangle2D a1;

			double width = (double) (2.0 / inteveral);
			for (int i = 0; i < inteveral; i++) {
				//System.out.println();
				x1 = x2;
				// System.out.println("x1 = " + x1);
				x2 += width;
					// System.out.println("x2 = " + x2);
				
				y1 = (double) Math.pow(Math.abs(1 - (x1 * x1)), .5);
				// System.out.println("y1 = " + y1);
				y2 = (double) Math.pow(Math.abs(1 - (x2 * x2)), .5);
				// System.out.println("y2 = " + y2);
				if (y1 > y2) {
					area += y2 * width;
					a1 = new Rectangle2D.Double(x1 * 100, 0, width * 100,
							y2 * 100);
					// System.out.println(y2);
				} else {
					area += y1 * width;
					a1 = new Rectangle2D.Double(x1 * 100, 0, width * 100,
							y1 * 100);
					// System.out.println(y1);
				}
				// System.out.println("Area: " + area);
				rects.add(a1);

			}
			// this.repaint();
			Iterator<Rectangle2D> it = rects.iterator();

			while (it.hasNext()) {
				Rectangle2D a2 = it.next();
				;
				g2.draw(a2);
			}
			area *= 2;
			System.out.println("Final estimate: " + area);
		}
	}
}
