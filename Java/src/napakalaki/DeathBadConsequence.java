/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;


public class DeathBadConsequence extends NumericBadConsequence {
    
    private static final int MAXVISIBLETREASURES = 5;
    private static final int MAXHIDDENTREASURES = 4;
    private static final int MAXLEVEL = 10;
    
    public DeathBadConsequence(String text){
        super(text, MAXLEVEL, MAXVISIBLETREASURES, MAXHIDDENTREASURES);
    }
    
    @Override
    public NumericBadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h){
        return new NumericBadConsequence(text, 0 , v.size(), h.size());
    }
    
}
