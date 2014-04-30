package april14;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
/**
 * This is a quiz game. It has a gui. It is able to load files from your computer
 * In the future, i will add support for making your own quizzes from within the GUI.
 * As of right now you have to make it in notepad, or similar .txt editors
 * 
 * the format for the files is 
 * Question? answer
 * the question mark is key. Without one, or with more than one, 
 * the file will load incorrectly
 * @author 14_thaumersen
 *
 */
public class QuizGame implements ActionListener{
	JFrame frame;

	JPanel menu;
	JButton getFileButton;
	JButton checkButton;
	JButton retryButton;
	
	JLabel scoreLabel;
	JScrollPane qScroller;
	QuestionPanel qPanel;
	
	JFileChooser fc;
	List<Question> questions = new ArrayList<Question>();

	public static void main(String[] args){
		QuizGame one = new QuizGame();
		one.init();
	}
	public void init(){
		frame = new JFrame("My Frame");
		frame.setSize(500,500);
		
		fc = new JFileChooser();
		//defaults to a premade file that is in your pDrive
		//loads it to the program memory
		getQuestions(new File("P:\\Quiz1.txt"));
		
		qPanel = new QuestionPanel(questions);
//		qPanel.setBackground(Color.RED);
		//adds the scrol
		qScroller = new JScrollPane(qPanel);
		qScroller.setBackground(Color.YELLOW);
		frame.setLayout(new GridLayout(0,1));
		
		menu = new JPanel();
		menu.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		g.gridx = 1; g.gridy = 0;
		g.ipadx = 5; g.ipady = 5;
		g.insets = new Insets(10,10,10,10);
		g.anchor = GridBagConstraints.NORTH;
		scoreLabel = new JLabel("Score: 0/0");
		g.gridwidth = 2;
		menu.add(scoreLabel);
		
		g.anchor = GridBagConstraints.EAST;
		g.gridwidth = 1;
		g.gridx = 0;
		g.gridy++;
		
		checkButton = new JButton("Check");
		checkButton.addActionListener(this);
		menu.add(checkButton,g);
		
		g.anchor = GridBagConstraints.WEST;
		g.gridx++;
		retryButton = new JButton("Retry");
		retryButton.addActionListener(this);
		menu.add(retryButton, g);
		
		g.anchor = GridBagConstraints.EAST;
		getFileButton = new JButton("Load File");
		getFileButton.addActionListener(this);
		g.gridx = 0;g.gridy++;
		menu.add(getFileButton,g);
		
		g.anchor = GridBagConstraints.WEST;
		g.gridx++;
		menu.add(new JButton("create quiz"),g);
		
		frame.add(qScroller);
		frame.add(menu);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}
	public void getQuestions(File file) {
		BufferedReader in;
		if(file.exists()){
			try{
			    in = new BufferedReader(new FileReader(file));
			    String currentLine = "";
			    while((currentLine = in.readLine()) != null){
			    	questions.add(new Question(currentLine));
			    }
			   
			   
			  
			    frame.validate();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == getFileButton){
			int result = fc.showOpenDialog(getFileButton);
			if(result == JFileChooser.APPROVE_OPTION){
				File file = fc.getSelectedFile();
				System.out.println(file.toString());
				getQuestions(file);
				qPanel.setQuestions(questions);

				int height = frame.getHeight();
				frame.pack();
				frame.setSize(frame.getWidth(), height);
				
			}
		}else if(e.getSource() == checkButton){
			int score = qPanel.checkAnswers();
			int total = qPanel.numOfQuestions();
			double percent = (0.0 + score) /(0.0 + total);
			
			String level = "";
			if(percent >= .9){
				level = "MASTER";
			}else if (percent >= .7){
				level = "Wizard";
			}else if(percent >= .5){
				level = "Mediocare";
			}else if(percent >= .3){
				level = "Terrible";
			}else{
				level = "Dispicable";
			}
			scoreLabel.setText("Score: "+score + "/" + total + ". Your Level: " + level);
		}else if(e.getSource() == retryButton){
			System.out.println("reset");
			qPanel.reset();
		}
	}
	private static class Question{
		String question;
		String answer;
//		Question(String q, String a){
//			this.question = q;
//			this.answer = a;
//		}
		Question(String data){
			String[] qAndA = data.split("\\?");
			this.question = (qAndA[0] + "?".trim());
			//System.out.println(question);
			this.answer = qAndA[1].trim();
			//System.out.println(answer);
		}
		boolean checkAnswer(String guess){
			return guess.toLowerCase().equals(answer.toLowerCase());
		}
		String getQuestion(){
			return question;
		}
		String getAnswer(){
			return answer;
		}
	
	}
	private class QuestionPanel extends JPanel implements ActionListener{
		List<Question> questions;
		JLabel[] questionLabels;
		AnswerField[] guessField;
		
		
		QuestionPanel(List<Question> q){
			this.questions = q;
			setQuestions(this.questions);
		}
		void reset(){
			setQuestions(this.questions);
			validate();
		}
		void setQuestions(List<Question> q){
			removeAll();
			this.questions = q;
			int size = this.questions.size();
			questionLabels = new JLabel[size];
			guessField = new AnswerField[size];
			
			Question currQ;
			
			GridBagLayout gl = new GridBagLayout();
			GridBagConstraints g = new GridBagConstraints();
			setLayout(gl);
			g.gridx = 0;
			g.gridy = 0;
			g.insets = new Insets(10,10,10,10);
			
			for(int i = 0; i < size; i++){
				
				currQ = this.questions.get(i);
				questionLabels[i] = new JLabel(currQ.getQuestion());
				
				
				g.gridx = 0;
				g.anchor = GridBagConstraints.EAST;
				add(questionLabels[i],g);
				
				guessField[i] = new AnswerField();
				guessField[i].setAnswer(currQ.getAnswer());
				guessField[i].setColumns(15);
				g.gridx++;
				g.anchor = GridBagConstraints.WEST;
				add(guessField[i],g);
				
				g.gridy++;
				
				
			}
			
		}
		public int checkAnswers(){
			int correct = 0;
			for(AnswerField a: guessField){
				if(a.checkAnswer()){
					a.setBackground(Color.GREEN);
					correct++;
				}else{
					a.setBackground(Color.RED);
				}
			}
			return correct;
		}
		public int numOfQuestions(){
			return this.questions.size();
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			for(AnswerField a: guessField){
				if(e.getSource() == a){
					System.out.println("Correct Answer is " + a.getAnswer());
				}
			}
			
		}
		
	}
	private class AnswerField extends JTextField{
		private String answer;
		
		public void setAnswer(String a){
			answer = a;
		}
		public String getAnswer(){
			return answer;
		}
		public boolean checkAnswer(){
			System.out.println("Guess:" + this.getText().toLowerCase());
			System.out.println("Answer:" + answer.toLowerCase());
			return answer.toLowerCase().equals(this.getText().trim().toLowerCase());
		}
	}
}
