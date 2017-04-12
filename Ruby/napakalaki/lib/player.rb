# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame

require_relative 'bad_consequence.rb'
require_relative 'prize.rb'
require_relative 'monster.rb'
require_relative 'treasure.rb'

class Player
  
  def initialize(name)
    @name = name
  end
  
  def bringToLife
    
    @death = false
    
  end
  
  def getName
    @name
  end
  
  def getDead
    @dead
  end
  
  def getVisibleTreasures
    @visibleTreasures
  end
  
  def getHiddenTreasures
    @hiddenTreasures
  end
  
  def getCombatLevel
    
    n = @level
    
    @visibleTreasures.each do |t|
      
      n += t.bonus
      
    end    
    
    return n
    
  end
  
  def incrementLevels(n)
    
    @level += n
    
  end
  
  def decrementLevels(n)
    
    if (@level <= n)
      then @level = 1
    else
      @level -= n
    end
   
   end
   
  def setPendingBC(bc)
    
    @pendingBC = bc
  
  end
  
  
  def applyPrize(m)
    
    incrementLevels(m.prize.levels)
    
    n = m.prize.treasures
    
    cd = CardDealer.instance
    
    n.times do
      
      t = cd.nextTreasure
      @hiddenTreasures << t
    end
   
  end
  
  def applyBadConsequence(m)
    
    decrementlevels(m.getBadConsequence.nLevels)
    
    setPendingBC(m.setBadConsequence.adjustToFitTreasureList(@visibleTreasures, @hiddenTreasures))
    
  end
  
  def canMakeTreasurevisible(t)
    
    if(t.type != TreasureKind.ONEHAND && t.type != TreasureKind.BOTHHANDS && !isTreasureKindInUse(t.type)) 
      return true
    end
    
    if t.type == TreasureKind.ONEHAND && !isTreasureKindInUse(TreasureKind.BOTHHANDS) && howManyVisibleTreasures(t.type) < 2
      return true
    end
    
    if t.type == TreasureKind.BOTHHANDS && !isTreasureKindInUse(t.type) && howManyVisibleTreasures(TreasureKind.ONEHAND) == 0
      return true
    end
    
    return false
  end
  
  def canYouGiveMeATreasure
    @hiddenTreasures.empty?
  end
  
  def haveStolen
    @canISteal = false
  end
  
  def discardAllTreasures
    
    @visibleTreasures.each do |t|
      discardVisibleTreasure(t)
    end
    
    @hiddenTreasures.each do |t|
      discardHiddenTreasure(t)
    end
  end
  
  
  def howManyVisibleTreasures(tk)
    
    ret = 0
    
    @visibleTreasures.each do |v|
      
      if v.type == tk
        ret = ret +1
      end
      
    end
    
    return ret
    
  end
  
  
  def isTreasurekindInUse(type)
    
   
    @visibleTreasures.each do |t|
      
      if (t.type == type)
        then
        return true
      end
      
    end
    
    return false
  
  end
  
  def dieIfNoTreasures
    
    if @visibleTreasures.empty? && @hiddenTreasures.empty?
      then 
      @dead = true
    end
  end
  
  def combat (m)
    
    if getCombatLevel <= m.getCombatLevel then
      
      applyBadConsequence(m)
      
      return CombatResult.LOSE
    end
    
    applyPrize(m)
    
    if @level >= 10 
      return CombatResult.WINGAME
    end
    
    return CombatResult.WIN
    
  end
  
  
  def makeTreasureVisible(t)
    
    if canMakeTreasureVisible(t)
      then
      
      @visibleTreasures << t
      @hiddenTreasures.delete(t)
    end
  end
  
  def discardVisibleTreasure(t)
    
    @visibleTreasures.remove(t)
    
    if @pendingBC != nil && @pendingBC.empty? then
      
      @pendingBC.substractVisibleTreasure(t)
    
    end
    
    dieIfNoTreasures
    
  end

  def hiddenVisibleTreasure(t)
    
    @hiddenTreasures.remove(t)
    
    if @pendingBC != nil && @pendingBC.empty? then
      
      @pendingBC.substractHiddenTreasure(t)
    
    end
    
    dieIfNoTreasures
    
  end
  
  def validState
    
    pendingBC != nil && @hiddenTreasures.length <= 4
    
  end
  
  def stealTreasure
    
    if !@canISteal 
      return nill
    end
    
    if !@enemy.canYougiveMeATreasure
      return nill
    end
    
    t = @enemy.giveMeATreasure
    @hiddenTreasure << t
    
    haveStolen
    
   return t
  end
  
  def giveMeATreasure
    
    t = @hiddenTreasures[rand(@hiddenTreasures.size)]
    @hiddenTreasures.delete(t)
    return t
  end
  
  def initTreasures
    
    dealer = CardDealer.getInstance
    dice = Dice.getInstance
    
    bringToLife
    
    treasure = dealer.nextTreasure
    @hiddenTreasures << treasure
    
    n = dice.nextNumber
    
    if n == 6 then
      treasure = dealer.nextTreasure
      @hiddenTreasures << treasures
      treasure = dealer.nextTreasure
      @hiddenTreasures << treasures
      
    elsif n > 1 then
      
      treasure = dealer.nextTreasure
      @hiddenTreasures << treasures
      
    end

  end
  
  def stealTreasure
    
    n = rand(@hiddenTreasures.length)
    
    t = @hiddenTreasures[n]
    @hiddenTreasures.delete(t)
    return t
    
  end
  
  def setEnemy (enemy)
    @enemy = enemy
  end
  
  private :bringToLife, :getCombatLevel, :incrementLevels, :decrementLevels, :setPendingBC, :applyPrize, :applyBadConsequence, :canMakeTreasureVisible, :howmanyvisibleTreasures, :dieIfNoTreasures
 
  
end
end
