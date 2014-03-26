package world;

import objects.Wall;
import objects.Entity;
import java.util.LinkedList;
import main.RogueStreets;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import objects.enemy.BasicEnemy;

public class ObjList {

    public static LinkedList<Shape> Objects = new LinkedList<>();
    public static LinkedList<Shape> ObjectsRendered = new LinkedList<>();
    
    public static LinkedList<Wall> World = new LinkedList<>(); //make a parent LevelObject class to replace temp 'Wall'
    public static LinkedList<Wall> WorldRendered = new LinkedList<>();
    
    public static LinkedList<objects.enemy.BasicEnemy> Mobs = new LinkedList<>();
    public static LinkedList<objects.enemy.BasicEnemy> MobsRendered = new LinkedList<>();
    
    public static LinkedList<player.Player> Players = new LinkedList<>();
    public static LinkedList<player.Player> PlayersRendered = new LinkedList<>();
    
    
    public static void updateAllObjects(){
        //update everything each loop if here
        for (int i = 0 ; i < Players.size(); i++){
            Players.get(i).update();
            
            //check if onscreen
            if (isOnscreen(Players.get(i))){
                if (PlayersRendered.contains(Players.get(i)) == false){
                    PlayersRendered.add(Players.get(i));
                }
            }else{
                if (PlayersRendered.contains(Players.get(i))){
                    PlayersRendered.remove(Players.get(i));
                }
            }
        }
        
        //update all mobs
        //requires two loops to preven 0 index value crash
        for (int i = 0 ; i < Mobs.size(); i++){
            Mobs.get(i).update();
        }
        for (int i = 0 ; i < Mobs.size(); i++){
            if (isOnscreenLocation(Mobs.get(i))){
                if (MobsRendered.contains(Mobs.get(i)) == false){
                    MobsRendered.add(Mobs.get(i));
                }
            }else{
                if (MobsRendered.contains(Mobs.get(i))){
                    MobsRendered.remove(Mobs.get(i));
                }
            }
        }
        
        //update all the level objects in the world
        for (int i = 0 ; i < World.size(); i++){
            World.get(i).update();
            
            //check if onscreen
            if (isOnscreen(World.get(i))){
                if (WorldRendered.contains(World.get(i)) == false){
                    WorldRendered.add(World.get(i));
                }
            }else{
                if (WorldRendered.contains(World.get(i))){
                    WorldRendered.remove(World.get(i));
                }
            }
        }
        
        /*
        for (int i = 0 ; i < Objects.size(); i++){
            Objects.get(i).update();
        }
        */
        
    }
    
    public static void renderOnscreenObjects() throws SlickException {
        
        //render only onscreen things(the things on the lists)
        
        
        //render the extra players if they are there    0.0
        if (WorldRendered.size() > 0){
            for (int xi = 0 ; xi < WorldRendered.size(); xi++){
                WorldRendered.get(xi).render(GlobalVariables.G);
            }
        }
        /*
        if (ObjectsRendered.size() > 1){
            for (int xi = 1 ; xi < ObjectsRendered.size(); xi++){
                ObjectsRendered.get(xi).render();
            }
        }
        */
        
        //render the mobs onscreen
        if (MobsRendered.size() > 0){
            for (int xi = 0 ; xi < MobsRendered.size(); xi++){
                MobsRendered.get(xi).render(GlobalVariables.G);
            }
        }
        
        //render the player on top of basically everything
        PlayersRendered.getFirst().render(GlobalVariables.G);
        
        //render the extra players if they are there    0.0
        if (PlayersRendered.size() > 1){
            for (int xi = 1 ; xi < PlayersRendered.size(); xi++){
                PlayersRendered.get(xi).render(GlobalVariables.G);
            }
        }
        
    }
    
    
    //to check if the object is onscreen to be updated
    private static boolean isOnscreen(Entity e){
        boolean isOn;//checks a little off the screen for proper rendering
        if (e.x >= -300 && e.x <= (RogueStreets.WIDTH+300) && e.y >= -300 && e.y <= (RogueStreets.HEIGHT+300)){
            isOn = true;
        }else{
            isOn = false;
        }
        return isOn;
    }
    //to check if the object is onscreen to be updated
    private static boolean isOnscreenLocation(BasicEnemy e){
        boolean isOn;//checks a little off the screen for proper rendering
        if (e.xLocation >= -300 && e.xLocation <= (RogueStreets.WIDTH+300) && e.yLocation >= -300 && e.yLocation <= (RogueStreets.HEIGHT+300)){
            isOn = true;
        }else{
            isOn = false;
        }
        return isOn;
    }
    
    //check if the levelobjects is on screen
    private static boolean isOnscreen(Wall e){
        boolean isOn;//checks a little off the screen for proper rendering
        if ((e.xLocation + e.w/2) >= -500 && (e.xLocation + e.w/2) <= (RogueStreets.WIDTH+500) 
                && (e.yLocation + e.l/2) >= -500 && (e.yLocation + e.l/2) <= (RogueStreets.HEIGHT+500)){
            isOn = true;
        }else{
            isOn = false;
        }
        return isOn;
    }
    
}
