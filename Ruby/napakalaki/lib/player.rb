# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame

require_relative 'bad_consequence.rb'
require_relative "specific_bad_consequence.rb"
require_relative "numeric_bad_consequence.rb"
require_relative "death_bad_consequence.rb"
require_relative 'prize.rb'
require_relative 'monster.rb'
require_relative 'treasure.rb'
require_relative 'combat_result.rb'
require_relative 'card_dealer.rb'
require_relative 'dice.rb'

class Player
  
  def initialize(name)
    @name = name
    @visibleTreasures = Array.new
    @hiddenTreasures = Array.new
    @death = true
    @level = 1    
    @canISteal = true;
    
  end
  
  
  def copy(player)
    
    @name = player.getName
    @visibleTreasures = player.getVisibleTreasures
    @hiddenTreasures = player.getHiddenTreasures
    @death = player.isDead
    @canISteal = player.canISteal
    @enemy = player.getEnemy
    @level = player.getLevel
    @pendingBC = player.getBC
    
  end
  
  def bringToLife
    
    @death = false
    
  end
  
  def getName
    @name
  end
  
  def getLevel
    @level
  end
  
  def getBC
    @pendingBC
  end
  
  def getEnemy
    @enemy
  end
  
  def isDead
    @death
  end
  
  def shouldConvert
    
    dice = Dice.instance
    n = dice.nextNumber
    return n == 6
    
  end
  
  def getVisibleTreasures
    @visibleTreasures
  end
  
  def getHiddenTreasures
    @hiddenTreasures
  end
  
  def getCombatLevel
   
    @visibleTreasures.inject(@level) { |acc,t| acc + t.bonus }

  end
  
  def incrementLevels(n)
    
    @level += n
    
  end
  
  def decrementLevels(n)
    
    @level = @level <= n ? 1 : @level - n
    
  end
   
  def setPendingBC(bc)
    
    @pendingBC = bc
  
  end
  
  
  def applyPrize(m)
    
    incrementLevels(m.getPrize.levels)
    
    n = m.getPrize.treasures
    
    cd = CardDealer.instance
    
    n.times do
      @hiddenTreasures << cd.nextTreasure
    end
   
  end
  
  def applyBadConsequence(m)
    
    decrementLevels(m.getBadConsequence.nLevels)
 
    setPendingBC(m.getBadConsequence.adjustToFitTreasureList(@visibleTreasures, @hiddenTreasures))
    
  end
  
  def canMakeTreasureVisible(t)
    
    if(t.type != TreasureKind::ONEHAND && t.type != TreasureKind::BOTHHANDS && !isTreasureKindInUse(t.type)) 
      return true
    end
    
    if t.type == TreasureKind::ONEHAND && !isTreasureKindInUse(TreasureKind::BOTHHANDS) && howManyVisibleTreasures(t.type) < 2
      return true
    end
    
    return t.type == TreasureKind::BOTHHANDS && !isTreasureKindInUse(t.type) && howManyVisibleTreasures(TreasureKind::ONEHAND) == 0

  end
  
  def canYouGiveMeATreasure
    @hiddenTreasures.any?
  end
  
  def haveStolen
    @canISteal = false
  end
  
  def canISteal
    @canISteal
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
    
    
    @visibleTreasures.collect{ |a| a.type}.count(tk)

  end
  
  
  def isTreasureKindInUse(type)
        
    @visibleTreasures.collect{ |a| a.type}.include?(type)

  end
  
  def getOponentLevel(m)
    m.getCombatLevel
  end
  
  def dieIfNoTreasures
    
    @death = true if @visibleTreasures.empty? && @hiddenTreasures.empty?
  end
  
  def combat (m)
    
    dice = Dice.instance
    n = dice.nextNumber
    
    level = getOponentLevel(m) +  (!canISteal && n < 3 ? @enemy.getCombatLevel : 0)
    
    if getCombatLevel <= level then
      
      applyBadConsequence(m)
      
      return CombatResult::LOSEANDCONVERT if shouldConvert
      
      return CombatResult::LOSE
      
    end
    
    applyPrize(m)
    
    if @level >= 10 
      return CombatResult::WINGAME
    end
    
    return CombatResult::WIN
    
  end
  
  
  def makeTreasureVisible(t)
    
    if canMakeTreasureVisible(t) then
      
      @visibleTreasures << @hiddenTreasures.delete(t)

    end
  end
  
  def discardVisibleTreasure(t)
    
    @visibleTreasures -= [t]
    
    c = CardDealer.instance
    
    c.giveTreasureBack(t);
    
    if @pendingBC != nil && !@pendingBC.empty? then
      
      @pendingBC.substractVisibleTreasure(t)
    
    end
    
    dieIfNoTreasures
    
  end

  def discardHiddenTreasure(t)
    
    @hiddenTreasures -= [t]
    
    c = CardDealer.instance
    
    c.giveTreasureBack(t);
    
    if @pendingBC != nil && !@pendingBC.empty? then
      
      @pendingBC.substractHiddenTreasure(t)
    
    end
    
    dieIfNoTreasures
    
  end
  
  def validState
    
    return @hiddenTreasures.length <= 4 && (@pendingBC.nil? || @pendingBC.empty?)
    
  end
  
  def stealTreasure
    
    unless @canISteal && @enemy.canYouGiveMeATreasure
      return nil
    end
        
    haveStolen
    
    t = @enemy.giveMeATreasure
    @hiddenTreasures << t

   return t
  end
  
  def giveMeATreasure
    
    t = @hiddenTreasures[rand(@hiddenTreasures.size)]
    discardHiddenTreasure(t)
    return t
    
  end
  
  def initTreasures
    
    dealer = CardDealer.instance
    dice = Dice.instance
    
    bringToLife
    
    treasure = dealer.nextTreasure
    @hiddenTreasures << treasure
    
    n = dice.nextNumber
    
    if n == 6 then
      treasure = dealer.nextTreasure
      @hiddenTreasures << treasure
      treasure = dealer.nextTreasure
      @hiddenTreasures << treasure
      
    elsif n > 1 then
      
      treasure = dealer.nextTreasure
      @hiddenTreasures << treasure
      
    end

  end
  
  def setEnemy (enemy)
    @enemy = enemy
  end
  
  def to_s
    "#{@name} (Nivel de jugador: #{@level}  Nivel de combate: #{getCombatLevel}  Enemigo: #{@enemy.getName})"
  end
  
  private :bringToLife, :incrementLevels, :decrementLevels, :setPendingBC, :applyPrize, :applyBadConsequence, :canMakeTreasureVisible, :howManyVisibleTreasures, :dieIfNoTreasures
  protected :giveMeATreasure, :canYouGiveMeATreasure, :getCombatLevel
  
end
end
