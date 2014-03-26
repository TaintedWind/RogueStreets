package world;

//entities will inherit these things to be defaulted
import engine.Physics;
import org.newdawn.slick.geom.Shape;

public class Entity extends Physics{
    
    //centre x and y location of the entity
    public int x, y;
    
    //the hitbox of the entity, in shape form for easy manipulation
    public Shape Hitbox;
    public Shape HitboxR;
    public float[] HitboxPoints;
    
    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void update(){
    }
    
    public void render(){
    }
    
}
