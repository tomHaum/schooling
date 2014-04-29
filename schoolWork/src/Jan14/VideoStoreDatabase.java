package Jan14;
import java.io.*;

public class VideoStoreDatabase
{
    static final String FILENAME = "P:/videos.txt";
    static final int MAX_VIDEOS = 5;
    private VideoTape[] videoTapes = new VideoTape[MAX_VIDEOS];
    private int numVideos = 0;  // number of videos currently in collection
    
	public static void main(String[] args)
    {
        new VideoStoreDatabase();
    }
	/**
	 * Constructor loops until command "quit" is used
	 */
	public VideoStoreDatabase()
	{
	  if (checkForFile())
	  {
	    output("Data file located and loaded with " + numVideos + " existing records\n");
	  }
	  else
	  {
	    output("No data file found\n");
	  }
	  char command;
	  do
	  {
	    command = getCommand();
	    doCommand(command);
	  }
	  while (command != 'q');
	}
	/**
	 * Method to check for existing file
	 * 
	 * @return boolean true if there is an existing file
	 */

    private boolean checkForFile()
    {
      // See if a file already exists
	  try
	  {
	    FileReader theFileID = new FileReader(FILENAME);
	    BufferedReader inFile = new BufferedReader(theFileID);
    
	    String line;     // String read from file
	    int length;      // length converted to int
	    boolean lent;    // lent converted to boolean
	    int x = 0;       // counter for number of entries in file
	    
	    // The title is in the first line
	    // readln returns null if the eof is reached
	    while ( (line = inFile.readLine()) != null ) 
	    {
	      // get the next two fields and construct a new object
	        length = Integer.parseInt(inFile.readLine());
	        lent = Boolean.getBoolean(inFile.readLine());
	        
	        videoTapes[x] = new VideoTape(line, length, lent);
	        x = x + 1;
	    }
	    numVideos = x;
	    inFile.close();
	    return true;
	  }
	  catch( IOException io)
	  {
	    output("Error trying to open file " + io.getMessage());
	  }
	  return false;
    }
	/**
	 * Method to get a valid command from the user
	 * 
	 * @return char, a valid command - either (a) add, (l) list or (q) quit
	 */	
	private char getCommand()
	{
	  String cs; // input command as a String
	  char ch;   // input commmand as a char
	  
	  // repeat until a valid command is entered
	  do
	  {
	    output("");
	    output("Would you like to: "); 
	    output("     (a)   add a video tape to the collection");
	    output("     (l)   list the tapes already in the collection");
	    output("     (s)   save the tapes to a data file");
	    output("     (q)   or quit the program");
	    output("");
	   cs = input("Your choice (a/l/s/q): ");
	    
	    // check if anything was input
	    if (cs.length() == 0)
	    {
	      output("Please enter a command!");
	      ch = 'n';
	    }
	    else
	    {
	      // check if the value input was a valid command
	      ch = cs.charAt(0);
	      if ( (ch != 'a') && (ch != 'q') && (ch != 'l')  && (ch != 's') )
	      {
	        output("Please enter a valid command (a, l, s or q)!");
	        ch = 'n';
	      }
	    }
	  } while (ch == 'n');
	  return ch;
	}
	/**
	 * Method to carry out requested operations
	 *
	 *@param command char - the command to be carried out by this method
	 */	
	private void doCommand(char command)
	{
	// using an else if chain...
	
	  if (command == 'a')
	  {
	    add();
	  }
	  else if (command == 'l')
	  {
	    list();
	  }
	  else if (command == 's')
	  {
	    save();
	  }
	  else if (command == 'q')
	  {
	    quit();
	  }
	  else
	  {
	    output("Some internal error in doCommand()");
	  }
	}
	/**
	 * Method to add a video tape
	 */	
	private void add()
	{
	  // see how many videos are in the collection:
	  if (numVideos >= MAX_VIDEOS)
	  {
	    output("Sorry, no more videos can be stored");
	  }
	  else
	  {
	    videoTapes[numVideos] = getVideoDetails();
	    numVideos = numVideos + 1;
	  }
	}
	/**
	 * Method to list video tapes in the collection
	 */	
	private void list()
	{
	  // loop through array
	  for(int x = 0; x < numVideos; x = x + 1)
	  {
	    output("" + x + ": " + videoTapes[x].toString());
	  }
	}
	/**
	 * Method to save the video tapes to a file
	 */	
	private void save()
	{
	  FileWriter theFileID;
	  PrintWriter outFile;
	  try
	  {
	    // set up data file for writing
	    theFileID = new FileWriter(FILENAME);
	    outFile = new PrintWriter(theFileID);
	  
	    // loop through array, writing out records
	    for(int x = 0; x < numVideos; x = x + 1)
	    {
	      outFile.println(videoTapes[x].getTitle());
	      outFile.println(videoTapes[x].getLength());
	      outFile.println(videoTapes[x].isLent());
	    }
	    outFile.close();
	  }
	  catch (IOException io)
	  {
	    output("Some error writing to the data file - " + io.getMessage());
	  }	
	}
	/**
	 * quit the program message
	 */	
	private void quit()
	{
	  output("Bye then");
	}
	
	/**
	 * Method to get details of tape
	 *
	 *@return VideoTape - a video tape object (well, how neat!)
	 */	
	private VideoTape getVideoDetails()
	{
	  String title = input("Please enter the title of your video: ");
	  int length = inputInt("Please enter the length in minutes: ");
	  // We assume it is not yet lent
	  return new VideoTape(title, length, false);
	}
	/**
	 * IBIO methods, (c) International Baccalaureate 2003
	 * Computer Science Subject Guide, Appendix 2.
	 */
	
	 static void output(String info) {  System.out.println(info); }
     static void output(double info) {  System.out.println(info); }
     static void output(int info)    {  System.out.println(info); }
  
     static int inputInt(String Prompt)
     {  
         int result=0;
         try{result=Integer.parseInt(input(Prompt).trim());}
         catch (Exception e){result = 0;}
         return result;
     }
     static double inputDouble(String Prompt)
     {  
         double result=0;
         try{result=Double.valueOf(input(Prompt).trim()).doubleValue();}
         catch (Exception e){result = 0;}
         return result;
     }
     static String input(String prompt)
     {  
         String inputLine = "";
         System.out.print(prompt);
         try
         { 
             java.io.InputStreamReader sys = new java.io.InputStreamReader(System.in);
             java.io.BufferedReader inBuffer = new java.io.BufferedReader(sys);
             inputLine = inBuffer.readLine();
         }
         catch (Exception e)
         { 
             String err = e.toString();
             System.out.println(err);
         }
         return inputLine;
      }
      static String input() { return input("");  }
}
