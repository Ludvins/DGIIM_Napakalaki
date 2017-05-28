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
    
    private final int levels;
    private final String name;
    
    public Cultist(int levels){
        this.name = "Cultist";
        this.levels = levels;
    }
    
    public Cultist(int levels, String name){
        this.name = name;
        this.levels = levels;
    }
    
    public int getGainedBonus(){
        return this.levels;
    }
    
}
