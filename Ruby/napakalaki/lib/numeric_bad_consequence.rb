# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'bad_consequence.rb'

module NapakalakiGame

class NumericBadConsequence < BadConsequence
  
  def initialize(aText, nLevels, nVisibleTreasures, nHiddenTreasures)
    
    @aText = aText
    @nLevels = nLevels
    @nVisibleTreasures = nVisibleTreasures
    @nHiddenTreasures = nHiddenTreasures
    @specificVisibleTreasures = Array.new
    @specificHiddenTreasures = Array.new
    @death = false
    
  end
  
  public_class_method :new
    
  
  def empty?
    @nVisibleTreasures == 0 && @nHiddenTreasures == 0
  end 
  
  def adjustToFitTreasureList(vTreasures, hTreasures)
            
    nvisible = [vTreasures.size, @nVisibleTreasures].min
    nhidden = [hTreasures.size, @nHiddenTreasures].min
                    
    return BadConsequence.newLevelNumberOfTreasures(@aText, 0, nvisible, nhidden)

  end
  
end
end
