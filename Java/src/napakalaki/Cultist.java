
package napakalaki;


public class Cultist {
    
    private final int levels;
    private final String name;
    
    public Cultist(int levels){
        this.name = "Cultist";
        this.levels = levels;
    }
    
    public Cultist(int levels, String name){
        this.name = name;
        this.levels = levels;
    }
    
    public int getGainedBonus(){
        return this.levels;
    }
    
}
