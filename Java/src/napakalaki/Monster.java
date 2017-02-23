/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

/**
 *
 * @author ludvins
 */
public class Monster {
    
    private final String name;
    private final int combatLevel;
    private final Prize prize;
    private final BadConsequence badconsequence;

    public Monster(String name, int combatLevel, Prize prize, BadConsequence badconsequence) {
        this.name = name;
        this.combatLevel = combatLevel;
        this.prize = prize;
        this.badconsequence = badconsequence;
    }

    public String getName() {
        return name;
    }

    public int getCombatLevel() {
        return combatLevel;
    }

    public Prize getPrize() {
        return prize;
    }

    public BadConsequence getBadconsequence() {
        return badconsequence;
    }

    @Override
    public String toString() {
        return "Monster{" + "name=" + name + ", combatLevel=" + combatLevel + ", prize=" + prize + ", badconsequence=" + badconsequence + '}';
    }
    
    
}
