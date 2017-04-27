# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame

require_relative 'bad_consequence.rb'
require_relative 'prize.rb'
require_relative 'monster.rb'
require_relative 'treasure.rb'
require_relative 'player.rb'
require_relative 'card_dealer.rb'
require_relative 'cultist.rb'
require_relative 'cultist_player.rb'
require "singleton"

class Napakalaki
  
  include Singleton
  
  private
  
  def initPlayers(names)
    @players = Array.new
    
    names.each do |a|
      @players << Player.new(a)
    end
  end
  
  def nextPlayer
    
    if @currentPlayer == nil
      @currentPlayer = rand(@players.size)
    else 
      
      @currentPlayer = (@currentPlayer + 1)% @players.size
      
    end
    
    
    
  end
  
  def nextTurnAllowed
    
    if @currentPlayer.nil?
      return true
    end
    
    return @players[@currentPlayer].validState

  end
  
  def setEnemies
    
    r = Random.new
    
    for i in 0..@players.size - 1
    
      loop do
            
        r = rand(@players.size)
    
   
        break if @players[i] != @players[r]
    
      end
    
      @players[i].setEnemy(@players[r])
    
    end
  end
  
  public
  
  def developCombat
    
    result = @players[@currentPlayer].combat(@currentMonster)
    
    if result == CombatResult::LOSEANDCONVERT
      
      cultist = CultistPlayer.new(@players[@currentPlayer], @dealer.nextCultist)
      
      @players.each do |p|
        
        p.setEnemy(cultist) if p.getEnemy == @players[@currentPlayer]
        
      end
      
      @players[@currentPlayer] = cultist

    end
  
    return result
    
  end
  
  def discardVisibleTreasures(treasures)
    
    treasures.each do |t|
      @players[@currentPlayer].discardVisibleTreasure(t)
    end
    
  end
  
  def discardHiddenTreasures(treasures)
    
    treasures.each do |t|
      @players[@currentPlayer].discardHiddenTreasure(t)
    end
  end
  
  def makeTreasuresVisible(treasures)
    
    treasures.each do |t|
      
      @players[@currentPlayer].makeTreasureVisible(t)
    end
    
  end
  
  def initGame(names)
    
    @dealer = CardDealer.instance
    
    @dealer.initCards
    
    initPlayers(names)
    
    setEnemies
   
    nextTurn
    
  end
  
  def getCurrentPlayer
    @players[@currentPlayer]
  end
  
  def getCurrentMonster
    @currentMonster
  end
  
  def nextTurn
    
    ret = nextTurnAllowed

     if ret then
       @currentMonster = @dealer.nextMonster
       nextPlayer

      if @players[@currentPlayer].isDead then
        @players[@currentPlayer].initTreasures
      end
     end
    return ret
  end
  
  def endOfGame(cr)
    
    cr == CombatResult.WINGAME
    
  end
end
end
