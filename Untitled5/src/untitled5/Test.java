
package untitled5;
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import java.awt.Color;
import java.util.Random;
import static untitled5.Untitled5.screenWidth;


public class Test {
    
    static Random rand = new Random();
    public static int randomInt( int min, int max ) {
        return rand.nextInt((max - min) + 1) + min; }
    
    public static String randomButton() throws Error{
        int ran = randomInt(0,7);
        if (ran == 0){ return "up";}
        else if (ran == 1){ return "right";}
        else if (ran == 2){ return "down";}
        else if (ran == 3){ return "left";}
        else if (ran == 4){ return "w";}
        else if (ran == 5){ return "d";}
        else if (ran == 6){ return "s";}
        else if (ran == 7){ return "a";}
        else throw new Error("Out of bounds error in randomButton");
    }
    
    public static void testRandomButton(int n){
        
        String init = randomButton();
        boolean fishy = true;
        for(int i = 0; i<n; i++){
            if(!randomButton().equals(init)){
                fishy = false;
            }
        }
        if(fishy){
            System.out.println("Check Random Button");
        }
    }
    
    public static Ambi randomAmbi(){
        return new Ambi(new Posn(randomInt(0,screenWidth),randomInt(0,Untitled5.screenHeight)), false);
    }

   
    public static Laser randomLaser(String type){
        if(type.equals("down")){return new Laser(new Posn(randomInt(0,Untitled5.screenWidth),0), 2, Laser.initSpeed);
        }
        else if(type.equals("any")){return new Laser(new Posn(randomInt(0,Untitled5.screenWidth),randomInt(0,Untitled5.screenHeight)), randomInt(0,3), Laser.initSpeed);
        }
        else if(type.equals("reset")){return Laser.reset(Laser.initSpeed);
        }
        else 
        return new Laser(new Posn(randomInt(0,Untitled5.screenWidth),randomInt(0,Untitled5.screenHeight)), randomInt(0,3), Laser.initSpeed);
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
           Laser testLaser = Laser.reset(Laser.initSpeed);
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
            if((ranLas.dir == 0 && ranLas.move().center.y != ranLas.center.y-ranLas.speed)
                    ||(ranLas.dir == 1 && ranLas.move().center.x != ranLas.center.x+ranLas.speed)
                    ||(ranLas.dir == 2 && ranLas.move().center.y != ranLas.center.y+ranLas.speed)
                    ||(ranLas.dir == 3 && ranLas.move().center.x != ranLas.center.x-ranLas.speed)
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
    
    public static void testWholeGame(int reps, int moves){
        //fishy1 is true if all reps end with Dexter in the center, is possible
        //but unlikely
            boolean fishy1 = true;
            for(int i = 0; i < reps; i++){
            Untitled5 testWorld = new Untitled5(new Ambi(new Posn(Untitled5.screenWidth/2, Untitled5.screenHeight/2),"up", true),
        Laser.checkForReset(new Laser[Untitled5.numberOfLasers], Laser.initSpeed), 0, Laser.initSpeed);
            for(int j = moves; j > 0; j--){
            testWorld = testWorld.onTick();
            testWorld = testWorld.onKeyEvent(Test.randomButton());
            if(testWorld.dexter.outOfBounds()){
                System.out.println("somehow got out of bounds");
            }
        }
            if(testWorld.gameOver && !testWorld.dexter.arrayCollisionCheck(testWorld.theLasers)){
                System.out.println("array Collision Check and/or gameOver aren't working");
            }
            if(testWorld.dexter.center.x != Untitled5.screenWidth/2 
                    && testWorld.dexter.center.y != Untitled5.screenHeight/2){
                fishy1 = false;
            }
            }
            if(fishy1){
                System.out.println("Unlikely that all would be in the middle... May have Error in Move");
            }
                
        }
    
    
    
    
}
