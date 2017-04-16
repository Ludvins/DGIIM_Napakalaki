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
    "Text: #{@aText} \nLevels lost: #{@nLevels} \nVisible treasures lost: #{@nVisibleTreasures} \nHidden treasures lost: #{@nHiddenTreasures} \nSpecific visible treasures lost: #{@specificVisibleTreasures} \nSpecific hidden treasures lost: #{@specificHiddenTreasures} \nDeath: #{@death}"
  end
  
  def empty?
    @nLevels = 0 && @nVisibleTreasures = 0 && @nHiddenTreasures = 0 && @specificVisibleTreasures.empty? && @specificHiddenTreasures.empty? && @death = false
  end
  
  def substractVisibleTreasure(t)
    @specificVisibleTreasures.delete(t.type)
  end
  
  def substractHiddenTreasure(t)
    @specificHiddenTreasures.delete(t.type)
  end
  
  def intersection(array1, array2)
    
   (array1 & array2).flat_map { |a| [a]*[array1.count(a),array2.count(a)].min }
            
  end
  
  def adjustToFitTreasureList(vTreasures, hTreasures)
    
    return @self if @death == true
    
    if @specificVisibleTreasures.empty? && @specificHiddenTreasures.empty? then
        
      nvisible = [vTreasures.size, @nVisibleTreasures].min
      nhidden = [hTreasures.size, @nHiddenTreasures].min
      
      puts nvisible
      puts nhidden
                    
      return BadConsequence.newLevelNumberOfTreasures(@aText, 0, nvisible, nhidden)
      
    end
    
    vTreasures = vTreasures.collect{ |a| a.type}
    hTreasures = hTreasures.collect{ |a| a.type}
    
    tVisible = intersection(vTreasures, @specificVisibleTreasures)
    tHidden = intersection(hTreasures, @specificHiddenTreasures)
    
    puts vTreasures
    puts @specificVisibleTreasures
    puts tVisible
    puts hTreasures
    puts @specificHiddenTreasures
    puts tHidden
    
    return BadConsequence.newLevelSpecificTreasures(@aText, 0, tVisible, tHidden)
    
  end
  
end
end