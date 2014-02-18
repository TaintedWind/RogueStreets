package world;

//entities will inherit these things to be defaulted
import engine.Physics;

public class Entity extends Physics{
    
    public int x, y;
    
    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void update(){
    }
    
    public void render(){
    }
    
}
