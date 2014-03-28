package march14;

import java.util.Scanner;

public class Roots {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		double a = in.nextDouble();
		double b = in.nextDouble();
		double n = in.nextDouble();
		double r = Math.sqrt((a*a) + (b*b));
		
		for(int i = 0; i < n; i++){
			double one = Math.cos(a)
		}
		System.out.println("quadds");
		quadRADic();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void quadRADic() {

		boolean imaginary = false;
		Scanner tom = new Scanner(System.in);
		System.out.println("Input A: ");
		double a = tom.nextDouble();
		System.out.println("Input B: ");
		double b = tom.nextDouble();
		System.out.println("Input C: ");
		double c = tom.nextDouble();

		double disc = (b * b) - (4 * a * c);
		imaginary = disc < 0.0;
		System.out.println("Answer is imaginary: " + imaginary);
		System.out.println("The Discriminate is " + disc);
		if (imaginary) {
			System.out.println((-b / (2 * a)) + " " + "+ " + Math.sqrt(-disc)/(2*a)
					+ " i");
			System.out.println((-b / (2 * a)) + " " + "- " + Math.sqrt(-disc)/(2*a)
					+ " i");
		} else {
			System.out.println((-b + Math.sqrt(disc)) / (2 * a));
			System.out.println((-b - Math.sqrt(disc)) / (2 * a));
		}
	}
	
}
