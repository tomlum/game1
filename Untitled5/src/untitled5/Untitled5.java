
package untitled5;
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import java.awt.Color;
import java.util.Random;

//I apologize, the names of the classes might not become completely apparent
//until the game is more fully realized!

public class Untitled5 extends World{
    
    static int screenWidth = 1000;
    static int screenHeight = 1000;
    Ambi Dexter;
    public WorldImage grid = new RectangleImage(new Posn(0, 0), screenWidth*2, screenHeight*2, new Black());
    
    
   public static void testBounds(int trials){
        for(int i = 0; i<trials; i++){
            Ambi randomAmbi = new Ambi(new Posn(randomInt(0,screenWidth),randomInt(0,screenWidth)), false);
            if(randomAmbi.horizEdgeCheck(false)&&randomAmbi.center.x<Ambi.horizBuffer){
                System.out.println("fix left bound");
            }
            else if(randomAmbi.horizEdgeCheck(true)&&randomAmbi.center.x>screenWidth-Ambi.horizBuffer){
                System.out.println("fix right bound");
            }
            else if(randomAmbi.vertEdgeCheck(false)&&randomAmbi.center.y>screenHeight-Ambi.vertBuffer){
                System.out.println("fix lower bound");
            }
            else if(randomAmbi.vertEdgeCheck(true)&&randomAmbi.center.y<Ambi.vertBuffer){
                System.out.println("fix upper bound");
            }
        }
        
    }
   
   public static void testCodeAndLocked(int trials){
        for(int i = 0; i<trials; i++){
            Ambi randomAmbi = new Ambi(new Posn(randomInt(0,screenWidth),randomInt(0,screenWidth)), true);
            String guess = Ambi.newCode();
            if(randomAmbi.tryUnlock(guess).locked && randomAmbi.code==guess){
                System.out.println("fix code and lock");
            
        }
        
    }
   }
    
    static Random rand = new Random();
    public static int randomInt( int min, int max ) {
        return rand.nextInt((max - min) + 1) + min; }
    
    
    
    public Untitled5(Ambi ambi) {
		super();
		this.Dexter = ambi;
	}
    
    
    
        public World onTick(){
            return this;
        }
        
        public WorldImage makeImage(){
		return new OverlayImages(this.grid, this.Dexter.ambiImage()); 
	}
    
 
        public World onKeyEvent(String ke) {
	  if (ke.equals("esc"))
	    return this.endOfWorld("Goodbye");
	  else
            return new Untitled5(this.Dexter.moveAmbi(ke));
	}
    
    
    

    public static void main(String[] args) {
        testBounds(500);
        testCodeAndLocked(500);
        Untitled5 w = new Untitled5(new Ambi(new Posn(500, 500),"up", true));
        w.bigBang(screenWidth, screenHeight, 0.3);
        
    }
    
}
