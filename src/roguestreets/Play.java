package roguestreets;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Play extends BasicGameState{
    
    public static boolean left, right, up, down;//movement keys
    
    // Offset for displaying objects
    public static int xOffset = 0;
    public static int yOffset = 0;
    
    public static int xpos,ypos;
    
    Image bgImage = null;//TEMP
    
    
    //print testing values
    String OffsetV = "Offset position NO VALUE";
    public String mouse = "Mouse position NO INPUT";
    String rotA = "Player rotation NO INPUT";
    
    public static Input input;
    
    player.Player player;
    
    
    public Play(int state){
        player = new player.Player(Game.gameDim.width/2,Game.gameDim.height/2);//create a player at centre of screen
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException { 
        
        try{
            bgImage = new Image("assets/imgs/mossconcrete_bg1.png");
            
        }catch(SlickException e){
            e.printStackTrace();
        }
        
        
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        
        g.setBackground(Color.black);
        
        bgImage.setFilter(Image.FILTER_LINEAR);
        bgImage.draw(xOffset,yOffset,Game.scale);
        
        g.setColor(Color.white);
        g.drawString(mouse, 950, 20);
        g.drawString(OffsetV, 950, 50);
        g.drawString(rotA,950,80);
        
        player.render(g);
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
        input = gc.getInput();
        
        bgImage.draw(xOffset,yOffset);
        
        xpos = Mouse.getX();
        ypos = (int)Game.gameDim.getHeight() - Mouse.getY();
        mouse = "Mouse position x: " + xpos + " y: " + ypos;
        OffsetV = "Offset Position x: " + xOffset + " y: " + yOffset;
        rotA = "Player Rotation: "+player.rotationAngle;
        
        ObjList.updateAllObjects();
        
        moveMap(delta);
        
    }
    //move everything in relation to offset position
    private void moveMap(int delta) {
        
        if(input.isKeyDown(Input.KEY_W)){
            yOffset += delta * 1f;
            up = true;
        } else {
            up = false;
        }
        if(input.isKeyDown(Input.KEY_S)){
            yOffset -= delta * 1f;
            down = true;
        }else {
            down = false;
        }
        if(input.isKeyDown(Input.KEY_A)){
            xOffset += delta * 1f;
            left = true;
        }else {
            left = false;
        }
        if(input.isKeyDown(Input.KEY_D)){
            xOffset -= delta * 1f;
            right = true;
        }else {
            right = false;
        }
        //reduce unecesary calculations
        if (up && down){
            up = false;
            down = false;
        }
        if (left && right){
            left = false;
            right = false;
        }
        //reduce angular speeds
        if (up && left){
            xOffset -= delta * 0.4f; 
            yOffset -= delta * 0.4f;
        }
        if (up && right){
            xOffset += delta * 0.4f; 
            yOffset -= delta * 0.4f;
        }
        if (down && left){
            xOffset -= delta * 0.4f; 
            yOffset += delta * 0.4f;
        }
        if (down && right){
            xOffset += delta * 0.4f; 
            yOffset += delta * 0.4f;
        }
        
    }
 
    public int getID() {
        return 1;
    }
    
}
    