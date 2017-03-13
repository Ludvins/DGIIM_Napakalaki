

package napakalaki;
import java.util.ArrayList;
/**
 *
 * @author ludvins
 */
public class BadConsequence {
    private String text;
    private int levels;
    private int nVisibleTreasures;
    private int nHiddenTreasures;
    private boolean death;
    private ArrayList<TreasureKind> specificHiddenTreasures = new ArrayList();
    private ArrayList<TreasureKind> specificVisibleTreasures = new ArrayList();
    
    public BadConsequence(String _text, int _levels, int _nVisible, int _nHidden){
        
        text = _text;
        levels = _levels;
        nVisibleTreasures = _nVisible;
        nHiddenTreasures = _nHidden;
        death = false;
        
    }
    
    public BadConsequence(String _text, boolean _death){
        
        text = _text;
        death = _death;
        
    }
    
    public BadConsequence(String _text, int _levels, ArrayList<TreasureKind> _tVisible, ArrayList<TreasureKind> _tHidden){

        text = _text;
        levels = _levels;
        specificHiddenTreasures = _tVisible;
        specificVisibleTreasures = _tHidden;
        
    }

    public String getText() {
        return text;
    }

    public int getLevels() {
        return levels;
    }

    public int getnVisibleTreasures() {
        return nVisibleTreasures;
    }

    public int getnHiddenTreasures() {
        return nHiddenTreasures;
    }

    public boolean isDeath() {
        return death;
    }

    public ArrayList<TreasureKind> getSpecificHiddenTreasures() {
        return specificHiddenTreasures;
    }

    public ArrayList<TreasureKind> getSpecificVisibleTreasures() {
        return specificVisibleTreasures;
    }

    @Override
    public String toString() {
        return "BadConsequence{" + "text=" + text + ", levels=" + levels + ", nVisibleTreasures=" + nVisibleTreasures + ", nHiddenTreasures=" + nHiddenTreasures + ", death=" + death + ", specificHiddenTreasures=" + specificHiddenTreasures + ", specificVisibleTreasures=" + specificVisibleTreasures + '}';
    }
        
    
    public boolean isEmpty () {
       return this.levels == 0 && this.death == false && this.nHiddenTreasures == 0 && this.nVisibleTreasures == 0 && this.specificHiddenTreasures.isEmpty() && this.specificVisibleTreasures.isEmpty();            
    }
   
    public void substractVisibleTreasure(Treasure t){
        this.specificVisibleTreasures.remove(t.getType());
    }
    
    public void substractHiddenTreasure(Treasure t){
        this.specificHiddenTreasures.remove(t.getType());
    }
    
    public BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h){
        
        ArrayList<TreasureKind> tHidden = new ArrayList();
        ArrayList<TreasureKind> tVisible = new ArrayList();
        
        for (Treasure t : v) {
            
            if(!this.specificVisibleTreasures.contains(t.getType())) tVisible.add(t.getType());
            
        }

        for (Treasure t : h) {
            
            if(!this.specificHiddenTreasures.contains(t.getType())) tHidden.add(t.getType());
        }
       
        
        BadConsequence bs = new BadConsequence(this.text, this.levels, tVisible, tHidden);
        
        return bs;     
        
        
    }
    
        
}
