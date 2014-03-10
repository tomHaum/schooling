package march14;

import java.util.Scanner;

import javax.swing.JFrame;

public class main {
	public static void main(String[] args) {
		TomIsBetterThanMatt a = new TomIsBetterThanMatt();
		JFrame frame = new JFrame("window");
		Scanner in = new Scanner(System.in);

		frame.getContentPane().add(a);
		frame.pack();
		frame.setSize(500, 500);
		frame.setVisible(true);
		// a.init();
		while (true) {
			System.out.println("How do you want to calculate? (R)ectangles or (P)olygons?");
			String input = in.next();
			if (input != null) {
				char flag = input.toLowerCase().charAt(0);
				switch (flag) {
				case 'r':
					a.rect = true;
					a.poly = false;
					a.rects.clear();
					System.out.println("How many rectangles do you want to use?");
					int rects = in.nextInt();
					if (rects > 0) {
						a.numOfRect = rects;
					}
					a.repaint();
					break;
				case 'p':
					a.rect = false;
					a.poly = true;
					
					System.out.println("How many sides do you want to use?");
					int sides = in.nextInt();
					if (sides > 0) {
						a.numOfSides = sides;
						System.out.println("Set sides");
					}
					a.repaint();
					System.out.println("repainted");
					break;
				}
			}
		}
	}
}
