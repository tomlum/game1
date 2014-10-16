package CDD;

import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;
import javalib.colors.*;
import javalib.worldimages.TextImage;


public class DeceptiBot extends Ambi {
        static int width = Ambi.width-7;
        static int height = Ambi.height-7;
    
        
        
    public DeceptiBot(Posn ce){
        super(ce, Test.randomButton(), false);
    }
    
    
    
    WorldImage deceptiImage(boolean reveal){
        if (reveal){
            return new OverlayImages(
                    new Block(this.center, width, height, new Red()).drawBlock(),
                    new TextImage(this.center, "MWAHAHAHA",20, new Green()));
        }
        return new OverlayImages(
                    new Block(this.center, width, height, new Green()).drawBlock(),
                    ambiArrow());
    }
}
