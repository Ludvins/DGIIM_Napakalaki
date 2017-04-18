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
      @currentPlayer = @players[rand(@players.size)]
    else 
      
      if @players.index(@currentPlayer) == @players.size - 1
        
        @currentPlayer = @players[0]
        
      else
        
        @currentPlayer = @players[@players.index(@currentPlayer) + 1]
      end
      
    end
    
    
    
  end
  
  def nextTurnAllowed
    
    if @currentPlayer.nil?
      return true
    end
    
    return @currentPlayer.validState

    
    return ret
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
    @currentPlayer.combat(@currentMonster)
  end
  
  def discardVisibleTreasures(treasures)
    
    treasures.each do |t|
      @currentPlayer.discardVisibleTreasure(t)
    end
    
  end
  
  def discardHiddenTreasures(treasures)
    
    treasures.each do |t|
      @currentPlayer.discardHiddenTreasure(t)
    end
  end
  
  def makeTreasuresVisible(treasures)
    
    treasures.each do |t|
      
      @currentPlayer.makeTreasureVisible(t)
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
    @currentPlayer
  end
  
  def getCurrentMonster
    @currentMonster
  end
  
  def nextTurn
    
    ret = nextTurnAllowed

     if ret then
       @currentMonster = @dealer.nextMonster
       nextPlayer

      if @currentPlayer.isDead then
        @currentPlayer.initTreasures
      end
     end
    return ret
  end
  
  def endOfGame(cr)
    
    cr == CombatResult.WINGAME
    
  end
end
end
