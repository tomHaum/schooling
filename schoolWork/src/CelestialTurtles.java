import java.awt.Color;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class CelestialTurtles {
	public static final double PI = Math.PI;

	public static void main(String[] args) {
		double moonTheta = 0, earthTheta = 0;
		Turtle.bgcolor(Color.BLACK);
		Turtle moon = new Turtle();

		Turtle earth = new Turtle();
		Turtle sun = new Turtle();
		sun.shape(getResource("matt.jpg").toString());
		moon.shape(getResource("ronSmall.jpg").toString());
		moon.up();
		Scanner in = new Scanner(System.in);
		System.out
				.println("how fast would you like it to go? 1 is the default");
		double speed;
		try {
			speed = in.nextDouble();
		} catch (Exception e) {
			speed = 1;
		}
		System.out.println("how many years would you like to model?");
		double years;
		try {
			years = in.nextDouble();
		} catch (Exception e) {
			years = 1;
		}

		// moonTheta = PI/4;
		moon.speed(.1);
		earth.speed(.1);
		sun.speed(.01);
		moon.penColor("red");
		earth.penColor("yellow");
		Clip clip = null;
		
		File musicFile;
		musicFile = getResource("finalTrim.wav");
		if(musicFile != null){
			try{
				AudioInputStream stream = AudioSystem.getAudioInputStream(musicFile);
				clip = AudioSystem.getClip();
				clip.open(stream);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		moonTheta = PI / 2;
		for (double i = 0; i < (32.0 * 365.0 * years) / speed; i++) {

			// earthTheta = orbit(earth,sun, (PI/32), earthTheta, (PI/32),
			// 100,100);
			// earth.forward(5);
			earthTheta = orbit(earth, sun,
					speed * (PI / (365 * 16)),
					earthTheta,
					speed * (PI / 16), 100, 100);
			moonTheta = orbit(moon, earth,
					speed * ((29.5 * (PI / (365 * 16)))),
					moonTheta, // both speeds are the same
					speed * (PI / (365 * 16)), 25, 25);
			
			// sun.forward(.1);
			// sun.right(.1);
		}
		if(clip != null){
			clip.close();
		}
	}

	/*
	 * Turtle s is the satilite Turtle b is the base to which the satilite is
	 * orbiting double rate is the constant which determines how far the
	 * satilite rotates double turn is the amount the satilite turns on its own
	 * axis int x and y is the scale of the circle/ellipse it returns the new
	 * angle
	 */
	public static double orbit(Turtle s, Turtle b, double rate, double theta,
			double turn, int scaleX, int scaleY) {
		int e = (int) Math.ceil((Math.random() * 254));
		int f = (int) Math.ceil((Math.random() * 254));
		int g = (int) Math.ceil((Math.random() * 254));
		s.fillColor(new Color(e, f, g));
		s.penColor(new Color(e, f, g));

		double x1 = b.getX(), y1 = b.getY();
		double x2 = x1 + (scaleX * Math.sin(theta));
		double y2 = y1 + (scaleY * Math.cos(theta));
		s.setPosition(x2, y2);
		s.right(turn * (180 / PI));
		theta += rate;
		if (theta > 2 * PI)
			theta -= 2 * PI;
		return theta;
	}

	public static File getResource(String fileName) {
		Scanner in = new Scanner(System.in);
		File target = null;
		try {
			ClassLoader cl = ClassLoader.getSystemClassLoader();
			URL[] urls = ((URLClassLoader) cl).getURLs();

			// gets directory of class returns the directory of parent file
			File mainFile = Paths.get(urls[0].toURI()).getParent().toFile();

			// an array of all the files in mainFile's directory
			File[] subFiles = mainFile.listFiles();

			File dir = null;

			four: for (File file : subFiles) {// iterate through subfiles to
												// find resources directory
				//System.out.println(file.toString());
				if (file.toString().endsWith("resources")) {
					dir = file;
					//System.out.println("foundit");
					break four;
				}
			}

			// finds coconut.wav in resource directory
			five: for (File file : dir.listFiles()) {
				if (file.toString().endsWith(fileName)) {
					target = file;
					//System.out.println("Found it");
					break five;
				}

			}
			// if error finding file put in file location below

			String fileLoc = "";
			if (target == null) {
				System.out.println("Could not find " + fileName + " \n"
								+ "Would you like to provide the location of the file?(y/n)");
				String flag2 = in.next();
				if (flag2.toLowerCase().charAt(0) == 'y') {
					System.out
							.println("please paste file location of the wav file: ");
					fileLoc = in.next();
					target = new File(fileLoc);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to load file");
		}
		return target;
	}
}
