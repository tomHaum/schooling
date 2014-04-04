package march14;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Screen extends JPanel {
	List<Point> points = new ArrayList<Point>();
	double scaleX = 10;
	double scaleY = 10;
	int width;
	int height;
	double pointsPerPixel = 1.0;

	public void paint(Graphics g1) {
		int w = this.getWidth();
		width = w;
		int h = this.getHeight();
		height = h;

		Graphics2D g = (Graphics2D) g1;
		g.translate(w / 2, h / 2);
		g.setPaint(Color.WHITE);
		g.fill3DRect(-w / 2, -h / 2, w, h, true);
		g.setPaint(Color.BLACK);
		g.drawLine(-200, 0, 200, 0);
		g.drawLine(0, -200, 0, 200);
		if (points.size() > 0)
		{
			Point last = points.get(0);
			for (int i = 1; i < points.size(); i++) {
				Point temp = points.get(i);
				// System.out.print("point: " + i);
				// System.out.println("x: " + temp.getX() + ". y: " +
				// temp.getY());
				g.drawLine((int) (last.getX() * scaleX),
						-(int) (last.getY() * scaleY),
						(int) (temp.getX() * scaleX),
						-(int) (temp.getY() * scaleY));
				last = temp;
			}
		}
	}

	public void setPoints(List<Point> pt) {
		points.clear();
		for (int i = 0; i < pt.size(); i++) {
			points.add(pt.get(i));
			if(i == 0){
				//System.out.println(points.get(0));
			}
		}
		//System.out.println("repainting");
		repaint();
	}

	public double[] getPointArgs() {
		//System.out.println("width"  + width);
		double start = -1.0 * width / scaleX / 2.0;
		double end = start * -1.0;
		double interval = end * 2.0 / (width/pointsPerPixel);
		double[] answer = { start, end, interval };
		return answer;
	}

}