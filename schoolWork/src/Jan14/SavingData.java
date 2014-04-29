package Jan14;
import java.io.*;
/**
 * This program allows users to save up to 10 strings and 10 ints to file
 * Author: Tom Haumersen
 */

public class SavingData{

	public static void main(String[] args) {
		String[] stringData = new String[10];//changed the data type to string
		int[] intData = new int[10];
		
		int intcount = 0;
		char flag = 'y';

		do {
			intData[intcount] = inputInt("Enter a int to be stored");
			flag = inputChar("Enter 'y' if that was the last integer");
			intcount++;
		} while (flag != 'y' && intcount != intData.length);
		String fileNameInt;
		fileNameInt = input("Where is your file located and whats it called? ex P:/intArray");
		if(!fileNameInt.endsWith(".txt")){//if the file name does not end with ".txt"
			fileNameInt = fileNameInt + ".txt";//then add the ".txt"
		}
			
		save(intData, fileNameInt);
		
		int count = 0;
		do {
			stringData[count] = input("Enter a string to be stored");
			flag = inputChar("Enter 'y' if that was the last string");
			count++;
		} while (flag != 'y' && count != intData.length);
		String fileNameString;
		fileNameString = input("Where is your file located and whats it called? ex P:/folder/subfolder/filename");
		if(!fileNameString.endsWith(".txt")){
			fileNameString = fileNameString + ".txt";
		}
			
		save(stringData, fileNameString);
		
		System.out.println("What Data do you want to access? (N)either (I)nt (S)tring or (B)oth");
		String input = input("");
		int[] intData2;
		String[] stringData2;
		if(input.length() > 0){//if they entered something
			input = input.toLowerCase();//makes it lower case
			if(input.charAt(0) == 'i' || input.charAt(0) == 'b'){//if the user wanted int or both
				String name = input("The exact file name for int data");
				if(name.length() > 0){
					if(!name.endsWith(".txt")){
						name = name + ".txt";
					}
					intData2 = getIntArray(name);
					for(int i = 0; i < intcount; i++){
						System.out.println(intData2[i]);
					}
				}
			
			}
			if(input.charAt(0) == 's' || input.charAt(0) == 'b'){//if the user wanted string or both
				String name = input("The exact file name for String data");
				if(name.length() > 0){
					if(!name.endsWith(".txt")){
						name = name + ".txt";
					}
					stringData2 = getStringArray(name);
					for(String s: stringData2){
						if(s == null)continue;
						System.out.println(s);
					}
				}
			}
		}


	}


	static void save(Object data, String fileName) {//saves the same way from int to string
		try {
			FileOutputStream fos = new FileOutputStream(fileName);

			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(data);

			oos.close();
		} catch (Exception e) {
			System.out.println("didn't work");
		}

	}

	static String[] getStringArray(String name) {

		try {
			FileInputStream fis = new FileInputStream(name);//variable depending on user input
			
			BufferedInputStream bis = new BufferedInputStream(fis);

			DataInputStream dis = new DataInputStream(bis);

			ObjectInputStream ois = new ObjectInputStream(dis);

			String[] x = (String[]) ois.readObject();//casts to make sure it is saved as string array

			fis.close();

			return x;
		} catch (Exception e) {
			System.out.println("error");

			return null;
		}

	}

	static int[] getIntArray(String name) {

		try {
			FileInputStream fis = new FileInputStream(name);//varies with user input

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

