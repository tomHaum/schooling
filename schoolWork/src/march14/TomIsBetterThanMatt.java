package march14;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class TomIsBetterThanMatt extends JPanel {
	List<Rectangle2D> rects = new ArrayList<Rectangle2D>();
	boolean poly = false, rect = false;
	int numOfSides = 3;
	int numOfRect = 10;

	public static void main(String[] args){
		TomIsBetterThanMatt a = new TomIsBetterThanMatt();
		JFrame frame = new JFrame("window");//The main frame for the gui
		Scanner in = new Scanner(System.in);//gets user input

		frame.getContentPane().add(a);//adds this instance ofTomIsBetter to the frame
		frame.pack();//compacts the gui
		frame.setSize(500, 500);//overrides the packing to default of 500,500
		frame.setVisible(true);//makes it visible
		
		while (true) {//indefinete loop... for ever
			System.out.println("How do you want to calculate? (R)ectangles or (P)olygons?");
			String input = in.next();
			if (input != null) {//if the user input something
				char flag = input.toLowerCase().charAt(0);
				switch (flag) {
				case 'r'://user wants inscribed rectangles
					a.rect = true;
					a.poly = false;
					a.rects.clear();
					System.out.println("How many rectangles do you want to use?");
					int rects = in.nextInt();//gets how many rectangles
					if (rects > 0) {
						a.numOfRect = rects;//sets the gui to know how many rects
					}
					a.repaint();//forces repaint
					break;
				case 'p'://user wants polygons
					a.rect = false;
					a.poly = true;
					
					System.out.println("How many sides do you want to use?");
					int sides = in.nextInt();//gets the number of sides per polygon
					if (sides > 0) {
						a.numOfSides = sides;//-passes it to the gui
					}
					a.repaint();
					break;
				}
			}
		}
		
	}


	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.clearRect(-0, 0, 1000, 1000);//clears all previous drawings
		System.out.println(poly + " " + rect);
		if (poly) {
			//circumscribed
			g2.translate(200, 200);//shifts 0,0 to a more central area of the frame
			g2.drawOval(-100, -100, 200, 200);//draws the base circle

			int sides = numOfSides;//from the user input

			double sideLengthCirc = Math.tan(Math.PI / sides);//gets half the side length
			double areaCirc = sideLengthCirc * 1 * sides;//.5 b h * number of triangles
			//the half the base is half the side length, the height is always the radius
			
			//This draws the first side length, always 1 radius away
			g2.drawLine((int) (-sideLengthCirc * 100), 100, (int) (sideLengthCirc * 100), 100);
			
			
			for (int i = 0; i < sides - 1; i++) {
				//rotates the canvas around the center of the circle
				g2.rotate(2 * Math.PI / sides, 0, 0);
				//the line is drawn at the same spot, no need to move cuz rotate covers that
				g2.drawLine((int) (-sideLengthCirc * 100), 100,
						(int) (sideLengthCirc * 100), 100);
			}
			//inscribed circle
			double sideLengthInc = Math.sin(Math.PI / sides);//same as last time but this time sin works
			double height = Math.cos(Math.PI / sides);//the distance is no longer constant, it is depended on number of sides
			double areaInc = sideLengthInc * height * sides;//same formula for area, but dependent on height
			double area = (areaInc + areaCirc) * .5;//average area
			System.out.println(area);
			
			//same thing as circumscribed but with the variable height
			g2.drawLine((int) (-sideLengthInc * 100), (int) (height * 100),
					(int) (sideLengthInc * 100), (int) (100 * height));
			
			//same cool for loop
			for (int i = 0; i < sides - 1; i++) {
				g2.rotate(2 * Math.PI / sides, 0, 0);
				g2.drawLine((int) (-sideLengthInc * 100), (int) (height * 100),
						(int) (sideLengthInc * 100), (int) (100 * height));
			}
		}
		if (rect) {
			g2.translate(150, 100);

			double x1 = -1;
			double y1 = 0;
			double x2 = -1;
			double y2 = 0;
			double area = 0;

			int inteveral = numOfRect;
			Rectangle2D a1;

			double width = (double) (2.0 / inteveral);
			for (int i = 0; i < inteveral; i++) {
				//makes the first x equal to the last x
				x1 = x2;
				//forms a new last x dependent on how wide the rectangles are
				x2 += width;
				
				//calcs using circle formula
				y1 = (double) Math.pow(Math.abs(1 - (x1 * x1)), .5);
				
				y2 = (double) Math.pow(Math.abs(1 - (x2 * x2)), .5);
				//if y1 is outside the circle
				if (y1 > y2) {
					//uses y2
					area += y2 * width;//adds area of rect to cumulitive area
					
					//makes a Rectangle object that represents the rectagle(for drawing purposes)
					a1 = new Rectangle2D.Double(x1 * 100, 100 - (y2 * 100), width * 100,
							y2 * 100);

				} else {//y2 is outside the circle\
					//use y1
					area += y1 * width;
					a1 = new Rectangle2D.Double(x1 * 100, 100 - (y1 * 100), width * 100,
							y1 * 100);
				}

				rects.add(a1);

			}
			//variable to iterate over every rectangle
			Iterator<Rectangle2D> it = rects.iterator();

			while (it.hasNext()) {
				Rectangle2D a2 = it.next();
				
				g2.draw(a2);//draws it to screen
			}
			area *= 2;//some weird thing that works
			System.out.println("Final estimate: " + area);
		}
	}
}
