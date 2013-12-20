public class potHole
{
   

        public static void main(String args[])
    {
        Turtle myTurtle = new Turtle();
        Turtle myT2 = new Turtle();
       
        int i;
        int j;       
        myTurtle.down();
        myT2.down();
        drawCircle(1, myTurtle);
        for (j=0; j<4; j++)
        {
        myTurtle.forward(8); 
            for (i=0; i<4; i++)
            {
            myTurtle.forward(4);
            myTurtle.left(90); 
            myTurtle.forward(8);
            myTurtle.right(180);
            myTurtle.forward(8);
            myTurtle.left(90);
            myTurtle.forward(8);   
            }
        myTurtle.forward(8); 
        myTurtle.left(90);
       }
            
        //Circle program will be inserted here. 
    }
    public static void drawCircle(int radius, Turtle t){
    
        for(int x = 0; x <90; x++){
            t.forward(radius + .01);
            t.left(4);
        
        }
    
    }
}

