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
public class DeathBadConsequence extends NumericBadConsequence {
    
    public DeathBadConsequence(String text){
        super(text, 10, 5, 4);
        this.death = true;
    }
    
    @Override
    public NumericBadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h){
        return new NumericBadConsequence(text, 0 , v.size(), h.size());
    }
    
}
