package player;

import main.RogueStreets;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import objects.Entity;
import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;
import world.GlobalVariables;
import static world.ObjList.World;

public class Player extends Entity{
    //Entity inherits location values and other stuff
    
    public boolean left, right, up, down, leftMouse, rightMouse, sneak;//input keys

    float yDistance;//distances between cursor and player
    float xDistance;
    
    //a speed changer; where 0.5 is normal walking
    double speed;
    
    int xMousePos,yMousePos;//cursor x and y positions
    
    //updated display of info
    String mouse;
    String rotA;
    String OffsetV;
    
    //followCursor determines if the player will face the cursor
    boolean followCursor;
    
    Image Dplayer;//display of player
    
    public Player(int x, int y) {
        
        super(x,y);
        
        this.followCursor = false;
        this.rotationSpeed = 0.19;
        this.OffsetV = "Offset position NO VALUE";
        this.rotA = "Rotation angle NO VALUE";
        this.mouse = "Mouse position NO INPUT";
        //the Player's hitbox polygon, the original and the transformed are set
        HitboxPoints = new float[]{0,0,32,0,32,20,0,20};
        Hitbox = new Polygon(HitboxPoints);
        HitboxR = new Polygon(HitboxPoints);
        
        speed = 0.5;
        
        world.ObjList.Players.add(this);
        //add the player to the playerlist to be 
        
        //the id of the player is its indexed value
        this.id = world.ObjList.Players.indexOf(this);
        
    } 
    
    @Override
    public void init(){
        getImg();// image of player
    }
    
    @Override
    public void update(){
        
        Hitbox.setLocation(x-16, y-10); //set location of the player's hitbox
        
        //these are the locations for the x and y mouse positions
        xMousePos = Mouse.getX();
        yMousePos = (int)RogueStreets.gameDim.getHeight() - Mouse.getY();
        
        //set final values for player movement
        movePlayer();
        
        //get and set player rotation
        getPlayerRotation();
        Dplayer.setRotation((float)rotationAngle);
        HitboxR = Hitbox.transform(Transform.createRotateTransform(((float)Math.toRadians(rotationAngle)), x, y));
        //finished rotating player
        
        checkCollision();
        
        //some debugging displays
        rotA = "Player Rotation: "+rotationAngle;
        mouse = "Mouse position x: " + xMousePos + " y: " + yMousePos;
        OffsetV = "Offset Position x: " + xOffset + " y: " + yOffset;
    }

    @Override
    public void render(Graphics g) throws SlickException {

        g.setColor(Color.white);
        //g.draw(HitboxR);//draw the player's hitbox
        Dplayer.draw(x-16,y-10,null);//draw the player image
        
        //draw debugging things
        g.drawString(mouse, 950, 20);
        g.drawString(rotA,950,80);
        g.drawString(OffsetV, 950, 50);
        
        if (followCursor){//aiming reticule
            g.setColor(Color.gray);
            g.drawLine(x, y, xMousePos, yMousePos);
        }
        
    }
    
    private void getImg() {
        //laod player imagge
        try{
            Dplayer = new Image("assets/imgs/player1.png");
            Dplayer.setFilter(Image.FILTER_NEAREST);
            
        }catch(SlickException e){
        }
    }
    
    private double getPlayerRotation(){
        //determine the correct orientation of the character
        if (rotationAngle < -5){//reduandancy for rotational movement
            rotationAngle = 355;
        }else if (rotationAngle > 365){
            rotationAngle = 5;
        } 
        // player movement rotation
        if (rightMouse){
            //right mouse(aiming) overrides movement
            followCursor = true;
        } else if (up || down || left || right){
            followCursor = false;
            if (up && right){
                rotationTarget = 45;
            }else if (up && left){
                rotationTarget = 315;
            }else if (down && left){
                rotationTarget = 225;
            }else if (down && right){
                rotationTarget = 135;
            }else if (up){
                rotationTarget = 0;
            }else if (down){
                rotationTarget = 180;
            }else if (left){
                rotationTarget = 270;
            }else if (right){
                rotationTarget = 90;
            }
        }else{
            followCursor = false;
        }
        
        //calculate angular velocities to turn character
        if (followCursor){
            xDistance = xMousePos - RogueStreets.WIDTH/2;
            yDistance = -(yMousePos) + RogueStreets.HEIGHT/2;
            rotationTarget = Math.toDegrees(Math.atan2(xDistance, yDistance));
            if(rotationTarget < 0){
                rotationTarget += 360; 
            }
        }
        //rotation velociy is calculated with modulo of 360 to find out the
        //best was to roate(so it doesnt rotate from 20 degress right to 270
        rotationVelocity = rotationSpeed * (((rotationTarget - rotationAngle)%360.0+360.0+180.0)%360.0-180.0);
        rotationAngle += rotationVelocity;
        
        return rotationAngle;
    }
    
    private void movePlayer(){
        //check if 'sneaking'
        if (sneak){
            speed = 0.3;
        } else {
            speed = 0.5;
        }
        //reduce angular speeds
        if (down && right){
            xOffset -=  GlobalVariables.deltaTime * 0.67082f * speed;
            yOffset -= GlobalVariables.deltaTime * 0.67082f * speed;
        }else if (down && left){
            xOffset +=  GlobalVariables.deltaTime * 0.67082f * speed;
            yOffset -= GlobalVariables.deltaTime * 0.67082f * speed;
        }else if (up && right){
            xOffset -=  GlobalVariables.deltaTime * 0.67082f * speed;
            yOffset += GlobalVariables.deltaTime * 0.67082f * speed;
        }else if (up && left){
            xOffset +=  GlobalVariables.deltaTime * 0.67082f * speed;
            yOffset += GlobalVariables.deltaTime * 0.67082f * speed;
        }else if (up){
            yOffset += GlobalVariables.deltaTime * 0.9f * speed;
        }else if (down){
            yOffset -= GlobalVariables.deltaTime * 0.9f * speed;
        }else if (left){
            xOffset += GlobalVariables.deltaTime * 0.9f * speed;
        }else if (right){
            xOffset -= GlobalVariables.deltaTime * 0.9f * speed;
        }
        
    }
    
    //cycle to check if colliding with any world objects
    private void checkCollision(){
        for (int ii = 0; ii < World.size(); ii++){
            if (this.isNear(this,World.get(ii),150,false) == 1){
                this.isColliding(this, World.get(ii));
            }
        }
    }

    @Override
    public void delete(){
         world.ObjList.Players.remove(this);
         world.ObjList.PlayersRendered.remove(this);
    }
    
}
