/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
