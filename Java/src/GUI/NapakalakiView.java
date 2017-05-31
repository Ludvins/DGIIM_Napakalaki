/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JOptionPane;
import napakalaki.Napakalaki;
import napakalaki.CombatResult;

/**
 * Buttons Rules
 *
 * Cant pass turn if you are not allowed and you havent combat. (currentHasCombat in PlayerView)
 * Cant combat before showing the monster.
 * 
 */
public class NapakalakiView extends javax.swing.JFrame {

    private Napakalaki napakalakiModel;
  
    public NapakalakiView() {
        initComponents();
        this.monsterView1.setVisible(false);
        this.nextTurn.setEnabled(false);
        this.combat.setEnabled(false);
    }
    
    public void setNapakalaki(Napakalaki n){
        this.napakalakiModel= n;        
        this.playerView2.setNapakalaki(n, this);
        this.monsterView1.setMonster(napakalakiModel.getCurrentMonster());
        this.playerView2.setPlayer(napakalakiModel.getCurrentPlayer());
        
        this.repaint();  //Not needed
    }
    
    
    //Enables or not the next turn button if it is allowed and has combat. Also does the Set.
    public void check(){

        this.nextTurn.setEnabled(this.napakalakiModel.nextTurnIsAllowed() && this.playerView2.currentHasCombat);
        this.playerView2.toggleSteal(this.napakalakiModel.nextTurnIsAllowed() && this.playerView2.currentHasCombat);
        this.setNapakalaki(napakalakiModel);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        monsterView1 = new GUI.MonsterView();
        playerView2 = new GUI.PlayerView();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        nextTurn = new javax.swing.JButton();
        combat = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        combatResult = new javax.swing.JLabel();
        showMonster = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(monsterView1, new org.netbeans.lib.awtextra.AbsoluteConstraints(701, 71, -1, -1));
        getContentPane().add(playerView2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nextTurn.setText("Next Turn");
        nextTurn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextTurnActionPerformed(evt);
            }
        });
        jLayeredPane1.add(nextTurn, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 11, -1, -1));

        combat.setText("Combat");
        combat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combatActionPerformed(evt);
            }
        });
        jLayeredPane1.add(combat, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 11, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("GAME DEVELOPMENT INFO");
        jLayeredPane1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 90, -1, -1));
        jLayeredPane1.add(combatResult, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 125, 343, 132));

        showMonster.setText("Meet the Monster");
        showMonster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showMonsterActionPerformed(evt);
            }
        });
        jLayeredPane1.add(showMonster, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        getContentPane().add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 464, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void showMonsterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showMonsterActionPerformed
        
        this.monsterView1.setVisible(true); 
        this.playerView2.noMakeVisible(); //After showing the monster, the player cant make treasures visible.        
        this.showMonster.setEnabled(false); //Cant show the monster (already shown).
        this.combat.setEnabled(true); //Can fight now
        
        this.setNapakalaki(napakalakiModel);
        
    }//GEN-LAST:event_showMonsterActionPerformed

    private void combatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combatActionPerformed
      
        this.combat.setEnabled(false);
        
        this.playerView2.currentHasCombat = true; //After combat player can steal treasure.
        
        CombatResult result = napakalakiModel.developCombat();

        switch (result) {
            case WIN:  combatResult.setText("<html>"+ "You have defeated the monster." + "</html>");
                    this.playerView2.toggleMakeVisible(); //After the combat, the player can make treasures visible again if cleaned the bad consequence
                break;
            case WINGAME:
                JOptionPane.showMessageDialog(null, "<html>"+ "The Player " + this.napakalakiModel.getCurrentPlayer().getName() + " has reached level 10" + "</html>");
                System.exit(0);
            break;
            case LOSE:  combatResult.setText("<html>"+ "You have been defeated. You'll be applied the bad consequence." + "</html>");
                break;
            case LOSEANDCONVERT: combatResult.setText("<html>"+ "You have been defeated. ¡Now you are a cultist!" + "</html>"); 
                break;
            default: combatResult.setText("<html>"+ "ERROR." + "</html>");
                break;
        }
        
        
        this.playerView2.toggleMakeVisible(); //After the combat, the player can make treasures visible again if cleaned the bad consequence
        
        this.check();  //Check does Set
     

    }//GEN-LAST:event_combatActionPerformed

    private void nextTurnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextTurnActionPerformed
        
        this.nextTurn.setEnabled(false); //Next turn isnt enabled before combat
        
        this.playerView2.currentHasCombat = false; //new player hasnt combat yet.
        
        this.napakalakiModel.nextTurn();
        
        this.monsterView1.setVisible(false); //dont show monster.
        
        this.showMonster.setEnabled(true); //show button enables.
        
        this.combatResult.setText(""); //Clean Info

        this.check();
        
    }//GEN-LAST:event_nextTurnActionPerformed

    public void showView() {
        this.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton combat;
    private javax.swing.JLabel combatResult;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private GUI.MonsterView monsterView1;
    private javax.swing.JButton nextTurn;
    private GUI.PlayerView playerView2;
    private javax.swing.JButton showMonster;
    // End of variables declaration//GEN-END:variables
}
