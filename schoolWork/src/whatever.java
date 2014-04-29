import java.util.Scanner;

public class whatever {
	public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			System.out.print("Enter your name: ");
			String name = in.next();
			name= name + " " + in.next();
			System.out.print("enter your address: ");
			String address = in.next();
			address = address +  " " + in.next() + " " + in.next();
			System.out.print("enter your city: ");
			String city = in.next();
			System.out.print("enter your state: ");
			String state = in.next();
			
			System.out.println(name);
			System.out.println(address + ", " + city + " " + state);
	}
}