

package napakalaki;
import java.util.ArrayList;
/**
 *
 * @author ludvins
 */
public abstract class BadConsequence {
    protected String text;
    protected int levels;
    protected boolean death;
    
    protected BadConsequence(){ }

    public String getText() {
        return text;
    }

    public int getLevels() {
        return levels;
    }
    
    public boolean isDeath(){
        return death;
    }
    
    public abstract boolean isEmpty () ;          
    
    public abstract BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h);
    
    public abstract void substractVisibleTreasure(Treasure t);
    
    public abstract void substractHiddenTreasure(Treasure t);
}
