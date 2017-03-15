/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ludvins
 */
public class Napakalaki {
    
    private static Napakalaki instance = null;
    private Monster currentMonster;
    private CardDealer cardDealer = CardDealer.getInstance();
    private Player currentPlayer;
    private ArrayList<Player> players; 
    
    private Napakalaki () {} 
    
    public Napakalaki getInstance () {
        
        if (instance == null) instance = new Napakalaki();
       
        return instance;
    }
    
    private void initPlayers(ArrayList<String> names){
        
        this.players = new ArrayList();

        for (String a : names){
            this.players.add(new Player(a));
        }
        
    }
    
    private void nextPlayer(){
        
        if(this.currentPlayer == null){
            this.currentPlayer = players.get(0);
        }
        else {
            this.currentPlayer = this.players.get(this.players.indexOf(this.currentPlayer) + 1);
        }
    }
    
    private boolean nextTurnAllowed(){
        
        boolean ret;
        
        if (this.currentPlayer == null) ret = true;
        
        else ret = this.currentPlayer.ValidState();
        
        return ret;
   
    }
    
    private void setEnemies() {
        
        Random rd = new Random();
        int n;
        
        do {
            
            n = rd.nextInt() % this.players.size();
        
        }
        while(n == this.players.indexOf(this.currentPlayer)); 
            
        this.currentPlayer.setEnemy(this.players.get(n));
        
    }
    
    public CombatResult developCombat(){
        
        CombatResult combat = this.currentPlayer.combat(this.currentMonster);
        
        // Sectario
        
        return combat;
    }
    
    public void discardVisibleTreasures(ArrayList<Treasure> treasures){
        
        for (Treasure t : treasures){
            
            this.currentPlayer.discardVisibleTreasure(t);
            
        }
        
    }
    
    public void discardHiddenTreasures(ArrayList<Treasure> treasures){
        
        for (Treasure t: treasures){
            
            this.currentPlayer.discardHiddenTreasure(t);
        
        }
    }
    
    public void makeTreasureVisible(ArrayList<Treasure> treasure){
        
        for (Treasure t : treasure){
            
            this.currentPlayer.makeTreasureVisible(t);
        }
    }
    
    public void initGame (ArrayList<String> names) {
        
        this.initPlayers(names);
        
        this.cardDealer.initCards();
        
        this.nextTurn();
    }
    
    public Player getCurrentPlayer(){
        
        return this.currentPlayer;
    }
    
    public Monster getCurrentMonster() {
        
        return this.currentMonster;
        
    }
    
    public boolean nextTurn(){
        
        boolean ret = this.nextTurnAllowed();
        
        if(ret){
            
            this.currentMonster = this.cardDealer.nextMonster();
            this.nextPlayer();
            
            boolean dead = this.currentPlayer.isDead();
            
            if (dead) this.currentPlayer.initTreasures();
            
        }
        
        return ret;
    }
    
    public boolean endOfGame(CombatResult cr){
        
        return cr == CombatResult.WINGAME;
    }
    
}
