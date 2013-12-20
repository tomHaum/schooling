import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class SortArrays {
	public static void main(String[] args) {
		//this line declares an array of integers, containing 6 test grades.
		int[] grades = new int[6];
		double[] temps = new double[5];
		
		//this loops through the array, assinging values to each index
		for(int y = 0; y < 6; y++){
			grades[y] = inputInt("input a grade");
		}
		
		//this loops through the array, outputting the values.
		for(int y = 0; y < 6; y++){
			output(grades[y]);
		}
		// This calls for a bubble sort //
		grades = BubbleSortInt(grades, true);

		output("The sorted array:");
		
		for(int y = 0; y < 6; y++){
			output(grades[y]);
		}		
		
		//this loops through the array, assigning values to each index
		for(int x = 0; x < 5; x++){
			temps[x] = inputDouble("input a temp");
		}
		
		//this loops through the array, outputting the values.
		for(int x = 0; x < 5; x++){
			output(temps[x]);
		}
		// This calls for a bubble sort //
		temps = SelectionSortDouble(temps);

		output("The sorted array:");
		for(int x = 0; x < 5; x++){
			output(temps[x]);
		}
		
	}// Thus ends the main program
		
	static int[] BubbleSortInt (int[] data, boolean sortDown){

		for  (int top = data.length-1; top>0; top--)
		{
			for (int high=1; high <= top; high ++)
			{
				int low= high-1;
				if ((data[high] < data[low] & !sortDown) ||
						(data[high] > data[low] && sortDown))
				{
					int holder= data[high];
					data[high]= data[low];
					data[low] = holder;
				}
			}
			
		}
		return data;
	}
	
	static double[] SelectionSortDouble(double[] data){
		for (int position = 0; position < data.length; position++){
			for(int item = position + 1; item < data.length; item++){
				if (data[item] > data[position]){//change sign to change from low to high
					double temp = data[position];
					data[position] = data[item];
					data[item] = temp;
				}
			}
		}
		
		return data;
	}
        
	public static void output(String info) {
		System.out.println(info);
	}

	public static void output(char info) {
		System.out.println(info);
	}

	public static void output(int info) {
		System.out.println(info);
	}

	public static void output(double info) {
		System.out.println(info);
	}

	public static char inputChar(String prompt) {
		char result = (char) 0;
		try {
			result = input(prompt).charAt(0);
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}

	public static String input(String prompt) {
		String iput = null;
		System.out.print(prompt + ": ");
		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(
					System.in));
			iput = is.readLine();
			if (iput.length() == 0)
				return null;
		} catch (IOException e) {
			System.out.println("IO Exception: " + e);
		}
		return iput;
	}

	public static double inputDouble(String prompt) {
		double result = 0;
		try {
			result = Double.parseDouble(input(prompt));
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}

	public static int inputInt(String prompt) {
		int result = 0;
		try {
			result = Integer.parseInt(input(prompt));
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}
}
