
package untitled5;
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import java.awt.Color;
import java.util.Random;

public class Block {
    public Posn center;
    static int width;
    static int height;
    int dir;
    int speed = 50;
    
    public Block(Posn center, int dir){
        this.center = center;
    }
    
    public Block move(){
        switch(dir){
        case 1: return new Block(new Posn(center.x+speed,center.y),dir);
        case 2: return new Block(new Posn(center.x,center.y+speed),dir);
        case 3: return new Block(new Posn(center.x-speed,center.y),dir);
        default: return new Block(new Posn(center.x,center.y-speed),dir);
    } 
    }
}
