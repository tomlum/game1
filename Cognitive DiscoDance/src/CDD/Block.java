package CDD;
import javalib.worldimages.*;
import javalib.colors.*;

public class Block {
    Posn center;
    int width;
    int height;
    IColor color;
    
    public Block(Posn ce, int w, int h, IColor co){
        this.center = ce;
        this.width = w;
        this.height = h;
        this.color = co;
    }
    
    
    
    public WorldImage drawBlock(){
            return new RectangleImage(this.center, this.width, this.height, this.color);
                    }
    
    
    public boolean outOfBounds(){
            if((center.x > CDD.screenWidth + width)||
                    (center.y > CDD.screenHeight + height)||
                    (center.y < 0 - height)||
                    (center.x < 0 - width)){
                return true;
            }
	
            return false;
        }
    
    
    public Block move(int dir, int speed){
     switch(dir){
        case 1: return new Block(new Posn(center.x+speed,center.y), width, height, color);
        case 2: return new Block(new Posn(center.x,center.y+speed), width, height, color);
        case 3: return new Block(new Posn(center.x-speed,center.y), width, height, color);
        default: return new Block(new Posn(center.x,center.y-speed), width, height, color);
	}   
    }
    
    
    public boolean bufferOutOfBounds(){
            if((center.x > CDD.screenWidth - width/2)||
                    (center.y > CDD.screenHeight - height/2)||
                    (center.y < height/2)||
                    (center.x < width/2)){
                return true;
            }
	
            return false;
        }
}
