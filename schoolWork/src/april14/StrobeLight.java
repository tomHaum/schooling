package april14;

import javax.swing.JFrame;

public class StrobeLight {
	public static void main(String[] args){
		JFrame frame = new JFrame("Strobing");
		Thread blinker = new Thread(){
			int iteration = 0;
			public void run(){
				iteration++;
				System.out.println("iter: " + iteration);
				run();
			}
		};
		blinker.start();
	}
}
