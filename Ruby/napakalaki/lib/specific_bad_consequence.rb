# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame

class SpecificBadConsequence < BadConsequence
  
  attr_reader :specificVisibleTreasures, :specificHiddenTreasures
  
  def initialize(text , nLevels, specificVisibleTreasures, specificHiddenTreasures)
    
    super(text, nLevels)
    @specificVisibleTreasures = specificVisibleTreasures
    @specificHiddenTreasures = specificHiddenTreasures
    
  end
  
  public_class_method :new
  
  def empty?
    @specificVisibleTreasures.empty? && @specificHiddenTreasures.empty?
  end

  def substractVisibleTreasure(t)
    @specificVisibleTreasures.delete_at(@specificVisibleTreasures.index(t.type))
  end
  
  def substractHiddenTreasure(t)
    @specificHiddenTreasures.delete_at(@specificHiddenTreasures.index(t.type))
  end
  
  def to_s
     "\n\tText: #{@aText} \n\tLevels lost: #{@nLevels} \n\tSpecific visible treasures lost: #{@specificVisibleTreasures} \n\tSpecific hidden treasures lost: #{@specificHiddenTreasures} \n\tDeath: #{@death}"
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
