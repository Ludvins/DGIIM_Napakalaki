/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Ludvins
 */
public class SpecificBadConsequence extends BadConsequence {
    
    private final ArrayList<TreasureKind> specificHiddenTreasures;
    private final ArrayList<TreasureKind> specificVisibleTreasures;
    
    public SpecificBadConsequence(String _text, int _levels, ArrayList<TreasureKind> _tVisible, ArrayList<TreasureKind> _tHidden){

        super(_text, _levels);
        specificVisibleTreasures = _tVisible;
        specificHiddenTreasures = _tHidden;
        
    }
    
    public ArrayList<TreasureKind> getSpecificHiddenTreasures() {
        return specificHiddenTreasures;
    }

    public ArrayList<TreasureKind> getSpecificVisibleTreasures() {
        return specificVisibleTreasures;
    }
    
    @Override
    public void substractVisibleTreasure(Treasure t){
        this.specificVisibleTreasures.remove(t.getType());
    }
    
    @Override
    public void substractHiddenTreasure(Treasure t){
        this.specificHiddenTreasures.remove(t.getType());
    }
    
    @Override
    public boolean isEmpty(){
        return this.specificVisibleTreasures.isEmpty() && this.specificHiddenTreasures.isEmpty();
    }
    
    @Override
    public SpecificBadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h){
        
        ArrayList <TreasureKind> vt = v.stream().map(a -> a.getType()).collect(Collectors.toCollection(ArrayList::new));
        ArrayList <TreasureKind> ht = h.stream().map(a -> a.getType()).collect(Collectors.toCollection(ArrayList::new));

        vt.retainAll(this.specificVisibleTreasures);
        ht.retainAll(this.specificHiddenTreasures);

        return new SpecificBadConsequence(this.text, 0, vt, ht);
    }
    
    @Override
    public String toString() {
        return "\n\tText: " + text + "\n\tLevels: " + levels + "\n\tSpecificHiddenTreasures: " + specificHiddenTreasures + "\n\tSpecificVisibleTreasures: " + specificVisibleTreasures;
    }
    
    
}
