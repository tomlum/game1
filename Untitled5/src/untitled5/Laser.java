package untitled5;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import java.awt.Color;
import java.util.Random;

public class Laser {
    Posn center;
    static int width = 50;
    static int height = 100;
    //0 is up, 1 is right, 2 is down, 3 is left
    int dir;
    boolean resetMe;
    final static int initSpeed = 40;
    int speed = initSpeed;
    static IColor laserColor = new Green();
    
    Laser(Posn center, int dir, int speed) {
		this.center = center;
                this.dir = dir;
                resetMe = false;
                this.speed = speed;
                    
	}
    
    
    
    
    
    public Laser move() {
                    switch(dir){
            case 1:  return new Laser(new Posn(center.x+speed,center.y), dir, this.speed);
            case 2:  return new Laser(new Posn(center.x,center.y+speed), dir, this.speed);
            case 3:  return new Laser(new Posn(center.x-speed,center.y), dir, this.speed);
            default: return new Laser(new Posn(center.x,center.y-speed), dir, this.speed);
                    }
	}
    public static Laser[] moveLasArr(Laser[] theArr){
        Laser[] newArr = new Laser[theArr.length];
        for(int i = 0; i<theArr.length;i++){
            newArr[i] = theArr[i].move();
            }
        return newArr;
    }
    
    public static WorldImage lasersImage(Laser[] currentArray, int i){
        
   
            Laser drawThis = currentArray[i];
        if(i==0){
        return new RectangleImage(drawThis.center, drawThis.width, drawThis.height, laserColor);
        }
        return new OverlayImages(new RectangleImage(drawThis.center, drawThis.width, drawThis.height, laserColor), lasersImage(currentArray,i-1));       
        
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
    
    public boolean outOfBounds() {
        if((this.center.x<0-width&&this.dir==3)
                ||(this.center.x>Untitled5.screenWidth+width&&this.dir==1)
                ||(this.center.y<0-height&&this.dir==0)
                ||(this.center.y>Untitled5.screenHeight+height&&this.dir==2)){
            return true;
        }
        return false;
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