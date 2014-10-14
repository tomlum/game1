
package untitled5;

import javalib.colors.*;
import javalib.worldimages.*;

public class Ambi extends Block {
	static int width = 150;
        static int height = 50;
        //the space between the center of the Ambi and the edges of the screen
        String code = "none";
        static int speed = 50;
        boolean locked;
        
        
	
	Ambi(Posn center, String code, boolean locked) {
        super(center, Ambi.width, Ambi.height, new Green());
                this.code = code;
                this.locked = locked;
	}
        
        Ambi(Posn center, boolean locked) {
        super(center, width, height, new Green());
                this.locked = locked;
                this.code = newCode();  
	}
        
        Ambi(Block block, boolean locked) {
        super(block.center, width, height, new Green());
                this.locked = locked;
                this.code = newCode();  
	}
       
        public static String newCode(){
            int ran = Test.randomInt(0,3);
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
        
        public boolean tryUnlock(String ke){
            if(this.code.equals(ke)){
                return true;
            }
            else return !locked;
        }
        
        WorldImage ambiImage(){
            return new OverlayImages(
                    this.drawBlock(),
                    ambiArrow());
        }
            
        //returns the apropriate arrow image depending on code
        WorldImage ambiArrow(){
            switch(code){
            case "up": return new TextImage(this.center, "/\\",20, new Blue());
            case "left": return new TextImage(this.center, "<-",20, new Blue());
            case "right": return new TextImage(this.center, "->",20, new Blue());
            case "down": return new TextImage(this.center, "\\/",20, new Blue());
            default : return new TextImage(this.center, "error",20, new Blue());
            }
        }
        
        
        WorldImage ambiText(){
            return new TextImage(this.center, "->",5, new Blue());
        }
        
        //tie resetting to the movement keys vs the code keys
        public Ambi moveAmbi(int dir, boolean lock){
            if(!this.move(dir, speed).bufferOutOfBounds()){
            return new Ambi(this.move(dir, speed), lock);
            }
            else return this;
        }
        
        public Ambi controlAmbi(String ke){
            
                locked = !this.tryUnlock(ke);
                if(!locked){
                    switch(ke){
                        case "d": return moveAmbi(1,true);
                        case "a": return moveAmbi(3,true);
                        case "w": return moveAmbi(0,true);
                        case "s": return moveAmbi(2,true);
                }
                }
                return this;
        }
        
        public boolean collisionCheck(Laser laser){
                return Math.abs(this.center.x-laser.center.x)<(width+Laser.width)/2&&
                   Math.abs(this.center.y-laser.center.y)<(height+Laser.height)/2;
        } 
       
        public boolean arrayCollisionCheck(Laser[] laserArr){
            for(int i = 0; i < laserArr.length;i++){
                if(collisionCheck(laserArr[i])){
                    return true;
            }
        }
            return false;
        }
        
        
}