package player;

import world.Entity;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import roguestreets.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

public class Player extends Entity{
    //Entity inherits location values and other stuff

    //to determine the angle of rotation fot the player
    double rotationAngle;//current angle
    double rotationTarget;//targeted rotation angle
    double rotationVelocity;//current speed of rotation
    double rotationSpeed;//speed of rotation constant
    float yDistance;//distances between cursor and player
    float xDistance;
    
    int xpos,ypos;//cursor x and y positions
    String mouse;
    String rotA;
    
    //followCursor determines if the player will face the cursor
    boolean followCursor = false;

    Image Dplayer;//display of player
    
    public Player(int x, int y) {
        
        super(x,y);
        
        this.rotationSpeed = 0.2;
        this.rotA = "Rotation angle NO VALUE";
        this.mouse = "Mouse position NO INPUT";
        //the Player's hitbox polygon, the original and the transformed are set
        HitboxPoints = new float[]{0,0,32,0,32,20,0,20};
        Hitbox = new Polygon(HitboxPoints);
        HitboxR = new Polygon(HitboxPoints);
        
        roguestreets.ObjList.Players.add(this);
        //add the player to the playerlist to be updated
        
    } 
    
    
    @Override
    public void update(){
               
        Dplayer = getPlayerImg();// image of player
        
        Hitbox.setLocation(x-16, y-10); //set location of the player's hitbox
        
        //these are the locations for the x and y mouse positions
        xpos = Mouse.getX();
        ypos = (int)Game.gameDim.getHeight() - Mouse.getY();
        
        //some debugging displays
        rotA = "Player Rotation: "+rotationAngle;
        mouse = "Mouse position x: " + xpos + " y: " + ypos;
        
        //get and set player rotation
        getPlayerRotation();
        Dplayer.setRotation((float)rotationAngle);
        HitboxR = Hitbox.transform(Transform.createRotateTransform(((float)Math.toRadians(rotationAngle)), x, y));
        //finished rotating player
    }

    public void render(Graphics g) throws SlickException {

        g.draw(HitboxR);//draw the player's hitbox
        Dplayer.draw(x-16,y-10,null);//draw the player image
        
        //draw debugging things
        g.drawString(mouse, 950, 20);
        g.drawString(rotA,950,80);
        
        g.setColor(Color.gray);
        if (followCursor){//aiming reticule
            g.drawLine(x, y, xpos, ypos);
        }
        
    }
    
    private Image getPlayerImg() {
        //laod player imagge
        Image playr = null;
        try{
            playr = new Image("assets/imgs/player1.png");
            playr.setFilter(Image.FILTER_NEAREST);
            
        }catch(SlickException e){
        }
        return playr;
    }
    
    private double getPlayerRotation(){
        //determine the correct orientation of the character
        if (rotationAngle < -5){//reduandancy for rotational movement
            rotationAngle = 355;
        }else if (rotationAngle > 365){
            rotationAngle = 5;
        } 
        // player movement rotation
        if (Play.input.isMouseButtonDown(1)){
            //right mouse(aiming) overrides movement
            followCursor = true;
        } else if (Play.up || Play.down || Play .left || Play.right){
            followCursor = false;
            if (Play.up && Play.right){
                rotationTarget = 45;
            }else if (Play.up && Play.left){
                rotationTarget = 315;
            }else if (Play.down && Play.left){
                rotationTarget = 225;
            }else if (Play.down && Play.right){
                rotationTarget = 135;
            }else if (Play.up){
                rotationTarget = 0;
            }else if (Play.down){
                rotationTarget = 180;
            }else if (Play.left){
                rotationTarget = 270;
            }else if (Play.right){
                rotationTarget = 90;
            }
        }else{
            followCursor = false;
        }
        
        //calculate angular velocities to turn character
        if (followCursor){
            xDistance = xpos - x;
            yDistance = -(ypos) + y;
            rotationTarget = Math.toDegrees(Math.atan2(xDistance, yDistance));
            if(rotationTarget < 0){
                rotationTarget += 360; 
            }
        }
        //ratation velociy is calculated with  modulo of 360 to find out the
        //best was to roate(so it doesnt rotate from 20 degress right to 270
        rotationVelocity = rotationSpeed * (((rotationTarget - rotationAngle)%360.0+360.0+180.0)%360.0-180.0);
        rotationAngle += rotationVelocity;
        
        return rotationAngle;
    }
    
}
