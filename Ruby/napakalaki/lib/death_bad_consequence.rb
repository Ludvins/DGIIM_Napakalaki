# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "bad_consequence.rb"
require_relative "numeric_bad_consequence.rb"

module NapakalakiGame
class DeathBadConsequence < NumericBadConsequence
  
  def initialize(aText)
    
    @aText = aText
    @nLevels = 10
    @nVisibleTreasures = 5
    @nHiddenTreasures = 4
    @specificVisibleTreasures = Array.new
    @specificHiddenTreasures = Array.new
    @death = true
    
  end
  
  public_class_method :new
  
  def adjustToFitTreasureList(vTreasures, hTreasures)
    
    return NumericBadConsequence.new(@aText, 0, vTreasures.size, hTreasures.size)
 
  end
end
end