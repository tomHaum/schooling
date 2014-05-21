package may14;

public class XYAxis{
	public static void main(String args[]) {
		Turtle t1 = new Turtle();
		drawAxis(t1, 300, 6);
			
	}

	public static void drawAxis(Turtle t, double axisLength, double tickLength) {
		t.up();
		double speed = t.getSpeed();
		t.speed(.01);
		for (int i = 0; i < 4; i++) {
			t.up();
			t.setPosition(0, 0);
			t.setDirection(90 * i);
			int temp = 0;
			t.down();
			while (temp + tickLength <= axisLength) {
				t.forward(tickLength);
				t.right(90);
				t.forward(5);
				t.backward(10);
				t.forward(5);
				t.left(90);
				temp += tickLength;

			}
			t.forward(axisLength - temp);

		}
		t.speed(speed);

	}
	
	public static void plotPoint(Turtle t, double x, double y){
		t.up();
		t.setPosition(x,y);
		t.dot();
	}
	
	public static void julia(Turtle t, double real, double imaginary){
		
	}
		
	}
}
