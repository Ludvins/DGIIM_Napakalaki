/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author ludvins
 */
public class CardDealer {
    
    private static CardDealer instance = null;
    private ArrayList<Monster> usedMonsters;
    private ArrayList<Monster> unusedMonsters;
    private ArrayList<Treasure> usedTreasures;
    private ArrayList<Treasure> unusedTreasures;
    
    private CardDealer() {
        
    }
    
    public static CardDealer getInstance() {
        
        if (instance == null) {
            instance = new CardDealer();
        }
        return instance;
}
    
    
    private void initTreasureCardDeck () {
        
        unusedTreasures = new ArrayList();
        usedTreasures = new ArrayList(); 

    } 
    
    private void initMonsterCardDeck () {
      
        unusedMonsters = new ArrayList();
        usedMonsters = new ArrayList();
        
    }
    
    private void shuffleMonsters() {
        
        Collections.shuffle(this.unusedMonsters);
        
    }
    
    private void shuffleTreasures() {
        
        Collections.shuffle(this.unusedTreasures);
      
    }
    
    public Treasure nextTreasure(){
        
        if(unusedTreasures.isEmpty()){
                
            for (Treasure t: this.usedTreasures) {
                
                this.unusedTreasures.add(t);
            }
            
            this.shuffleTreasures();
            
            this.usedTreasures.clear();
            
        }
                  
        Treasure t = unusedTreasures.remove(0);
        
        this.usedTreasures.add(t);
        
        return t;
        
    }
    
    public Monster nextMonster(){
        
        if (unusedMonsters.isEmpty()){
            
            for (Monster m : this.usedMonsters){
                
                this.unusedMonsters.add(m);
            
            }
            
            this.shuffleMonsters();
            
            this.usedMonsters.clear();
        
        }
        
        Monster m = unusedMonsters.remove(0);
        
        this.usedMonsters.add(m);
        
        return m;
    }
    
    public void giveTreasureBack(Treasure t){
        
        this.usedTreasures.add(t);
        
    }
    
    public void giveMonsterBack(Monster m){
        
        this.usedMonsters.add(m);
        
    }
    
    public void initCards() {
        
        this.initTreasureCardDeck();
        this.shuffleTreasures();
        
        this.initMonsterCardDeck();
        this.shuffleMonsters();
    }
}
