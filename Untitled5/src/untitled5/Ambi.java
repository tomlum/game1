
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
        public Ambi moveAmbi(int dir, boolean lock){
        switch(dir){
        case 1: return new Ambi(new Posn(center.x+speed,center.y), lock);
        case 2: return new Ambi(new Posn(center.x,center.y+speed), lock);
        case 3: return new Ambi(new Posn(center.x-speed,center.y), lock);
        default: return new Ambi(new Posn(center.x,center.y-speed), lock);
	}
            
        }
        
        public Ambi controlAmbi(String ke){
            
                locked = !this.tryUnlock(ke);
                if(!locked){
		if (ke.equals("d") && !moveAmbi(1,false).bufferOutOfBounds()){
                    return moveAmbi(1,true);
		}
		else if (ke.equals("a") && !moveAmbi(3,false).bufferOutOfBounds()){
                    return moveAmbi(3,true);
		}
		else if (ke.equals("w") && !moveAmbi(0,false).bufferOutOfBounds()){
                    return moveAmbi(0,true);
		}
		else if (ke.equals("s") && !moveAmbi(2,false).bufferOutOfBounds()){
                    return moveAmbi(2,true);
		}
	}
                return this;
                
}
        public boolean collisionCheck(Laser laser){
                if(Math.abs(this.center.x-laser.center.x)<(width+Laser.width)/2&&
                   Math.abs(this.center.y-laser.center.y)<(height+Laser.height)/2
                        ){
                    return true;
            }
        
            return false;
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