package march14;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Drawing extends MouseAdapter {
	JFrame frame = new JFrame();

	public static void main(String[] args) {
		Drawing d = new Drawing();
		d.init();
	}

	public void init() {
		frame.getContentPane().add(new JComponent() {
			List<Point> points = new ArrayList<Point>();
			List<List<Point>> superList = new ArrayList<List<Point>>();
			Color[] colors = { Color.RED, Color.BLACK, Color.BLUE, Color.CYAN,
					Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.MAGENTA,
					Color.YELLOW };
			float trans = 0f;
			{
				MouseAdapter l = new MouseAdapter() {
					public void mouseMoved(MouseEvent e) {
						// System.out.println("Moved");
						points.add(e.getPoint());
						repaint();
					}

					public void mousePressed(MouseEvent e) {
						System.out.println("press");
						if (e.getButton() == MouseEvent.BUTTON3) {
							System.out.println("right pressed");
							superList.add(points);
							points = new ArrayList<Point>();
							repaint();
						}
						if (e.getButton() == MouseEvent.BUTTON1) {
							System.out.println("left pressed");
						}
					}

					// needs work
					public void mouseWheelMoved(MouseWheelEvent e) {
						System.out.println("wheeled");

						float wheelRot = e.getWheelRotation();
						System.out.println(wheelRot);
						trans += .1 * wheelRot;
						if (trans < 0) {
							trans = 0f;
							System.out.println("set to 0");
						}
						if (trans > 1) {
							System.out.println("set to 1");
							trans = 1f;
						}
						repaint();

					}
				};
				addMouseMotionListener(l);
				addMouseListener(l);
				addMouseWheelListener(l);
			}

			public void paint(Graphics g) {
				// System.out.println("painting");
				Graphics2D g2 = (Graphics2D) g;
				g2.setPaint(new Color(1f, 0f, 0f, trans));
				Iterator<List<Point>> mega = superList.iterator();
				while (mega.hasNext()) {
					List<Point> l = mega.next();
					Iterator<Point> it = l.iterator();
					Point last = null;
					while (it.hasNext()) {
						// System.out.println("drawing");
						Point current = it.next();
						if (last != null) {
							g2.drawLine((int) last.getX(), (int) last.getY(),
									(int) current.getX(), (int) current.getY());
						}
						last = current;
					}
				}
			}
		});
		frame.setVisible(true);
		frame.setSize(500, 500);
	}
}
