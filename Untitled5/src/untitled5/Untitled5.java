
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
    
    static Random rand = new Random();
    public static int randomInt( int min, int max ) {
        return rand.nextInt((max - min) + 1) + min; }
    
    Ambi Dexter;
    public WorldImage grid = new RectangleImage(new Posn(0, 0), 2000, 2000, new Black());
    
    
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
        Untitled5 w = new Untitled5(new Ambi(new Posn(500, 500),"up", true));
        w.bigBang(1000, 1000, 0.3);
        
        
    }
    
}
