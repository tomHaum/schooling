
/**
 * Write a description of class stupid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class stupid
{
    public static void main(String[] agrs){
        Turtle go = new Turtle();
        
    for(double i =0; i<100;i+= 1){
        go.forward(i*i*(.5));
        go.right(i);
    }
}
}
