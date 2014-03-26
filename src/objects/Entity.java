package objects;

//entities will inherit these things to be defaulted
import engine.Physics;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class Entity extends Physics{
    
    //center x and y location of the entity
    public int x, y;//the x and y locations of the entity
    // Offset for displaying objects
    public int xOffset = 0;//drawing offset values
    public int yOffset = 0;
    public double mass; //where 0 is immovable, and 1 is normal.
    
    public int xLocation, yLocation; //the relative location calculated with offset
    
    public double healthCurrent, healthMax;//the health amd maxhealth
    
    //to determine the angle of rotation fot the player
    public double rotationAngle;//current angle
    public double rotationTarget;//targeted rotation angle
    public double rotationVelocity;//current speed of rotation
    public double rotationSpeed;//speed of rotation constant
    
    public int id;//the id of the entity in it's list
    
    //the hitbox of the entity, in shape form for easy manipulation
    public Shape Hitbox;
    public Shape HitboxR;
    public float[] HitboxPoints;
    
    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void init(){
        //things to be done first, and once
    }
    
    public void update(){
        //all things to be updated per loop
    }
    
    public void render(Graphics G)throws SlickException{
        //all things to be drawn to screen
    }
    
    public void delete(){
    }
    
}
