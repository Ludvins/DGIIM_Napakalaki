

package napakalaki;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;
import GUI.Dice;


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
        return canISteal && (this.pendingBC == null || this.pendingBC.isEmpty());
    }
    
    public int getCombatLevel() {

        return this.visibleTreasures.stream().map(Treasure::getBonus).reduce(this.level, Integer::sum);

    }
    
    public BadConsequence getPendingBC(){
        return this.pendingBC;
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
        return dice.nextNumber("Has perdido el combate.","En caso de sacar un 6, te convertiras en Sectario.") == 6;
        
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
        
        this.setPendingBadconsequence(m.getBadconsequence().adjustToFitTreasureLists(this.visibleTreasures, this.hiddenTreasures));
              
    }
    
    private boolean canMakeTreasureVisible(Treasure t){
        
        if (t.getType() != TreasureKind.ONEHAND && t.getType() != TreasureKind.BOTHHANDS && !this.isTreasureKindInUse(t.getType())) return true;
        
        if (t.getType() == TreasureKind.ONEHAND && !this.isTreasureKindInUse(TreasureKind.BOTHHANDS)  && this.howManyVisibleTreasures(TreasureKind.ONEHAND) < 2) return true;
    
        return t.getType() == TreasureKind.BOTHHANDS && !this.isTreasureKindInUse(TreasureKind.BOTHHANDS)  && this.howManyVisibleTreasures(TreasureKind.ONEHAND) == 0;
    
    }
    
    protected Treasure giveMeATreasure(){
        
        return this.deleteAndReturnRandomIn(hiddenTreasures);
    }
    
    protected Treasure deleteAndReturnRandomIn(ArrayList<Treasure> list){
        
        Random rd = new Random();
        Treasure t = list.get(rd.nextInt() % list.size());
        
        list.remove(t);
        
        this.dieIfNoTreasures();
        
        return t;
    }
    
    private int howManyVisibleTreasures(TreasureKind tk){

        int b = Collections.frequency(this.visibleTreasures.stream().map(a -> a.getType()).collect(Collectors.toList()), tk);
        return b;
    }
    
    private boolean isTreasureKindInUse(TreasureKind type) {
        
        boolean c = this.visibleTreasures.stream().map( a -> a.getType() ).collect(Collectors.toList()).contains(type);
        return c;
    }
    
    private void dieIfNoTreasures() {
        
        if(this.visibleTreasures.isEmpty() && this.hiddenTreasures.isEmpty()) 
            this.dead = true;    
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
        
        int lv = getOponentLevel(m) + (!canISteal() && dice.nextNumber("Determinará si se suma el nivel del enemigo.", "Esto ocurrirá en caso de sacar 1 o 2.") < 3 ? this.enemy.getCombatLevel() : 0);
        
        if (this.getCombatLevel() <= lv){
            
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

    public void discardHiddenTreasure(Treasure t){
        
        CardDealer cd = CardDealer.getInstance();
        
        cd.giveTreasureBack(t);

        this.hiddenTreasures.remove(t);
        
        if (this.pendingBC != null && !this.pendingBC.isEmpty()) {

            this.pendingBC.substractHiddenTreasure(t);

        }

        this.dieIfNoTreasures();
    }
    
     public void discardVisibleTreasure(Treasure t){

        CardDealer cd = CardDealer.getInstance();
        
        cd.giveTreasureBack(t);
                
        this.visibleTreasures.remove(t);
        
        if (this.pendingBC != null && !this.pendingBC.isEmpty()) {

            this.pendingBC.substractVisibleTreasure(t);

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
        
        int number = dice.nextNumber("INICIALIZACIÓN DE TESOROS"," ");
        
        
        treasure = dealer.nextTreasure();
        this.hiddenTreasures.add(treasure);   
        
        if (number == 6) {
            
            treasure = dealer.nextTreasure();
            this.hiddenTreasures.add(treasure);
        }
        if (number > 1){
            treasure = dealer.nextTreasure();
            this.hiddenTreasures.add(treasure);
        }
      
    }
    
    
    public int getLevels() {
        
        return this.level;
    }
    
    public Treasure stealTreasure(){
        
        if(!isAbleToSteal()) return null;
        
        Treasure t = this.enemy.giveMeATreasure();
        this.hiddenTreasures.add(t);
        
        this.haveStolen();
        
        return t;
    }   
    
    protected boolean canYouGiveMeATreasure(){
        return !this.hiddenTreasures.isEmpty();
    }
    
    public boolean isAbleToSteal(){
        return this.canISteal && this.enemy.canYouGiveMeATreasure();
    }
    
    public void haveStolen(){
        this.canISteal = false;
    }
    
    public void discardAllTreasures(){
        
        ArrayList<Treasure> visTreasures = new ArrayList(visibleTreasures);
        ArrayList<Treasure> hidTreasures = new ArrayList(hiddenTreasures);

        for(Treasure t : visTreasures){
            this.discardVisibleTreasure(t);
        }
        
        for(Treasure t : hidTreasures){
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
