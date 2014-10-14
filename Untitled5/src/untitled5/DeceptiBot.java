package untitled5;

import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;
import javalib.colors.*;
import javalib.worldimages.TextImage;


public class DeceptiBot extends Ambi{
        static int width = Ambi.width-5;
        static int height = Ambi.height-5;
    
    public DeceptiBot(Posn ce){
        super(ce, Test.randomButton(), false);
    }
    public DeceptiBot(Ambi ay){
        super(ay.center, Test.randomButton(), false);
    }
    
    WorldImage deceptiImage(boolean reveal){
        if (reveal){
            return new OverlayImages(
                    new Block(this.center, width, height, new Green()).drawBlock(),
                    new TextImage(this.center, "MWAHAHAHA",20, new Red()));
        }
        return new OverlayImages(
                    new Block(this.center, width, height, new Green()).drawBlock(),
                    ambiArrow());
    }
    
    
}
