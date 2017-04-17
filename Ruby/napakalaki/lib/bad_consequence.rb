# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame

require_relative 'treasure_kind.rb'

class BadConsequence
  
  
  attr_reader :aText, :nLevels, :nVisibleTreasures, :nHiddenTreasures, :death, :specificVisibleTreasures, :specificHiddenTreasures
  
  
  def initialize(aText, nLevels = 0, nVisibleTreasures = 0, nHiddenTreasures = 0, specificVisibleTreasures = Array.new, specificHiddenTreasures = Array.new, death = false)
    @aText = aText
    @nLevels = nLevels
    @nVisibleTreasures = nVisibleTreasures
    @nHiddenTreasures = nHiddenTreasures
    @specificVisibleTreasures = specificVisibleTreasures
    @specificHiddenTreasures = specificHiddenTreasures
    @death = death
  end
  
  private_class_method:new
  
  def self.newLevelNumberOfTreasures(aText, nLevels, nVisibleTreasures, nHiddenTreasures)
    
    new(aText, nLevels, nVisibleTreasures, nHiddenTreasures)
    
  end 
  
  def self.newLevelSpecificTreasures(aText, nLevels, specificVisibleTreasures, specificHiddenTreasures)
  
     new(aText, nLevels, 0, 0, specificVisibleTreasures, specificHiddenTreasures)     
  
  end
  
  def self.newDeath(aText)
    
    new(aText, 10, 5, 4, Array.new, Array.new, true)
    
  end

  def to_s
    "\n\tText: #{@aText} \n\tLevels lost: #{@nLevels} \n\tVisible treasures lost: #{@nVisibleTreasures} \n\tHidden treasures lost: #{@nHiddenTreasures} \n\tSpecific visible treasures lost: #{@specificVisibleTreasures} \n\tSpecific hidden treasures lost: #{@specificHiddenTreasures} \n\tDeath: #{@death}"
  end
  
  def empty?
    @nVisibleTreasures == 0 && @nHiddenTreasures == 0 && @specificVisibleTreasures.empty? && @specificHiddenTreasures.empty? && @death == false
  end
  
  def substractVisibleTreasure(t)
    if @specificVisibleTreasures.delete(t.type).nil? && @nVisibleTreasures > 0 then
         @nVisibleTreasures -= 1
    end

  end
  
  def substractHiddenTreasure(t)
    if @specificHiddenTreasures.delete(t.type).nil? && @nHiddenTreasures > 0 then
           @nHiddenTreasures -= 1
    end
  end
  
  def intersection(array1, array2)
    
   (array1 & array2).flat_map { |a| [a]*[array1.count(a),array2.count(a)].min }
            
  end
  
  def adjustToFitTreasureList(vTreasures, hTreasures)
    
    return BadConsequence.newLevelNumberOfTreasures(@aText, 0, vTreasures.size, hTreasures.size) if @death
    
    if @specificVisibleTreasures.empty? && @specificHiddenTreasures.empty? then
        
      nvisible = [vTreasures.size, @nVisibleTreasures].min
      nhidden = [hTreasures.size, @nHiddenTreasures].min
                    
      return BadConsequence.newLevelNumberOfTreasures(@aText, 0, nvisible, nhidden)
      
    end
    
    vTreasures = vTreasures.collect{ |a| a.type}
    hTreasures = hTreasures.collect{ |a| a.type}
    
    tVisible = intersection(vTreasures, @specificVisibleTreasures)
    tHidden = intersection(hTreasures, @specificHiddenTreasures)
    
    return BadConsequence.newLevelSpecificTreasures(@aText, 0, tVisible, tHidden)
    
  end
  
end
end