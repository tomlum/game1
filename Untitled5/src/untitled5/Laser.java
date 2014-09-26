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
    static int speed = 50;
    static IColor laserColor = new Green();
    
    Laser(Posn center, int dir) {
		this.center = center;
                this.dir = dir;
                resetMe = false;
                    
	}
    
    public Laser moveLaser() {
                    switch(dir){
            case 1:  return new Laser(new Posn(center.x+speed,center.y), dir);
            case 2:  return new Laser(new Posn(center.x,center.y+speed), dir);
            case 3:  return new Laser(new Posn(center.x-speed,center.y), dir);
            default: return new Laser(new Posn(center.x,center.y-speed), dir);
                    }
	}
    
    public static WorldImage lasersImage(Laser[] currentArray, int i){
        
   
            Laser drawThis = currentArray[i];
        if(i==0){
        return new RectangleImage(drawThis.center, drawThis.width, drawThis.height, laserColor);
        }
        return new OverlayImages(new RectangleImage(drawThis.center, drawThis.width, drawThis.height, laserColor), lasersImage(currentArray,i-1));       
        
    }
    
    
    
    public Laser reset() {
        int whereToSet = Untitled5.randomInt(0, 3);
        switch(whereToSet){
        case 1: return new Laser(new Posn(0,Untitled5.randomInt(0,Untitled5.screenHeight)), 1);
        case 2: return new Laser(new Posn(Untitled5.randomInt(0,Untitled5.screenWidth),0), 2);
        case 3: return new Laser(new Posn(Untitled5.screenWidth,Untitled5.randomInt(0,Untitled5.screenHeight)), 3);
        default: return new Laser(new Posn(Untitled5.randomInt(0,Untitled5.screenWidth),Untitled5.screenHeight), 0);
	}
    }
    
    public boolean Bounds() {
        if((this.center.x<0-width&&this.dir==3)
                ||(this.center.x>Untitled5.screenWidth+width&&this.dir==1)
                ||(this.center.y<0-height&&this.dir==0)
                ||(this.center.y>Untitled5.screenHeight+height&&this.dir==2)){
            return true;
        }
        return false;
	}

    
    public static Laser[] checkForReset(Laser[] currentArray) {
        Laser[] newArray = new Laser[currentArray.length];
        //marks lasers to be removed in the add function
		for(int i = 0; i < currentArray.length; i++){
                    newArray = currentArray;
                    if(currentArray[i].Bounds()){
                    newArray[i] = newArray[i].reset();
                    }
                }
                return newArray;
	}
    
}
