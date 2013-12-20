import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/*
 * This program is coupled with a list of prime numbers
 * if there is no list it will generate a text document with
 * all the prime numbers needed.
 * If there is a problem with the number of primes, then the 
 * program knows how to generate more primes
 */

public class Primes {
	//nums is the giant list of prime numbers
	//this is filled with the primes from the text document
	public static List<Long> nums = new ArrayList<Long>();
	public static Long startTime;

	public static void main(String[] args) throws Exception {
		
		//Start time is the current time in Mills
		startTime = System.currentTimeMillis();
		
		
		File primesFile = getResource("primesList.txt");

		// reads the primes list
		nums = loadPrimes(nums, primesFile);
				

		// if list is blank add 2 as first prime
		try {
			nums.get(0);
		} catch (Exception ignored) {
			nums.add(2L);
		}
		//generate a prime for fun
		genAPrime();
		

		//this char controls while loops
		char flag = 'y';
		
		//the scanner object gets user input into the program
		//much easier to use than inputInt() and whatnot
		Scanner input = new Scanner(System.in);
		//this is where the user's data is stored
		double guess = 0.0;
		//the list of factors of the given number
		List<Long> factoringList;
		while(flag != 'n'){
			System.out.println("Give a number you would like to factor");
			guess = input.nextLong();
			System.out.println("ty");
			factoringList = factor(guess);
			printList(factoringList);
			
			//printList(factor(guess));
			System.out.println("\nfactored");
			System.out.println("Would you like to factor again?(y/n)");
			//this is where the loop is determined to go again
			flag = input.next().charAt(0);
		}
		flag = 'y';
		while(flag != 'n'){
			System.out.println("How many more prime numbers do you want to create");
			int x;
			try{
				x = input.nextInt();
			}catch(Exception ignored){
				//if the user inputted a non-number
				x = 1;
			}
			//the for loop does the number of iterations that users wants
			for(int i = 0; i < x; i++){
				genAPrime();
			}
			System.out.println("Would you like make more primes?(y/n)");
			flag = input.next().charAt(0);
			
		}
		
		long aNumber;
		long total;
		System.out.println("Input a number you would like to find the sum of the lower primes of.");
		try{
			aNumber = input.nextLong();
		}catch(Exception ignored){
			aNumber = 0;
		}
		total = getSumOfLowerPrimes(aNumber);
		System.out.println("sum of primes below " + aNumber + " is " + total);
		
		
		// saves the list in a txt document
		
		
		if (!primesFile.exists()) {//this part checks for if the file already exists
			System.out.println("Trying to create new file");
			try {
				primesFile.createNewFile();
				System.out.println("File successfully created.");

			} catch (IOException e) {
				System.out.println("File not created");
				e.printStackTrace();
			}
		}
		
		//this part writes the list of primes to the file
		try {
			//Buffered writer writes data 
			BufferedWriter output = new BufferedWriter(new FileWriter(
					primesFile));
			//it is an object that iterates over the prime number list
			Iterator<Long> it = nums.iterator();
			//while there is a next number in the iterator, keep going through the loop
			while (it.hasNext()) {
				//writes to the file
				output.write(it.next().toString());
				output.newLine();
			}
			//closing the 
			output.close();
		} catch (Exception e) {
			//if anything goes wrong print the source
			e.printStackTrace();
		}
	}
	//this is dependent on genPrimes which creates prime upto a limit
	public static long getSumOfLowerPrimes(long upperLimit){
		//if the biggest number in the list is still smaller than the upper limit
		//then gen primes up to the request
		if(getBiggest(nums) < upperLimit){
			genPrimes(upperLimit);
		}
		int curr = 0;//for the index of the list
		long total = 0;
		//while next prime is still smaller than the upperLim, add to total
		while(upperLimit>nums.get(curr)){
			total += nums.get(curr);
			curr++;
		}
		return total;
	}
	
	
	//reads the list of longs off a text document
	private static List<Long> loadPrimes(List<Long> primeList, File primesFile) {
		BufferedReader in;
		if (primesFile.exists()) {
			try {
				in = new BufferedReader(new FileReader(primesFile));
				String currentLine = null;
				long currentPrime = 0;
				while ((currentLine = in.readLine()) != null) {
					currentPrime = Long.parseLong(currentLine);
					if ((currentPrime != 0)) {
						primeList.add(currentPrime);
					}

				}
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return primeList;
	}

	private static List<Long> factor(double d) {
		//mirror of the prime list so it cant be messed up
		List<Long> mirror = nums;
		
		
		List<Long> factors = new ArrayList<Long>();
		Iterator<Long> it;
		while (d > 1 && !(d<2)) {
			
			boolean factored = false;
			Loop:for(long curr: mirror){
				if (d % curr == 0 && curr != 0) {
					d /= curr;
					factors.add(curr);
					factored = true;
					//System.out.println(curr);
					break Loop;
				}
			}

			
			if(!factored){
				System.out.println("failed attempt, try again?(y/n)");
				if((new Scanner(System.in)).next().toLowerCase().charAt(0) != 'y'){
					return null;
				}
				genPrimes(getBiggest(nums) + 10000);
				genAPrime();
			}
			
		}
		return factors;
	}
	//gets the size of a list
	public static long getSize(List<Long> x) {
		Iterator<Long> it = x.iterator();
		long count = 0;
		//while there are still nums left add to the count
		while (it.hasNext()) {
			it.next();
			count++;

		}
		return count;

	}
	//prints a list of longs
	public static void printList(List<Long> x) {
		//doest attempt if there is elements in the list
		if(x == null)
			return;
		
		Iterator<Long> it = x.iterator();
		long count = 0;
		//while there are number in list
		while (it.hasNext()) {
			//makes a new line ever 5 numbers printed
			if (count % 5 == 0)
				System.out.println();
			//prints the prime without making a new line
			System.out.print(it.next() + "\t");
			count++;
		}
	}
	// creates a single prime number, this is used in many other methodes
	public static void genAPrime() {
		//System.out.println("creating primes");
		boolean generated = false;
		//the largest prime number
		long start = getBiggest(nums);
		//count is how much larger the potential prime is than the largest prime
		long count = 0;
		while (!generated) {
			// +=2 because primes cant be even(except 2)
			count += 2;
			Iterator<Long> it = nums.iterator();
			boolean isPrime = true;
			//this while loop goes while there are still 
			//unseen numbers in the prime list iterator
			//this loop checks to see if the prime 
			Loop: while (it.hasNext()) {
				long curr = it.next();
				if ((start + count) % curr == 0) {
					isPrime = false;// not prime
					break Loop;// for efficency
				}
			}
			if (isPrime) {
				
				generated = true;
			}

		}
		// System.out.println(start);
		// System.out.println(count);
		nums.add(start + count);

	}
	
	
	//generates all primes primes below the upperLimit, if not already in the list
	//dependent on the getBiggest function to return biggest num in a list
	public static long genPrimes(long upperLimit) {
		long count = 0;
		//while the biggest prime is still less than the upper limit make a single prime
		while (getBiggest(nums) < upperLimit) {
			genAPrime();
		}
		return count;
	}
	//returns the largest prime in the list
	public static long getBiggest(List<Long> x) {
		//this is the place holder for the largest num in list
		long biggest = 0;
		for (long i : x) {
			//if there is a prime bigger then the biggest, make it the new biggest
			if (i >= biggest) {
				biggest = i;
			}

		}
		return biggest;

	}
	//this get resources from the the resource folder
	//that is at the same level as the src folder that holds the java docs
	public static File getResource(String resourceName) throws Exception{
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		URL[] urls = ((URLClassLoader) cl).getURLs();
		// gets directory of class returns the directory of parent file
		File mainFile = Paths.get(urls[0].toURI()).getParent().toFile();
		System.out.println(mainFile.toString());
		File primesFile = new File(mainFile.toString()
				+ "/resources/" + resourceName);
		return primesFile;
	}

}
