package objects.enemy;

import objects.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;
import world.ObjList;


public class BasicEnemy extends Entity{
    //the drawn image of the entity
    Image display;
    
    int speed;
    
    //will target nearest player
    Entity target;
    
    //the distance which the entity will target other things
    double targetDist;
    
    public BasicEnemy(int x,int y){
        super(x,y);
        
        HitboxPoints = new float[]{0,0,32,0,32,20,0,20};
        Hitbox = new Polygon(HitboxPoints);
        HitboxR = new Polygon(HitboxPoints);
        
        //set speed of mob
        this.speed = 2;
        
        this.rotationSpeed = 0.5;
        this.rotationAngle = 0;
        
        this.xLocation = x;
        this.yLocation = y;
        
        //set health
        this.healthMax = 10;
        this.healthCurrent = healthMax;
        
        world.ObjList.Mobs.add(this);
        //add the player to the playerlist to be 
        
        //the id of the player is its indexed value
        this.id = world.ObjList.Mobs.indexOf(this);
        
    }
    
    @Override
    public void init(){
        display = getImg();
    }
    
    @Override
    public void update(){
        
        if (healthCurrent <= 0){
            delete();
        }
        //find where enemy will rotate too
        rotationTarget = getTargetAngle(getClosestPlayerTarget(400));
        
        //self contained rotation calculator based on target
        getRotation();
        
        //her we move the mob towards its target angle
        double cos = Math.cos(rotationAngle * 0.0174532) * speed;
        double sin = Math.sin(rotationAngle * 0.0174532) * speed;
        x += sin;
        y -= cos;
        
        xLocation = x + ObjList.Players.getFirst().xOffset;
        yLocation = y + ObjList.Players.getFirst().yOffset;
        
        Hitbox.setLocation(xLocation,yLocation);
        
        display.setRotation((float)rotationAngle);
        
        //just rotate the proper hitbox to the correct position
        HitboxR = Hitbox.transform(Transform.createRotateTransform(((float)Math.toRadians(rotationAngle)),xLocation + (Hitbox.getWidth()/2), yLocation + (Hitbox.getHeight()/2)));
        
    }
    
    @Override
    public void render(Graphics g)throws SlickException{
        
        g.setColor(Color.white);
        
        display.draw(xLocation,yLocation);

    }
    
    private Image getImg() {
        //laod player imagge
        Image playr = null;
        try{
            playr = new Image("assets/imgs/zomb1.png");
            playr.setFilter(Image.FILTER_NEAREST);
            
        }catch(SlickException e){
        }
        return playr;
    }
    
    private double getTargetAngle(Entity t){ //FIX HERE SOMEWHERE]\
        double xDistance, yDistance, finalTarget;
        
        xDistance = this.HitboxR.getCenterX() - t.HitboxR.getCenterX();
        
        yDistance = - this.HitboxR.getCenterY() + t.HitboxR.getCenterY();
        
        finalTarget = Math.toDegrees(Math.atan2(xDistance, yDistance));
        
        //make 0 degrees top orientation
        finalTarget -= 180;
        
        if (finalTarget < 0){
            finalTarget += 360;
        }
        
        return finalTarget;
    }
    
    private void getRotation(){
        if (rotationAngle < -5){//reduandancy for rotational movement
            rotationAngle = 355;
        }else if (rotationAngle > 365){
            rotationAngle = 5;
        } 
        
        rotationVelocity = rotationSpeed * (((rotationTarget - rotationAngle)%360.0+360.0+180.0)%360.0-180.0);
        rotationAngle += rotationVelocity;
    }
    
    //finds the closest player in the targettig area
    private Entity getClosestPlayerTarget(int closestPlayer){
        //closestPlayer is the targetting distance
        int closestPlayerIndex = 0;
        
        for (int i = 0 ; i < ObjList.PlayersRendered.size(); i++){
            double xLoc = ObjList.PlayersRendered.get(i).HitboxR.getCenterX();
            double yLoc = ObjList.PlayersRendered.get(i).HitboxR.getCenterY();
            double distance = (yLoc - this.HitboxR.getCenterY()) / (xLoc - this.HitboxR.getCenterX());
            if (distance < 0 ){
                distance *= -1;
            }
            if (distance < closestPlayer){
                closestPlayerIndex = i;
                closestPlayer = (int)distance;
            }
            
        }
        
        return ObjList.PlayersRendered.get(closestPlayerIndex);
        
    }
    
    //this will remove the object from its arrays
    @Override
    public void delete(){
         world.ObjList.Mobs.remove(this);
         world.ObjList.MobsRendered.remove(this);
    }
    
}
