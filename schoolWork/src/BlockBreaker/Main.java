package BlockBreaker;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main{
	public static void main(String[] args){
		JFrame frame = new JFrame("The Game");
		GameWindow g = new GameWindow();
		g.setSize(400, 400);
		JPanel p = new JPanel();
		p.add(g);
		frame.setContentPane(p);
		frame.setVisible(true);
		frame.setSize(400,400);
		g.repaint();
	}
	
}
