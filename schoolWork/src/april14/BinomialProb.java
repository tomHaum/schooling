package april14;

import java.math.BigInteger;
import java.util.Scanner;
/**
 * Uses previously discover methods, we calculate binomial probabilities
 * @author Tom Haumersen
 *
 */
public class BinomialProb {
	public static void main(String[] args){
		System.out.println(binomialBetween(10,3,5,.5));
		Scanner in = new Scanner(System.in);
		//equivalent to n
		System.out.println("Number of trials");
		int trials = in.nextInt();
		//equivalent to p
		System.out.println("Probabily of success");
		double probSucc = in.nextDouble();
		//equivalent to x
		System.out.println("Number of Successes?");
		int a = in.nextInt();
		
		System.out.println("Which probability do you want?");
		System.out.println("1. P(X = a)");
		System.out.println("2. P(X <= a)");
		System.out.println("3. P(X => a)");
		System.out.println("4. P(a <= X <= b)");
		System.out.println("5. Quit");
		String input = in.next();
		char flag = '0';
		//makes sure that you typed something in
		if(input.length() > 0){
			flag = input.charAt(0);
		}
		double prob = 0;
		//switches through the different options that the users can chose
		switch(flag){
		case '1':
			prob = binomialPDF(trials,a,probSucc);
			System.out.println("P(X = " + a + ") = " +prob);
			break;
		case '2':
			prob = binomialCDF(trials, a, probSucc);
			System.out.println("P(X <= " + a + ") = " +prob);
			break;
		case '3':
			prob = binomialCDFUp(trials, a, probSucc);
			System.out.println("P(X => " + a + ") = " +prob);
			break;
		case '4':
			System.out.println("What is the upper bound");
			int b = in.nextInt();
			prob = binomialBetween(trials, a, b, probSucc);
			System.out.println("P(" + a + " <= x <= " + b +  ") = " +prob);
			break;
		default:
			System.out.println("Quiting");
			break;
		}
		in.close();
	}
	//finds the prob of a certain number of successes
	static double binomialPDF(int n, int x, double p){
		// p = n C x * p ^ x * q ^ n-x
		return combination(n,x).doubleValue() * Math.pow(p,x) * Math.pow(1-p, n-x);		
	}
	static double binomialCDF(int n, int x, double p){
		double prob = 0;
		//loops through all the numbers below x, including x
		//adding their prob to the running total
		for(int i = 0; i<= x;i++){			
			prob = prob + binomialPDF(n,i,p);
		}
		return prob;
	}
	
	static double binomialCDFUp(int n, int x, double p){
		//finds the upper prob using the lower prob. its basic math
		return 1- binomialCDF(n,x-1,p);
	}
	static double binomialBetween(int n, int a, int b, double p){
		//super meta
		//user both lower and upper binomial calculations
		return 1 - binomialCDF(n,a-1,p) - binomialCDFUp(n,b + 1, p);
	}
	//the factorial method that does stuff
	static BigInteger factorial(BigInteger i){
		BigInteger total = BigInteger.ONE;
		for(BigInteger j = i.add(BigInteger.ZERO); j.compareTo(BigInteger.ONE) == 1; j = j.subtract(BigInteger.ONE)){
			total = total.multiply(j);
		}
		return total;
	}
	
	//Uses the factorial method to get combination of big numbers
	static BigInteger combination(int a, int b){
		
		BigInteger n = BigInteger.valueOf(a);
		BigInteger k = BigInteger.valueOf(b);
		BigInteger num = factorial(n);
		//	k!(n-k)!
		BigInteger denom = factorial(k).multiply(factorial(n.subtract(k)));
		return(num.divide(denom));
	}
}
