import java.io.File;

import java.net.URL;
import java.net.URLClassLoader;

import java.nio.file.Paths;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class randTurtle1 {
	static int one = 0;
	static int two = 0;
	static int three = 0;
	static int four = 0;
	static int five = 0;
	static int six = 0;
	static int first = 0;
	static int second = 0;
	static int farthestPosition = 0;
	static String farthestHorse = "";

	public static void main(String args[]) throws Exception {
		Turtle myT1 = new Turtle();
		Turtle myT2 = new Turtle();
		Turtle myT3 = new Turtle();
		Turtle myT4 = new Turtle();
		Turtle myT5 = new Turtle();
		Turtle myT6 = new Turtle();
		Clip coco = null;

		myT1.up();
		myT2.up();
		myT3.up();
		myT4.up();
		myT5.up();
		myT6.up();

		myT1.setPosition(0, 0);
		myT2.setPosition(0, 50);
		myT3.setPosition(0, 100);
		myT4.setPosition(0, 150);
		myT5.setPosition(0, 200);
		myT6.setPosition(0, 250);

		myT1.down();
		myT2.down();
		myT3.down();
		myT4.down();
		myT5.down();
		myT6.down();
		
		Horse[] horses = new Horse[6];
	
		
		// gets the coconut sound to work
		System.out
				.println("Do you want to try to play the coconut sound?(y/n) ");
		Scanner input = new Scanner(System.in);
		String flag = input.next();
		if (flag.toLowerCase().charAt(0) == 'y') {
			try {

				ClassLoader cl = ClassLoader.getSystemClassLoader();
				URL[] urls = ((URLClassLoader) cl).getURLs();
				
				// gets directory of class  returns the directory of parent file
				File mainFile = Paths.get(urls[0].toURI()).getParent().toFile();
				
				// an array of all the files in mainFile's directory
				File[] subFiles = mainFile.listFiles();
				
				File target = null;// random initialization
				File dir = null;
				
				four: for (File file : subFiles) {// iterate through subfiles to find resources directory
					System.out.println(file.toString());
					if (file.toString().endsWith("resources")) {
						dir = file;
						System.out.println("foundit");
						break four;
					}
				}
				
				// finds coconut.wav in resource directory
				five: for (File file : dir.listFiles()) {
					if (file.toString().endsWith("coconuts.wav")) {
						target = file;
						System.out.println("Found it");
						break five;
					}

				}
				// if error finding file put in file location below
				
				String fileLoc = "";
				if(target == null){
					System.out.println("Could not find coconuts.wav \n" +
							"Would you like to provide the location of the file?(y/n)");
					String flag2 = input.next();
					if(flag2.toLowerCase().charAt(0) == 'y'){
						System.out.println("please paste file location of the wav file: ");
						fileLoc = input.next();
						target = new File(fileLoc);
					}
				}
				if(target != null){
					AudioInputStream in = AudioSystem.getAudioInputStream(target);
					coco = AudioSystem.getClip();
					coco.open(in);
					coco.loop(Clip.LOOP_CONTINUOUSLY);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Failed to load sound clip");
			}
		}
		System.out.println(one);
		System.out.println(two);
		System.out.println(three);
		System.out.println(four);
		System.out.println(five);
		System.out.println(six);

		for (int i = 1; i <= 600; i++) {
			double a = 6 * Math.random();
			if (a == 0) {
				System.out.println("ZERO ROLE");
				a = 0.1;
			}
			a = Math.ceil(a);

			// System.out.println(a);

			if (a == 1) {
				one = one + 1;
				myT1.forward(1);
			} else if (a == 2) {
				two = two + 1;
				myT2.forward(1);
			} else if (a == 3) {
				three = three + 1;
				myT3.forward(1);
			} else if (a == 4) {
				four = four + 1;
				myT4.forward(1);
			} else if (a == 5) {
				five = five + 1;
				myT5.forward(1);
			} else if (a == 6) {
				six = six + 1;
				myT6.forward(1);
			}
			if (i % 20 == 0) {
				System.out.println("one  : " + one);
				System.out.println("two  : " + two);
				System.out.println("three: " + three);
				System.out.println("four : " + four);
				System.out.println("five : " + five);
				System.out.println("six  : " + six);
			}
		}
		System.out.println("\nThe Final Positions:\n");
		System.out.println("one  : " + one);
		System.out.println("two  : " + two);
		System.out.println("three: " + three);
		System.out.println("four : " + four);
		System.out.println("five : " + five);
		System.out.println("six  : " + six);

		if (coco != null)
			coco.stop();// stops the coconut loop if it is not null

		double chi1 = 0.0, chi2 = 0, chi3 = 0, chi4 = 0, chi5 = 0, chi6 = 0;
		
		horses[0] = new Horse("Bruce", one);
		horses[1] = new Horse("Leroy",two);
		horses[2] = new Horse("Lightning", three);
		horses[3] = new Horse("Huge Jacked Man",four);
		horses[4] = new Horse("Bolt Speedmen", five);
		horses[5] = new Horse("Sea Bisquete" , six);
		horses = sortHorses(horses, true);
		printHorses(horses);
		
		
		chi1 = Math.pow(one - 100.0, 2) / 100.0;
		chi2 = Math.pow(two - 100.0, 2) / 100.0;
		chi3 = Math.pow(three - 100.0, 2) / 100.0;
		chi4 = Math.pow(four - 100.0, 2) / 100.0;
		chi5 = Math.pow(five - 100.0, 2) / 100.0;
		chi6 = Math.pow(six - 100.0, 2) / 100.0;
		double chiSum = chi1 + chi2 + chi3 + chi4 + chi5 + chi6;
		// System.out.println(chi1 + " " + chi2 + " " + chi3 + " " + chi4 + " "
		// + chi5 + " " + chi6);

		// five degrees of freedom check
		double p = 0;
		if (chiSum <= 1.14) {
			p = .95;
		} else if (chiSum <= 1.61)
			p = .9;
		else if (chiSum <= 2.34)
			p = .8;
		else if (chiSum <= 3.0)
			p = .7;
		else if (chiSum <= 4.35)
			p = .5;
		else if (chiSum <= 6.06)
			p = .3;
		else if (chiSum <= 7.29)
			p = .2;
		else if (chiSum <= 9.24)
			p = .1;
		else if (chiSum <= 11.07)
			p = .05;
		else if (chiSum <= 15.09)
			p = .01;
		else
			p = .001;
		/*
		// Finding winner(s)
		String temp = biggestLead();// first place
		// System.out.println(temp);

		// gets the horsename(s)
		String firstPlaceHorse = temp.substring(temp.indexOf(".") + 1);

		// gets horse's position
		int firstPlaceScore = new Integer(temp.substring(0, temp.indexOf(".")));

		// second place
		temp = biggestLead();
		// System.out.println(temp);
		String secondPlaceHorse = temp.substring(temp.indexOf(".") + 1);
		int secondPlaceScore = new Integer(temp.substring(0, temp.indexOf(".")));

		// Third place
		temp = biggestLead();
		// System.out.println(temp);
		String thirdPlaceHorse = temp.substring(temp.indexOf(".") + 1);
		int thirdPlaceScore = new Integer(temp.substring(0, temp.indexOf(".")));

		// prints out the announcement
		System.out.print("In first place we have " + firstPlaceHorse);

		if (!secondPlaceHorse.equals("no")) {//checks to see if there is a second place
			System.out.print(" beating out " + secondPlaceHorse);
			System.out.print(" by "
					+ (firstPlaceScore - secondPlaceScore + "."));

			if (!thirdPlaceHorse.equals("no")) {//checks for a third place
				System.out.print("\nAnd in third place we have "
						+ thirdPlaceHorse);
				System.out.println(" trailing by "
						+ (secondPlaceScore - thirdPlaceScore) + ".");
			}

		} else {
			System.out.println(".");
		}

		// outputs whether or not the dice rolls were statistically significant
		// or not
		System.out.print("The P-Value was " + p);
		if (p <= .05) {
			System.out.println(" which is statistically signifigant.");
		} else
			System.out.println(" which is NOT statistically signifigant.");
	*/
	}

	public static Horse[] sortHorses(Horse[] horses, boolean sortDown) {
		

			for  (int top = horses.length-1; top>0; top--)
			{
				for (int high=1; high <= top; high ++)
				{
					int low= high-1;
					if ((horses[high].getPos() < horses[low].getPos() & !sortDown) ||
							(horses[high].getPos() > horses[low].getPos() && sortDown))
					{
						Horse holder= horses[high];
						horses[high]= horses[low];
						horses[low] = holder;
					}
				}
				
			}
		
		return horses;
	}

	public static String biggestLead() {
		int firstSetScore = 0;
		String firstSetHorse = "";
		// first half of horses
		if (one > two && one > three) {
			firstSetScore = one;
			firstSetHorse = "Bruce";
		} else if (one < two && two > three) {
			firstSetScore = two;
			firstSetHorse = "Leroy";
		} else if (one < three && two < three) {
			firstSetScore = three;
			firstSetHorse = "Lightning";
		} else if (one == two && one == three) {
			firstSetScore = one;
			firstSetHorse = "Bruce and Leroy and Lightning";
		} else if (one == two) {
			firstSetScore = one;
			firstSetHorse = "Bruce and Leroy";
		} else if (two == three) {
			firstSetScore = two;
			firstSetHorse = "Leroy and Lightning";
		} else if (one == three) {
			firstSetScore = one;
			firstSetHorse = "Bruce and Lightning";
		}
		// second set
		int secondSetScore = 0;
		String secondSetHorse = "";

		if (four > five && four > six) {
			secondSetScore = four;
			secondSetHorse = "Huge Jacked Man";
		} else if (four < five && five > six) {
			secondSetScore = five;
			secondSetHorse = "Bolt Speedmen";
		} else if (four < six && five < six) {
			secondSetScore = six;
			secondSetHorse = "Sea Bisquete";
		} else if (four == five && four == six) {
			secondSetScore = four;
			secondSetHorse = "Huge Jacked Man and Bolt Speedmen and Sea Bisquete";
		} else if (four == five) {
			secondSetScore = four;
			secondSetHorse = "Huge Jacked Man and Bolt Speedmen";
		} else if (five == six) {
			secondSetScore = five;
			secondSetHorse = "Bolt Speedmen and Sea Bisquete";
		} else if (four == six) {
			secondSetScore = four;
			secondSetHorse = "Huge Jacked Man and Sea Bisquete";
		}

		int winningScore = 0;
		String winningHorse = "";
		if (firstSetScore != 0 || secondSetScore != 0) {

			if (firstSetScore == secondSetScore) {
				// System.out.println("tie in winner bracket");
				winningScore = firstSetScore;
				winningHorse = firstSetHorse + " and " + secondSetHorse;

			} else {
				winningScore = (firstSetScore < secondSetScore) ? secondSetScore
						: firstSetScore;
				winningHorse = (firstSetScore < secondSetScore) ? secondSetHorse
						: firstSetHorse;
			}

		} else {
			return "0.no";
		}

		if (winningHorse.contains("Bruce"))
			one = 0;
		if (winningHorse.contains("Leroy"))
			two = 0;
		if (winningHorse.contains("Lightning"))
			three = 0;
		if (winningHorse.contains("Huge Jacked Man"))
			four = 0;
		if (winningHorse.contains("Bolt Speedmen"))
			five = 0;
		if (winningHorse.contains("Sea Bisquete"))
			six = 0;

		return winningScore + "." + winningHorse;
	}
	public static class Horse{
		private String name;
		private int position;
		Horse(){
			this.position = 0;
			this.name = "Name";
		}
		Horse(String name){
			this.name = name;
			this.position = 0;
		}
		Horse(String name, int position){
			this.name = name;
			this.position = position;
		}
		
		int position(int position){
			this.position = position;
			return this.position;
		}
		int move(int change){
			this.position += change;
			return this.position;
		}
		int getPos(){
			return this.position;
		}
		String getName(){
			return this.name;
		}
		
	}
	public static void printHorses(Horse[] horsies){
		for(Horse h:horsies){
			System.out.println(h.getName() + " " + h.getPos());
		}
		
		
	}
}
