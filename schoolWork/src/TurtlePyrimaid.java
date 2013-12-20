import java.util.Scanner;


public class TurtlePyrimaid {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		
		Turtle tom = new Turtle();
		tom.speed(1);
		//drawRect(10,10, tom);
		//drawRow(5,10,10,tom);
		int rowStartLength = 10;
		int rowLength = rowStartLength;
		int rectW = 10;
		int rectH = 10;
		int rowStartX = 0;
		int rowStartY = 0;
		
		System.out.println("input the rectangle width");
		rectW = in.nextInt();
		System.out.println("input the rectangle hieght");
		rectH = in.nextInt();
		System.out.println("input number of rects in starting row");
		rowStartLength = in.nextInt();
		
		for(int i = 0; i<rowStartLength; i++){
			System.out.println(i+1);
			drawRow(rowLength, rectH, rectW, tom);
			tom.setPosition(rowStartX, rowStartY);
			rowStartX+=(rectW/2);
			rowStartY+=rectH;
			tom.setPosition(rowStartX, rowStartY);
			rowLength--;
			
		}
	}
	public static void drawRect(int h, int w, Turtle t){
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
		
	}
	public static void drawRow(int l, int h, int w, Turtle t){
		System.out.println("Drawing Row of length "+ l);
		for(int i = 0; i<l;i++){
			drawRect(h,w,t);
		}		
	}
	
	
			

}
