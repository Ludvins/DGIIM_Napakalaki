# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame

require_relative 'treasure'
require_relative 'monster'
require_relative 'treasure_kind'
require_relative 'combat_result'
require_relative 'dice'
require_relative 'napakalaki'
require_relative 'player'

class CultistPlayer < Player
  
  @@totalCultistsPlayers = 0
  
  def initialize(player, cultist)
    
    @@totalCultistsPlayers += 1
   
    copy(player)
   
    @cultistCard = cultist
    
  end
  
  def getCombatLevel
    
    (super * 0.7).to_i + (@cultistCard.getGainedBonus * @@totalCultistsPlayers)
    
  end
  
  def shouldConvert
    false
  end
  
  def getOponentLevel(m)
    m.getCombatLevelAgainstCultist
  end
  
  def giveMeATreasure
    
    t = @visibleTreasures[rand(@visibleTreasures.size)]
    discardVisibleTreasure(t)
    return t
    
  end
  
  def canYouGiveMeATreasure
    @visibleTreasures.any?
  end
  
  def to_s
    "#{@name} (Sectario) (Nivel de jugador: #{@level}  Nivel de combate: #{getCombatLevel}  Enemigo: #{@enemy.getName})"
  end
  
end
end
