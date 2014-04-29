package craps;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Graphics {
	JFrame theFrame = new JFrame("Craps");
	JButton roll = new JButton("Roll");
	JTextField number = new JTextField();
	JPanel contentPane = new JPanel();
	JPanel panel1 = new JPanel();
	Graphics(){
		contentPane.setLayout(new GridLayout(0,2));
		panel1.setLayout(new GridLayout(0,2));
		
		number.setEditable(false);
		number.setMinimumSize(new Dimension(20,20));
		
		roll.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				double random1 = Math.random();
				double random2 = Math.random();
				int int1 = 0;
				System.out.println("New Roll");
				for(int i = 1; i < 7; i ++){
					if(random1 < (i/6.0)){
						int1 = i;
						break;
					}else{
						System.out.println("lower");
					}
				}
				if(int1 == 0)int1 = 6;
				
				int int2 = 0;
				for(int i = 1; i < 7; i ++){
					if(random2 < (i/6.0)){
						int2 = i;
						break;
					}
				}
				if(int2 == 0)int2 = 6;
				
				number.setText(String.format("%d + %d = %d", int1,int2, int1+int2));
			}
			
		});
		
		
		panel1.add(number);
		panel1.add(roll);
		contentPane.add(panel1);
		theFrame.setContentPane(contentPane);
		theFrame.setVisible(true);
		
	}
	public static void main(String[] args){
		Graphics g = new Graphics();
		int i = 3;
		System.out.println(i/3.0);
	}
}
