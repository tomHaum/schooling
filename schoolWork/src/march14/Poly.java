package march14;

import java.util.Scanner;

public class Poly {
	public static void main(String[] args){
		Scanner tom = new Scanner(System.in);
		
//		System.out.println("Root 1");
//		double one = tom.nextDouble();
//		System.out.println("Root 2");
//		double two = tom.nextDouble();
//		System.out.println("Root 3");
//		double three = tom.nextDouble();
		
		System.out.println("input root in the form a + b ^(1/n)");
		String root1 = tom.next();
		String[] parts1;
		boolean positive1 = false;
		if(root1.contains("+")){
			parts1 = root1.split("+");
			positive1 = true;
		}else if(root1.contains("-")){
			parts1 = root1.split("-");
			positive1 = false;
		}
		
		
	}
	static void quadRegular(){
		Scanner in = new Scanner(System.in);
		System.out.println("Root 1");
		double one = in.nextDouble();
		System.out.println("Root 2");
		double two = in.nextDouble();
		double a = 1.0;
		double b = -1 * (one + two);
		double c = one * two;
		String part1 = (b > 0)? ("+ " + b ) : ("- " + Math.abs(b)) + "x ";
		if(b == 0)part1 = "";
		String part2 = (c > 0)? ("+ " + c) : ("- " + Math.abs(c));
		if(c == 0)part2 = "";
		System.out.println(a + "x^2 "+  part1 + part2);
	}
	static void cubicRegular(double one, double two, double three){
		double a = 1.0;
		double b = (one + two + three) * -1.0;
		double c = one*two + one*three + two*three;
		double d = one*two*three;
		
		String part1 = (b > 0)? ("+ " + b ) : ("- " + Math.abs(b)) + "x^2 ";
		if(b == 0)part1 = "";
		String part2 = (c > 0)? ("+ " + c) : ("- " + Math.abs(c)) + "x ";
		if(c == 0)part2 = "";
		String part3 = (d > 0)? ("+ " + d) : ("- " + Math.abs(d));
		if(d == 0)part3 = "";
		
		System.out.println(a + "x^3 "+  part1 + part2 + part3);
	}
}
