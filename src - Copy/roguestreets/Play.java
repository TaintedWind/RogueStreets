package roguestreets;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import world.GlobalVariables;


public class Play extends BasicGameState{
    
    public static boolean left, right, up, down;//movement keys
    
    // Offset for displaying objects
    public static int xOffset = 0;
    public static int yOffset = 0;
    
    Image bgImage = null;//TEMP
    
    //print testing values
    String OffsetV = "Offset position NO VALUE";
    
    public static Input input;
    
    player.Player player;
    
    public Play(int state){
        super();
        player = new player.Player(Game.gameDim.width/2,Game.gameDim.height/2);//create a player at centre of screen
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException { 
        
        try{
            bgImage = new Image("assets/imgs/mossconcrete_bg1.png");
            bgImage.setFilter(Image.FILTER_LINEAR);
            
        }catch(SlickException e){
        }
        
        
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        
        g.setBackground(Color.black);
        
        bgImage.draw(xOffset,yOffset,Game.scale);
        
        g.setColor(Color.white);
        g.drawString(OffsetV, 950, 50);
        
        player.render(g);
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
        GlobalVariables.deltaTime = delta;
        
        input = gc.getInput();
        
        OffsetV = "Offset Position x: " + xOffset + " y: " + yOffset;
        
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
            xOffset -= delta * 0.38f; 
            yOffset -= delta * 0.38f;
        }
        if (up && right){
            xOffset += delta * 0.38f; 
            yOffset -= delta * 0.38f;
        }
        if (down && left){
            xOffset -= delta * 0.38f; 
            yOffset += delta * 0.38f;
        }
        if (down && right){
            xOffset += delta * 0.38f; 
            yOffset += delta * 0.38f;
        }
        
    }
 
    @Override
    public int getID() {
        return 1;
    }
    
}
    