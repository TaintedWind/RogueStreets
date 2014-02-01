package roguestreets;

import java.awt.Rectangle;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;

import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{

    public String mouse = "NO INPUT";

    Image backG;

    private Rectangle PlayB = new Rectangle(100,100,100,20);


    public Menu(int state){

    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        backG = new Image("assets/imgs/city_bg1.jpg");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        backG.draw(0, 0);

        g.setColor(Color.cyan);
        g.fillRoundRect(100, 100, 100, 20, 2);

        g.setColor(Color.black);
        g.drawString(mouse, 950, 20);

        g.drawString("PLAYGAME", 102, 102);


    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{

        Input input = gc.getInput();

        //track the mouse position for testing
        int xpos = Mouse.getX();
        int ypos = (int)Game.gameDim.getHeight() - Mouse.getY();
        mouse = "Mouse position x: " + xpos + " y: " + ypos;
        
        if (PlayB.contains(xpos, ypos)){
            if(input.isMouseButtonDown(0)){
                sbg.enterState(1);
            }
        }

    }

    public int getID(){
       return 0;
    }
}
