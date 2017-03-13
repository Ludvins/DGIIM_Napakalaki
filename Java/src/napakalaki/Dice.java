
package napakalaki;

import java.util.Random;
        
/**
 *
 * @author ludvins
 */
public class Dice {
    
    private static final Dice instance = null;
    
    private Dice () { }
    
    public static Dice getInstance() {
        return instance;
    }
    
    public int nextNumber(){
        Random rd = new Random();
        return rd.nextInt() % 6;
    }
}
