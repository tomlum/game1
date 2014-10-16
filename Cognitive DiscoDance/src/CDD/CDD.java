
package CDD;
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldimages.*;

public class CDD extends World{
    
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
    static public WorldImage back = new RectangleImage(new Posn(screenWidth/2, screenHeight/2), screenWidth, CDD.screenHeight, new Blue());
    
    
    public CDD(Ambi ambi, Laser[] las, DeceptiBot dec, int time, int laserSpeed) {
		super();
		this.dexter = ambi;
                this.theLasers = las;
                this.time = time;
                this.laserSpeed = laserSpeed;
                this.gigatron = dec;
	}
    
    
    
    public CDD onTick(){
            int newLaserSpeed = this.laserSpeed;
            if(time%laserSpeedIncRate==laserSpeedIncRate-1){
            newLaserSpeed = this.laserSpeed + 1;
            }
            return new CDD(dexter, Laser.arrMoveCheckReset(theLasers, laserSpeed), gigatron, time+1, newLaserSpeed);
        }
        
    
    public WorldEnd worldEnds(){
            if(dexter.arrayCollisionCheck(theLasers)){
                gameOver = true;
                return new WorldEnd(true, new OverlayImages(this.makeImage(),
                    new TextImage(new Posn(screenWidth/2,screenHeight/2), "Game Over",35, new Black())));
            }
            else return new WorldEnd(false, this.makeImage());
        }
        
        
    public CDD onKeyEvent(String ke) {
            return new CDD(this.dexter.controlAmbi(ke), this.theLasers, new DeceptiBot(this.gigatron.moveAmbi(Test.randomInt(0, 3), false).center), this.time, this.laserSpeed);
	}
    
        
    public WorldImage makeImage(){
            
		return new OverlayImages(back, 
                new OverlayImages(lasersImage(), new OverlayImages(this.dexter.ambiImage(),
                new OverlayImages((gigatron.deceptiImage(gameOver)),
                new TextImage(new Posn(100,40), "Score is" + " " + time,30, new Red()))))); 
        }
        
    
    public WorldImage lasersImage(){
            WorldImage lasArrImage = new RectangleImage(new Posn(0,0), 0, 0, new Blue());
            for(int i = 0; i < theLasers.length; i++){
                lasArrImage = new OverlayImages(theLasers[i].drawBlock(), lasArrImage);
            }
            return lasArrImage;
        }
    
        
    
    
    public static void main(String[] args) {
        Test.testBoundsandMove(500,10);
        Test.testCodeAndLocked(500);
        Test.testMoveLaser(500);
        Test.testLaserBounds(500);
        Test.testCollisionAndLaserMovement(500);
        Test.testRandomButton(300);
        Test.testWholeGame(100,100);
        CDD w = new CDD(new Ambi(new Posn(screenWidth/2, screenHeight/2),"GO!", false),
        Laser.arrMoveCheckReset(new Laser[numberOfLasers], Laser.initSpeed), new DeceptiBot(Test.randomAmbiPosn()), 0, Laser.initSpeed);
        w.bigBang(screenWidth, screenHeight, 0.3);
    }
}