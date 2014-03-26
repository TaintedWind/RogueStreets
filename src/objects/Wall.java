package objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import world.ObjList;

public class Wall{
    
    public int x, y, w, l;//the x and y locations of the entity being intiated
    public int xLocation, yLocation; //the relative location calculated with offset
    float angle; //angle of the objects rotation
    public double mass = 0; //where 0 is immovable, and 1 is normal.
    
    public int id;//the id of the entity in it's list
    
    //the hitbox of the entity, in shape form for easy manipulation
    Shape Hitbox;
    public Shape HitboxR;
    
    public Wall(int x, int y,int w,int l, float angle) {
        this.x = x;
        this.y = y;
        this.w = w; //of x axis
        this.l = l; //of y axis
        this.angle = angle;
        
        this.xLocation = x;
        this.yLocation = y;
        
        this.Hitbox = new Rectangle(x,y,w,l);
        this.HitboxR = new Rectangle(x,y,w,l);
        
        //getImg();
        
        world.ObjList.World.add(this);
        
    }
    
    
    public void update(){
        
        xLocation = x + ObjList.Players.getFirst().xOffset;
        yLocation = y + ObjList.Players.getFirst().yOffset;
        
        Hitbox.setLocation(xLocation,yLocation);
        
        //just rotate the proper hitbox to the correct position
        HitboxR = Hitbox.transform(Transform.createRotateTransform(((float)Math.toRadians(angle)),xLocation + w/2, yLocation + l/2));
        
    }
    
    
    public void render(Graphics g) throws SlickException {
        
        g.setColor(Color.gray);
        g.fill(HitboxR);
        
    }
    
    private Image getImg() {
        //laod player imagge
        Image imag = null;
        try{
            imag = new Image("assets/imgs/player1.png");
            imag.setFilter(Image.FILTER_NEAREST);
            
        }catch(SlickException e){
        }
        return imag;
    }
    
}
