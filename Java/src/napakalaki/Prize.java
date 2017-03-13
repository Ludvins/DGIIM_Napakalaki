

package napakalaki;

/**
 *
 * @author ludvins
 */
public class Prize {
    private final int treasures;
    private final int levels;
    
    
    public Prize (int _treasures, int _levels){
        treasures = _treasures;
        levels = _levels;        
    }
    
    
    public int getTreasures(){ return treasures;}
    
    public int getLevels() {return levels;}

    @Override
    public String toString() {
        return "Prize{" + "treasures=" + treasures + ", levels=" + levels + '}';
    }
    
    
}
