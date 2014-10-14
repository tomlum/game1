
package untitled5;
import javalib.worldimages.*;
import java.util.Random;
//high score is 177 :D

public class Test {
    
    static Random rand = new Random();
    public static int randomInt( int min, int max ) {
        return rand.nextInt((max - min) + 1) + min; }
    
    public static String randomButton(){
        int ran = randomInt(0,7);
        if (ran == 0){ return "up";}
        switch(ran){
        case 1: return "right";
        case 2: return "down";
        case 3: return "left";
        case 4: return "w";
        case 5: return "d";
        case 6: return "s";
        case 7: return "a";
        default : return "ERROR";
        }
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
    
    public static Posn randomPosn(){
        return new Posn(randomInt(0,Untitled5.screenWidth),randomInt(0,Untitled5.screenHeight));
    }
    
     public static Posn randomAmbiPosn(){
        return new Posn(randomInt(Ambi.width/2,Untitled5.screenWidth-Ambi.width/2),randomInt(Ambi.height/2,Untitled5.screenHeight- Ambi.height/2));
    }
    
    public static Ambi randomAmbi(){
        return new Ambi(randomPosn(), false);
    }

   
    public static Laser randomLaser(String type){
        switch(type){
            case "down" : return new Laser(new Posn(randomInt(0,Untitled5.screenWidth),0), 2, Laser.initSpeed);
        
            case "any" : return new Laser(new Posn(randomInt(0,Untitled5.screenWidth),randomInt(0,Untitled5.screenHeight)), randomInt(0,3), Laser.initSpeed);
        
            case "reset": return Laser.reset(Laser.initSpeed);
                
            default: return new Laser(new Posn(randomInt(0,Untitled5.screenWidth),randomInt(0,Untitled5.screenHeight)), randomInt(0,3), Laser.initSpeed);
        }
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
            Ambi randomAmbiA = new Ambi(new Posn(randomInt(Ambi.width/2,Untitled5.screenWidth-Ambi.height/2),randomInt(Ambi.height/2,Untitled5.screenHeight-Ambi.height/2)), false);
            int[] randomPaces = new int[paces];
            for(int j = 0; j < paces; j++){
                randomPaces[j] = randomInt(0,3);
            }
            Ambi randomAmbiB = randomAmbiA;
            for(int j = 0; j < paces; j++){
                randomAmbiB = randomAmbiB.moveAmbi(randomPaces[j], false);
            }
            if(randomAmbiB.bufferOutOfBounds() != 
                    (randomAmbiB.center.x>Untitled5.screenWidth-Ambi.width/2||
                    randomAmbiB.center.x<Ambi.width/2||
                    randomAmbiB.center.y>Untitled5.screenHeight - Ambi.height/2||
                    randomAmbiB.center.y<Ambi.height/2)){
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
               testLaser = testLaser.moveLaser();
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
            Ambi randomAmbi = new Ambi(randomAmbiPosn(), true);
            String guess = Ambi.newCode();
            if(!randomAmbi.tryUnlock(guess) == (randomAmbi.code.equals(guess))){
                System.out.println("fix code and lock");
            
        }
        
    }
   }
   
   public static void testMoveLaser(int trials){
        for(int i = 0; i<trials; i++){
            Laser ranLas = randomLaser("any");
            if((ranLas.dir == 0 && ranLas.moveLaser().center.y != ranLas.center.y-ranLas.speed)
                    ||(ranLas.dir == 1 && ranLas.moveLaser().center.x != ranLas.center.x+ranLas.speed)
                    ||(ranLas.dir == 2 && ranLas.moveLaser().center.y != ranLas.center.y+ranLas.speed)
                    ||(ranLas.dir == 3 && ranLas.moveLaser().center.x != ranLas.center.x-ranLas.speed)
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
            int moves = Test.randomInt(0, 20);
            for(int j = 0; j<5;j++){
                for(int k = 0; k < moves; k++){
                Laser current = ranLazArr[j];
                Laser next = ranLazArr[j].moveLaser();
                
            if(current.outOfBounds()!= next.outOfBounds()
                    && 
                    !(next.center.x > Untitled5.screenWidth + Laser.width
                    || next.center.x < 0 - Laser.width
                    || next.center.y > Untitled5.screenHeight + Laser.height
                    || next.center.y < 0 - Laser.height)){
                System.out.println("Error in Laser Bounds");
            }
                }
            }
            
        }
        
    }
    
    public static void testWholeGame(int reps, int moves){
        //fishy1 is true if all reps end with Dexter in the center, is possible
        //but unlikely
            boolean fishy1 = true;
            boolean fishy2 = true;
            boolean fishy3 = true;
            boolean fishy4 = true;
            int lastscore1 = 0;
            int lastspeed1 = 0;
            for(int i = 0; i < reps; i++){
                
            Untitled5 testWorld = new Untitled5(new Ambi(new Posn(Untitled5.screenWidth/2, Untitled5.screenHeight/2),"up", true),
        Laser.arrayCheckAndReset(new Laser[Untitled5.numberOfLasers], Laser.initSpeed), new DeceptiBot(randomAmbiPosn()), 0, Laser.initSpeed);
            
            for(int j = moves; j > 0; j--){
                if(!testWorld.gameOver){
            Posn initDeceptPosn = testWorld.gigatron.center;
            testWorld = testWorld.onKeyEvent(Test.randomButton());
            testWorld = testWorld.onTick();
            
                    if(testWorld.dexter.arrayCollisionCheck(testWorld.theLasers)){
                    testWorld.gameOver = true;
                    if(testWorld.gigatron.center.x != initDeceptPosn.x||
                       testWorld.gigatron.center.y != initDeceptPosn.y
                    ){
                        fishy4 = false;
                    }
                    }
                }
                
                //checks if out of bounds
                if(testWorld.dexter.outOfBounds()){
                System.out.println("somehow got out of bounds");
                }
            }
           if(i==0){
               lastscore1 = testWorld.time;
           }
           if(i==0){
               lastspeed1 = testWorld.laserSpeed;
           }
           if (lastscore1!=testWorld.time){
                fishy2 = false;
            }
           
           if (lastspeed1!=testWorld.laserSpeed){
                fishy3 = false;
            }
           
            //Prints out the scores of the random test, very cool to see!!!
           //System.out.println(testWorld.time);
           //System.out.println(testWorld.laserSpeed);
            
            if(testWorld.gameOver && !testWorld.dexter.arrayCollisionCheck(testWorld.theLasers)){
                System.out.println("array Collision Check and/or gameOver aren't working");
            }
            if(testWorld.dexter.center.x != Untitled5.screenWidth/2 
                    && testWorld.dexter.center.y != Untitled5.screenHeight/2){
                fishy1 = false;
            }
            
            
            
            
            }
            //it's possibly for these to print, but
            //if these are consistantly returning, that will be a problem
            if(fishy1){
                System.out.println("Unlikely that all would be in the middle... May have Error in Move");
            }
            if(fishy2){
                System.out.println("Unlikely that all have the same score...");
            }
            if(fishy3){
                System.out.println("Unlikely that all lasers would have the same speed...");
            }
            if(fishy3){
                System.out.println("Unlikely that Deceptibot wouldn't move at all...");
            }
                
        }
    
    
    
    
}
