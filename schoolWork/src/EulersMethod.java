public class EulersMethod {
	// formulal
	// Xn+1 = Xn - (sinx)/(cosx);
	public static void main(String[] args) {
		double[] piGuess = new double[10];
		piGuess[0] = 3;
		double diff = 100;
		int index =1;
		while(diff > .0000001) {
			piGuess[index] = piGuess[index - 1]-
					(Math.sin(piGuess[index - 1]) / Math.cos(piGuess[index - 1]));
			diff = Math.abs(piGuess[index] - piGuess[index -1]);
			System.out.println(index);
			index++;
		}
		for(double d : piGuess){
			System.out.println(d);
		}
		
	}
}
