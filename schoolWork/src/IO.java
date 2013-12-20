import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class IO {
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
