package untitled5;

import javalib.colors.*;
import javalib.worldimages.*;

public class Laser extends Block {
    static int width = 50;
    static int height = 100;
    //0 is up, 1 is right, 2 is down, 3 is left
    int dir;
    boolean resetMe;
    final static int initSpeed = 40;
    int speed = initSpeed;
    static IColor col = new Green();
    
    Laser(Posn center, int dir, int speed) {
        super(center, width, height, new Green());
                this.dir = dir;
                resetMe = false;
                this.speed = speed;
	}
    
    Laser(Block block, int dir, int speed) {
        super(block.center, width, height, new Green());
                this.dir = dir;
                resetMe = false;
                this.speed = speed;
	}
    
    
    
    
    public Laser moveLaser() {
        
                    return new Laser(this.move(dir, speed), dir, speed);
	}
    public static Laser[] moveLasArr(Laser[] theArr){
        Laser[] newArr = new Laser[theArr.length];
        for(int i = 0; i<theArr.length;i++){
            newArr[i] = theArr[i].moveLaser();
            }
        return newArr;
    }
    
    
    
    
    
    public static Laser reset(int speed) {
        int whereToSet = Test.randomInt(0, 3);
        switch(whereToSet){
        case 1: return new Laser(new Posn(0,Test.randomInt(0,Untitled5.screenHeight)), 1, speed);
        case 2: return new Laser(new Posn(Test.randomInt(0,Untitled5.screenWidth),0), 2, speed);
        case 3: return new Laser(new Posn(Untitled5.screenWidth,Test.randomInt(0,Untitled5.screenHeight)), 3, speed);
        default: return new Laser(new Posn(Test.randomInt(0,Untitled5.screenWidth),Untitled5.screenHeight), 0, speed);
	}
    }
    
    

    
    public static Laser[] arrayCheckAndReset(Laser[] currentArray, int speed) {
        Laser[] newArray = new Laser[currentArray.length];
        //marks lasers to be removed in the add function
		for(int i = 0; i < currentArray.length; i++){
                    newArray = currentArray;
                    try{if(currentArray[i].outOfBounds()){
                    newArray[i] = reset(speed);
                    }}
                    catch(NullPointerException bob){
                            newArray[i] = reset(speed);
                            }
                }
                return newArray;
	}
    
}