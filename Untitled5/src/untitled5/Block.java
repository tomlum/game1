package untitled5;
import javalib.worldimages.*;
import javalib.colors.*;

public class Block {
    Posn center;
    int width;
    int height;
    static IColor col;
    
    public Block(Posn c, int w, int h){
        this.center = c;
    }
    
    public WorldImage drawBlock(){
            return new RectangleImage(this.center, this.width, this.height, this.col);
                    }
}
