
package untitled5;
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import java.awt.Color;
import java.util.Random;
import static untitled5.Untitled5.screenWidth;


public class Test {
    
    
    public static Ambi randomAmbi(){
        return new Ambi(new Posn(randomInt(0,screenWidth),randomInt(0,Untitled5.screenHeight)), false);
    }

   
    public static Laser randomLaser(String type){
        if(type.equals("down")){return new Laser(new Posn(randomInt(0,Untitled5.screenWidth),0), 2);
        }
        else if(type.equals("any")){return new Laser(new Posn(randomInt(0,Untitled5.screenWidth),randomInt(0,Untitled5.screenHeight)), randomInt(0,3));
        }
        else if(type.equals("reset")){return Laser.reset();
        }
        else 
        return new Laser(new Posn(randomInt(0,Untitled5.screenWidth),randomInt(0,Untitled5.screenHeight)), randomInt(0,3));
        }
    
    
    public static Laser[] randomNewLaserSet(int num){
        Laser[] newSet = new Laser[num];
        for(int i = 0; i < num; i++){
            newSet[num] = randomLaser("reset");
        }
        return newSet;
        }
    
    
    
   public static void testBoundsandMove(int trials,int paces){
        for(int i = 0; i<trials; i++){
            Ambi randomAmbiA = new Ambi(new Posn(randomInt(Ambi.horizBuffer,screenWidth-Ambi.horizBuffer),randomInt(Ambi.vertBuffer,Untitled5.screenHeight-Ambi.vertBuffer)), false);
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
                    randomAmbiB.center.y>Untitled5.screenHeight - Ambi.vertBuffer||
                    randomAmbiB.center.y<Ambi.vertBuffer)){
                System.out.println("fix Bounds and Move");
            }
            
        }
        
    }
   
   //constructs an Ambi surrounded by 4 other Ambis.  If the laser hits the
   //center Ambi, then it must have hit one of the other Ambis
   public static void testCollisionAndLaserMovement(int trials){
      
       for(int i = 0; i<trials; i++){
           Ambi testAmbiCenter = randomAmbi();
           Ambi testAmbiUp = new Ambi(new Posn(testAmbiCenter.center.x, testAmbiCenter.center.y-Ambi.height), "up", false);
           Ambi testAmbiDown = new Ambi(new Posn(testAmbiCenter.center.x, testAmbiCenter.center.y+Ambi.height), "down", false);
           Ambi testAmbiLeft = new Ambi(new Posn(testAmbiCenter.center.x-Ambi.width, testAmbiCenter.center.y), "left", false);
           Ambi testAmbiRight = new Ambi(new Posn(testAmbiCenter.center.x+Ambi.width, testAmbiCenter.center.y), "right", false);
           Laser testLaser = Laser.reset();
           while(testLaser.outOfBounds()){
               testLaser = testLaser.move();
               if(testAmbiCenter.collisionCheck(testLaser)&&!(
                       testAmbiDown.collisionCheck(testLaser)||
                       testAmbiLeft.collisionCheck(testLaser)||
                       testAmbiRight.collisionCheck(testLaser)
                       )){
                   System.out.println("fix Collision/Laser Movement");
               }
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
            if((ranLas.dir == 0 && ranLas.move().center.y != ranLas.center.y-Laser.speed)
                    ||(ranLas.dir == 1 && ranLas.move().center.x != ranLas.center.x+Laser.speed)
                    ||(ranLas.dir == 2 && ranLas.move().center.y != ranLas.center.y+Laser.speed)
                    ||(ranLas.dir == 3 && ranLas.move().center.x != ranLas.center.x-Laser.speed)
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
                Laser next = ranLazArr[j].move();
            if(current.outOfBounds()!= next.outOfBounds()
                    && 
                    !(next.center.x > screenWidth
                    || next.center.x < 0
                    || next.center.y > Untitled5.screenHeight
                    || next.center.y < 0)){
                System.out.println("Error in Bounds");
            }
            }
            
        }
        
    }
    
    static Random rand = new Random();
    public static int randomInt( int min, int max ) {
        return rand.nextInt((max - min) + 1) + min; }
    
    
}
