import javax.swing.JFrame;


public class mainGui {
	public static void main(String[] args){
		JFrame frame = new JFrame();
		Circum c = new Circum();
		frame.add(c);
		frame.setSize(500,500);
		frame.setVisible(true);
		
	}
}
