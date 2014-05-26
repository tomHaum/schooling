package gameOfTurtles;

import java.awt.Color;

public class Projectile {
	Turtle base;
	int tick;
	double speed;

	Projectile(){
		base = new Turtle();
		tick = 5;
		speed = 10;
	}
	Projectile(double x, double y, double direction, double speed, int tick){
		base = new Turtle();
		base.speed(.0001);
		base.fillColor(Color.BLACK);
		base.up();
		base.setPosition(x, y, direction);
		base.show();
		
		this.tick = tick;
		this.speed = speed;
	}

	void kill() {
		base.hide();
	}

	int getTick() {
		return tick;
	}
	
	Turtle getTurtle(){
		return base;
	}

	void set(double x, double y, double direction, double speed, int tick) {
		base.setPosition(x, y, direction);
		this.tick = tick;
		this.speed = speed;
		base.show();
	}

	void tick() {
		if (tick > 0) {
			base.forward(speed * 1);
			tick--;
		}
	}
}
