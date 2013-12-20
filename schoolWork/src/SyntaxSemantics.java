import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SyntaxSemantics {
	public static void main(String[] args) {
		char choice1 = 'h';
		int number = 0;
		double battingAverage = .000;
		choice1 = inputChar("Enter a letter h to print hello world");
		if (choice1 == 'h') {
			output("Hello World");
		} else {
			String SomeString = "Whatever";
			SomeString = input("Then what do you want to print");
			output(SomeString);
		}
		number = inputInt("Pick an integer from 1 to 10");
		if (number <= 0 || number > 10) {
			output("Can't you follow a simple instruction");
		} else {
			output("Wow! You can follow instructions");
		}
		battingAverage = inputDouble("Enter a valid batting average");
		if (battingAverage >= 0.000 && battingAverage <= 1.000) {
			output("That would be okay.");
		} else {
			output("That's impossible");
		}
	}

	static void output(String info) {
		System.out.println(info);
	}

	static void output(char info) {
		System.out.println(info);
	}

	static void output(int info) {
		System.out.println(info);
	}

	static void output(double info) {
		System.out.println(info);
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

	static double inputDouble(String prompt) {
		double result = 0;
		try {
			result = Double.parseDouble(input(prompt));
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}

	static int inputInt(String prompt) {
		int result = 0;
		try {
			result = Integer.parseInt(input(prompt));
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}
}