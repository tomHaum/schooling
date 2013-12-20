import java.util.ArrayList;
import java.util.List;

public class SortArray {
	public static void main(String[] args) {
		int[] nums = new int[16];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = genARandom(99, 0);
			if (nums[i] == 100) {
				nums[i] = 99;
			}
		}
		for (int x : nums) {
			System.out.println(x);
		}

		System.out.println();

		nums = BubbleSortInt(nums, false);
		for (int x : nums) {
			System.out.println(x);
		}
		int randomNumber = genARandom(99, 0);
		if (randomNumber == 100)
			randomNumber = 99;

		int checks = 0;
		boolean found = false;
		Main: for (int i = 0; i < nums.length; i++) {
			checks++;
			if (nums[i] == randomNumber) {
				//System.out.println("Found it");
				found = true;
				break Main;
			}
		}
		if (found) {
			System.out.print("Found the random number " + randomNumber
					+ " after " + checks);
		} else {
			System.out.println("Could not find " + randomNumber + ".");
		}
		System.out.println();

		int upperIndex = nums.length - 1;
		int lowerIndex = 0;
		boolean notThere = false;
		boolean foundIt = false;
		int middle = 0;
		
		//SEQUENTIAL SEARCH
		while (!notThere && !foundIt) {
			middle = (upperIndex + lowerIndex) / 2;
			if (nums[middle] < randomNumber) {
				lowerIndex = middle;
				//System.out.println("lower " + lowerIndex);

			} else if (nums[middle] > randomNumber) {
				upperIndex = middle;
				//System.out.println("upper " + upperIndex);

			} else {
				foundIt = true;
				//System.out.println("found it");

			}
			if(nums[middle] == randomNumber){
				//System.out.println("Found it");
			}
			if ((upperIndex == lowerIndex || Math.abs(upperIndex - lowerIndex)<=1) && nums[middle] != randomNumber) {
				notThere = true;
			}
			//System.out.println("checking");
			//System.out.println(lowerIndex + " " + upperIndex);
			
			
		}
		
		System.out.println((foundIt)? "Found at index " + middle:
				"Random Number was not found");
		}

	static int genARandom(int upper, int lower) {
		double ran = Math.random();
		ran = ran * (upper - lower + 1);
		ran = ran + lower;
		// ran = Math.floor(ran);
		return (int) ran;
	}

	static int[] BubbleSortInt(int[] data, boolean sortDown) {

		for (int top = data.length - 1; top > 0; top--) {
			for (int high = 1; high <= top; high++) {
				int low = high - 1;
				if ((data[high] < data[low] & !sortDown)
						|| (data[high] > data[low] && sortDown)) {
					int holder = data[high];
					data[high] = data[low];
					data[low] = holder;
				}
			}

		}
		return data;
	}

}
