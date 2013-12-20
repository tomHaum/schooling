package qwirkle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

class Success extends JFrame {

	public Success() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		setSize(450, 450);

		JButton button = new JButton("press");
		panel.add(button);
	}

	public void paint(Graphics g) {
		super.paint(g); // fixes the immediate problem.
		Graphics2D g2 = (Graphics2D) g;
		boolean red = true;
		for (int j = 0; j < 10; j++) {
			red = !red;
			for (int i = 0; i < 10; i++) {
				if(red)g.setColor(Color.BLACK);
				else
					g.setColor(Color.RED);
				red = !red;
				g.fillRect(j * 50, i * 50, 50, 50);
			}
		}
	}

	public static void main(String[] args) {
		Success s = new Success();
		s.setVisible(true);
	}
}