
package untitled5;
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import java.awt.Color;
import java.util.Random;

//as time goes on, speed increases

public class Untitled5 extends World{
    
    static final int screenWidth = 1000;
    static final int screenHeight = 700;
    //currently changing this won't actually change the number of boxes
    static final int numberOfLasers = 6;
    static boolean gameOver = false;
    static int time = 0;
    static int laserSpeedIncRate = 20;
            
            
    Ambi dexter;
    static Laser[] theLasers = new Laser[numberOfLasers]; 
    public WorldImage back = new RectangleImage(new Posn(0, 0), screenWidth*2, Untitled5.screenHeight*2, new Black());
    
    
    
    
    
    
    public Untitled5(Ambi ambi, Laser[] las) {
		super();
		this.dexter = ambi;
                this.theLasers = las;
	}
    
    
    
        public World onTick(){
            if(dexter.arrayCollisionCheck(theLasers)){
                gameOver = true;
            }
            
            
            if(time%laserSpeedIncRate==0){
            Laser.speed=Laser.speed+1;
            }
            
            if(!gameOver){
                time++;
            theLasers = Laser.moveLasArr(theLasers);
            theLasers = Laser.checkForReset(theLasers);
            }
            return this;
        
        }
        
        
        public World onKeyEvent(String ke) {
            if(!gameOver){
            return new Untitled5(this.dexter.controlAmbi(ke), this.theLasers);
            }
            return this;
	}
        
        public WorldImage makeImage(){
            if(!gameOver){
		return new OverlayImages(this.back, 
                new OverlayImages(this.dexter.ambiImage(),
                new OverlayImages(Laser.lasersImage(theLasers, 5),
                new TextImage(new Posn(100,40), "Score is" + " " + time,30, new Blue())))); 
	}
            return new OverlayImages(this.back, 
                new OverlayImages(this.dexter.ambiImage(),
                new OverlayImages(Laser.lasersImage(theLasers, 5),
                new OverlayImages(new TextImage(new Posn(screenWidth/2,screenHeight/2), "Game Over",30, new White()),
                new TextImage(new Posn(100,40), "Score is" + " " + time,30, new Blue()))))); 
        }
    
 
        
    
    
    

    public static void main(String[] args) {
        Test.testBoundsandMove(500,10);
        Test.testCodeAndLocked(500);
        Test.testMoveLaser(500);
        Test.testLaserBounds(500);
        Test.testCollisionAndLaserMovement(500);
        
        for(int i = 0; i <theLasers.length; i++){
            theLasers[i] = Laser.reset();
        }
        Untitled5 w = new Untitled5(new Ambi(new Posn(screenWidth/2, screenHeight/2),"up", true),
        theLasers);
        w.bigBang(screenWidth, screenHeight, 0.3);
        
    }
    
}