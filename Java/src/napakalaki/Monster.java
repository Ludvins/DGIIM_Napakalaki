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
    private final int levelChangeAgainstCultistPlayer;

    public Monster(String name, int combatLevel, Prize prize, BadConsequence badconsequence) {
        this.name = name;
        this.combatLevel = combatLevel;
        this.prize = prize;
        this.badconsequence = badconsequence;
        this.levelChangeAgainstCultistPlayer = 0;
    }
    
    public Monster(String name, int combatLevel, Prize prize, BadConsequence badconsequence, int levelChange) {
        this.name = name;
        this.combatLevel = combatLevel;
        this.prize = prize;
        this.badconsequence = badconsequence;
        this.levelChangeAgainstCultistPlayer = levelChange;
    }

    public String getName() {
        return name;
    }
    
    public int getCombatLevelAgainstCultistPlayer(){
        return this.combatLevel + this.levelChangeAgainstCultistPlayer;
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
        return "\nName: " + name + "\nCombatLevel: " + combatLevel + "\nPrize: " + prize + "\nBadconsequence: " + badconsequence;
    }
    
    
}
