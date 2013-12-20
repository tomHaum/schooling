import java.io.*;

public class FunctionExample2 {

	public static void main(String[] args) {
		int number = 0; // initializes a number
		char prime = 't', check = 'y'; // initializes characters

		do {
			number = inputInt("What number do you want to do the prime factorization of?");
			prime = checkPrime(number); // Function call to check if prime

			if (prime == 't') {
				output("The number is prime.");
			} else {
				output("not prime");
				factorize(number); // Function call to find factors
			}

			check = inputChar("continue (y/n)");
		} while (check != 'n');
	}

	static char checkPrime(int num) {
		char check = 't';
		int factor = 2;
		int remainder = 1;

		do {
			remainder = num % factor; // checks for factors
			if (remainder == 0) {
				return 'f'; // Factor was found, returns false
			} else {
				factor++;
			}
		} while (factor < num);
		return 't';

	}

	static void factorize(int num) {
		int remainder = 1;
		int factor = 2;
		char flag = 't';

		do {
			remainder = num % factor;
			if (remainder == 0) {
				System.out.print(factor + " * ");
				num = num / factor; // Divide out prime factor
				flag = checkPrime(num); // checks if new value is prime

				if (flag == 't') {
					output(num);
					flag = 'f';
				} else {
					factorize(num); // Calls factor function if num is not prime
				}

			} else {
				factor++;
			}
		} while (flag == 't');

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