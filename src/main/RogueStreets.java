package main;

import java.awt.Dimension;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class RogueStreets extends StateBasedGame{
   
   public static final String gamename = "Rogue Streets Dev 0.0.9";
   public static final int menu = 0;
   public static final int play = 1;
   
   public static final int WIDTH = 1280;//set the default size of the window
   public static final int HEIGHT = 720;
   public static final Dimension gameDim = new Dimension(WIDTH, HEIGHT); 
   
   public static final float scale = HEIGHT / 240;
   
   
   public RogueStreets(String gamename){
       
       super(gamename);
       
       this.addState(new Menu(menu));
       this.addState(new Play(play));
       
   }
   
   @Override
   public void initStatesList(GameContainer gc) throws SlickException{
       
       this.getState(menu).init(gc, this);
       this.getState(play).init(gc, this);
       
       this.enterState(menu);

   }
     
    public static void main(String[] args) {
        AppGameContainer appgc;
        try{

            appgc = new AppGameContainer(new RogueStreets(gamename));
            appgc.setDisplayMode((int)gameDim.getWidth(), (int)gameDim.getHeight(), false);
            appgc.setTargetFrameRate(120);
            appgc.start();
            
        }catch(SlickException e){
            e.printStackTrace();    
        }
    }

}
