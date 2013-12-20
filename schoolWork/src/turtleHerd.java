

public class turtleHerd
{
    public static void main(String[] args){
        Turtle tom = new Turtle();
        Turtle matt = new Turtle();
        matt.fillColor("blue");
        boolean moveT, moveM;
        int sumT = 0, sumM = 0;
        
        tom.up();
        tom.setPosition(tom.getX(), tom.getY()+30);
        tom.down();
        
        for(int i = 0; i < 100; i++){
            moveM = (Math.random()> .8) ? true : false;
            moveT = (Math.random()> .8) ? true : false;
            if(moveT == false){
                tom.forward(20);
                sumT+=20;
            }else{
                tom.forward(40);
                sumT+= 40;
            }
            if(moveM == false){
                matt.forward(20);
                sumM += 20;
            }else{
                matt.forward(40);
                sumM += 40;
            }

        }
        if(sumM > sumT){
            System.out.println("Matt is the winner, he must have cheated");
            dance(matt);
        }else if(sumT > sumM){
            System.out.println("Tom is the winner, its in his genes");
            dance(tom);
        }else
            System.out.println("tie!!!");
        
    }
    static void dance(Turtle myTurtle){
        double turn = 0;   
        for(int i = 0; i<100; i++){
           turn = Math.random() * 90;
           if(Math.random() > .50){
               myTurtle.right(turn);
           }else{
               myTurtle.left(turn);
           }
           
           myTurtle.forward(Math.random() * 20);
        
        }
       
    
    }
}
