package qwirkle;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

public class GUI{

	private JFrame frame = new JFrame("qwirkle");
	GUI() {
		// JFrame frame = new JFrame("Mancala");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//addButtons(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
		frame.setSize(150,150);
	}

	public void paint(Graphics g){
		g.setColor(Color.RED);
		for(int i = 0; i<10 ; i++){
			g.drawRect(10,10+i*10,10,10);
		}
		
		
	}
}
