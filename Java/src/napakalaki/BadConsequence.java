

package napakalaki;
import java.util.ArrayList;

public abstract class BadConsequence {
    protected String text;
    protected int levels;
    
    protected BadConsequence(String text, int levels){
        this.text = text;
        this.levels = levels;
    }

    public String getText() {
        return text;
    }

    public int getLevels() {
        return levels;
    }
    
    @Override
    public String toString(){
        return "\n\tText: " + text + "\n\tLevels: " + levels;
    }
    
    public abstract boolean isEmpty () ;          
    
    public abstract BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h);
    
    public abstract void substractVisibleTreasure(Treasure t);
    
    public abstract void substractHiddenTreasure(Treasure t);
}
