
package napakalaki;

import java.util.Random;
        
/**
 *
 * @author ludvins
 */
public class Dice {
    
    private static Dice instance = null;
    
    private Dice () { }
    
    public static Dice getInstance() {
        if (instance == null)
           instance = new Dice();
        return instance;
    }
    
    public int nextNumber(){
        Random rd = new Random();
        return 1 + rd.nextInt() % 6;
    }
}
