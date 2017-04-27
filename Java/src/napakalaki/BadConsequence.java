

package napakalaki;
import java.util.ArrayList;
import java.util.stream.Collectors;
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
        return "\n\tText: " + text + "\n\tLevels: " + levels + "\n\tnVisibleTreasures: " + nVisibleTreasures + "\n\tnHiddenTreasures: " + nHiddenTreasures + "\n\tDeath: " + death + "\n\tSpecificHiddenTreasures: " + specificHiddenTreasures + "\n\tSpecificVisibleTreasures: " + specificVisibleTreasures;
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
        
        if (this.death) return new BadConsequence(text, 0 , nHiddenTreasures, nVisibleTreasures);
        
        if (specificHiddenTreasures.isEmpty() && specificVisibleTreasures.isEmpty())
            
            return new BadConsequence(text, 0, Math.min(nVisibleTreasures, v.size()), Math.min(nHiddenTreasures, h.size()));
        
         ArrayList <TreasureKind> vt = v.stream().map(a -> a.getType()).collect(Collectors.toCollection(ArrayList::new));
         ArrayList <TreasureKind> ht = h.stream().map(a -> a.getType()).collect(Collectors.toCollection(ArrayList::new));
        
         vt.retainAll(this.specificVisibleTreasures);
         ht.retainAll(this.specificHiddenTreasures);
         
         return new BadConsequence(this.text, 0, vt, ht);
        
    }
    
        
}
