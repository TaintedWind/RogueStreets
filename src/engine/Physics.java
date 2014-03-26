package engine;

import org.lwjgl.util.vector.Vector;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;
import player.Player;
import objects.Entity;
import world.GlobalVariables;
import world.ObjList;
import objects.Wall;


//import org.newdawn.slick.geom.

public class Physics{
    
    
    //will determine if the collision is worth determining
    public int isNear(Shape Obj1, Shape Obj2,int radius, boolean display){
        //disply is used if to output the distance between two objects
        
        int output;
        float x1,y1,x2,y2;
        x1 = Obj1.getCenterX();
        y1 = Obj1.getCenterY();
        x2 = Obj2.getCenterX();
        y2 = Obj2.getCenterY();
        
        double seperation;// check if the point is within given circle
        seperation = ((x1 - x2)*(x1 - x2)) + ((y1 - y2)*(y1 - y2));
                  
        if (display == false){
            if (seperation < (radius*radius)){
                output = 1;
            }else {
                output = 0;
            }
        }else{
            output = (int)seperation;
        }

        /*
         * When returning 0 - not near enough
         * When returning 1 - within radius of given circle
         */
        return output;
    }
    
    public int isNear(Player Obj1, Wall Obj2,int radius, boolean display){
        //disply is used if to output the distance between two objects
        int output;
        float x1,y1,x2,y2;
        x1 = Obj1.x;
        y1 = Obj1.y;
        x2 = Obj2.x + ObjList.Players.getFirst().xOffset + Obj2.w/2;
        y2 = Obj2.y + ObjList.Players.getFirst().yOffset + Obj2.l/2;
        
        double seperation;// check if the point is within given circle
        seperation = ((x1 - x2)*(x1 - x2)) + ((y1 - y2)*(y1 - y2));
                  
        if (display == false){
            if (seperation < (radius*radius)){
                output = 1;
            }else {
                output = 0;
            }
        }else{
            output = (int)seperation;
        }

        /*
         * When returning 0 - not near enough
         * When returning 1 - within radius of given circle
         */
        return output;
    }
    
    //if isNear is returned true, then will determine the collision
    public float[] isColliding(Shape Obj1, Shape Obj2, double mass1, double mass2){
        //the shapes are the hitboxes, and the masses are in the entity class
        float[] Output = {0,0,0,0};
        
        //input the two shapes to test collision
        if(Obj1.intersects(Obj2)){//main check
            
            float x1,y1,x2,y2;
        
            float xSeperation,ySeperation;
        
            double angle;
            
            x1 = Obj1.getCenterX();
            y1 = Obj1.getCenterY();
            x2 = Obj2.getCenterX();
            y2 = Obj2.getCenterY();
            
            xSeperation = x1 - x2;
            ySeperation = -(y1) + y2;
            
            angle = Math.toDegrees(Math.atan2(xSeperation, ySeperation));
            
            Output[0] -= mass1 * Math.sin(angle);
            Output[1] -= mass1 * Math.cos(angle);
            
            Output[2] -= mass2 * Math.sin(angle);
            Output[3] -= mass2 * Math.cos(angle);
            
        }
        
        //output 0 will be the movement of x axis
        //output 1 will be the movement of y axis
        return Output;
    }
    
    //entity collision with walls
    public void isColliding(Entity Obj1, Wall Obj2){
        //the shapes are the hitboxes, and the masses are in the entity class
        //input the two shapes to test collision
        if(Obj2.HitboxR.contains(Obj1.HitboxR) || Obj2.HitboxR.intersects(Obj1.HitboxR)){//main check
            
            System.out.println("COLLISION");
            
            Point closestPoint;
            Point edgePoint;
            
            /*
            
            double leftOverlap=Obj1.x+Obj1.xOffset-Obj2.x;//find out how much rect1 overlaps rect2 on each side
            double rightOverlap=Obj2.x+Obj2.w-Obj1.x;
            double topOverlap=Obj1.y+Obj1.yOffset-Obj2.y;
            double botOverlap=Obj2.y+Obj2.l-Obj1.y;
            
            */
            double angle;
            
            double x1,y1,x2,y2; //two centre point of the shapes
            
            x1 = Obj1.HitboxR.getCenterX();
            y1 = Obj1.HitboxR.getCenterY();
            x2 = Obj2.HitboxR.getCenterX();
            y2 = Obj2.HitboxR.getCenterY();

            double xSeperation,ySeperation;
            
            xSeperation = x1 - x2;
            ySeperation = -(y1) + y2;
            
        }
    }
    // Calculate the distance between [minA, maxA] and [minB, maxB]
    // The distance will be negative if the intervals overlap
    public float IntervalDistance(float minA, float maxA, float minB, float maxB) {
        if (minA < minB) {
           return minB - maxA;
        } else {
            return minA - maxB;
        }
    }
    
    //finds the dot product of two arrays (adds the two points in each location
    //eg: a[0] + b[0], a[1] + b[1] etc...
    public double dotProduct(int[] a, int[] b){
        int sum = 0;
        int n = a.length;
        
        for (int i = 0; i < n; i++) {
            sum += a[n] * b[n];    
        }
        
        return sum;
    }
    public float dotProduct(float[] a){
        int sum = 0;
        int n = a.length;
        
        for (int i = 0; i < n; i += 2) {
            sum += a[n] * a[n+1];    
        }
        
        return sum;
    }
    
    public void ProjectPolygon(Vector axis, Shape polygon, float min, float max){
        float DotProduct = dotProduct(polygon.getPoints());
        min = DotProduct;
        max = DotProduct;
        for (int i = 0; i < polygon.getPointCount(); i++){
            
        }
    }
    
    
}
