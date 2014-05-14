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
/* example text quiz contents
What is the halving frequency(in blocks)? 210000
What is the highest historic USD value? 1200
What is the goal block creation time(min)? 10
Who has the most bitcoins? satoshi
Who has the largest wallet? FBI
Currently, what is the reward for finding a block(BTC)? 25
What algrorithm is used to hash the blockchain? SHA256
Who controls bitcoin network? nobody
What is the main meme inspired bitcoin spin-off? DogeCoin
What is the ceiling for number of bitcoins in circulation(number)? 21000000
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
		
		//from her to the end of the method
		//it is mostly contruction of the GUI
		//not direly important
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
	//returns the set of questions within a file location
	public void getQuestions(File file) {
		BufferedReader in;
		if(file.exists()){//makes sure that the file is real
			try{
			    in = new BufferedReader(new FileReader(file));
			    String currentLine = "";
			    while((currentLine = in.readLine()) != null){//while there is still lines to read
			    	questions.add(new Question(currentLine));
			    }
			   
			   
			  
			    frame.validate();//updates the frame to hold all the questions
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * the action listener for all the commopents on the GUI
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == getFileButton){//if the source of the click is the same as the getFileButton
			int result = fc.showOpenDialog(getFileButton);//a seperate class that handles file selections
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
			//makes sure that the percent is stored in a decimal, not a whole number
			double percent = (0.0 + score) /(0.0 + total);
			
			String level = "";
			//assigns the name to the score
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
	/**
	 * inner class that holds two strings
	 * the question and answer, so one question in a quiz 
	 * 
	 *
	 */
	private static class Question{
		String question;
		String answer;
		/**
		 * parses data from the text file. Is reliant on the user creating a document
		 * that has a question followed by a single question mark then the answer to that question
		 * 
		 */
		Question(String data){
			//breaks down the string by the question mark
			String[] qAndA = data.split("\\?");
			this.question = (qAndA[0] + "?".trim());
			//System.out.println(question);
			this.answer = qAndA[1].trim();
			//System.out.println(answer);
		}
		/**
		 * Small method to check if the user's guess matfch3es the answer 
		 * @param String guess
		 * @return boolean if the guess matches the answer
		 */
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
	/**
	 * an extension of jPanel that has a JLable for each question 
	 * and a JText field for the answer so the user can answer it there.
	 * 
	 * @author 14_thaumersen
	 *
	 */
	private class QuestionPanel extends JPanel implements ActionListener{
		//the list of questions that can be changed on a whim
		//could have worked as an array
		List<Question> questions;
		//the labels for the questions
		JLabel[] questionLabels;
		//answer fields
		AnswerField[] guessField;
		
		
		QuestionPanel(List<Question> q){
			this.questions = q;
			setQuestions(this.questions);
		}
		void reset(){
			setQuestions(this.questions);
			validate();
		}
		//resets the questions to a new question set
		void setQuestions(List<Question> q){
			//removes all previous question and answers
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
	/**
	 * another inner class for the text field.
	 * It holds the answer within its own class 
	 * so that it can easily be checked by classes calling on it
	 * 
	 * all methods are self explanitory
	 *
	 */
	private class AnswerField extends JTextField{
		private String answer;
		
		public void setAnswer(String a){
			answer = a;
		}
		public String getAnswer(){
			return answer;
		}
		public boolean checkAnswer(){
//			System.out.println("Guess:" + this.getText().toLowerCase());
//			System.out.println("Answer:" + answer.toLowerCase());
			return answer.toLowerCase().equals(this.getText().trim().toLowerCase());
		}
	}
}
