
package untitled5;


public class LaserArr {
    Laser[] Arr;
    
    public static Laser[] moveLasArr(Laser[] theArr){
        Laser[] newArr = new Laser[theArr.length];
        for(int i = 0; i<theArr.length;i++){
            newArr[i] = theArr[i].move();
            }
        return newArr;
    }
    
}
