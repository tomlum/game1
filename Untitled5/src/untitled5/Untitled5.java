
package untitled5;
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldimages.*;

public class Untitled5 extends World{
    
    static final int screenWidth = 1000;
    static final int screenHeight = 700;
    //currently changing this won't actually change the number of boxes
    static final int numberOfLasers = 6;
    int time = 0;
    static int laserSpeedIncRate = 20;
    int laserSpeed = Laser.initSpeed;
    boolean gameOver = false;
    
            
            
    Ambi dexter;
    Laser[] theLasers = new Laser[numberOfLasers]; 
    DeceptiBot gigatron;
    static public WorldImage back = new RectangleImage(new Posn(screenWidth/2, screenHeight/2), screenWidth, Untitled5.screenHeight, new Blue());
    
    
    
    public Untitled5(Ambi ambi, Laser[] las, DeceptiBot dec, int time, int laserSpeed) {
		super();
		this.dexter = ambi;
                this.theLasers = las;
                this.time = time;
                this.laserSpeed = laserSpeed;
                this.gigatron = dec;
	}
    
    
    
        public Untitled5 onTick(){
            int newLaserSpeed = this.laserSpeed;
            if(time%laserSpeedIncRate==laserSpeedIncRate-1){
            newLaserSpeed = this.laserSpeed + 1;
            }
            return new Untitled5(dexter, Laser.arrayCheckAndReset(Laser.moveLasArr(theLasers), laserSpeed), gigatron, time+1, newLaserSpeed);
            
        
        }
        
        public WorldEnd worldEnds(){
            if(dexter.arrayCollisionCheck(theLasers)){
                gameOver = true;
                return new WorldEnd(true, new OverlayImages(this.makeImage(),
                    new TextImage(new Posn(screenWidth/2,screenHeight/2), "Game Over",30, new White())));
            }
            else return new WorldEnd(false, this.makeImage());
        }
        
        
        public Untitled5 onKeyEvent(String ke) {
            return new Untitled5(this.dexter.controlAmbi(ke), this.theLasers, new DeceptiBot(this.gigatron.moveAmbi(Test.randomInt(0, 3), false)), this.time, this.laserSpeed);
	}
        
        public WorldImage makeImage(){
            if(gameOver){
		return new OverlayImages(back, 
                new OverlayImages(lasersImage(theLasers, 5), new OverlayImages(this.dexter.ambiImage(),
                new OverlayImages((gigatron.deceptiImage(true)),
                new TextImage(new Posn(100,40), "Score is" + " " + time,30, new Blue()))))); 
        }
            else return new OverlayImages(back, 
                new OverlayImages((gigatron.deceptiImage(false)), new OverlayImages(this.dexter.ambiImage(),
                new OverlayImages(lasersImage(theLasers, 5),
                new TextImage(new Posn(100,40), "Score is" + " " + time,30, new Blue()))))); 
        }
        
        public WorldImage lasersImage(Laser[] currentArray, int i){
            Laser drawThis = currentArray[i];
        if(i==0){
        return drawThis.drawBlock();
        }
        return new OverlayImages(drawThis.drawBlock(), lasersImage(theLasers,i-1));       
        
    }
    
        
    
    

    public static void main(String[] args) {
        Test.testBoundsandMove(500,10);
        Test.testCodeAndLocked(500);
        Test.testMoveLaser(500);
        Test.testLaserBounds(500);
        Test.testCollisionAndLaserMovement(500);
        Test.testRandomButton(300);
        Test.testWholeGame(100,100);
        Untitled5 w = new Untitled5(new Ambi(new Posn(screenWidth/2, screenHeight/2),"up", true),
        Laser.arrayCheckAndReset(new Laser[numberOfLasers], Laser.initSpeed), new DeceptiBot(Test.randomPosn()), 0, Laser.initSpeed);
        w.bigBang(screenWidth, screenHeight, 0.3);
        
    }
    
}