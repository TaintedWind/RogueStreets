package roguestreets;

import java.util.LinkedList;

public class ObjList {

    public static LinkedList<Object> Objects = new LinkedList<>();
    public static LinkedList<player.Player> Players = new LinkedList<>();
    
    
    public static void updateAllObjects(){
        
        for (int i = 0 ; i != Players.size(); i++){
            Players.get(i).update();
        }
        /*
        for (int i = 0 ; i != Objects.size(); i++){
            Objects.get(i).update();
        }
        */
        
    }
    
    
    
}
