

package napakalaki;

/**
 *
 * @author ludvins
 */
public class Treasure {
    private String name;
    private int bonus;
    private TreasureKind type;
    
    public Treasure (String name, int bonus, TreasureKind tk){
        
        this.name = name;
        this.bonus = bonus;
        this.type = tk;
    
    }

    public String getName() {
        return name;
    }

    public int getBonus() {
        return bonus;
    }

    public TreasureKind getType() {
        return type;
    }
    
    @Override
    public String toString(){
        return "Name: " + name + "\n\tTipo: " + type + "\n\tBonus: " + bonus;
    }
    
}
