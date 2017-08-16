
package napakalaki;

public class CultistPlayer extends Player {
    
    private static int totalCultistsPlayers = 0;
    private final Cultist myCultistCard;
    
    public CultistPlayer(Player p, Cultist c){
        
        super(p);
        myCultistCard = c;
        CultistPlayer.totalCultistsPlayers++;

    }
    
    //CombatLevel of a cultist is increased.
    @Override
    public int getCombatLevel(){
        return (int)(1.7 * super.getCombatLevel()) + this.myCultistCard.getGainedBonus()*CultistPlayer.totalCultistsPlayers;
    }
    
    //A cultist can't convert again.
    @Override
    public boolean shouldConvert(){
        return false;
    }
    
    //Monster might have a different combat level versus a cultist.
    @Override 
    protected int getOponentLevel(Monster m){
        return m.getCombatLevelAgainstCultistPlayer();
    }
    
    //When stolen they lose a visible treasure.
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
        return super.toString() + "(Sectario)";
    }
    
    public static int getTotalCultistsPlayers(){
        return totalCultistsPlayers;
    }
  
}
