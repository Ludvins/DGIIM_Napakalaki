# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.


require_relative 'treasure_kind.rb'

class BadConsequence
  
  
  attr_reader :aText, :nLevels, :nVisibleTreasures, :nHiddenTreasures, :death, :SpecificVisibleTreasures, :SpecificHiddenTreasures
  
  
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
    
    self.new(aText, nLevels, nVisibleTreasures, nHiddenTreasures)
    
  end 
  
  def self.newLevelSpecificTreasures(aText, nLevels, nSpecificVisibleTreasures, nSpecificHiddenTreasures)
  
     self.new(aText, nLevels, 0, 0, nSpecificVisibleTreasures, nSpecificHiddenTreasures)     
  
  end
  
  def self.newDeath(aText)
    
    self.new(aText, 0, 0, 0, Array.new, Array.new, true)
    
  end

  def to_s
    "Text: #{aText} \nLevels lost: #{nLevels} \nVisible treasures lost: #{nVisibleTreasures} \nHidden treasures lost: #{nHiddenTreasures} \nSpecific visible treasures lost: #{nSpecificVisibleTreasures} \nSpecific hiden treasures lost: #{nSpecificHiddenTreasures} \nDeath: #{death}"
  end
  
  def empty?
    @nLevels = 0 && @nVisibleTreasures = 0 && @nHiddenTreasures = 0 && @specificVisibleTreasures.empty? && @spedificHiddenTreasures.empty? && @death = false
  end
  
  def substractVisibleTreasure(t)
    @specificVisibleTreasures.delete(t.type)
  end
  
  def substractHiddenTreasure(t)
    @specificHiddenTreasures.delete(t.type)
  end
  
  def adjustToFitTreasureLists(vTreasures, hTreasures)
    
    tVisible = Array.new
    tHidden = Array.new
    
    vTreasures.each do |v|
      if @specificVisibleTreasures.include?(v.type) 
        then tVisible << v.type
      end
    end
    
    hTreasures.each do |h|
      if @specificHiddenTreasures.include?(h.type) 
        then hVisible << h.type
      end
    end
    
    bs = BadConsequence.newLevelSpecificTreasures(@aText, @nLevels, tVisible, tHidden)
    
  end
  
  
end
