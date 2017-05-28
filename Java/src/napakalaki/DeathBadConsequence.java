/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;


public class DeathBadConsequence extends NumericBadConsequence {
    
    private static final int MAXVISIBLETREASURES = 5; //Max visible treasures that a player can have.
    private static final int MAXHIDDENTREASURES = 4; //Max hidden treasures that a player can have.
    private static final int MAXLEVEL = 10; //Max level possible of a player.
    
    public DeathBadConsequence(String text){
        super(text, MAXLEVEL, MAXVISIBLETREASURES, MAXHIDDENTREASURES);
    }
    
    
    /*
    Super method does work but with this I throw off the min call.
    */
    @Override
    public NumericBadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h){
        return new NumericBadConsequence(text, 0 , v.size(), h.size());
    }
    
}
