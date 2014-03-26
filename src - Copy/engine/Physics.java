package engine;


//import org.newdawn.slick.geom.

public class Physics {
    
    
    //will determine if the collision is worth determining
    public int isNear(int x1,int x2,int y1,int y2, boolean display){
        //disply is used if to output the distance between two objects
        
        int output;
        
        double seperation;//default distance is well max of the check
        seperation = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
                
        if (display == false){
            }if (seperation <= 100 && seperation > 50){
                output = 2;
            }else if (seperation <= 50){
                output = 1;
            }else {
                output = 0;
            
        }
        /*
         * When returning 0 - not near enough greater than 100 px
         * When returning 1 - within 20 px
         * When returning 2 - within 50 px
         */
        return output;
    }
    
    //if isNear is returned true, then will determine the collision vectors
    public double isColliding(int distance,float angle){
        return 0;
    }
    
    
}
