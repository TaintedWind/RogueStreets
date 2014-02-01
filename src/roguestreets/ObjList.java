package roguestreets;

import java.util.LinkedList;

public class ObjList {

    public static LinkedList<Object> Objects = new LinkedList<Object>();
    public static LinkedList<player.Player> Players = new LinkedList<player.Player>();
    
    public static void updateAllObjects(){
        
        for (int i = 0 ; i != Players.size(); i++){
            Players.get(i).update();
        }
        
    }
    
    
    
}
