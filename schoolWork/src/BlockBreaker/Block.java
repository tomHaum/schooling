package BlockBreaker;


public class Block{
	int x,y,h,w;
	public Block(int x, int y, int w, int h){
		this.x = x; this.y = y; this.h = h; this.w = w;
	}
	Point getCenter(){
		int x =  this.x + (w /2);
		int y = this.y + (h / 2);
		return new Point(x,y);
	}
	void moveCenter(int newX, int newY){
		this.x = newX - (w/2);
		this.y = newY - (h/2);
	}

	
}
