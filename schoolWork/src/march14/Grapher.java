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

	public void initialize() {

		JPanel content = new JPanel();
		JPanel inputPanel = new JPanel();

		displayPanel.setSize(400, 400);
		inputPanel.setSize(400, 400);

		JButton manual = new JButton("manual");
		manual.addActionListener(new ActionListener() {
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
				System.out.println("Graphing");
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
		final JTextField textA = new JTextField("root1");
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
		final JTextField textB = new JTextField("root2");
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
		final JTextField textC = new JTextField("root3");
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
				double root1 = Double.parseDouble(aText);
				double root2 = Double.parseDouble(bText);
				calcQuad(root1, root2);
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
				double root1 = Double.parseDouble(aText);
				double root2 = Double.parseDouble(bText);
				double root3 = Double.parseDouble(cText);
				calcCubic(root1, root2, root3);
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
				double scaleX = Math.pow(10,
						((JSlider) e.getSource()).getValue() / 10.0);
				displayPanel.scaleX = scaleX;
				displayPanel.repaint();
				displayPanel.setPoints(getPoints());
				scaleXLabel.setText(String.format("%.4f",scaleX));
			}

		});
		scaleXSlider.setMajorTickSpacing(10);
		scaleXSlider.setMinorTickSpacing(5);
		scaleXSlider.setPaintTicks(true);

		g.gridx = 1;
		inputPanel.add(scaleXSlider, g);
		
		g.gridwidth = 1;
		g.gridx = 3;
		inputPanel.add(scaleXLabel,g);

		
		JSlider scaleYSlider = new JSlider(JSlider.HORIZONTAL, -50, 50, 1);
		final JLabel scaleYLabel = new JLabel("1");
		
		scaleYSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				
				double scaleY = Math.pow(10,
						((JSlider) e.getSource()).getValue() / 10.0);
				displayPanel.scaleY = scaleY;
				System.out.println(((JSlider)e.getSource()).getValue());
				displayPanel.repaint();
				scaleYLabel.setText(String.format("%.4f",scaleY));
			}
		});

		scaleYSlider.setMajorTickSpacing(10);
		scaleYSlider.setMinorTickSpacing(5);
		scaleYSlider.setPaintTicks(true);

		g.gridy = 7;
		g.gridx = 0;
		inputPanel.add(new JLabel("Y Scale"), g);
		g.gridx = 1;g.gridwidth = 2;
		inputPanel.add(scaleYSlider, g);

		
		g.gridwidth = 1;
		g.gridx = 3;
		inputPanel.add(scaleYLabel,g);
		
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
		equation.setText(a + "x^3" + b + "x^2 " + c + "x" + d);
	}

	List<Point> getPoints() {
		List<Point> pts = new ArrayList<Point>();
		double[] param = displayPanel.getPointArgs();
		System.out.println(param[0] + ", " + param[1] + ", " + param[2]);
		for (double i = param[0]; i < param[1]; i = i + param[2]) {
			//System.out.println(i);
			double temp = a * Math.pow(i, 3);
			temp = temp + b * Math.pow(i, 2);
			temp = temp + c * ((double) i);
			temp = temp + d;
			//System.out.println(temp);
			pts.add(new Point(i, temp));
		}
		System.out.println(pts.size());

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
