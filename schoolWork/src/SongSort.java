import java.util.Scanner;


/**
 * 
 * 
 * 
 * 
 * @author Tom Haumersen
 * 
 */
public class SongSort {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Song[] songs = new Song[21];
		songs[0] = new Song("Time is on my side", 1, 2.59);
		songs[1] = new Song("Heart of Stone", 2, 2.49);
		songs[2] = new Song("Play with Fire", 3, 2.13);
		songs[3] = new Song("Satisfaction", 4, 3.43);
		songs[4] = new Song("As Tears Go By", 5, 2.44);
		songs[5] = new Song("Get off of my Cloud", 6, 2.55);
		songs[6] = new Song("Mothers Little H", 7, 2.46);
		songs[7] = new Song("19th Nervous Breakdown", 8, 3.56);
		songs[8] = new Song("Paint it Black", 9, 3.22);
		songs[9] = new Song("Under My Thumb", 10, 3.42);
		songs[10] = new Song("Ruby Tuesday", 11, 3.16);
		songs[11] = new Song("Let's Spend the Night Together", 12, 3.37);
		songs[12] = new Song("Jumping Jack Flash", 13, 3.41);
		songs[13] = new Song("Street Fighting Man", 14, 3.14);
		songs[14] = new Song("Sympathy for the Devil", 15, 6.18);
		songs[15] = new Song("Honky Tonk Women", 16, 3.00);
		songs[16] = new Song("Gimme Shelter", 17, 4.31);
		songs[17] = new Song("Midnight Rambler", 18, 9.05);
		songs[18] = new Song("You Can't Always Get What You Want", 19, 7.30);
		songs[19] = new Song("Brown Sugar", 20, 3.48);
		songs[20] = new Song("Wild Horses", 21, 5.42);

		char flag = 'y';

		while (flag != 'n') {
			System.out
					.println("Which sort do you want?(t)rack, (l)ength , (n)ame");
			flag = in.next().toLowerCase().charAt(0);

			switch (flag) {
				case ('t'): {
					songs = sortTrack(songs);
					break;
				}
				case('l'):	{
					songs = sortLength(songs);
					break;
				}
				case('n'):	{
					songs = stringSort(songs);
					break;
				}
				
				default:	{
					System.out.println("not a sort");
				}
			}
			printAlbum(songs);
			System.out.println("Again?(y/n)");
			flag = in.next().toLowerCase().charAt(0);
		}

	}

	public static void printAlbum(Song[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i].getName() + "-" + data[i].getTrack()
					+ " " + data[i].getLength());
		}

	}

	static Song[] sortTrack(Song[] data) {

		for (int top = data.length - 1; top > 0; top--) {
			for (int high = 1; high <= top; high++) {
				int low = high - 1;
				if (data[high].getTrack() < data[low].getTrack()) {

					Song holder = data[high];
					data[high] = data[low];
					data[low] = holder;
				}
			}

		}
		return data;
	}

	static Song[] sortLength(Song[] data) {

		for (int top = data.length - 1; top > 0; top--) {
			for (int high = 1; high <= top; high++) {
				int low = high - 1;
				if (data[high].getLength() < data[low].getLength()) {

					Song holder = data[high];
					data[high] = data[low];
					data[low] = holder;
				}
			}

		}
		return data;
	}

	static Song[] stringSort(Song[] data) {

		for (int top = data.length - 1; top > 0; top--) {
			for (int high = 1; high <= top; high++) {
				int low = high - 1;
				if (data[low].getName().compareTo(data[high].getName()) > 0) {
					Song temp = data[low];

					data[low] = data[high];

					data[high] = temp;

				}
			}
		}
		return data;
	}
}