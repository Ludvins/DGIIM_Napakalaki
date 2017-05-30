
package napakalaki;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;


public class CardDealer {
    
    private static CardDealer instance = null;
    private ArrayList<Monster> usedMonsters;
    private ArrayList<Monster> unusedMonsters;
    private ArrayList<Treasure> usedTreasures;
    private ArrayList<Treasure> unusedTreasures;
    private ArrayList<Cultist> unusedCultists;
    private ArrayList<Cultist> usedCultists;
    
    private CardDealer() {};
    
    public static CardDealer getInstance() {
        
        if (instance == null) {
            instance = new CardDealer();
        }
        return instance;
}
    
    
    private void initCultistsCardDeck() {
        
        unusedCultists = new ArrayList();
        usedCultists = new ArrayList();
        
        unusedCultists.add(new Cultist(1));
        unusedCultists.add(new Cultist(1));
        unusedCultists.add(new Cultist(1));
        unusedCultists.add(new Cultist(1));
        unusedCultists.add(new Cultist(2));
        unusedCultists.add(new Cultist(2));
        
    }
    
    private void initTreasureCardDeck () {
        
        unusedTreasures = new ArrayList();
        usedTreasures = new ArrayList(); 
        
        unusedTreasures.add(new Treasure("Linterna a 2 manos", 3, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Necronomicon", 5, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Gaita", 4, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Botas de lluvia acida", 1, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Lanzallamas", 4, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Escopeta de 3 cañones", 3, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Ametralladora ACME", 4, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Shogulador", 1, TreasureKind.BOTHHANDS));
        
        unusedTreasures.add(new Treasure("Necrotelecom", 2, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Casco minero", 2, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Tentáculo de pega", 2, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Capucha de Cthulhu", 3, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Fez alopodo", 3, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("¡Si mi amo!", 4, TreasureKind.HELMET));
        
        unusedTreasures.add(new Treasure("Camiseta de la ETSIIT", 1, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("La rebecametalica", 2, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("A prueba de babas", 2, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("El aparato del Pr. Tesla", 4, TreasureKind.ARMOR));
        
        unusedTreasures.add(new Treasure("Garabato mistico", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necrocomicon", 1, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necroplayboycon", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Hacha prehistorica", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Mazo de los antiguos", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Clavo de rail ferroviario", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Porra preternatural", 1, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Cuchillo de sushi arcano", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Varita de atizamiento", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necrognomicon", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Insecticida", 2, TreasureKind.ONEHAND));
        
        unusedTreasures.add(new Treasure("Zapato deja-amigos", 1, TreasureKind.SHOES));
        unusedTreasures.add(new Treasure("botas de investigacion", 3, TreasureKind.SHOES));
        
        
    } 
    
    private void initMonsterCardDeck () {
      
        unusedMonsters = new ArrayList();
        usedMonsters = new ArrayList();
        
 
        DeathBadConsequence badConsequence = new DeathBadConsequence("Estos monstruos resultan bastante superficiales y te abren mortalmente. Estás muerto.");
        Prize prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Los hondos", 8, prize, badConsequence));
        
        
        badConsequence = new DeathBadConsequence("La familia te atrapa. Estás muerto.");
        prize = new Prize(3, 1);
        unusedMonsters.add(new Monster("Familia feliz", 1, prize, badConsequence));
        

        badConsequence = new DeathBadConsequence("No le hace gracia que pronuncien mal su nombre. Estás muerto.");
        prize = new Prize(3, 1);
        unusedMonsters.add(new Monster("Yskhtihyssg-Goth", 14, prize, badConsequence));

        
        SpecificBadConsequence badConsequence2 = new SpecificBadConsequence("Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos", 3,
        new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS, TreasureKind.ONEHAND, TreasureKind.ONEHAND)), new ArrayList(Arrays.asList()));
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Bicéfalo", 21, prize, badConsequence2));

        badConsequence2 = new SpecificBadConsequence("Pierdes tu armadura visible y otra oculta", 0, new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList(Arrays.asList(TreasureKind.ARMOR)));
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("3 Byakhees de bonanza", 8, prize, badConsequence2));

        badConsequence2 = new SpecificBadConsequence("Embobados con el lindo primigenio te descargas de tu casco visible", 0, new ArrayList(Arrays.asList(TreasureKind.HELMET)), new ArrayList(Arrays.asList()));
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Tenochtitlan", 2, prize, badConsequence2));

        badConsequence2 = new SpecificBadConsequence("El primordial bostezo contagioso. Pierdes el calzado visible.", 0,
                new ArrayList(Arrays.asList(TreasureKind.SHOES)), new ArrayList(Arrays.asList()));
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("El sopor de Dunwich", 2, prize, badConsequence2));

        badConsequence2 = new SpecificBadConsequence("Te atrapan para llevarte de fiesta y dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta.", 0,
                new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), new ArrayList(Arrays.asList(TreasureKind.ONEHAND)));
        prize = new Prize(4, 1);
        unusedMonsters.add(new Monster("Demonios de Magaluf", 2, prize, badConsequence2));

        NumericBadConsequence badConsequence3 = new NumericBadConsequence("Pierdes todos tus tesoros visibles", 0, 6, 0);
        prize = new Prize(3, 1);
        unusedMonsters.add(new Monster("El gorrón en el umbral", 13, prize, badConsequence3));

        badConsequence2 = new SpecificBadConsequence("Pierdes la armadura visible.", 0, new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList(Arrays.asList()));
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("H.P. Munchcraft", 6, prize, badConsequence2));

        badConsequence2 = new SpecificBadConsequence("Sientes bichos bajo la ropa. Descarta la armadura visible.", 0, new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList(Arrays.asList()));
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Necrófago", 13, prize, badConsequence2));

        badConsequence3 = new NumericBadConsequence("Pierdes 5 niveles y 3 tesoros visibles", 5, 3, 0);
        prize = new Prize(3, 2);
        unusedMonsters.add(new Monster("El rey de rosado", 11, prize, badConsequence3));

        badConsequence3 = new NumericBadConsequence("Toses los pulmones y pierdes 2 niveles", 2, 0, 0);
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Flecher", 2, prize, badConsequence3));

        badConsequence3 = new NumericBadConsequence("Pierdes 2 niveles y 2 tesoros ocultos", 2, 0, 2);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Semillas Cthulhu", 4, prize, badConsequence3));

        badConsequence2 = new SpecificBadConsequence("Te intentas escapar. Pierdes una mano visible.", 0, new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), new ArrayList(Arrays.asList()));
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Dameargo", 1, prize, badConsequence2));

