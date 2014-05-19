package BlockBreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GameWindow extends JPanel {
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setBackground(Color.RED);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i % 2 == 0) {
					if (j % 2 == 0)
						g2.setColor(Color.BLUE);
					else
						g2.setColor(Color.GREEN);
				} else {
					if (j % 2 == 0)
						g2.setColor(Color.GREEN);
					else
						g2.setColor(Color.BLUE);
				}
				g2.fillRect(i * 50, j * 50, 50, 50);
			}
		}
	}
}
