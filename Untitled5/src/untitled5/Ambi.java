
package untitled5;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import java.awt.Color;
import java.util.Random;
public class Ambi {
        Posn center;
	int width = 150;
        int height = 50;
	IColor col = new Red();
        String code = "up";
        boolean locked;
	
	Ambi(Posn center, String code, boolean locked) {
		this.center = center;
                this.code = code;
                this.locked = locked;
                    
	}
       
        
        public String newCode(){
            locked = true;
            int ran = Untitled5.randomInt(0,3);
            if(ran == 0){
                return "up";
            }
            else if(ran == 1){
                return "left";
            }
            else if(ran == 2){
                return "right";
            }
            else if(ran == 3){
                return "down";
            }
            return "error";
        }
        
        public void unlock(String ke){
            if(this.code.equals(ke))
                locked = false;
        }
        
        WorldImage ambiImage(){
            return new OverlayImages(
                    new RectangleImage(this.center, this.width, this.height, this.col),
                    ambiArrow());
        }
            
        //returns the apropriate arrow image depending on code
        WorldImage ambiArrow(){
            if(code.equals("up")){
                return new TextImage(this.center, "/\\",20, new Blue());
            }
            else if(code.equals("left")){
                return new TextImage(this.center, "<-",20, new Blue());
            }
            else if(code.equals("right")){
                return new TextImage(this.center, "->",20, new Blue());
            }
            else if(code.equals("down")){
                return new TextImage(this.center, "\\/",20, new Blue());
            }
           return new TextImage(this.center, "error",20, new Blue());
        }
        
        
        WorldImage ambiText(){
            return new TextImage(this.center, "->",5, new Blue());
        }
        
        //tie resetting to the movement keys vs the code keys
        
        public Ambi moveAmbi(String ke){
            
                unlock(ke);
                if(!locked){
		if (ke.equals("d")){
                    newCode();
			return new Ambi(new Posn(this.center.x + 50, this.center.y),
							newCode(), true);
		}
                
		else if (ke.equals("a")){
                    newCode();
			return new Ambi(new Posn(this.center.x - 50, this.center.y),
							newCode(), true);
		}
		else if (ke.equals("w")){
                    newCode();
			return new Ambi(new Posn(this.center.x, this.center.y-50),
							newCode(), true);
		}
		else if (ke.equals("s")){
                    newCode();
			return new Ambi(new Posn(this.center.x, this.center.y+50),
							newCode(), true);
		}
		
	}
                return this;
                
}
}