        badConsequence3 = new NumericBadConsequence("Da mucho asquito. Pierdes 3 niveles", 3, 0, 0);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Pollipolipo volante", 3, prize, badConsequence3));

        badConsequence2 = new SpecificBadConsequence("La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible", 2,
                new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS)), new ArrayList(Arrays.asList()));
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Roboggoth", 8, prize, badConsequence2));

        badConsequence2 = new SpecificBadConsequence("Te asusta en la noche. Pierdes un casco visible.", 0, new ArrayList(Arrays.asList(TreasureKind.HELMET)), new ArrayList(Arrays.asList()));
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("El espia sordo", 5, prize, badConsequence2));

        badConsequence3 = new NumericBadConsequence("Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles", 2, 5, 0);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Tongue", 19, prize, badConsequence3));
        
        
        badConsequence2 = new SpecificBadConsequence("Pierdes 1 mano visible", 0, new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), new ArrayList(Arrays.asList()));
        prize = new Prize(3,1);
        unusedMonsters.add(new Monster("El mal indecible impronunciable", 10, prize, badConsequence2, -2));
        
        badConsequence3 = new NumericBadConsequence("Pierdes tus tesoros visibles. Jajaja", 0, 5, 0);
        prize = new Prize(3,1);
        unusedMonsters.add(new Monster("Testigos oculares", 6, prize, badConsequence3, 2));
        
        badConsequence = new DeathBadConsequence("Hoy no es tu dia de suerte. Mueres.");
        prize = new Prize(2,5);
        unusedMonsters.add(new Monster("El gran cthulhu", 20, prize, badConsequence, 4));
        
        badConsequence3 = new NumericBadConsequence("Tu gobierno te recorta 2 niveles.", 2, 0, 0);
        prize = new Prize(2,1);
        unusedMonsters.add(new Monster("Serpiente Politico", 8, prize, badConsequence3, -2));
        
        badConsequence2 = new SpecificBadConsequence("Pierdes tu casco y tu armadura visible. Pierdes tus manos ocultas.", 0, new ArrayList(Arrays.asList(TreasureKind.HELMET, TreasureKind.ARMOR)), new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS, TreasureKind.ONEHAND, TreasureKind.ONEHAND)));
        prize = new Prize(1,1);
        unusedMonsters.add(new Monster("Felpuggoth", 2, prize, badConsequence2, 5));
        
        badConsequence3 = new NumericBadConsequence("Pierdes 2 niveles",2, 0, 0);
        prize = new Prize(4,2);
        unusedMonsters.add(new Monster("Shoggoth", 16, prize, badConsequence3, -4));
        
        badConsequence3 = new NumericBadConsequence("Pintalabios negro. Pierdes 2 niveles",2, 0, 0);
        prize = new Prize(1,1);
        unusedMonsters.add(new Monster("LoLitagooth", 2, prize, badConsequence3, +3));
        
        
    }
    
    private void shuffleMonsters() {
        
        Collections.shuffle(this.unusedMonsters);
        
    }
    
    private void shuffleTreasures() {
        
        Collections.shuffle(this.unusedTreasures);
      
    }
    
    private void shuffleCultists() {
        
        Collections.shuffle(this.unusedCultists);
    }
    
    public Treasure nextTreasure(){
        
        if(this.unusedTreasures.isEmpty()){
                
            for (Treasure t: this.usedTreasures) {
                
                this.unusedTreasures.add(t);
            }
            
            this.shuffleTreasures();
            
            this.usedTreasures.clear();
            
        }
                  
        return this.unusedTreasures.remove(0);
        
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
    
    public Cultist nextCultist(){
        
        Cultist c = unusedCultists.remove(0);
        
        this.usedCultists.add(c);
        
        return c;
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
        
        this.initCultistsCardDeck();
        this.shuffleCultists();
    }
}
