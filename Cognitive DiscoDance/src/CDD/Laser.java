package CDD;

import javalib.colors.*;
import javalib.worldimages.*;

public class Laser extends Block {
    static int width = 50;
    static int height = 100;
    static IColor col = new Green();
    static int initSpeed = 40;
    //0 is up, 1 is right, 2 is down, 3 is left
    int dir;
    int speed = initSpeed;
    
    
    Laser(Posn center, int dir, int speed) {
        super(center, width, height, new Green());
                this.dir = dir;
                this.speed = speed;
	}
    
  
    
    public Laser moveLaser() {
                    return new Laser(this.move(dir, speed).center, dir, speed);
	}
    
    
    public static Laser reset(int speed) {
        int whereToSet = Test.randomInt(0, 3);
        switch(whereToSet){
        case 1: return new Laser(new Posn(0,Test.randomInt(0,CDD.screenHeight)), 1, speed);
        case 2: return new Laser(new Posn(Test.randomInt(0,CDD.screenWidth),0), 2, speed);
        case 3: return new Laser(new Posn(CDD.screenWidth,Test.randomInt(0,CDD.screenHeight)), 3, speed);
        default: return new Laser(new Posn(Test.randomInt(0,CDD.screenWidth),CDD.screenHeight), 0, speed);
	}
    }
    
    
    public static Laser[] arrMoveCheckReset(Laser[] currentArray, int speed) {
        Laser[] newArray = new Laser[currentArray.length];
        //marks lasers to be removed in the add function
		for(int i = 0; i < currentArray.length; i++){
                    try{
                        newArray[i] = currentArray[i].moveLaser();
                        
                        if(currentArray[i].outOfBounds()){
                    newArray[i] = reset(speed);
                    }}
                    catch(NullPointerException noLaserYet){
                            newArray[i] = reset(speed);
                            }
                }
                return newArray;
	}
}