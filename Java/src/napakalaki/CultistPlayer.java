
package napakalaki;
import java.util.Random;

public class CultistPlayer extends Player {
    
    private static int totalCultistsPlayers = 0;
    private final Cultist myCultistCard;
    
    public CultistPlayer(Player p, Cultist c){
        
        super(p);
        myCultistCard = c;
        CultistPlayer.totalCultistsPlayers++;

    }
    
    @Override
    public int getCombatLevel(){
        return (int)(1.7 * super.getCombatLevel()) + this.myCultistCard.getGainedBonus()*CultistPlayer.totalCultistsPlayers;
    }
    
    @Override
    public boolean shouldConvert(){
        return false;
    }
    
    @Override 
    protected int getOponentLevel(Monster m){
        return m.getCombatLevelAgainstCultistPlayer();
    }
    
    @Override
    protected Treasure giveMeATreasure(){
        
        return this.deleteAndReturnRandomIn(this.visibleTreasures);
    }

    @Override
    protected boolean canYouGiveMeATreasure(){
        return !this.visibleTreasures.isEmpty();
    }
    
    @Override
    public String toString(){
        return name + " (Sectario) " + " Nivel del jugador: " + level + " Nivel de combate: " + getCombatLevel() + " Enemigo: " + enemy.getName();
    }
    
    public static int getTotalCultistsPlayers(){
        return totalCultistsPlayers;
    }
  
}
