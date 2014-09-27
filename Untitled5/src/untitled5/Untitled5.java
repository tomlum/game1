
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
    
    
    public static Ambi randomAmbi(){
        return new Ambi(new Posn(randomInt(0,screenWidth),randomInt(0,screenHeight)), false);
    }

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
    
    
    
   public static void testBoundsandMove(int trials,int paces){
        for(int i = 0; i<trials; i++){
            Ambi randomAmbiA = new Ambi(new Posn(randomInt(Ambi.horizBuffer,screenWidth-Ambi.horizBuffer),randomInt(Ambi.vertBuffer,screenHeight-Ambi.vertBuffer)), false);
            int[] randomPaces = new int[paces];
            for(int j = 0; j < paces; j++){
                randomPaces[j] = randomInt(0,3);
            }
            Ambi randomAmbiB = randomAmbiA;
            for(int j = 0; j < paces; j++){
                randomAmbiB = randomAmbiB.moveAmbi(randomPaces[j], false);
            }
            if(randomAmbiB.outOfBounds() != 
                    (randomAmbiB.center.x>screenWidth-Ambi.horizBuffer||
                    randomAmbiB.center.x<Ambi.horizBuffer||
                    randomAmbiB.center.y>screenHeight - Ambi.vertBuffer||
                    randomAmbiB.center.y<Ambi.vertBuffer)){
                System.out.println("fix Bounds and Move");
            }
            
        }
        
    }
   
   public static void testCodeAndLocked(int trials){
        for(int i = 0; i<trials; i++){
            Ambi randomAmbi = new Ambi(new Posn(randomInt(0,screenWidth),randomInt(0,screenWidth)), true);
            String guess = Ambi.newCode();
            if(!randomAmbi.tryUnlock(guess) == (randomAmbi.code==guess)){
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
            if(current.outOfBounds()!= next.outOfBounds()
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
            if(dexter.collisionCheck()){
                gameOver = true;
            }
            
            
            if(time%laserSpeedIncRate==0){
            Laser.speed=Laser.speed+1;
            }
            
            if(!gameOver){
                time++;
            for(int i = 0; i<theLasers.length;i++){
            theLasers[i] = theLasers[i].moveLaser();
            }
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
		return new OverlayImages(this.grid, 
                new OverlayImages(this.dexter.ambiImage(),
                new OverlayImages(Laser.lasersImage(theLasers, 5),
                new TextImage(new Posn(100,40), "Score is" + " " + time,30, new Blue())))); 
	}
            return new OverlayImages(this.grid, 
                new OverlayImages(this.dexter.ambiImage(),
                new OverlayImages(Laser.lasersImage(theLasers, 5),
                new OverlayImages(new TextImage(new Posn(screenWidth/2,screenHeight/2), "Game Over",30, new White()),
                new TextImage(new Posn(100,40), "Score is" + " " + time,30, new Blue()))))); 
        }
    
 
        
    
    
    

    public static void main(String[] args) {
        testBoundsandMove(500,10);
        testCodeAndLocked(500);
        testMoveLaser(500);
        testLaserBounds(500);
        for(int i = 0; i <theLasers.length; i++){
            theLasers[i] = Laser.reset();
        }
        Untitled5 w = new Untitled5(new Ambi(new Posn(screenWidth/2, screenHeight/2),"up", true),
        theLasers);
        w.bigBang(screenWidth, screenHeight, 0.3);
        
    }
    
}