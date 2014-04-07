package april14;

public class FactorialStuff {
	public static void main(String[] args){
		pascal(15);
	}
	static void pascal(int rows){
		for(int i = 0; i < rows + 1; i++){
			System.out.print("\t");
			for(int x = 0; x < rows - i; x++){
				System.out.print("\t");
			}
			for(int j = 0; j < i + 1; j++){
				System.out.print(combination(i,j) + "\t\t");
			}
			System.out.println();
		}
	}
	static int factorial(int i){
		if(i <= 1){
			return 1; 
		}else{
			return i * (factorial(i -1));
		}
	}
	static int combination(int n, int k){
		int num = factorial(n);
		int denom = factorial(k)*factorial(n-k);
		return(num/denom);
	}
}
