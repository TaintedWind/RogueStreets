package player;

import org.newdawn.slick.Color;
import roguestreets.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Entity{
    //Entity inherits location values and other stuff

    //to determine the angle of rotation fot the player
    public double rotationAngle;//current angle
    double rotationTarget;//targeted rotation angle
    double rotationVelocity;//current speed of rotation
    double rotationSpeed = 0.2;//speed of rotation constant
    float yDistance;//distances between cursor and player
    float xDistance;
    
    //followCursor determines if the player will face the cursor
    boolean followCursor = false;

    Image Dplayer;//display of player
    Rectangle PHitbox;
    int cursorX,cursorY;
    
    public Player(int x, int y) {
        
        super(x,y);
        roguestreets.ObjList.Players.add(this);
        //add the player to the playerlist to be updated(prep for multiplayer)
        
    } 
    
    @Override
    public void update(){
        
        PHitbox = new Rectangle(x,y,32,20);
        
        Dplayer = getPlayerImg();// image of player
        
        x = (Game.gameDim.width / 2) - 16;//location of player on screen
        y = (Game.gameDim.height / 2) - 10;
        
        //determine the correct orientation of the character
        if (rotationAngle < -5){//redundancy for rotational movement
            rotationAngle = 360;
        }else if (rotationAngle > 365){
            rotationAngle = 0;
        } 
        //     player movement rotation
        if (Play.input.isMouseButtonDown(1)){
            //left mouse(aiming) overrides movement
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
            xDistance = Play.xpos - x;
            yDistance = -(Play.ypos) + y;
            rotationTarget = Math.toDegrees(Math.atan2(xDistance, yDistance));
            if(rotationTarget < 0){
                rotationTarget += 360; 
            }
        }
        //ratation velociy is calculated with  modulo of 360 to find out the
        //best was to roate(so it doesnt rotate from 20 degress right to 270
        rotationVelocity = rotationSpeed * (((rotationTarget - rotationAngle)%360.0+360.0+180.0)%360.0-180.0);
        rotationAngle += rotationVelocity;
        
        Dplayer.setRotation((float)rotationAngle);
        //finished rotating player
    }

    public void render(Graphics g) throws SlickException {

        Dplayer.draw(x,y,null);
        
        g.setColor(Color.gray);
        if (followCursor){//aiming reticule
            g.drawLine(x+16, y+10, Play.xpos, Play.ypos);
        }
        
    }
    
    public Image getPlayerImg() {
        //laod player imagge
        Image playr = null;
        try{
            playr = new Image("assets/imgs/player1.png");
            playr.setFilter(Image.FILTER_NEAREST);
            
        }catch(SlickException e){
            e.printStackTrace();
        }
        return playr;
        
    }
    
}
