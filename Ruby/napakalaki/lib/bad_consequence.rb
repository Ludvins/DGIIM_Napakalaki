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
    
    new(aText, 0, 0, 0, Array.new, Array.new, true)
    
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
  
  def adjustToFitTreasureList(vTreasures, hTreasures)
    
    return @self if @death == true
    
    tVisible = Array.new
    tHidden = Array.new
    
    nvisible = vTreasures.size
    nhidden = hTreasures.size
    
    if @nHiddenTreasures < nhidden
      nhidden = @nHiddenTreasures
    end
    
    if @nVisibleTreasures < nvisible
      nvisible = @nvisibleTreasures
    end
    
    vTreasures.each do |v|
      if @specificVisibleTreasures.include?(v.type) 
        then tVisible << v.type
      end
    end
    
    hTreasures.each do |h|
      if @specificHiddenTreasures.include?(h.type) 
        then tVisible << h.type
      end
    end
    
    if tVisible.empty? && tHidden.empty?
     
      return BadConsequence.newLevelNumberOfTreasures(@aText, @nLevels, nvisible, nhidden)
      
    end
    
    return BadConsequence.newLevelSpecificTreasures(@aText, @nLevels, tVisible, tHidden)
    
  end
  
end
end