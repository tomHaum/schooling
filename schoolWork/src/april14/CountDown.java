package april14;

import java.awt.GridLayout;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CountDown extends JFrame{
	Timer t = new Timer();
	static CountDown frame = new CountDown();
	public static void main(String[] args){
		frame.init();
	}
	public void init(){
		JPanel content = new JPanel();
		JPanel imagePanel = new JPanel();
		content.setLayout(new GridLayout(0,3));
		JButton startButton = new JButton("Start");
		JLabel time = new JLabel("0");
		content.add(startButton);
		content.add(time);
		content.add(imagePanel);
		frame.setContentPane(content);
		frame.pack();
		frame.setVisible(true);
	}
}
