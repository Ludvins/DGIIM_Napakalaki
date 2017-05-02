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

  def to_s
    "\n\tText: #{@aText} \n\tLevels lost: #{@nLevels} \n\tVisible treasures lost: #{@nVisibleTreasures} \n\tHidden treasures lost: #{@nHiddenTreasures} \n\tSpecific visible treasures lost: #{@specificVisibleTreasures} \n\tSpecific hidden treasures lost: #{@specificHiddenTreasures} \n\tDeath: #{@death}"
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
  
end
end