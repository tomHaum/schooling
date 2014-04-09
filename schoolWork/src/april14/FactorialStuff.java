package april14;

import java.math.BigInteger;
import java.util.Scanner;

public class FactorialStuff {
	public static void main(String[] args){
		Scanner tom = new Scanner(System.in);
		char flag = 'y';
		do{
			System.out.println("What do you want to do?");
			System.out.println("Show (P)ascal, Binomial Expainsion:(R)eal or (I)maginary, (Q)uit");
			String inputstuff = tom.next();
			if(!(inputstuff.length() > 0))
				continue;
			
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
			answer[i] = row[i].multiply(a.pow(c-i)).multiply(b.pow(i));
			//a % 4 ==0
			if(answer[i].mod(BigInteger.valueOf(4)).compareTo(BigInteger.ZERO) == 0){
				real = real.add(answer[i]);
			}else if(answer[i].mod(BigInteger.valueOf(3)).compareTo(BigInteger.ZERO) == 0){
				imaginary = imaginary.add(answer[i].negate());
			}else if(answer[i].mod(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO) == 0){
				real = real.add(answer[i].negate());
			}else{
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
		BigInteger[] row = pascalRow(c);
//		for(BigInteger x: row){
//			System.out.println(x);
//		}
		BigInteger[] answer = new BigInteger[row.length];
		for(int i = 0; i < answer.length; i++){
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
	static BigInteger[] pascalRow(int rowNumber){
		BigInteger[] theRow = new BigInteger[rowNumber + 1];
		for(int i = 0; i < rowNumber + 1; i++){
			theRow[i] = combination(rowNumber,i);
		}
		return theRow;
	}
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
	static BigInteger combination(int a, int b){
		BigInteger n = BigInteger.valueOf(a);
		BigInteger k = BigInteger.valueOf(b);
		BigInteger num = factorial(n);
		BigInteger denom = factorial(k).multiply(factorial(n.subtract(k)));
		return(num.divide(denom));
	}
	private static class Term{
		public int coeff = 1;
		public int pow = 1;
		
		public Term(){
			this(1,1);
		}
		public Term(int coeff){
			this(coeff,1);
		}
		public Term(int coeff, int pow){
			this.coeff = coeff;
			this.pow = pow;
		}
		public Term(Term t){
			this(t.getCoeff(), t.getPow());
		}
		public int getCoeff(){
			return this.coeff;
		}
		public void setCoeff(int coeff){
			this.coeff = coeff;
		}
		public int getPow(){
			return this.pow;
		}
		public void setPow(int pow){
			this.pow = pow;
		}
		public Term multiply(Term t){
			this.setCoeff(t.getCoeff() * this.getCoeff());
			this.setPow(t.getPow() + this.getPow());
			return this;
		}
		public boolean canAdd(Term t){
			return this.getPow() == t.getPow();
		}
		
		public Term add(Term t){
			if(this.canAdd(t)){
				this.setCoeff(this.getCoeff() + t.getCoeff());
			}
			return this;
		}
		public Term raiseToThe(int pow){
			this.setCoeff((int)Math.pow(this.getCoeff(), pow));
			return this;
		}
	}
}
