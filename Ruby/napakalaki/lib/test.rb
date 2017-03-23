# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'bad_consequence.rb'
require_relative 'prize.rb'
require_relative 'monster.rb'
require_relative 'treasure.rb'

class Test
  def initialize
    
  end
  
  def self.testClase 
    p18 = Prize.new(2, 1)
    bc18 = BadConsequence.newLevelSpecificTreasures('Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos.', 3, [TreasureKind::ONEHAND,TreasureKind::ONEHAND,TreasureKind::BOTHHANDS], 0)
    m = Monster.new('Bicefalo', 21, p18, bc18)
    
    puts m
  end
  

  def testInstancia
    p17 = Prize.new(2, 1)
    bc17 = BadConsequence.newLevelNumberOfTreasures('Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles.', 2, 5, 0)
    m = Monster.new('Tongue', 19, p17, bc17)
    
    puts m
  end
end

t = Test.new 

puts "Instancia"

t.testInstancia

puts "Clase"

Test.testClase
