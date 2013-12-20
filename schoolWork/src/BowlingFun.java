import java.util.Scanner;

/*
 * This program will generate a array with bowling scores
 * then find the total for each row
 * then it will find the highest game
 */
public class BowlingFun {
	static double[][] bowlingScores = new double[12][4];

	public static void main(String[] args){
		for(int i = 0; i< bowlingScores.length; i++){
			for(int j = 0; j<bowlingScores[i].length; j++){
				bowlingScores[i][j] = j+i;
				
			}
		}
		
		printArray(bowlingScores);
		System.out.println();
		Scanner input = new Scanner(System.in);
		//inputs shit
		
		
		
		for(int i = 0; i< bowlingScores.length -1 ; i++){
			for(int j = 0; j<bowlingScores[i].length - 1; j++){
				System.out.println("Score for Game " + (j+1) + " week " + (i+1));
				bowlingScores[i][j] = input.nextInt();
			}
		}
		
		
		//gets the total
		for(int i = 0; i< bowlingScores.length -1; i++){
			int total = 0;
			for(int j = 0; j<bowlingScores[i].length-1; j++){
				total += bowlingScores[i][j];
			}
			bowlingScores[i][bowlingScores[i].length-1] = total;
		}
		printArray(bowlingScores);
		
		//finds the average
		for(int i = 0; i<bowlingScores[0].length - 1; i++){
			int gameTotal =0;//resets each time the outer loop runs
			for(int j = 0; j<bowlingScores.length -1;j++ ){
				gameTotal += bowlingScores[j][i];
			}
			System.out.println(gameTotal);
			bowlingScores[bowlingScores.length-1][i] = ((double)gameTotal)/(bowlingScores.length-1);
		}
		printArray(bowlingScores);
		double max = 0;
		double min = Double.MAX_VALUE;
		for(int i = 0; i < bowlingScores.length -1; i++){
			for(int j = 0; j<bowlingScores[i].length - 1 ; j++){
				double curr = bowlingScores[i][j];
				if(max < curr){
					max = curr;
				}
				if(min> curr){
					min = curr;
				}
			}
		}
		System.out.println("the max is " + max + " and the min is " + min);
		double weekMax = 0;
		double weekMin = Double.MAX_VALUE;
		for(int i = 0; i<bowlingScores.length -1; i++){
			double curr = bowlingScores[i][bowlingScores[0].length-1];
			if(weekMax < curr){
				weekMax = curr;
			}
			if(weekMin> curr){
				weekMin = curr;
			}
		}
		System.out.println("the best week had a score of " +
				weekMax + " and the lowest was " +weekMin);
		System.out.println("what number would you like to try to find?");
		double d = input.nextDouble();
		for(int i = 0; i<bowlingScores.length-1; i++){//the minus 1 is so it doesnt go into the averages
			for(int j = 0; j<bowlingScores[i].length-1;  j++){//minus one so i doesnt hit hte week totals
				if(d == bowlingScores[i][j]){
					//System.out.println("foun it");
					System.out.println("At week " +(1+i) + " Game " + (1+j));
					System.out.println("we found " + d + ".");
				
				}
			}
		}
	}

	public static void printArray(double[][] array) {
		for(int i = 0; i<array.length +1; i++){
			
			System.out.println();
			for(int j = 0; j<array[0].length+1; j++){
				if(i == 12 && j ==0){
					System.out.print("avg" + "\t");
					continue;
				}
				if(i==0 && j!=0 && j<array[0].length+1){
					if(j==array[0].length){
						System.out.print("total");
						continue;
					}
					System.out.print("g"+(j) + "\t");
					continue;
				}
				if(j==0 && i == 0){
					System.out.print("\t");
					continue;
				}
				if(j ==0){
					System.out.print("w " + i + "\t");
				}
				
				if(j>0 && i >0 && i<array.length+1 && j<array[0].length+1)
					System.out.print(array[i-1][j-1] + "\t");
			}	
		}
		System.out.println();
	}

}
