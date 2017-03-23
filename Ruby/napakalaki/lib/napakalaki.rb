# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame

require_relative 'bad_consequence.rb'
require_relative 'prize.rb'
require_relative 'monster.rb'
require_relative 'treasure.rb'
require "singleton"

class Napakalaki
  
  include Singleton
  
  attr_accessor :currentPlayer, :players, :dealer, :currentMonster
  private :initPlayers, :nextPlayer, :nextTurnAllowed, :setEnemies
  
  def initPlayers(names)
    @players = Array.new
    
    names.each do |a|
      @players << Player.new(a)
    end
  end
  
  def nextPlayer
    if @currentPlayer == @players[@players.size] then
      @currentPlayer = @players[0]
    else
      @currentPlayer = @players[@players.rindex(@currentPlayer) + 1]
    end
  end
  
  def nextTurnAllowed
    
    if @currentPlayer == nil
      ret = false
    else
      ret = @currentPlayer.validState
    end
    
    return ret
  end
  
  def setEnemies
    
    loop do
    r = rand(@players.size)
    
    break if @currentPlayer != @players[r]
    end
    @currentPlayer.setEnemy(@players[r])
  end
  
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
    
    initPlayers(names)
    
    @dealer = CardDealer.instance
    
    nextTurn
    
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
