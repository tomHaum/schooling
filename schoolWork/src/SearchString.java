import java.util.Scanner;

public class SearchString {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] names = { "tom", "Matt", "Ron", "Kyle", "Chris", "Ryan",
				"Douge", "Gooley", "Eric", "John" };
		int[] scores = { 40, 39, 32, 98, 10, 55, 76, 88, 99, 23 };
		int big = 0;
		int index = -1;
		for (int i = -0; i < scores.length; i++) {
			if (scores[i] > big) {
				big = scores[i];
				index = i;
			}
		}
		System.out.println(names[index] + " scored the highest score of "
				+ scores[index]);
		System.out.println("Who do you want to find?");
		String findName = in.next();
		int indexName = searchStringArray(names, findName);
		if (indexName == -1) {
			System.out.println(findName + " is not a student.");
		} else {
			System.out.println(findName + " got a score of "
					+ scores[indexName]);
		}
	}

	static int searchStringArray(String[] data, String query) {
		int index = -1;
		for (int x = 0; x < data.length; x++) {
			if (data[x].equalsIgnoreCase(query)) {
				index = x;
			}
		}
		return index;
	}
}