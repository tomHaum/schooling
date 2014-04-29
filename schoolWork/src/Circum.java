import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Circum extends JPanel {
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(200, 200);
		g2.drawOval(-100, -100, 200, 200);
		
		int sides = 3;
		
		double sideLengthCirc = Math.tan( Math.PI / sides);
		
		g2.drawLine((int)( -sideLengthCirc*100), 100,
				(int) (sideLengthCirc * 100), 100);
		for (int i = 0; i < sides - 1; i++) {

			g2.rotate(2 * Math.PI / sides, 0, 0);
			g2.drawLine((int)( -sideLengthCirc*100), 100,
					(int) (sideLengthCirc * 100), 100);
		}
		
		double sideLengthInc = Math.sin(Math.PI / sides);
		double height  = Math.cos(Math.PI / sides);
		g2.drawLine((int)( -sideLengthInc*100), (int)(height*100),
				(int) (sideLengthInc * 100), (int)(100*height));
		for(int i = 0; i < sides-1; i ++){
			g2.rotate(2 * Math.PI / sides, 0, 0);
			g2.drawLine((int)( -sideLengthInc*100), (int)(height*100),
					(int) (sideLengthInc * 100), (int)(100*height));
		}
	}
}
