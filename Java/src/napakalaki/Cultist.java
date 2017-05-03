/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

/**
 *
 * @author Ludvins
 */
public class Cultist {
    
    private int levels;
    
    public Cultist(int levels){
        this.levels = levels;
    }
    
    public int getGainedBonus(){
        return this.levels;
    }
    
}
