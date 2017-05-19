# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'bad_consequence.rb'

module NapakalakiGame

class NumericBadConsequence < BadConsequence
  
  def initialize(aText, nLevels, nVisibleTreasures, nHiddenTreasures)
    
    super(aText, nLevels)
    @nVisibleTreasures = nVisibleTreasures
    @nHiddenTreasures = nHiddenTreasures
    
  end
  
  public_class_method :new
    
  
  def empty?
    @nVisibleTreasures == 0 && @nHiddenTreasures == 0
  end 
  
  def to_s
    "\n\tText: #{@aText} \n\tLevels lost: #{@nLevels} \n\tVisible treasures lost: #{@nVisibleTreasures} \n\tHidden treasures lost: #{@nHiddenTreasures} \n\tDeath: #{@death}"
  end
  
  def substractVisibleTreasure(t)
     @nVisibleTreasures -= 1 if @nVisibleTreasures > 0
        
  end
  
  def substractHiddenTreasure(t)
    @nHiddenTreasures -= 1 if @nHiddenTreasures > 0
        
  end
  
  
  def adjustToFitTreasureList(vTreasures, hTreasures)
            
    nvisible = [vTreasures.size, @nVisibleTreasures].min
    nhidden = [hTreasures.size, @nHiddenTreasures].min
                    
    return NumericBadConsequence.new(@aText, 0, nvisible, nhidden)

  end
  
end
end
