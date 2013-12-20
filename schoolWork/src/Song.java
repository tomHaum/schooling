public class Song {
	private String name;
	private int track;
	private double length;

	Song() {
		this("", 0, 0.0);
	}

	public Song(String s, int t, double l) {
		this.name = s;
		this.track = t;
		this.length = l;
	}

	public void setName(String n) {
		this.name = n;
	}

	public String getName() {
		return this.name;
	}

	public void setTrack(int t) {
		this.track = t;
	}

	public int getTrack() {
		return this.track;
	}

	public void setLength(double l) {
		this.length = l;
	}

	public double getLength() {
		return this.length;
	}

}
