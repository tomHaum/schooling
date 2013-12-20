public class randTurtle {
	public static void main(String args[]) {
		Turtle myT1 = new Turtle();
		Turtle myT2 = new Turtle();
		Turtle myT3 = new Turtle();
		Turtle myT4 = new Turtle();
		Turtle myT5 = new Turtle();
		Turtle myT6 = new Turtle();

		myT1.up();
		myT2.up();
		myT3.up();
		myT4.up();
		myT5.up();
		myT6.up();

		myT1.setPosition(0, 0);
		myT2.setPosition(0, 50);
		myT3.setPosition(0, 100);
		myT4.setPosition(0, 150);
		myT5.setPosition(0, 200);
		myT6.setPosition(0, 250);

		myT1.down();
		myT2.down();
		myT3.down();
		myT4.down();
		myT5.down();
		myT6.down();

		int one = 0;
		int two = 0;
		int three = 0;
		int four = 0;
		int five = 0;
		int six = 0;
		int first = 0;
		int second = 0;
		System.out.println(one);
		System.out.println(two);
		System.out.println(three);
		System.out.println(four);
		System.out.println(five);
		System.out.println(six);

		for (int i = 1; i < 100; i++) {
			double a = Math.ceil(6 * Math.random());
			// System.out.println(a);

			if (a == 1) {
				one = one + 1;
				myT1.forward(1);
			} else if (a == 2) {
				two = two + 1;
				myT2.forward(1);
			} else if (a == 3) {
				three = three + 1;
				myT3.forward(1);
			} else if (a == 4) {
				four = four + 1;
				myT4.forward(1);
			} else if (a == 5) {
				five = five + 1;
				myT5.forward(1);
			} else if (a == 6) {
				six = six + 1;
				myT6.forward(1);
			}

			System.out.println(one);
			System.out.println(two);
			System.out.println(three);
			System.out.println(four);
			System.out.println(five);
			System.out.println(six);
		}
		Horse oneH = new Horse(one, "Bruce");
		Horse twoH = new Horse(two, "Leroy");
		Horse threeH = new Horse(three, "Lightning");
		Horse fourH = new Horse(four, "Hank");
		Horse fiveH = new Horse(five, "Bolt Speedmen");
		Horse sixH = new Horse(six, "Matilda");
		// first set
		Horse firstSet = null;
		if (oneH.getP() > twoH.getP() && oneH.getP() > threeH.getP()) {
			firstSet = oneH;
		} else if (oneH.getP() < twoH.getP() && twoH.getP() > threeH.getP()) {
			firstSet = twoH;
		} else if (oneH.getP() < threeH.getP() && twoH.getP() < threeH.getP()) {
			firstSet = threeH;
		} else if (oneH.getP() == twoH.getP() && oneH.getP() == threeH.getP()) {
			firstSet = new Horse(oneH.getP(), oneH.getN() + " and "
					+ twoH.getN() + " and " + threeH.getN());
		} else if (oneH.getP() == twoH.getP()) {
			// System.out.println("tie in first set");
			firstSet = new Horse(oneH.getP(), oneH.getN() + " and "
					+ twoH.getN());
		} else if (twoH.getP() == threeH.getP()) {
			// System.out.println("tie in first set");
			firstSet = new Horse(twoH.getP(), twoH.getN() + " and "
					+ threeH.getN());
		} else if (oneH.getP() == threeH.getP()) {
			firstSet = new Horse(oneH.getP(), oneH.getN() + " and "
					+ threeH.getN());
		}
		Horse secondSet = null;

		if (fourH.getP() > fiveH.getP() && fourH.getP() > sixH.getP()) {
			secondSet = fourH;
		} else if (fourH.getP() < fiveH.getP() && fiveH.getP() > sixH.getP()) {
			secondSet = fiveH;
		} else if (sixH.getP() > fiveH.getP() && fourH.getP() < sixH.getP()) {
			secondSet = sixH;
		} else if (fourH.getP() == fiveH.getP() && fourH.getP() == sixH.getP()) {
			secondSet = new Horse(fourH.getP(), fourH.getN() + " and "
					+ fiveH.getN() + " and " + sixH.getN());
		} else if (fourH.getP() == fiveH.getP()) {
			// System.out.println("tie in second set");
			secondSet = new Horse(fourH.getP(), fourH.getN() + " and "
					+ fiveH.getN());
		} else if (fiveH.getP() == sixH.getP()) {
			secondSet = new Horse(fiveH.getP(), fiveH.getN() + " and "
					+ sixH.getN());
		} else if (fourH.getP() == sixH.getP()) {
			secondSet = new Horse(fourH.getP(), fourH.getN() + " and "
					+ sixH.getN());
		}
		Horse winner;
		if (firstSet != null && secondSet != null) {

			if (firstSet.getP() == secondSet.getP()) {
				// System.out.println("tie in winner bracket");
				winner = new Horse(firstSet.getP(), firstSet.getN() + " and "
						+ secondSet.getN());
			} else {
				winner = (firstSet.getP() > secondSet.getP()) ? firstSet
						: secondSet;
			}

			System.out.print(winner.getN() + " won with a position of "
					+ winner.getP());

		} else {
			System.out.println("A tie somewhere");
		}

	}

	static class Horse {
		public int position;
		public String name;

		Horse(int x) {
			position = x;
		}

		Horse(String n) {
			name = n;
		}

		Horse(int x, String n) {
			position = x;
			name = n;
		}

		int getP() {
			return position;
		}

		String getN() {
			return name;
		}

	}
}
