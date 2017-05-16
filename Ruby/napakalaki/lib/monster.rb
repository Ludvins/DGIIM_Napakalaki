# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame

require_relative 'prize.rb'
require_relative 'bad_consequence.rb'

class Monster
  
  private_class_method :new
  
  def self.newStandard (name, combatLevel, prize, badConsequence)
    
    new(name, combatLevel, prize, badConsequence)
    
  end
  
  def self.newCultist(name, combatLevel, prize, badConsequence, levelChange)
    
    new(name, combatLevel, prize, badConsequence, levelChange)
    
  end
  
  def initialize(name, combatLevel, prize, badConsequence, levelChange = 0)
    @name = name
    @combatLevel = combatLevel
    @prize = prize
    @badConsequence = badConsequence
    @levelChangeAgainstCultistPlayer = levelChange
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
  
  def getCombatLevelAgainstCultistPlayer
    @combatLevel + @levelChangeAgainstCultistPlayer
  end
  
  def getBadConsequence
    @badConsequence
  end
  
  def to_s
    "Monster name: #{@name} \nMonster level: #{@combatLevel} \nMonster level against Cultist: #{getCombatLevelAgainstCultistPlayer}\nPrize: #{@prize} \nBadconsequence: #{@badConsequence}"
  end
end
end
