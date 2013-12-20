import java.awt.Color;
import java.util.Scanner;

public class LeArrays {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		/*
		// declares and assigns an array of doubles with 8 values double[]
		double[] temps = { 98.6, 99.7, 100.3, 101.5, 100.4, 99.9, 99.1, 98.6 }; // loops
		// through the array and outputs the values
		for (int x = 0; x < 8; x++) {
			System.out.println(temps[x]);
		}
		System.out.println(" ");

		// this line declares an array of integers, containing 6 test grades.
		int[] grades = new int[6];

		// this loops through the array, assigning values to each index
		for (int y = 0; y < 6; y++) {
			System.out.println("input a test grade");
			grades[y] = input.nextInt();
		}
		double sum = 0;
		for (int i = 0; i < grades.length; i++) {
			sum += grades[i];
		}
		double average = sum / grades.length;

		if (average >= 90.0) {
			System.out.print("you got an A");
		} else if (average >= 80.0) {
			System.out.print("you got a B");
		} else if (average >= 70.0) {
			System.out.print("you got a C");
		} else if (average >= 60.0) {
			System.out.print("you got a D");
		} else {
			System.out.print("you failed");
		}
		System.out.println(" " + average);
		*/
		// this loops through the array, outputting the value s. for(int y =0; y
		// < 6; y++){ System.out.println(grades[y]); }

		Turtle[] armada = new Turtle[100];
		for (int i = 0; i < 100; i++) {
			armada[i] = new Turtle();
			armada[i].down();
			armada[i].speed(.1);
			int a,b,c;
			a = (int)(Math.ceil(Math.random()*255));
			b = (int)(Math.ceil(Math.random()*255));
			c = (int)(Math.ceil(Math.random()*255));
			armada[i].penColor(new Color(a,b,c));
			
		}
		for (int i = 0; i < 100; i++) {
			armada[i].setPosition(0, i * 10);
		}
		for (int j = 0; j < 360; j++) {
			for (Turtle t : armada) {
				t.forward(10);
				t.right(1);
			}
		}

	}

	void output(String s) {
		System.out.println(s);
	}

}
