
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
        //the space between the center of the Ambi and the edges of the screen
        static int horizBuffer = 75;
        static int vertBuffer = 60;
        
	IColor col = new Red();
        String code = "none";
        boolean locked;
        
	
	Ambi(Posn center, String code, boolean locked) {
		this.center = center;
                this.code = code;
                this.locked = locked;
                    
	}
        
        Ambi(Posn center, boolean locked) {
		this.center = center;
                this.code = code;
                this.locked = locked;
                this.code = newCode();  
	}
       
        public boolean horizEdgeCheck(boolean rightHuh){
            if((center.x < horizBuffer && !rightHuh) || (center.x > Untitled5.screenHeight-horizBuffer && rightHuh)) 
                return false;
            else
                return true;
        }
        
        public boolean vertEdgeCheck(boolean upHuh){
            if((center.y < vertBuffer && upHuh) || (center.y > Untitled5.screenHeight-vertBuffer && !upHuh)) 
                return false;
            else
                return true;
        }
        
        public static String newCode(){
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
        
        public Ambi tryUnlock(String ke){
            if(this.code.equals(ke))
                return new Ambi(this.center,this.code,false);
            else return this;
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
            
                if(this.tryUnlock(ke).locked){
            locked = true;
		if (ke.equals("d") && horizEdgeCheck(true)){
                    newCode();
			return new Ambi(new Posn(this.center.x + 50, this.center.y), true);
		}
                
		else if (ke.equals("a") && horizEdgeCheck(false)){
                    newCode();
			return new Ambi(new Posn(this.center.x - 50, this.center.y), true);
		}
		else if (ke.equals("w") && vertEdgeCheck(true)){
                    newCode();
			return new Ambi(new Posn(this.center.x, this.center.y-50), true);
		}
		else if (ke.equals("s") && vertEdgeCheck(false)){
                    newCode();
			return new Ambi(new Posn(this.center.x, this.center.y+50), true);
		}
		
	}
                return this;
                
}
}
