package april14;

import java.math.BigInteger;
import java.util.Scanner;
/**
 * 
 * @author Tom "Better Than Matt" Haumersen
 *
 */
public class TomIsActuallyBetterThanMatt {
	public static void main(String[] args){
		Scanner tom = new Scanner(System.in);
		char flag = 'y';
		do{
			System.out.println("What do you want to do?");
			System.out.println("Show (P)ascal, Binomial Expainsion:(R)eal or (I)maginary, (Q)uit");
			String inputstuff = tom.next();
			if(!(inputstuff.length() > 0))
				continue;//makes sure you typed something in
			
			flag = inputstuff.charAt(0);
			switch(flag){
			case 'p':
				System.out.println("how many rows");
				int rows = tom.nextInt();
				pascal(rows - 1);
				break;
			case 'r':
				System.out.println("(aX + c)^c");
				System.out.println("a: ");
				int a1 = tom.nextInt();
				System.out.println("b: ");
				int b1 = tom.nextInt();
				System.out.println("c: ");
				int c1 = tom.nextInt();
				binomialExpansionReal(a1, b1, c1);
				break;
			case'i':
				System.out.println("(ai + b)^c");
				System.out.println("a: ");
				int a2 = tom.nextInt();
				System.out.println("b: ");
				int b2 = tom.nextInt();
				System.out.println("c: ");
				int c2 = tom.nextInt();
				binomialExpansionImag(a2,b2,c2);
				break;
				
			}
		}while(flag != 'q');
		
		
		
	}
	static void binomialExpansionImag(int a2, int b2, int c2) {
		BigInteger a = BigInteger.valueOf(a2);
		BigInteger b = BigInteger.valueOf(b2);
		int c = c2;
		BigInteger[] row = pascalRow(c);
//		for(BigInteger x: row){
//			System.out.println(x);
//		}
		BigInteger[] answer = new BigInteger[row.length];
		BigInteger real = BigInteger.valueOf(0);
		BigInteger imaginary = BigInteger.valueOf(0);
		for(int i = 0; i < answer.length; i++){
			//answer[i] = (row[i] * (a^(c-i)))*(b^i)
			answer[i] = row[i].multiply(a.pow(c-i)).multiply(b.pow(i));
			
			//a % 4 ==0
			int remainder = (c-i) % 4;
			//gets the remainder after dividing the power of the current term by 4
			                
			if(remainder == 0){// i ^ 4
				real = real.add(answer[i]);
			}else if(remainder == 1){// i ^ 3
				imaginary = imaginary.add(answer[i].negate());
			}else if(remainder == 2){// i ^ 2
				real = real.add(answer[i].negate());
			}else{// i^ 1
				imaginary = imaginary.add(answer[i]);
			}
		}
		System.out.print(real);
		
		
		if(imaginary.compareTo(BigInteger.ZERO) == -1){
			System.out.print("-");
			imaginary = imaginary.negate();
		}else{
			System.out.print("-");
		}
		System.out.print(" " + imaginary + "i");
		
	}
	static void binomialExpansionReal(int one, int two, int three){
		BigInteger a = BigInteger.valueOf(one);
		BigInteger b = BigInteger.valueOf(two);
		int c = three;
		//gets the current row of the pascal triangle into a array
		BigInteger[] row = pascalRow(c);

		BigInteger[] answer = new BigInteger[row.length];
		for(int i = 0; i < answer.length; i++){
			// row[i] * (a^(c-i)) * b^i
			answer[i] = row[i].multiply(a.pow(c-i)).multiply(b.pow(i));
			if(c- i > 1){
				System.out.print(answer[i] + "x^" + (c-i) + " + ");
			}else if(c - i == 1){
				System.out.print(answer[i] + "x" + " + ");
			}else{
				System.out.println(answer[i]);
			}
		}
		
	}
	//gets one row of the pascal triangle
	static BigInteger[] pascalRow(int rowNumber){
		BigInteger[] theRow = new BigInteger[rowNumber + 1];
		
		for(int i = 0; i < rowNumber + 1; i++){
			theRow[i] = combination(rowNumber,i);
		}
		
		return theRow;
	}
	//prints out a pascal triangle of any length. NO UPPER LIMIT. just looks funny
	static void pascal(int rows){
		//goes to the last row and finds the largest number
		//for(int i = 0; i < )
		for(int i = 0; i < rows + 1; i++){
			System.out.print("\t");
			for(int x = 0; x < rows - i; x++){
				System.out.print("\t");
			}
			for(int j = 0; j < i + 1; j++){
				System.out.print(combination(i,j)
						+ "\t\t");
						
			}
			System.out.println();
		}
	}
	static BigInteger factorial(BigInteger i){
//		old recursive method
//		if(i <= 1){
//			return 1; 
//		}else{
//			return i * (factorial(i -1));
//		}
		//System.out.println("start");
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
