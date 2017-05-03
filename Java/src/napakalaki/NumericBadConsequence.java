/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;

/**
 *
 * @author Ludvins
 */
public class NumericBadConsequence extends BadConsequence{
    
    protected int nVisibleTreasures;
    protected int nHiddenTreasures;
    
    public NumericBadConsequence(String text, int levels, int nVisible, int nHidden){
        this.text = text;
        this.levels = levels;
        this.nVisibleTreasures = nVisible;
        this.nHiddenTreasures = nHidden;
        this.death = false;
    }
    
    @Override
    public boolean isEmpty(){
       return this.nHiddenTreasures == 0 && this.nVisibleTreasures == 0;
    }
    
    @Override
    public NumericBadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h){
        
        return new NumericBadConsequence(text, 0, Math.min(nVisibleTreasures, v.size()), Math.min(nHiddenTreasures, h.size()));
         
    }
    
    @Override
    public void substractVisibleTreasure(Treasure t){
        if (this.nVisibleTreasures > 0) this.nVisibleTreasures--;
    }
    
    @Override
    public void substractHiddenTreasure(Treasure t){
        if (this.nHiddenTreasures > 0 ) this.nHiddenTreasures--;
    }
    
    public int getnVisibleTreasures() {
        return nVisibleTreasures;
    }

    public int getnHiddenTreasures() {
        return nHiddenTreasures;
    }

    @Override
    public String toString() {
        return "\n\tText: " + text + "\n\tLevels: " + levels + "\n\tnVisibleTreasures: " + nVisibleTreasures + "\n\tnHiddenTreasures: " + nHiddenTreasures + "\n\tDeath: " + death;
    }
}
