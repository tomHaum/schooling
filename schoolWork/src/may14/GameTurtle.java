package may14;

import java.util.ArrayList;
import java.util.List;

public class GameTurtle{
	static List<Turtle> enemy = new ArrayList<Turtle>();



	public static void main(String[] args) {
		
		canvas = new GameCanvas(); // initialize canvas
		xPos = 0;
		yPos = 0;
		initializePlayer(canvas.player);
		while (true) {
			canvas.player.forward(10);
			tick();

		}
	}

	public static boolean checkCollision(Turtle a, Turtle b) {
		return 30 > b.distance(a.getX(), a.getY());
	}
	
	public static void tick() {

		for (int i = 0; i < enemy.size(); i++) {
			Turtle t = enemy.get(i);
			turnTo(t,canvas.player);
			t.forward(1);
			if (checkCollision(canvas.player,enemy.get(i))) {
				System.out.println("removed");
				enemy.remove(i).hide();
				System.out.println(enemy.size());

				i--;
			}
		}
		for (Turtle t : enemy) {
			turnTo(t, canvas.player);
			t.forward(1);

		}
	}

	public static void spawn(int count) {
		for (int i = 0; i < count; i++) {
			double degree = (Math.random() * Math.PI * 2);
			double radius = (Math.random() * 50) + 50;

			double y = Math.sin(degree) * radius;
			double x = Math.cos(degree) * radius;
			Turtle t = new Turtle(canvas.player.getX() + x, canvas.player.getY() + y);	
			turnTo(t,canvas.player);
			
			enemy.add(t);
		}
	}

	public static void turnTo(Turtle a, Turtle b) {
		a.face(b.getX(), b.getY());
	}

	static GameCanvas canvas;
	static double xPos, yPos;

	public static void MattsShit(String[] args) {
		canvas = new GameCanvas(); // initialize canvas
		xPos = 0;
		yPos = 0;
		initializePlayer(canvas.player);
		while (true) { // THE WHILE LOOP
			canvas.player.forward(10);
		}
	}

	public static void initializePlayer(Turtle t) {
		t.shape("circle");
		t.up();
		t.onKey("moveRight", "d"); // on Key commands
		t.onKey("moveLeft", "a");
		t.onKey("moveUp", "w");
		t.onKey("moveDown", "s");
	}

	public void moveRight() {
		canvas.player.setDirection(0);
		// canvas.player.forward(10);
		positionReport();
	}

	public void moveLeft() {
		canvas.player.setDirection(180);
		// canvas.player.forward(10);
		positionReport();
	}

	public void moveUp() {
		canvas.player.setDirection(90);
		// canvas.player.forward(10);
		positionReport();
	}

	public void moveDown() {
		canvas.player.setDirection(270);
		// canvas.player.forward(10);
		positionReport();
	}

	public void positionReport() {
		xPos = canvas.player.getX();
		yPos = canvas.player.getY();
		System.out.println("X: " + xPos);
		System.out.println("Y: " + yPos);
	}

	// garbage class for doing things.
	

}
