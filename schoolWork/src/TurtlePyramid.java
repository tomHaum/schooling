import java.awt.Color;
import java.util.Scanner;

public class TurtlePyramid {
	public static void main(String[] args){
		//scanner does the same and the output methods
		Scanner input = new Scanner(System.in);
		Turtle.bgcolor(new Color(0,0,0));
		
		Turtle tom = new Turtle();
		tom.speed(.000005);
		int rowStartLength = 10;//this is what the length of the bottom row
		int rowLength = rowStartLength;//this is the length of the current row
		int rectW = 10;
		int rectH = 10;
		int rowStartX = 0;
		int rowStartY = 0;
				
		tom.width(10);
		
		System.out.println("input the rectangle width");
		try{
			rectW = input.nextInt();
		}catch(Exception ignored){
			rectW = 50;
		}
		
		System.out.println("input the rectangle hieght");
		try{
			rectH = input.nextInt();
		}catch(Exception ignored){
			rectH = 50;
		}
		
		System.out.println("input number of rects in starting row");
		try{
			rowStartLength = input.nextInt();
		}catch(Exception ignored){
			rowStartLength = 10;
		}
		
		rowLength = rowStartLength;
		//where the pyramid is actually drawn
		for(int i = 0; i<rowStartLength; i++){
			System.out.println(i+1);//prints the row number
			drawRow(rowLength, rectH, rectW, tom);
			tom.setPosition(rowStartX, rowStartY);//goes back to the start of row+
			rowStartX+=(rectW/2);
			rowStartY+=rectH;
			tom.setPosition(rowStartX, rowStartY);
			rowLength--;
			
		}
	}
	public static void drawRect(int h, int w, Turtle t){
		//makes a random pen color
		int a =(int)Math.ceil((Math.random()*254));
		int b =(int)Math.ceil((Math.random()*254));
		int c =(int)Math.ceil((Math.random()*254));
		System.out.println(a + " " + b + " " + c);
		t.penColor(new Color(a, b, c));
		//draws the box
		t.down();
		t.setDirection(0);
		t.forward(w);
		t.left(90);
		t.forward(h);
		t.left(90);
		t.forward(w);
		t.left(90);
		t.forward(h);
		t.left(90);
		t.forward(w);
		t.up();
		//makes the turtle a different color
		a =(int)Math.ceil((Math.random()*254));
		b =(int)Math.ceil((Math.random()*254));
		c =(int)Math.ceil((Math.random()*254));
		t.fillColor(new Color(a,b,c));
	}
	public static void drawRow(int l, int h, int w, Turtle t){
		int a =(int)Math.ceil((Math.random()*254));
		int b =(int)Math.ceil((Math.random()*254));
		int c =(int)Math.ceil((Math.random()*254));
		System.out.println(a + " " + b + " " + c);
		//t.penColor(new Color(a, b, c));
		System.out.println("Drawing Row of length "+ l);
		for(int i = 0; i<l;i++){
			drawRect(h,w,t);
		}		
	}
}