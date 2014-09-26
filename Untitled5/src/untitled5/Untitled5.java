
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
    
    static final int screenWidth = 1000;
    static final int screenHeight = 1000;
    //currently changing this won't actually change the number of boxes
    static final int numberOfBoxes = 6;
    Ambi dexter;
    Laser[] theLasers;

    public WorldImage grid = new RectangleImage(new Posn(0, 0), screenWidth*2, screenHeight*2, new Black());
    
    public static Laser randomLaser(String type){
        if(type.equals("down")){return new Laser(new Posn(randomInt(0,Untitled5.screenWidth),0), 2);
        }
        else if(type.equals("any")){return new Laser(new Posn(randomInt(0,Untitled5.screenWidth),randomInt(0,Untitled5.screenHeight)), randomInt(0,3));
        }
        else if(type.equals("reset")){return new Laser(new Posn(0,0),0).reset();
        }
        else 
        return new Laser(new Posn(randomInt(0,Untitled5.screenWidth),randomInt(0,Untitled5.screenHeight)), randomInt(0,3));
        }
    
    
    
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
            if(randomAmbi.tryUnlock(guess).locked == (randomAmbi.code==guess)){
                System.out.println("fix code and lock");
            
        }
        
    }
   }
   
   public static void testMoveLaser(int trials){
        for(int i = 0; i<trials; i++){
            Laser ranLas = randomLaser("any");
            if((ranLas.dir == 0 && ranLas.moveLaser().center.y != ranLas.center.y-Laser.speed)
                    ||(ranLas.dir == 1 && ranLas.moveLaser().center.x != ranLas.center.x+Laser.speed)
                    ||(ranLas.dir == 2 && ranLas.moveLaser().center.y != ranLas.center.y+Laser.speed)
                    ||(ranLas.dir == 3 && ranLas.moveLaser().center.x != ranLas.center.x-Laser.speed)
                    ){
                System.out.println("Error in moveLaser");
            }
        }
        
    }
   
    public static void testLaserBounds(int trials){
        for(int i = 0; i<trials; i++){
            Laser[] ranLazArr = new Laser[] {
                randomLaser("any"),
                randomLaser("any"),
                randomLaser("any"),
                randomLaser("any"),
                randomLaser("any"),
                randomLaser("any")
            };
            for(int j = 0; j<5;j++){
                Laser current = ranLazArr[j];
                Laser next = ranLazArr[j].moveLaser();
            if(current.Bounds()!= next.Bounds()
                    && 
                    !(next.center.x > screenWidth
                    || next.center.x < 0
                    || next.center.y > screenHeight
                    || next.center.y < 0)){
                System.out.println("Error in Bounds");
            }
            }
            
        }
        
    }
    
    static Random rand = new Random();
    public static int randomInt( int min, int max ) {
        return rand.nextInt((max - min) + 1) + min; }
    
    
    
    public Untitled5(Ambi ambi, Laser[] las) {
		super();
		this.dexter = ambi;
                this.theLasers = las;
	}
    
    
    
        public World onTick(){
            for(int i = 0; i<theLasers.length;i++){
            theLasers[i] = theLasers[i].moveLaser();
            }
            theLasers = Laser.checkForReset(theLasers);
            return this;
        }
        
        public World onKeyEvent(String ke) {
            return new Untitled5(this.dexter.moveAmbi(ke), this.theLasers);
	}
        
        public WorldImage makeImage(){
		return new OverlayImages(this.grid, 
                new OverlayImages(this.dexter.ambiImage(),
                Laser.lasersImage(theLasers, 5))); 
	}
    
 
        
    
    
    

    public static void main(String[] args) {
        testBounds(500);
        testCodeAndLocked(500);
        testMoveLaser(500);
        testLaserBounds(500);
        Untitled5 w = new Untitled5(new Ambi(new Posn(500, 500),"up", true),
        new Laser[]{
        new Laser(new Posn(randomInt(0,Untitled5.screenWidth),0), 2),
        new Laser(new Posn(randomInt(0,Untitled5.screenWidth),-20), 2),
        new Laser(new Posn(randomInt(0,Untitled5.screenWidth),-40), 2),
        new Laser(new Posn(randomInt(0,Untitled5.screenWidth),-60), 2),
        new Laser(new Posn(randomInt(0,Untitled5.screenWidth),-80), 2),
        new Laser(new Posn(randomInt(0,Untitled5.screenWidth),-100), 2)
        });
        w.bigBang(screenWidth, screenHeight, 0.3);
        
    }
    
}
