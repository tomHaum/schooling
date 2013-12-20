import java.util.Scanner;

public class StringSort {
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println(5/2 + 1);
		String[] data = new String[5];

		for (int x = 0; x < data.length; x++) {
			System.out.println("Enter a name");
			data[x] = in.next();
		}

		String[] sortedData = stringSort(data);

		for (int x = 0; x < data.length; x++) {
			System.out.println(sortedData[x]);
		}

	}

	static String[] stringSort(String[] data) {

		for (int top = data.length - 1; top > 0; top--) {
			for (int high = 1; high <= top; high++) {
				int low = high - 1;
				if (data[low].compareTo(data[high]) > 0) {
					String temp = data[low];

					data[low] = data[high];

					data[high] = temp;

				}
			}
		}
		return data;
	}
}
