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
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Grapher {
	public double a;
	public double b;
	public double c;
	public double d;
	public Screen displayPanel = new Screen();
	JFrame frame = new JFrame("Graphing by Roots");
	public JLabel equation = new JLabel("null");

	public static void main(String[] args) {
		Grapher grapher = new Grapher();
		grapher.initialize();
	}

	public double stringToDouble(String s) {
		s = s.trim();
		String string1 = "";
		int end1 = 0;
		for (int i = 0; i < s.length(); i++) {
			end1 = i;
			if (Character.isDigit(s.charAt(i)) //character is a digit
					|| s.charAt(i) == '-'	//char is a minus
					|| s.charAt(i) == '.') {//char is a decimal point
			
				//adds the char to the number string
				string1 = string1 + "" + s.charAt(i);
				

				continue;
			} else {
				break;
			}
		}

		double num1;
		//converts the number string to a double
		num1 = Double.parseDouble(string1);
		
//		System.out.println("the end is at " + end1 + " which is "
//				+ s.charAt(end1));
		
		//repeats similar code to find the next decimal number
		
		int start2 = end1;
		String string2 = "";
		int end2 = 0;
		//gets rid of the number that was already found
		String str2 = s.substring(start2);
		//removes the white space
		str2 = str2.trim();
		//System.out.println("Next String: " + str2);
		//checks to make sure there is another term
		//if there is no other term then it returns the first number
		if (str2.length() < 1)
			return num1;
		if (!(str2.charAt(0) == '+' || str2.charAt(0) == '-'))
			return num1;
		
		//remembers whether to add or subtract
		boolean num2Neg = str2.charAt(0) != '-';
		
		//removes the addition/subtraction symbol
		str2 = str2.substring(1);
		//System.out.println("sub: " +str2);
		for (int i = 0; i < str2.length(); i++) {
			end2 = i;
			//System.out.println("Char at Iteration: " + str2.charAt(i));
			String curr = str2.substring(i, i+1);

			if(str2.charAt(i) == '('){continue;}//makes it ignore parenthese
			
			if (Character.isDigit(curr.charAt(0)) 
					|| curr.charAt(0) == ".".charAt(0)
					|| curr.charAt(0) == " ".charAt(0)) {
				
				string2 = string2 + "" + str2.charAt(i);
				continue;
				
			} else {
				end2 -= 1;
				//System.out.println("break");
				break;
			}
		}
		//System.out.println(str2.charAt(end2));
		str2 = str2.trim();
		double num2 = Double.parseDouble(string2);
	
		//System.out.println("num2: " + num2);
		//if there is not another term after the second
		if(!(str2.length() - end2 > 1)){
			return num1 + num2;
		}
		
		//the next term is the exponent
		//similair code to last two
		
		int start3 = end2;
		String string3 = "";
		while(!Character.isDigit(str2.charAt(start3)) 
				|| (str2.charAt(start3) == '-')){
			start3 ++;
		}
		String str3 = str2.substring(start3);
		boolean num3Neg = ('-' == str2.charAt(start3-1));
		//System.out.println("3neg: " + num3Neg);
		//System.out.println(str3);
		int end3 = 0;
		for (int i = 0; i < str3.length(); i++) {
			end3 = i;
			if(str3.charAt(i) == '('){continue;}
			if (Character.isDigit(str3.charAt(i)) 
					|| str3.charAt(i) == '.' 
					|| str3.charAt(i) == ' ') {
				
				string3 = string3 + "" + str3.charAt(i);

				continue;
			} else {
				break;
			}
		}
		//System.out.println(string3);
		double num3 = Double.parseDouble(string3);
		if(num3Neg){
			num3 *= -1.0;
		}
		//System.out.println("num3: " + num3);
		
		str3 = str3.trim();
		
		//if there is no denominator to the exponent
		if(!(str3.length() - end3 > 1)){
			//System.out.println("Breaking at 3");
			//weird code to make sure it doesnt take the sqrt of a negative number
			//while makeing sure it remebers wheter the radical is added or not
			return num1 + ((num2Neg?1.0:-1.0)* Math.pow(Math.abs(num2),num3));
		}
		
		
		
		int start4 = end3;
		String string4 = "";
		while(!Character.isDigit(str3.charAt(start4))
				|| str2.charAt(start4) == '-'){
			start4++;
		}
		String str4 = str3.substring(start4);
		boolean num4Neg = ('-' == str3.charAt(start4 - 1));
		int end4 = 0;
		
		for (int i = 0; i < str4.length(); i++) {
			end4 = i;
			if (Character.isDigit(str4.charAt(i)) 
					|| str4.charAt(i) == '.' 
					|| str4.charAt(i) == ' ') {
				
				string4 = string4 + "" + str4.charAt(i);

				continue;
			} else {
				break;
			}
		}
		
		//System.out.println(string4);
		double num4 = Double.parseDouble(string4);
		if(num4Neg){
			//chagnes it to a negative
			num4 *= -1.0;
		}
		//System.out.println("num4: " + num4);
		//does the math to convert radical to decimal, including fraction exponent
		return num1 + ((num2Neg?1.0:-1.0)* Math.pow(Math.abs(num2),(num3/num4)));
		
	}

	public void initialize() {

		JPanel content = new JPanel();
		JPanel inputPanel = new JPanel();

		displayPanel.setSize(400, 400);
		inputPanel.setSize(400, 400);

		JButton manual = new JButton("manual");
		manual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//only for manual entry
				//this is where it all started
				
				frame.invalidate();
				Scanner in = new Scanner(System.in);
				System.out.println("Root 1");
				double one = in.nextDouble();
				System.out.println("Root 2");
				double two = in.nextDouble();
				double a1 = 1.0;
				double b1 = -1 * (one + two);
				double c1 = one * two;
			
				
				//for displaying results properly
				//if b1 > 0 then use string "+ " + b1 else use "- " + abs b1
				String part1 = (b1 > 0) ? ("+ " + b1) : ("- " + Math.abs(b1))
						+ "x ";
				if (b1 == 0)
					part1 = "";
				String part2 = (c1 > 0) ? ("+ " + c1) : ("- " + Math.abs(c1));
				if (c1 == 0)
					part2 = "";
				System.out.println(a1 + "x^2 " + part1 + part2);
				setCubic(0, a1, b1, c1);

			}
		});

		JButton graphButton = new JButton("Graph");
		graphButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Graphing");
				displayPanel.setPoints(getPoints());
				frame.invalidate();

			}

		});

		inputPanel.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = 0;
		g.gridwidth = 4;
		inputPanel.add(equation, g);

		g.gridx = 0;
		g.gridy = 1;
		g.gridwidth = 1;
		inputPanel.add(new JLabel("Root 1"), g);

		g.gridx = 1;
		g.weightx = 2;
		g.fill = g.HORIZONTAL;
		final JTextField textA = new JTextField("1 + 2 ^(3)");
		textA.setSize(100, textA.getHeight());
		inputPanel.add(textA, g);

		g.fill = g.NONE;
		g.gridx = 0;
		g.gridy = 2;
		g.gridwidth = 1;
		inputPanel.add(new JLabel("Root 2"), g);
		g.gridx = 1;
		g.weightx = 2;
		g.fill = g.HORIZONTAL;
		final JTextField textB = new JTextField("3 + 4 ^(2)");
		textB.setSize(100, textB.getHeight());
		inputPanel.add(textB, g);

		g.fill = g.NONE;
		g.gridx = 0;
		g.gridy = 3;
		g.gridwidth = 1;
		inputPanel.add(new JLabel("Root 3"), g);
		g.gridx = 1;
		g.weightx = 2;
		g.fill = g.HORIZONTAL;
		final JTextField textC = new JTextField("A + (B ) ^ (C/D)");
		textC.setSize(100, textC.getHeight());
		inputPanel.add(textC, g);

		g.fill = g.NONE;
		g.weightx = 1;
		g.gridx = 0;
		g.gridy = 4;
		JButton quadButton = new JButton("Quad");
		quadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String aText = textA.getText();
				String bText = textB.getText();
				System.out.println("Quadradic Function");
				a = stringToDouble(aText);
				System.out.println("Root 1: " + a);
				b = stringToDouble(bText);
				System.out.println("Root 2: " + b);

				calcQuad(a, b);
			}
		});
		inputPanel.add(quadButton, g);

		g.gridx = 1;
		JButton cubicButton = new JButton("Cubic");
		cubicButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String aText = textA.getText();
				String bText = textB.getText();
				String cText = textC.getText();
				System.out.println("Cubic Function");
				a = stringToDouble(aText);
				System.out.println("Root 1: " + a);
				b = stringToDouble(bText);
				System.out.println("Root 2: " + b);
				c = stringToDouble(cText);
				System.out.println("Root 3: " + c);
				
				calcCubic(a, b, c);
			}
		});
		inputPanel.add(cubicButton, g);

		g.gridy = 5;
		g.gridx = 0;
		g.gridwidth = 2;
		inputPanel.add(manual, g);
		g.gridx = 2;
		inputPanel.add(graphButton, g);

		g.gridx = 0;
		g.gridy = 6;
		g.gridwidth = 1;
		inputPanel.add(new JLabel("X Scale"), g);
		JSlider scaleXSlider = new JSlider(JSlider.HORIZONTAL, -50, 50, 1);
		final JLabel scaleXLabel = new JLabel("1");
		scaleXSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				//scale of the x axis
				double scaleX = Math.pow(10,
						((JSlider) e.getSource()).getValue() / 10.0);
				//lets the grapher know about the change
				displayPanel.scaleX = scaleX;
				//forces redrawing of graph
				displayPanel.repaint();
				//changes the points because the x axis changed 
				//changing the x values need to fill horizontally
				displayPanel.setPoints(getPoints());
				scaleXLabel.setText(String.format("%.4f", scaleX));
			}
		});
		scaleXSlider.setMajorTickSpacing(10);
		scaleXSlider.setMinorTickSpacing(5);
		scaleXSlider.setPaintTicks(true);

		g.gridx = 1;
		inputPanel.add(scaleXSlider, g);

		g.gridwidth = 1;
		g.gridx = 3;
		inputPanel.add(scaleXLabel, g);

		JSlider scaleYSlider = new JSlider(JSlider.HORIZONTAL, -50, 50, 1);
		final JLabel scaleYLabel = new JLabel("1");

		scaleYSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				//the scale of the y axis
				double scaleY = Math.pow(10,
						((JSlider) e.getSource()).getValue() / 10.0);
				//divide by 10 to give more precision
				displayPanel.scaleY = scaleY;//lets the grapher know about the change
				//System.out.println(((JSlider) e.getSource()).getValue());
				//forces repaint
				displayPanel.repaint();
				//lets user know
				scaleYLabel.setText(String.format("%.4f", scaleY));
			}
		});

		scaleYSlider.setMajorTickSpacing(10);
		scaleYSlider.setMinorTickSpacing(5);
		scaleYSlider.setPaintTicks(true);

		g.gridy = 7;
		g.gridx = 0;
		inputPanel.add(new JLabel("Y Scale"), g);
		g.gridx = 1;
		g.gridwidth = 2;
		inputPanel.add(scaleYSlider, g);

		g.gridwidth = 1;
		g.gridx = 3;
		inputPanel.add(scaleYLabel, g);

		// inputPanel.invalidate()
		GridLayout gL = new GridLayout(1, 2);
		content.setLayout(gL);
		content.add(inputPanel);
		content.add(displayPanel);

		frame.setContentPane(content);
		frame.setVisible(true);
		frame.setSize(800, 400);
		// frame.setResizable(false);

		List<Point> points = new ArrayList<Point>();
		points.add(new Point(10, 10));
		points.add(new Point(-10, 10));
		displayPanel.setPoints(points);

	}

	void setCubic(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		//makes sure each is 4 decimals
		equation.setText(String.format("%.4f", a) + "x^3"
				+ String.format("%.4f", b) + "x^2 "
				+ String.format("%.4f", c) + "x"
				+ String.format("%.4f", d));
	}
	//calculates a list of points based on the cubic function
	//quadradic is the same as cubic but with 0 as first term ;)
	List<Point> getPoints() {
		List<Point> pts = new ArrayList<Point>();
		double[] param = displayPanel.getPointArgs();
		//System.out.println(param[0] + ", " + param[1] + ", " + param[2]);
		for (double i = param[0]; i < param[1]; i = i + param[2]) {
			
			double temp = a * Math.pow(i, 3);
			temp = temp + b * Math.pow(i, 2);
			temp = temp + c * ((double) i);
			temp = temp + d;
			
			pts.add(new Point(i, temp));
		}
		//System.out.println(pts.size());

		return pts;
	}

	public void calcQuad(double one, double two) {
		double a1 = 1.0;
		double b1 = -1 * (one + two);
		double c1 = one * two;
		String part1 = (b1 > 0) ? ("+ " + b1) : ("- " + Math.abs(b1)) + "x ";
		if (b1 == 0)
			part1 = "";
		String part2 = (c1 > 0) ? ("+ " + c1) : ("- " + Math.abs(c1));
		if (c1 == 0)
			part2 = "";
		System.out.println(a1 + "x^2 " + part1 + part2);
		setCubic(0, a1, b1, c1);

	}

	public void calcCubic(double one, double two, double three) {
		double a1 = 1.0;
		double b1 = (one + two + three) * -1.0;
		double c1 = one * two + one * three + two * three;
		double d1 = -one * two * three;

		String part1 = (b1 > 0) ? ("+ " + b1) : ("- " + Math.abs(b1)) + "x^2 ";
		if (b1 == 0)
			part1 = "";
		String part2 = (c1 > 0) ? ("+ " + c1) : ("- " + Math.abs(c1)) + "x ";
		if (c1 == 0)
			part2 = "";
		String part3 = (d1 > 0) ? ("+ " + d1) : ("- " + Math.abs(d1));
		if (d1 == 0)
			part3 = "";

		System.out.println(a1 + "x^3 " + part1 + part2 + part3);
		setCubic(a1, b1, c1, d1);
	}

}
