import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class MyFirstDraw {
    public static boolean waitingForClick = false;
    public static boolean clicked = false;
    public static double clickX, clickY;
    public static char[][] board = new char[3][3];
    public static int guessX, guessY;
    public static  int turnCount = 0;
    public static void main(String args[]) throws InterruptedException {
        Turtle tom = new Turtle();
        Turtle matt = new Turtle();
        char flag = 'y';
        
        do{
        turnCount = 0;
        tom.clear();
        matt.clear();
            for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                board[i][j] = 'q';
            }

        }

        
        matt.penColor("blue");
        tom.up();
        matt.up();
        tom.fillColor("red");
        matt.fillColor("white");
        /*
         * tom.setPosition(100,-100); tom.down(); drawT(tom);
         * tom.setPosition(tom.getX()+75, tom.getY()); tom.down(); drawH(tom);
         */

        tom.setPosition(-200, 200);
        matt.setPosition(-200, 200);

       
        String winType = "";
        boolean turn = false;
        drawGrid(tom);

            do {
            //checks for fullboard

            turn = !turn;

            //waitingForClick = true;
            //System.out.println("click the square u want");
            
            
            
            do {
                guessX = inputInt("x cord");
                guessY = inputInt("y guess");
            } while (checkGuess(guessX, guessY, board));
            turnCount++;
            
            //waitingForClick = false;

            if (turn) {
                drawX(tom, guessX, guessY); 
                board[guessY - 1][guessX - 1] = 'X';

            } else {
                drawO(matt, guessX, guessY);
                board[guessY - 1][guessX - 1] = 'O';
            }
            //printB(board);
            winType = checkWinner(guessX, guessY, turn, board);

            //System.out.println(winType);

        } while (winType == "no");
        drawWin(guessX, guessY, winType, (turn) ? tom : matt);
        System.out.println(((turn)? "X's" : "Circles") + " won the game.");
        flag = inputChar("Play again?");
    
    } 
    while(flag != 'n');

    }
    private static int getRow(double y){
        y -= 200;
        y = Math.abs(y);
        return (int)Math.floor(y / 83);
        
    }
    private static int getCol(double x){
        x += 200;
        x = Math.abs(x);
        return (int)Math.floor(x/83);
        
    }
    
    private static boolean checkGuess(int x, int y, char[][] b) {
        // true is a bad guess, false is a good guess
        
        if (x < 1 || y < 1 || x > b.length || y > b.length) {
            System.out.println("Guess was not on the board");
            clicked = false;
            return true;
        }
        char l = b[y - 1][x - 1];
        //System.out.println(l);
        if (l != 'q') {
            System.out.println("Square is already taken");
            clicked = false;
            return true;
        }
        return false;
    }

    private static void drawWin(int col, int row, String winType, Turtle t) {
        double x = t.getX(), y = t.getY();
        t.up();

        switch (winType) {
        case "diag":
            t.down();
            t.right(45);
            t.forward(353);
            break;
        case "anti diag":
            t.forward(250);
            t.left(180 + 45);
            t.down();
            t.forward(353);

        case "col":
            int shiftX;
            if (col > 1) {
                shiftX = 42 + (83 * (col - 1));
            } else {
                shiftX = 42;
            }
            t.setPosition(x + shiftX, y);
            t.right(90);
            t.down();
            t.forward(250);
            break;
        case "row":
            int shiftY = 42 + (83 * (row - 1));
            
            t.setPosition(x, y - shiftY);
            t.down();
            t.forward(250);
            break;
        
        default:
            System.out.println("TIEEEEEEEEE");
            turnCount = 0;
        }
        t.up();
        t.setPosition(x, y);
        t.setDirection(0);
    }

    private static String checkWinner(int x, int y, boolean xTurn, char[][] b) {
        char l;
        x -= 1;
        y -= 1;
        
        if (xTurn == true){
            l = 'X';
            System.out.println("x turn");
        }
        else
            l = 'O';
        // row
        for (int i = 0; i < b.length; i++) {
            if (b[y][i] != l) {
                    break;
            }
            if (i == b.length - 1) {
                return "row";
            }
        }
        // col
        for (int i = 0; i < b.length; i++) {
            if (b[i][x] != l) {
                break;
            }
            if (i == b.length - 1) {
                return "col";
            }
        }
        // diag
        if (x == y) {
            for (int i = 0; i < b.length; i++) {

                if (b[i][i] != l)
                    break;
                if (i == b.length - 1)
                    return "diag";
            }
            

        }
        // anti diag
        for (int i = 0; i < b.length; i++) {
            if (b[i][b.length - i - 1] != l) {
                break;
            }
            if (i == b.length - 1) {
                return "anti diag";
            }

        }
        if (turnCount >= board.length * board.length){
              return "tie";

        }
        return "no";
    }
    private static void printB(char[][] b) {
        for (char[] x : b) {
            for (char y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }

    }

    private static void drawT(Turtle t) {
        t.down();
        t.setDirection(0);
        double x = t.getX();
        double y = t.getY();
        t.forward(30);
        t.right(90);
        t.forward(100);
        t.left(90);
        t.setPosition(x + 30, y);
        t.forward(30);
        t.setPosition(x, y);
        t.up();

    }

    private static void drawH(Turtle t) {
        t.down();
        t.setDirection(0);
        double x = t.getX();
        double y = t.getY();

        t.right(90);
        t.forward(50);
        t.left(90);
        t.forward(50);
        t.left(90);
        t.forward(50);
        t.up();
        t.setPosition(x, y - 100);
        t.down();
        t.forward(50);
        t.right(180);
        t.up();
        t.setPosition(x + 50, y - 50);
        t.down();
        t.forward(50);
        t.up();

        t.setPosition(x, y);
        t.setDirection(0);

    }

    private static void drawO(Turtle t, int col, int row) {
        double speed = t.getSpeed();
        t.speed(1);
        t.up();
        t.setDirection(0);
        double x = t.getX();
        double y = t.getY();
        double shiftX;
        if (col > 1) {
            shiftX = 42 + (83 * (col - 1));
        } else {
            shiftX = 42;
        }
        double shiftY;
        shiftY = (83 * row) - 15;
        t.setPosition(x + shiftX, y - shiftY -8);
        t.down();
        for (int i = 0; i < 180; i++) {
            t.forward(1.2);
            t.left(2);
        }

        t.up();
        t.speed(speed);
        t.setPosition(x, y);

    }

    private static void drawGrid(Turtle t) {
        double x = t.getX();
        double y = t.getY();

        t.down();
        t.setDirection(0);
        t.right(90);
        t.forward(250);// each little square would be 82 wide
        for (int i = 0; i < 3; i++) {
            t.left(90);
            t.forward(250);
        }
        t.up();
        t.setPosition(x + 83, y);
        t.down();
        t.setDirection(0);
        t.right(90);
        t.forward(250);
        t.up();
        t.setPosition(x + 83 + 83, y);
        t.down();
        t.forward(250);
        t.up();
        t.setPosition(x, y - 83);
        t.setDirection(0);
        t.down();
        t.forward(250);
        t.up();
        t.setPosition(x, y - 83 - 83);
        t.down();
        t.forward(250);
        t.up();
        t.setPosition(x, y);
        t.setDirection(0);
    }

    private static void drawX(Turtle t, int col, int row) {

        // draws x at offset
        // position, in line
        // with the grid.
        // Start Turtle in
        // Upper left corner
        // of board
        t.up();
        t.setDirection(0);
        double x = t.getX();
        double y = t.getY();
        double shiftX;
        if (col > 1) {
            shiftX = 42 + (83 * (col - 1));
        } else {
            shiftX = 42;
        }
        double shiftY;
        shiftY = (83 * row);

        t.setPosition(x + shiftX, y - shiftY + 41 + 2 * 17.6777);
        t.forward(2 * 17.6777);
        t.left(180 + 45);
        t.down();
        t.forward(100);
        t.up();
        t.setPosition(x + shiftX, y - shiftY + 41 + 2 * 17.6777);
        t.setDirection(180);
        t.forward(2 * 17.6777);
        t.right(180 + 45);
        t.down();
        t.forward(100);
        t.up();
        t.setDirection(0);
        t.setPosition(x, y);

    }

    static void output(String info) {
        System.out.println(info);
    }

    static void output(char info) {
        System.out.println(info);
    }

    static void output(int info) {
        System.out.println(info);
    }

    static void output(double info) {
        System.out.println(info);
    }

    static char inputChar(String prompt) {
        char result = (char) 0;
        try {
            result = input(prompt).charAt(0);
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }

    static String input(String prompt) {
        String iput = null;
        System.out.print(prompt + ": ");
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(
                    System.in));
            iput = is.readLine();
            if (iput.length() == 0)
                return null;
        } catch (IOException e) {
            System.out.println("IO Exception: " + e);
        }
        return iput;
    }

    static double inputDouble(String prompt) {
        double result = 0;
        try {
            result = Double.parseDouble(input(prompt));
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }

    public static int inputInt(String prompt) {
        int result = 0;
        try {
            result = Integer.parseInt(input(prompt));
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }
}
