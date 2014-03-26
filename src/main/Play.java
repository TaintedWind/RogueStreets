package main;

import world.ObjList;
import org.newdawn.slick.Input;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import world.GlobalVariables;
import world.level.TutorialLevel;

public class Play extends BasicGameState{
    
    Input input;
    
    public player.Player player;
    
    //level testing
    TutorialLevel level1;
    
    public Play(int state){
        player = new player.Player(RogueStreets.gameDim.width/2,RogueStreets.gameDim.height/2);//create a player at centre of screen
        level1 = new TutorialLevel();
        
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException { 
        //initialize all things
        player.init();
        level1.init();
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        
        GlobalVariables.G = g; //global drawing thing
        
        level1.render(); //just the background
        
        g.setBackground(Color.black);
        g.setColor(Color.white);
        
        ObjList.renderOnscreenObjects();
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        //give delta to everything
        GlobalVariables.deltaTime = delta;
        //get input for local player
        input = gc.getInput();
        getLocalInput();
        
        //update everything
        ObjList.updateAllObjects();
        
    }
    //move everything in relation to offset position
    private void getLocalInput() {
        
        if(input.isKeyDown(Input.KEY_W)){
            player.up = true;
        } else {
            player.up = false;
        }
        if(input.isKeyDown(Input.KEY_S)){
            player.down = true;
        }else {
            player.down = false;
        }
        if(input.isKeyDown(Input.KEY_A)){
            player.left = true;
        }else {
            player.left = false;
        }
        if(input.isKeyDown(Input.KEY_D)){
            player.right = true;
        }else {
            player.right = false;
        }
        //reduce unecesary calculations redundencies
        if (player.up && player.down){
            player.up = false;
            player.down = false;
        }
        if (player.left && player.right){
            player.left = false;
            player.right = false;
        }
        
        //sneak
        if(input.isKeyDown(Input.KEY_LSHIFT)){
            player.sneak = true;
        }else {
            player.sneak = false;
        }
        
        //clicking
        if (input.isMouseButtonDown(1)){
            player.rightMouse = true;
        }else{
            player.rightMouse = false;
        }
        if (input.isMouseButtonDown(0)){
            player.leftMouse = true;
        }else{
            player.leftMouse = false;
        }
    }
    
 
    @Override
    public int getID() {
        return 1;
    }
    
}
    