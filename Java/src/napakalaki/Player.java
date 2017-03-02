/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;
import java.util.ArrayList;

/**
 *
 * @author ludvins
 */
public class Player {
    
    private String name;
    private int level = 1;
    private boolean dead = true; 
    private boolean canISteal = true;
    private ArrayList<Treasure> hiddenTreasures = new ArrayList();
    private ArrayList<Treasure> visibleTreasures = new ArrayList(); 
    
    
    public Player (String name){
        
        this.name = name;
    }
    
    public String getName() {
        
        return this.name;
    }
    
    private void bringToLife() {
        
        dead = false;
    }
    
    private int getCombatLevel() {
        
        int lv = this.level;
        
        for (Treasure t : this.visibleTreasures){
            lv += t.getBonus();
        }
        
        return lv;
    }
    
    private void incrementLevels(int l){
        
        this.level += l;
    }
    
    private void decrementLevels(int l){
        
        this.level -= l;
    }
    
    
    
}
