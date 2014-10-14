package untitled5;
import javalib.worldimages.*;
import javalib.colors.*;
import static untitled5.Ambi.width;

public class Block {
    Posn center;
    int width;
    int height;
    static IColor col;
    int dir;
    
    public Block(Posn ce, int w, int h, IColor co){
        this.center = ce;
        this.width = w;
        this.height = h;
        this.col = co;
    }
    
    public WorldImage drawBlock(){
            return new RectangleImage(this.center, this.width, this.height, this.col);
                    }
    
    
    public boolean outOfBounds(){
            if((center.x > Untitled5.screenWidth + width)||
                    (center.y > Untitled5.screenHeight + height)||
                    (center.y < 0 - height)||
                    (center.x < 0 - width)){
                return true;
            }
	
            return false;
        }
    
    public boolean bufferOutOfBounds(){
            if((center.x > Untitled5.screenWidth - width/2)||
                    (center.y > Untitled5.screenHeight - height/2)||
                    (center.y < height/2)||
                    (center.x < width/2)){
                return true;
            }
	
            return false;
        }
}
