package Jan14;

import java.io.*;
/**
 * This program allows users to save up to 10 strings to file.
 * Author: tom haumersen
 */

public class SavingData1{

	public static void main(String[] args) {
		String[] x = new String[10];//changed the data type to string
		int count = 0;
		char flag = 'y';

		do {
			x[count] = input("Enter a String to be stored");
			flag = inputChar("Enter 'y' if that was the last integer");
			count++;
		} while (flag != 'y' && count != x.length);

		saveString(x);

		String[] y = getStringArray();

		for (int i = 0; i < count; i++) {//changed x.length with count to stop at the highest one
			System.out.println(y[i]);
		}

	}

	static void saveString(Object data) {
		try {
			FileOutputStream fos = new FileOutputStream("P:/stringarray.txt");//changed file location so it does not conflict with intarray

			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(data);

			oos.close();
		} catch (Exception e) {
			System.out.println("didn't work");
		}

	}

	static void saveInt(Object data) {
		try {
			FileOutputStream fos = new FileOutputStream("P:/intarray.txt");

			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(data);

			oos.close();
		} catch (Exception e) {
			System.out.println("didn't work");
		}

	}

	static String[] getStringArray() {

		try {
			FileInputStream fis = new FileInputStream("P:/stringarray.txt");//changed to match other function

			BufferedInputStream bis = new BufferedInputStream(fis);

			DataInputStream dis = new DataInputStream(bis);

			ObjectInputStream ois = new ObjectInputStream(dis);

			String[] x = (String[]) ois.readObject();

			fis.close();

			return x;
		} catch (Exception e) {
			System.out.println("error");

			return null;
		}

	}

	static int[] getIntArray() {

		try {
			FileInputStream fis = new FileInputStream("P:/intarray.txt");

			BufferedInputStream bis = new BufferedInputStream(fis);

			DataInputStream dis = new DataInputStream(bis);

			ObjectInputStream ois = new ObjectInputStream(dis);

			int[] x = (int[]) ois.readObject();

			fis.close();

			return x;
		} catch (Exception e) {
			System.out.println("error");

			return null;
		}

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
