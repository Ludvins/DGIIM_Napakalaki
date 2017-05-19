# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame

require_relative 'treasure_kind.rb'

class BadConsequence
  
  
  attr_reader :aText, :nLevels, :death
  
  
  def initialize(aText, nLevels)
    @aText = aText
    @nLevels = nLevels 
  end
  
  private_class_method:new

end
end