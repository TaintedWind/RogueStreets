package roguestreets;

//entities will inherit these things to be defaulted
public class Entity {
    
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
