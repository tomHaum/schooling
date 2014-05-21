package may14;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameTurtle extends Turtle {
	static List<GameTurtle> enemy = new ArrayList<GameTurtle>();
	boolean isPlayer = false;
	static GameTurtle player;
	public GameTurtle(double x, double y) {
		this.up();
		this.setPosition(x, y);
	}
	public static void main(String[] args){
		player = new GameTurtle(0,0);
		player.isPlayer = true;
		while(true){
			player.forward(1);
			tick();
			spawn(1);
		}
	}
	public static void tick(){
		for(GameTurtle t: enemy){
			t.turnTo(player);
			t.forward(1);
			
		}
	}
	public static void spawn(int count){
		for(int i = 0; i< count; i++){
			double degree = (Math.random() * Math.PI * 2);
			double radius = (Math.random() * 50) + 50;
		
			double y = Math.sin(degree) * radius;
			double x = Math.cos(degree) * radius;
			GameTurtle t = new GameTurtle(x,y);
			t.turnTo(player);
			t.isPlayer = false;
			enemy.add(new GameTurtle(x,y));
		}
	}
	public void changeDirection(Turtle target, Turtle enemy){
		enemy.face(target.getX(), target.getY());
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("EVENT");
		if(e.getKeyCode() == KeyEvent.VK_W){
			System.out.println("W pressed");
		}
		super.keyPressed(e);
	}



	
	public void turnTo(Turtle t){
		this.face(t.getX(), t.getY());
	}
}
