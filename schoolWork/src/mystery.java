import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * this finds the sum of the first 3999999 that are even
 * 
 * @author	Tommy The Hammer Haumersen
 *
 */
public class mystery {
	public static void main(String[] args) {
		long first = 1, second = 2, next = 0;
		long total = 2;
		char stop;
		do {
			next = first + second;
			if (next % 2 == 0) {
				total = total + next;
			}
			first = second;
			second = next;
		} while (next < 4000000);
		System.out.println("the sum is " + total);
	}

	static char inputChar(String prompt) {
		char result = (char) 0;
		try {
			result = input(prompt).charAt(0);
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}

	static String input(String prompt) {
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

}
