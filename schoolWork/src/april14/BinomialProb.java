package april14;

import java.math.BigInteger;

public class BinomialProb {
	public static void main(String[] args){
		System.out.println(binomialBetween(10,3,5,.5));
		
	}
	//finds the probabability of a certain number of successes
	static double binomialPDF(int n, int x, double p){
		return combination(n,x).doubleValue() * Math.pow(p,x) * Math.pow(1-p, n-x);		
	}
	static double binomialCDF(int n, int x, double p){
		double prob = 0;
		for(int i = 0; i<= x;i++){			
			prob = prob + binomialPDF(n,i,p);
		}
		return prob;
	}
	
	static double binomialCDFUp(int n, int x, double p){
		return 1- binomialCDF(n,x-1,p);
	}
	static double binomialBetween(int n, int a, int b, double p){
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
