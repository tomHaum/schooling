package Jan14;

public class VideoTape
{
	// instance variables - or data members
	protected String title;  // title of the video
	private int length;    // in minutes
	private boolean lent;  // is it lent to someone

	/**
	 * No argument Constructor for objects of class VideoTape
	 */
	public VideoTape()
	{
		// initialise instance variables
		this.title = null;
		this.length = 0;
		this.lent = false;
	}
	/**
	 * Constructor for objects of class VideoTape
	 */
	public VideoTape(String title, int length, boolean lent)
	{
	  setTitle(title);
	  setLength(length);
	  setLent(lent);
	}

	/*
	 * Mutator methods change the objects data fields
	*/

    /**
     * set the title
     * 
	 * @param  String title the video title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
    /**
     * set the length
     * 
	 * @param  int the video length in minutes
	 */
	public void setLength(int length)
	{
		this.length = length;
	}
    /**
     * set the status of lent
     * 
	 * @param  boolean lent true if the video is on loan
	 */
	public void setLent(boolean lent)
	{
		this.lent = lent;
	}
	/*
	 * Accessor methods return the objects data fields
	*/

    /**
     * Return the title
     * 
	 * @return String the video title
	 */
	public String getTitle()
	{
		return this.title;
	}
    /**
     * get the length
     * 
	 * @return int the video length in minutes
	 */
	public int getLength()
	{
		return this.length;
	}
    /**
     * get the status of lent
     * 
	 * @return boolean lent true if the video is on loan
	 */
	public boolean isLent()
	{
		return this.lent;
	}
    /**
     * All Classes should override this method (derived from Object)
     * 
	 * @return String the VideoTape object as a String
	 */
	public String toString()
	{
		return ( this.getTitle().trim() + " - " 
		       + this.getLength() + " - "
		       + this.isLent() );
	}
}
