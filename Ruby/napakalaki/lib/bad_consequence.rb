# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

class BadConsequence
  def initialize(aText, someLevels, someVisibleTreasures, someHiddenTreasures, someSpecificVisibleTreasures, someSpecificHiddenTreasures, death)
    @aText = aText
    @someLevels = someLevels
    @someVisibleTreasures = someVisibleTreasures
    @someHiddenTreasures = someHiddenTreasures
    @someSpecificVisibleTreasures = someSpecificVisibleTreasures
    @someSpecificHiddenTreasures = someSpecificHiddenTreasures
    @death = death
  end
  
  
  
  def to_s
    "Text: #{aText} \nLevels lost: #{someLevels} \nVisible treasures lost: #{someVisibleTreasures} \nHidden treasures lost: #{someHiddenTreasures} \nSpecific visible treasures lost: #{someSpecificVisibleTreasures} \nSpecific hiden treasures lost: #{someSpecificHiddenTreasures} \nDeath: #{death}"
  end
end
