

package napakalaki;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author ludvins
 */

public class Player {
    
    protected String name;
    protected int level = 1;
    protected boolean dead = true; 
    protected boolean canISteal = true;
    protected ArrayList<Treasure> hiddenTreasures = new ArrayList();
    protected ArrayList<Treasure> visibleTreasures = new ArrayList(); 
    protected BadConsequence pendingBC;
    protected Player enemy;
  
    
    
    public Player (String name){
        
        this.name = name;
    }
    
    public Player(Player p){
        
        this.name = p.name;
        this.level = p.level;
        this.dead = p.dead;
        this.canISteal = p.canISteal;
        this.hiddenTreasures = p.hiddenTreasures;
        this.visibleTreasures = p.visibleTreasures;
        this.pendingBC = p.pendingBC;
        this.enemy = p.enemy;

    }
    
    public String getName() {
        
        return this.name;
    }
    
    private void bringToLife() {
        
        dead = false;
    }
    
    public boolean canISteal(){
        return canISteal;
    }
    
    protected int getCombatLevel() {

        return this.visibleTreasures.stream().map(Treasure::getBonus).reduce(this.level, Integer::sum);

    }
    
    public Player getEnemy(){
        return enemy;
    }
    
    private void incrementLevels(int l){
        
        this.level += l;
    }
    
    private void decrementLevels(int l){
        
        this.level = this.level <= l ? 1 : this.level-l;

    }
    
    public boolean shouldConvert(){
        
        Dice dice = Dice.getInstance();
        return dice.nextNumber() == 6;
        
    }
    
    private void setPendingBadconsequence (BadConsequence r){
        pendingBC = r;
    }
    
    private void applyPrize (Monster m) {
        
        this.incrementLevels(m.getPrize().getLevels());
        
        int nTreasures = m.getPrize().getTreasures();
            
        CardDealer cd = CardDealer.getInstance();
            
        for (int i = 0; i < nTreasures; i++) 
            this.hiddenTreasures.add(cd.nextTreasure());

        
    }
    
    private void applyBadConsequence (Monster m){
        
        this.decrementLevels(m.getBadconsequence().getLevels());
        
        if (m.getBadconsequence().isDeath()) this.dead = true;
        
        this.setPendingBadconsequence(m.getBadconsequence().adjustToFitTreasureLists(this.visibleTreasures, this.hiddenTreasures));
              
    }
    
    private boolean canMakeTreasureVisible(Treasure t){
        
        if (t.getType() != TreasureKind.ONEHAND && t.getType() != TreasureKind.BOTHHANDS && !this.isTreasureKindInUse(t.getType())) return true;
        
        if (t.getType() == TreasureKind.ONEHAND && !this.isTreasureKindInUse(TreasureKind.BOTHHANDS)  && this.howManyVisibleTreasures(TreasureKind.ONEHAND) < 2) return true;
    
        return t.getType() == TreasureKind.BOTHHANDS && !this.isTreasureKindInUse(TreasureKind.BOTHHANDS)  && this.howManyVisibleTreasures(TreasureKind.ONEHAND) == 0;
    
    }
    
    protected Treasure giveMeATreasure(){
        
        Random rd = new Random();
        Treasure t = this.hiddenTreasures.get(rd.nextInt() % this.hiddenTreasures.size());
        
        this.discardHiddenTreasure(t);
        
        return t;
    }
    
    private int howManyVisibleTreasures(TreasureKind tk){

        int b = Collections.frequency(this.visibleTreasures.stream().map(a -> a.getType()).collect(Collectors.toList()), tk);
        System.out.println(b);
        return b;
    }
    
    private boolean isTreasureKindInUse(TreasureKind type) {
        
        boolean c = this.visibleTreasures.stream().map( a -> a.getType() ).collect(Collectors.toList()).contains(type);
        System.out.println(c);
        return c;
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
    
    protected int getOponentLevel(Monster m){
        
        return m.getCombatLevel();
    }
    
    public CombatResult combat(Monster m) {
        
        Dice dice = Dice.getInstance();
        int n = dice.nextNumber();
        
        int level = getOponentLevel(m) + (!canISteal() && n < 3 ? this.enemy.getCombatLevel() : 0);
        
        if (this.getCombatLevel() <= level){
            
            this.applyBadConsequence(m);
            
            if (shouldConvert()) return CombatResult.LOSEANDCONVERT;
            
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
        
        return (this.pendingBC == null || this.pendingBC.isEmpty()) && this.hiddenTreasures.size() <= 4;
        
    }
    
    public void initTreasures(){
        
        CardDealer dealer = CardDealer.getInstance();
        
        Dice dice = Dice.getInstance();
        
        this.bringToLife();
        
        Treasure treasure;
        
        int number = dice.nextNumber();
        
        treasure = dealer.nextTreasure();
        this.hiddenTreasures.add(treasure);   
        
        if (number == 6) {
            
            treasure = dealer.nextTreasure();
            this.hiddenTreasures.add(treasure);
        }
        
        else if (number > 1){
            treasure = dealer.nextTreasure();
            this.hiddenTreasures.add(treasure);
        }
      
    }
    
    
    public int getLevels() {
        
        return this.level;
    }
    
    public Treasure stealTreasure(){
        
        if(!this.canISteal || !this.enemy.canYouGiveMeATreasure()) return null;
        
        Treasure t = this.enemy.giveMeATreasure();
        this.hiddenTreasures.add(t);
        
        this.haveStolen();
        
        return t;
    }   
    
    protected boolean canYouGiveMeATreasure(){
        return !this.hiddenTreasures.isEmpty();
    }
    
    public void haveStolen(){
        this.canISteal = false;
    }
    
    public void discardAllTreasures(){
        
        for(Treasure t : this.visibleTreasures){
            this.discardVisibleTreasure(t);
        }
        
        for(Treasure t : this.hiddenTreasures){
            this.discardHiddenTreasure(t);
        }
    }
    
    public void setEnemy (Player enemy){
        
        this.enemy = enemy;
    
    }
    
    @Override
    public String toString() {
        return name + " Nivel del jugador: " + level + " Nivel de combate: " + getCombatLevel() + " Enemigo: " + enemy.getName();
    }

}
