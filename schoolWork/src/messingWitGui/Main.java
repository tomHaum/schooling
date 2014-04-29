package messingWitGui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {
	static JTextField text = new JTextField("Default");
	
	public static void main(String[] args){
		JFrame frame = new JFrame("The Window");//the window that holds all the panels
		frame.setVisible(true);
		
		Panel content = new Panel();
		content.setLayout(new GridLayout(0,2));
		//when panels are added they will be in 2 colums with infinite rows
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0,255,255));
		
		panel.setLayout( new GridLayout(0,2));
		//when buttons are added they will be in only 2 columns and infinite rows
		
		JButton button = new JButton("Button 1");
		button.addActionListener(new ActionListener(){
			//Anonymous inner class
			//used to define a new action listener at this one point
			@Override
			public void actionPerformed(ActionEvent arg0) {//when button is clicked
				System.out.println("Button clicked");
				text.setText("Button 1");
			}
		});
		
		JButton b2 = new JButton("button 2");
		b2.addActionListener(al);
		
		panel.add(button);
		panel.add(b2);
		panel.add(text);
		final JButton b3 = new JButton("I say what u say");
		b3.addActionListener(new ActionListener(){
			//Anonymous inner class
			//used to define a new action listener at this one point
			@Override
			public void actionPerformed(ActionEvent arg0) {//when button is clicked
				b3.setText(text.getText());
			}
		});
		
		panel.add(b3);
		panel.add(new JButton("Also does nothing"));
		content.add(panel);//adds my panel to the content panel
		
		JPanel p2 = new JPanel();
		p2.setBackground(Color.RED);
		p2.add(new JButton("Does nothing"));
		
		
		content.add(p2);
		
		frame.setContentPane(content);//adds the content panel to the frame
		frame.pack();
		frame.setMinimumSize(new Dimension(200,200));
		frame.pack();
		
		//GridLayout layout = new GridLayout(0,4);
		//content.setLayout(layout);
		/*
		JPanel[] panel = new JPanel[20];
		for(int i = 0; i< panel.length; i++){
			panel[i] = new JPanel();
			panel[i].setBackground(new Color(i*10,i*10,i*10));
			panel[i].setSize(new Dimension(50,50));
			panel[i].setMinimumSize(new Dimension(25,25));
			panel[i].add(new JButton());
			}

		for(JPanel p : panel){
			content.add(p);
		}
		content.setMinimumSize(new Dimension(100,100));
		frame.setContentPane(content);
		frame.pack();
		frame.setMinimumSize(new Dimension(200,200));
		frame.pack();
		
	*/
		
	}
	static ActionListener al = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Global action listener");
			text.setText("other button");
		}
		
	};
}
