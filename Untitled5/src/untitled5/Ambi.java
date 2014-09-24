
package untitled5;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import java.awt.Color;
import java.util.Random;
public class Ambi {
        Posn center;
	int radius;
	IColor col;
	
	Ambi(Posn center, int radius, IColor col) {
		this.center = center;
		this.radius = radius;
		this.col = col;
	}
        
        WorldImage ambiImage(){
            return new RectangleImage(this.center, this.radius*2, this.radius, this.col);
        }
        
        //tie resetting to the movement keys vs the code keys
        
        public Ambi moveAmbi(String ke){
            
            
		if (ke.equals("right")){
			return new Ambi(new Posn(this.center.x + 50, this.center.y),
							this.radius, this.col);
		}
                
		else if (ke.equals("left")){
			return new Ambi(new Posn(this.center.x - 50, this.center.y),
							this.radius, this.col);
		}
		else if (ke.equals("up")){
			return new Ambi(new Posn(this.center.x, this.center.y - 50),
							this.radius, this.col);
		}
		else if (ke.equals("down")){
			return new Ambi(new Posn(this.center.x, this.center.y + 50),
							this.radius, this.col);
		}
		
		else
			return this;
	}
}
