# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'prize.rb'
require_relative 'bad_consequence.rb'

class Monster
    
  attr_reader :name, :combatLevel, :prize, :badConsequence
  
  def initialize(name, combatLevel, prize, badConsequence)
    @name = name
    @combatLevel = combatLevel
    @prize = prize
    @badConsequence = badConsequence
  end

  
  def to_s
    "Monster name: #{@name} \nMonster level: #{@combatLevel} \nPrize: #{@prize} \nBadconsequence: #{@badConsequence}"
  end
end
