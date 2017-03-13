
package napakalaki;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;


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
        
 
        BadConsequence badConsequence = new BadConsequence("Estos monstruos resultan bastante superficiales y te abren mortalmente. Estás muerto.", true);
        Prize prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Los hondos", 8, prize, badConsequence));
        
        
        badConsequence = new BadConsequence("La familia te atrapa. Estás muerto.", true);
        prize = new Prize(3, 1);
        unusedMonsters.add(new Monster("Familia feliz", 1, prize, badConsequence));
        

        badConsequence = new BadConsequence("No le hace gracia que pronuncien mal su nombre. Estás muerto.", true);
        prize = new Prize(3, 1);
        unusedMonsters.add(new Monster("Yskhtihyssg-Goth", 14, prize, badConsequence));

        
        badConsequence = new BadConsequence("Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos", 3,
        new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS, TreasureKind.ONEHAND, TreasureKind.ONEHAND)), new ArrayList(Arrays.asList()));
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Bicéfalo", 21, prize, badConsequence));

        badConsequence = new BadConsequence("Pierdes tu armadura visible y otra oculta", 0, 1, 1);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("3 Byakhees de bonanza", 8, prize, badConsequence));

        badConsequence = new BadConsequence("Embobados con el lindo primigenio te descargas de tu casco visible", 0, new ArrayList(Arrays.asList(TreasureKind.HELMET)), new ArrayList(Arrays.asList()));
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Tenochtitlan", 2, prize, badConsequence));

        badConsequence = new BadConsequence("El primordial bostezo contagioso. Pierdes el calzado visible.", 0,
                new ArrayList(Arrays.asList(TreasureKind.SHOES)), new ArrayList(Arrays.asList()));
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("El sopor de Dunwich", 2, prize, badConsequence));

        badConsequence = new BadConsequence("Te atrapan para llevarte de fiesta y dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta.", 0,
                new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), new ArrayList(Arrays.asList(TreasureKind.ONEHAND)));
        prize = new Prize(4, 1);
        unusedMonsters.add(new Monster("Demonios de Magaluf", 2, prize, badConsequence));

        badConsequence = new BadConsequence("Pierdes todos tus tesoros visibles", 0, 6, 0);
        prize = new Prize(3, 1);
        unusedMonsters.add(new Monster("El gorrón en el umbral", 13, prize, badConsequence));

        badConsequence = new BadConsequence("Pierdes la armadura visible.", 0, new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList(Arrays.asList()));
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("H.P. Munchcraft", 6, prize, badConsequence));

        badConsequence = new BadConsequence("Sientes bichos bajo la ropa. Descarta la armadura visible.", 0, new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList(Arrays.asList()));
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Necrófago", 13, prize, badConsequence));

        badConsequence = new BadConsequence("Pierdes 5 niveles y 3 tesoros visibles", 5, 3, 0);
        prize = new Prize(3, 2);
        unusedMonsters.add(new Monster("El rey de rosado", 11, prize, badConsequence));

        badConsequence = new BadConsequence("Toses los pulmones y pierdes 2 niveles", 2, 0, 0);
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Flecher", 2, prize, badConsequence));

        badConsequence = new BadConsequence("Pierdes 2 niveles y 2 tesoros ocultos", 2, 0, 2);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Semillas Cthulhu", 4, prize, badConsequence));

        badConsequence = new BadConsequence("Te intentas escapar. Pierdes una mano visible.", 0, new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), new ArrayList(Arrays.asList()));
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Dameargo", 1, prize, badConsequence));

        badConsequence = new BadConsequence("Da mucho asquito. Pierdes 3 niveles", 3, 0, 0);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Pollipolipo volante", 3, prize, badConsequence));

        badConsequence = new BadConsequence("La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible", 2,
                new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS)), new ArrayList(Arrays.asList()));
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Roboggoth", 8, prize, badConsequence));

        badConsequence = new BadConsequence("Te asusta en la noche. Pierdes un casco visible.", 0, new ArrayList(Arrays.asList(TreasureKind.HELMET)), new ArrayList(Arrays.asList()));
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("El espia sordo", 5, prize, badConsequence));

        badConsequence = new BadConsequence("Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles", 2, 5, 0);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Tongue", 19, prize, badConsequence));
    }
    
    private void shuffleMonsters() {
        
        Collections.shuffle(this.unusedMonsters);
        
    }
    
    private void shuffleTreasures() {
        
        Collections.shuffle(this.unusedTreasures);
      
    }
    
    public Treasure nextTreasure(){
        
        if(this.unusedTreasures.isEmpty()){
                
            for (Treasure t: this.usedTreasures) {
                
                this.unusedTreasures.add(t);
            }
            
            this.shuffleTreasures();
            
            this.usedTreasures.clear();
            
        }
                  
        Treasure t = this.unusedTreasures.remove(0);
        
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
