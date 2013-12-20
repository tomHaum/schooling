import java.io.BufferedReader;//io is input output
import java.io.IOException;
import java.io.InputStreamReader;

 public class NestedLoops{
        public static void main(String[] args) {
        
        /* This will print times tables for odd integers 
        
        for(int x = 1; x <= 9; x = x + 2){
            for (int y = 1; y <= 12; y++){
                output (x + " times " + y + " equals " + x*y);
            }
            
            char stop; // Variable to stop program until enter is pressed 
            stop = inputChar("Press enter to continue");
        }
        
        char flag = 'y'; // Variable to stop loop 
        
        //This will print a selected times table 
        
        do {
            int x = 1;
            x = inputInt("Enter a number to print the times table of");
            for (int y = 1; y <= 12; y++){
                output (x + " times " + y + " equals " + x*y);
            }   
            
            flag = inputChar("Do you want to continue (y or n)");
            
        }while (flag != 'n');
        
        
        */
       
       for(int x = 0; x<=4; x+=1){//this is to count 0-20 by fives, including 0
           
           for(int y = 0; y<5; y++){//this is to count 1-5
               System.out.print(((x*5)+y + 1) + "\t");
           }
           System.out.println();
       }
       for(int x = 0; x<=4; x+=1){//this is to count 0-20 by fives, including 0
           
           for(int y = 0; y<5; y++){//this is to count 1-5
               System.out.print(x+y+2 + "\t");
           }
           System.out.println();
       }
       
    }
    
    static void output(String info){
        System.out.println(info);
    }
    static void output(char info){
        System.out.println(info);
    }
    static void output(int info){
        System.out.println(info);
    }
    static void output(double info){
        System.out.println(info);
    } 
    static char inputChar(String prompt){
        char result = (char)0;
        try{
            result = input (prompt).charAt(0);
        }
        catch (Exception e){
            result = 0;
        }
        return result;
    }
    static String input (String prompt){
        String iput = null;
        System.out.print(prompt + ": ");
        try{
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            iput = is.readLine();
            if (iput.length() == 0) return null;
        }
        catch (IOException e){
            System.out.println("IO Exception: " + e);
        }
        return iput;
    }
    static double inputDouble(String prompt){
        double result = 0;
        try{
            result=Double.parseDouble (input(prompt));
        }
        catch (Exception e){
            result = 0;
        }
        return result;
    }
    public static int inputInt(String prompt){
        int result = 0;
        try{
            result = Integer.parseInt(input(prompt));
        }
        catch (Exception e){
            result = 0;
        }
        return result;
    }
}
