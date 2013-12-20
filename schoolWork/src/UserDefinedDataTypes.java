import java.util.Scanner;


public class UserDefinedDataTypes {
	public static class UserDefinedDataType
	{

		public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			Player[] roster = new Player[5];
			
			for(int x = 0; x < 5; x++){
				roster[x] = new Player();
			}
			
			for(int x = 0; x < 5; x++){
				System.out.println("enter player's name");
				roster[x].setName(in.next());
				System.out.println("enter player's number");
				roster[x].setNumber(in.nextInt());
			}
			
			char cont = 'n';
			
			do{
			System.out.println("How would you like to sort array( (S)tring / (N)umber");
			char action = in.next().charAt(0);
			
			
			
			switch(action){
			case('s'):
			{
				roster = stringSort(roster);
				
				for(int x = 0; x < 5; x++){
					System.out.println(roster[x].getName() + " " + roster[x].getNumber());
				}
				
				break;
			}
			
			case('n'):
			{
				roster = Sortbubble(roster);
				
				for(int x = 0; x < 5; x++){
					System.out.println(roster[x].getName() + " " + roster[x].getNumber());
				}
				
				break;
			}
			
			default:
			{
				System.out.println("invalid selection");
				cont = 'y';
				break;
			}
			}
			
			}while(cont == 'y');
			
			
			
		}
		
		static Player[] stringSort(Player[] data){
			
			for  (int top = data.length-1; top>0; top--)
			{
				for (int high=1; high <= top; high ++)
				{
					int low= high-1;
					if(data[low].getName().compareTo(data[high].getName()) > 0){
						Player temp = data[low];
					
						data[low]=data[high];
						
						data[high] = temp;
						
					}
				}
			}
			return data;
		}
		
		static Player[] Sortbubble (Player[] data){
			
			for  (int top = data.length-1; top>0; top--)
			{
				for (int high=1; high <= top; high ++)
				{
					int low= high-1;
					if (data[high].getNumber() < data[low].getNumber())
					{
		  
						Player holder= data[high];
						data[high]= data[low];
						data[low] = holder;
					}
				}
		 
			}
			return data;
		}
		public static class Player
		{
		 // instance variables - or data members
			private String name;  // title of the video
			private int number;    // in minutes

			/**
			 * No argument Constructor for objects of class VideoTape
			 */
			public Player()
			{
				// initialise instance variables
				this.name = null;
				this.number = 0;
			}
			/**
			 * Constructor for objects of class VideoTape
			 */
			public Player(String name, int number)
			{
			  setName(name);
			  setNumber(number);
			}

			/*
			 * Mutator methods change the objects data fields
			*/

		    /**
		     * set the title
		     * 
			 * @param  String title the video title
			 */
			public void setName(String name)
			{
				this.name = name;
			}
		    /**
		     * set the length
		     * 
			 * @param  int the video length in minutes
			 */
			public void setNumber(int number)
			{
				this.number = number;
			}
		    /**
		     * set the status of lent
		     * 
			 * @param  boolean lent true if the video is on loan
			 */
			
			/*
			 * Accessor methods return the objects data fields
			*/

		    /**
		     * Return the title
		     * 
			 * @return String the video title
			 */
			public String getName()
			{
				return this.name;
			}
		    /**
		     * get the length
		     * 
			 * @return int the video length in minutes
			 */
			public int getNumber()
			{
				return this.number;
			}
		    /**
		     * get the status of lent
		     * 
			 * @return boolean lent true if the video is on loan
			 */
			
		    /**
		     * All Classes should override this method (derived from Object)
		     * 
			 * @return String the VideoTape object as a String
			 */
			public String toString()
			{
				return ( this.getName().trim() + " - " 
				       + this.getNumber() );
			}
		}
	}
}
