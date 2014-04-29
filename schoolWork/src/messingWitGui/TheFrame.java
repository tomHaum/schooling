package messingWitGui;

import java.awt.Component;

import javax.swing.JFrame;

public class TheFrame{
	JFrame frame;
	TheFrame(){
		frame = new JFrame();
		
	}
	public void addComponent(Component c){
		frame.add(c);
	}
	public void show(){
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
