

package napakalaki;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ludvins
 */

public class Player {
    
    private String name;
    private int level = 1;
    private boolean dead = true; 
    private boolean canISteal = true;
    private ArrayList<Treasure> hiddenTreasures = new ArrayList();
    private ArrayList<Treasure> visibleTreasures = new ArrayList(); 
    private BadConsequence pendingBC;
    private Player enemy;
  
    
    
    public Player (String name){
        
        this.name = name;
    }
    
    public String getName() {
        
        return this.name;
    }
    
    private void bringToLife() {
        
        dead = false;
    }
    
    private int getCombatLevel() {
        
        int lv = this.level;
        
        for (Treasure t : this.visibleTreasures){
            lv += t.getBonus();
        }
        
        return lv;
    }
    
    private void incrementLevels(int l){
        
        this.level += l;
    }
    
    private void decrementLevels(int l){
        
        if(this.level <= l) this.level = 1;
        else
            this.level -= l;
    }
    
    private void setPendingBadconsequence (BadConsequence r){
        pendingBC = r;
    }
    
    private void applyPrize (Monster m) {
        
        this.incrementLevels(m.getPrize().getLevels());
        
        int nTreasures = m.getPrize().getTreasures();
            
        CardDealer cd = CardDealer.getInstance();
            
        for (int i = 0; i < nTreasures; i++) this.hiddenTreasures.add(cd.nextTreasure());

        
    }
    
    private void applyBadConsequence (Monster m){
        
        this.decrementLevels(m.getBadconsequence().getLevels());
        
        this.setPendingBadconsequence(m.getBadconsequence().adjustToFitTreasureLists(this.visibleTreasures, this.hiddenTreasures));
              
    }
    
    private boolean canMakeTreasureVisible(Treasure t){
        
        if (t.getType() != TreasureKind.ONEHAND && !this.isTreasureKindInUse(t.getType())) return true;
        
        return t.getType() == TreasureKind.ONEHAND && this.howManyVisibleTreasures(TreasureKind.ONEHAND) < 2;
    }
    
    private int howManyVisibleTreasures(TreasureKind tk){
     
        int ret = 0;
        
        for (Treasure t : this.visibleTreasures){
            
            if(t.getType() == tk) ret++;
        }
        
        return ret;
    }
    
    private boolean isTreasureKindInUse(TreasureKind type) {
        
        
        for (Treasure t : this.visibleTreasures) {

            if (type == t.getType()) {

                return true;
                

            }

        }
        
        return false;
    }
    
    private void dieIfNoTreasures() {
        
        if(this.visibleTreasures.isEmpty() && this.hiddenTreasures.isEmpty()) this.dead = true;
                
    }
    
    public boolean isDead() {
        
        return this.dead;
    }

    public ArrayList<Treasure> getHiddenTreasures() {
        return hiddenTreasures;
    }

    public ArrayList<Treasure> getVisibleTreasures() {
        return visibleTreasures;
    }
    
    public CombatResult combat(Monster m) {
        
        if (this.getCombatLevel() <= m.getCombatLevel()){
            
            this.applyBadConsequence(m);
            
            return CombatResult.LOSE;
        }
        
        
        this.applyPrize(m);
        
        if(this.level >= 10) return CombatResult.WINGAME;
        
        return CombatResult.WIN;
        
    }
    
    public void makeTreasureVisible(Treasure t){
        
        if(this.canMakeTreasureVisible(t)) {
            
            this.visibleTreasures.add(t);
            
            this.hiddenTreasures.remove(t);
            
        }
    }
    
    public void discardVisibleTreasure(Treasure t){
        
        this.visibleTreasures.remove(t);
        
        if (this.pendingBC != null && !this.pendingBC.isEmpty()) {

            this.pendingBC.substractVisibleTreasure(t);

        }
        
        this.dieIfNoTreasures();
    
    }
    
    
    public void discardHiddenTreasure(Treasure t){
        
        this.hiddenTreasures.remove(t);
        
        if (this.pendingBC != null && !this.pendingBC.isEmpty()) {

            this.pendingBC.substractHiddenTreasure(t);

        }

        this.dieIfNoTreasures();
    }
    
    public boolean ValidState(){
        
        return this.pendingBC == null || (this.pendingBC.isEmpty() && this.hiddenTreasures.size() <= 4);
        
    }
    
    public void initTreasures(){
        
        CardDealer dealer = CardDealer.getInstance();
        
        Dice dice = Dice.getInstance();
        
        this.bringToLife();
        
        Treasure treasure;
        
        int number = dice.nextNumber();
        
        switch (number) {
            
            case 6:
                
                treasure = dealer.nextTreasure();
                this.hiddenTreasures.add(treasure);
            
            case 5:
            case 4:
            case 3:
            case 2:
                treasure = dealer.nextTreasure();
                this.hiddenTreasures.add(treasure);
                
            case 1:
                treasure = dealer.nextTreasure();
                this.hiddenTreasures.add(treasure);
            
        }
      
    }
    
    
    public int getLevels() {
        
        return this.level;
    }
    
    public Treasure stealTreasure(){
        
        Random rd = new Random();
        int number = rd.nextInt() % this.hiddenTreasures.size();
        
        Treasure t = this.hiddenTreasures.get(number);
        this.hiddenTreasures.remove(number);
        return t;
        
    }   
    
    public void setEnemy (Player enemy){
        
        this.enemy = enemy;
    
    }

}
