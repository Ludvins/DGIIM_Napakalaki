# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame

require_relative 'prize.rb'
require_relative 'bad_consequence.rb'

class Monster
  
  def initialize(name, combatLevel, prize, badConsequence)
    @name = name
    @combatLevel = combatLevel
    @prize = prize
    @badConsequence = badConsequence
  end

  def getName
    @name
  end
  
  def getCombatLevel
    @combatLevel
  end
  
  def getPrize
    @prize
  end
  
  def getBadConsequence
    @badConsequence
  end
  
  def to_s
    "Monster name: #{@name} \nMonster level: #{@combatLevel} \nPrize: #{@prize} \nBadconsequence: #{@badConsequence}"
  end
end
end
