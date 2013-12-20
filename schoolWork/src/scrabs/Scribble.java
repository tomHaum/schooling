package scrabs;

import java.util.Scanner;

/**
 * @author tom haumersen
 */
public class Scribble {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char[] chars = new char[4];
		int start = 1;
		for (int i = 0; i < chars.length; i++) {// this gets the characters from the user
			System.out.print("input character " + (i + 1) + ": ");
			chars[i] = in.next().toLowerCase().charAt(0);// makes sure that the characters are all lowercase
		}
		char direction = ' ';
		for (int i = 0; i < 5; i++) {
			System.out.print("what is the number to start at? ");
			start = in.nextInt();
			System.out
					.print("what is the direction (H)orizontal or (V)ertical?");
			direction = in.next().toLowerCase().charAt(0);
			// the following chose whether or not to use the vertical or
			// horizontal, the vertical is the default
			// if the user's input was unexpected.
			int[] values = (direction == 'h') 
					? getHorizontal(start, 4)
					: getVerticle(start, 4);
			System.out.println(getValue(chars, values));
		}
		in.close();
	}

	static int[] getVerticle(int start, int length) {
		int[] nums = new int[length];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = start + 10 * i;
		}
		return nums;
	}

	static int[] getHorizontal(int start, int length) {
		int[] nums = new int[length];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = start + i;
		}
		return nums;
	}

	static int getValue(char[] chars, int[] nums) {
		int[] charValues = new int[chars.length];
		for (int i = 0; i < chars.length; i++) {// sets the value of each of the letters
			if (chars[i] == 'a' || chars[i] == 'e')
				charValues[i] = 1;
			if (chars[i] == 'd' || chars[i] == 'r')
				charValues[i] = 2;
			if (chars[i] == 'b' || chars[i] == 'm')
				charValues[i] = 3;
			if (chars[i] == 'v' || chars[i] == 'y')
				charValues[i] = 4;
			if (chars[i] == 'j' || chars[i] == 'x')
				charValues[i] = 8;
		}
		/* these are the positions that have special attributes,it is easier to
		 * hard code the values rather than deal with checking to see if a spot
		 * already has a special attribute;
		 */
		int[] doubleLetter = { 3, 9, 15, 21, 27, 33, 39 };
		int[] tripleLetter = { 5, 10, 20, 25, 30, 35, 40 };
		int[] doubleWord = { 7, 14, 28 };
		int[] tripleWord = { 8, 16, 24, 32 };
		for (int i = 0; i < nums.length; i++) {
			if (intInArray(tripleLetter, nums[i])) {// checks to see if the position has a triple letter
				charValues[i] *= 3;// triple the letters value;
				continue;// makes sure that a letter can not be both a triple and a double
			}
			if (intInArray(doubleLetter, nums[i])) {// checks for double letter
				charValues[i] *= 2;
				continue;
			}
		}
		int sum = 0;
		for (int i : charValues) {// iterates over each letters value
			sum += i;// adds the value to the sum;
		}
		int mult = 1;
		for (int i = 0; i < nums.length; i++) {// iterates over all the boardvalues of each letter
			if (intInArray(doubleWord, nums[i])) {// if the value is in the list of double word spots
				if (mult < 2) {// makes sure the highest possible multiplier is used
					mult = 2;
				}
			}
			if (intInArray(tripleWord, nums[i])) {// if the value is in the triple word spot
				if (mult < 3) {
					mult = 3;
				}
			}
		}
		return sum * mult;
	}

	static boolean intInArray(int[] ints, int i) {
		for (int j : ints) {// for all the integers in the array ints
			if (j == i)// if the int in the array equals i(i is the int being searched for)
				return true;
		}
		return false;// if a match was not found it returns false
	}
}