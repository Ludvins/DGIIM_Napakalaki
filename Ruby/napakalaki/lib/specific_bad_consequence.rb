# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame

class SpecificBadConsequence < BadConsequence
  
  def initialize(aText, nLevels, specificVisibleTreasures, specificHiddenTreasures)
    
    @aText = aText
    @nLevels = nLevels
    @nVisibleTreasures = 0
    @nHiddenTreasures = 0
    @specificVisibleTreasures = specificVisibleTreasures
    @specificHiddenTreasures = specificHiddenTreasures
    @death = false
    
  end
  
  public_class_method :new
  
  def empty?
    @specificVisibleTreasures.empty? && @specificHiddenTreasures.empty?
  end
  
  def intersection(array1, array2)
    
   (array1 & array2).flat_map { |a| [a]*[array1.count(a),array2.count(a)].min }
            
  end
  
  def adjustToFitTreasureList(vTreasures, hTreasures)

    vTreasures = vTreasures.collect{ |a| a.type}
    hTreasures = hTreasures.collect{ |a| a.type}
    
    tVisible = intersection(vTreasures, @specificVisibleTreasures)
    tHidden = intersection(hTreasures, @specificHiddenTreasures)
    
    return SpecificBadConsequence.new(@aText, 0, tVisible, tHidden)
    
  end
  
  private :intersection
  
end
end
