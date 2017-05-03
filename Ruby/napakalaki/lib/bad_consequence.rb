# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame

require_relative 'treasure_kind.rb'

class BadConsequence
  
  
  attr_reader :aText, :nLevels, :death
  
  
  def initialize(aText, nLevels, death = false)
    @aText = aText
    @nLevels = nLevels 
    @death = death
  end
  
  private_class_method:new

  def to_s
  end
  
  def substractVisibleTreasure(t)
  end
  
  def substractHiddenTreasure(t)
  end
  
 def adjustToFitTreasureList(vTreasures, hTreasures)
   
 end
  
end
end