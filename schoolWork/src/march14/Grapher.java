package march14;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Grapher {
	public double a;
	public double b;
	public double c;
	public double d;
	public Screen displayPanel = new Screen(); 
	JFrame frame = new JFrame("Graphing by Roots");
	public JLabel equation = new JLabel("null");
	public static void main(String[] args){
		Grapher grapher = new Grapher();
		grapher.initialize();
	}
	public void initialize(){
		
		JPanel content = new JPanel();
		JPanel inputPanel = new JPanel();
		
		displayPanel.setSize(400,400);
		inputPanel.setSize(400,400);
		
		JButton manual = new JButton("manual");
		manual.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.invalidate();
				Scanner in = new Scanner(System.in);
				System.out.println("Root 1");
				double one = in.nextDouble();
				System.out.println("Root 2");
				double two = in.nextDouble();
				double a1 = 1.0;
				double b1 = -1 * (one + two);
				double c1 = one * two;
				String part1 = (b1 > 0)? ("+ " + b1 ) : ("- " + Math.abs(b1)) + "x ";
				if(b1 == 0)part1 = "";
				String part2 = (c1 > 0)? ("+ " + c1) : ("- " + Math.abs(c1));
				if(c1 == 0)part2 = "";
				System.out.println(a1 + "x^2 "+  part1 + part2);
				setCubic(0,a1,b1,c1);
				
				
			}
		});
		
		JButton graphButton = new JButton("Graph");
		graphButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Graphing");
				displayPanel.setPoints(getPoints());
				frame.invalidate();
				
			}
			
		});
		
		inputPanel.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		g.gridx = 0; g.gridy = 0;
		g.gridwidth = 4;
		inputPanel.add(equation,g);
		g.gridx = 0;g.gridy = 1;
		g.gridwidth = 1;
		inputPanel.add(new JLabel("Input A"),g);
		g.gridx = 1;
		g.weightx = 2;
		final JTextField textA = new JTextField("examoly a");
		textA.setSize(100, textA.getHeight());
		textA.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setCubic(Double.parseDouble(textA.getText()),
						b,c,d);
			}			
		});
		inputPanel.add(textA,g);
		g.gridy = 5;
		g.gridx = 0;g.gridwidth = 2;
		inputPanel.add(manual,g);
		g.gridx = 2;
		inputPanel.add(graphButton,g);
		inputPanel.invalidate()
		GridLayout gL = new GridLayout(1,2);
		content.setLayout(gL);
		
		
		content.add(inputPanel);
		content.add(displayPanel);
		
		frame.setContentPane(content);
		frame.setVisible(true);
		frame.setSize(800,400);
		//frame.setResizable(false);
		
		List<Point> points = new ArrayList<Point>();
		points.add(new Point(10,10));
		points.add(new Point(-10,10));
		displayPanel.setPoints(points);
		
	}
	void setCubic(double a, double b, double c, double d){
		this.a = a;this.b=b;this.c=c;this.d=d;
		equation.setText( a + "x^3" + b + "x^2 " + c + "x" + d);
	}
	List<Point> getPoints(){
		List<Point> pts = new ArrayList<Point>();
		for(double i = -10; i < 11; i = i + .5){
			System.out.println(i);
			double temp = a*Math.pow(i,3);
			temp = temp + b*Math.pow(i, 2);
			temp = temp + c*((double)i);
			temp = temp + d;
			System.out.println(temp);
			pts.add(new Point(i,temp));
		}
		
		return pts;
	}
	
}






















