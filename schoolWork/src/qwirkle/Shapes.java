package qwirkle;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public enum Shapes{
	CIRCLE(makeCircle()),
	STAR4(make4Point()),
	DIAMOND(makeRhombus()),
	SQUARE(makeSquare()),
	STAR8(make8Point()),
	PLUS(makePlus());
	
	private Shape shape;
	Shapes(Shape s){
		this.shape =s;
	}
	private static Shape makePlus() {
		
		return null;
	}
	private static Shape makeCircle(){
		Ellipse2D.Double circle = new Ellipse2D.Double();
		circle.setFrame(0,0,50,50);
		return circle;
	}
	private static Shape makeSquare(){
		Rectangle2D.Double square = new Rectangle2D.Double();
		square.setFrame(0,0,50,50);
		return square;
	}
	private static Shape makeRhombus(){
		int[] x = {0,25,50,25};
		int[] y = {25,50,25,0};
		
		Polygon rhom = new Polygon(x,y,4);
		//square.rotate();
		return rhom;
	}
	private static Shape make8Point(){
		int[] x = {
				30,
				26,
				16,
				21,
				10,
				21,
				16,
				26,
				30,
				34,
				44,
				39,
				50,
				39,
				44,
				34
		};
		int[] y = {
				20,
				16,
				6,
				12,
				0,
				11,
				6,
				16,
				20,
				24,
				34,
				29,
				40,
				29,
				34,
				24
		};
		
		
		Polygon star = new Polygon(x, y, 16
				);
		return star;
	}
	public static Shape make4Point(){
		int[] x = {0,25,50,25+13,50,25,0,13};
		int[] y = {0,13,0,25,50,25+13,50,25};
		return new Polygon(x,y,8);

	}
	public Shape getShape(){
		return this.shape;
	}
}
