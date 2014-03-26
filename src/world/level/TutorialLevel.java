package world.level;

import main.RogueStreets;
import objects.Wall;
import objects.enemy.BasicEnemy;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import world.ObjList;

 
//all that is needed is to create all the instances and set their initial locations
//then ti initialize them
public class TutorialLevel {//testing level for loading objects and such
    
    Image bgImage = null;//TEMP
    Image bgTile = null;//TEMP
    Image bg1 = null;
    
    Wall testWall,testWall2;
    
    Wall spawnwall1,spawnwall2,spawnwall3,spawnwall4;
    
    BasicEnemy zombie;
    
    public TutorialLevel(){
        testWall = new Wall(300,300,80,50,90);
        testWall2 = new Wall(500,200,80,50,45);
        zombie = new BasicEnemy(600,600);  
        //zombie2 = new BasicEnemy(-600,242);  
        //testPlayer = new Player(0,0);
        
        spawnwall1 = new Wall(0,-10,1000,80,0);
        spawnwall1 = new Wall(500,300,700,80,80);
        
    }
    
    public void init(){
        zombie.init();
        //zombie2.init();
        getImg();
    }
    
    public void getImg(){
        //get the bg image
        try{
            bgImage = new Image("assets/imgs/mossconcrete_bg1.png");
        }catch(SlickException e){
        }
        try{
            bgTile = new Image("assets/imgs/tile-1.jpg");
        }catch(SlickException e){
        }
        try{
            bg1 = new Image("assets/imgs/level-1.png");
        }catch(SlickException e){
        }
    }
    
    public void render(){
        //tileImage();
        bg1.draw(ObjList.Players.getFirst().xOffset,ObjList.Players.getFirst().yOffset,RogueStreets.scale);//temp draw of the world
    }
    
    public void tileImage(){
        
        int xx, yy;
        
        int width, height;

        width = this.bgTile.getWidth() / 3;
        height = this.bgTile.getHeight() / 3;

        if(width > 0 && height > 0){
            for(xx = -500; xx < (width * 8) ; xx += width * 3){
                for(yy = -500; yy < (height * 8) ; yy += height * 3){
                    int locationX = xx + ObjList.Players.getFirst().xOffset;
                    int locationY = yy + ObjList.Players.getFirst().yOffset;
                    
                    //check if the image is onscreen, then to draw if it is
                    if ((locationX > -300) && (locationX < main.RogueStreets.WIDTH + 300) && (locationY > -300) && (locationY < main.RogueStreets.HEIGHT + 300)){
                        bgTile.draw(locationX , locationY,RogueStreets.scale/3);
                    }
                }
            }
        }
	
    }
    
}
