# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.


require_relative 'monster.rb'
require_relative 'bad_consequence.rb'
require_relative 'prize.rb'
require_relative 'treasure_kind.rb'

class PruebaNapakalaki

  def initialize 
    @@monsters = Array.new
    generateMonsters()
  end

  def generateMonsters()
    
    # 3 byakhees de bonanza
    price1 = Prize.new(2, 1)
    badConsequence1 = BadConsequence.newLevelSpecificTreasures('Pierdes tu armadura visible y otra oculta', 0, [TreasureKind::ARMOR], [TreasureKind::ARMOR])
    @@monsters << Monster.new('3 byakhees de bonanza', 8, price1, badConsequence1)
    
    # Tenochtitlan
    price2 = Prize.new(1, 1)
    badConsequence2 = BadConsequence.newLevelSpecificTreasures('Embobados con el primigenio te descartas de tu casco visible.', 0, [] , [TreasureKind::HELMET])
    @@monsters << Monster.new('Tenochtitlan', 2, price2, badConsequence2)
    
    # El sopor de Dunwich
    price3 = Prize.new(1, 1)
    badConsequence3 = BadConsequence.newLevelSpecificTreasures('El primordial bostezo contagioso, pierdes el calzado visible', 0, [TreasureKind::SHOES], [])
    @@monsters << Monster.new('El sopor de Dunwich', 2, price3, badConsequence3)
    
    # Demonios de magaluf
    prize4 = Prize.new(4, 1)
    bc4 = BadConsequence.newLevelSpecificTreasures("Te atrapan para llevarte de fiesta y te dejan caer en mitad de vuelo. Descarta 1 mano visible y 1 mano oculta.", 0, [TreasureKind::ONEHAND], [TreasureKind::ONEHAND])
    @@monsters << Monster.new('Demonios de magaluf', 2, prize4, bc4)
    
    # El gorron en el umbral
    p5 = Prize.new(3, 1)
    bc5 = BadConsequence.newLevelNumberOfTreasures("pierdes todos tus tesoros visibles", 0, 10, 0)
    @@monsters << Monster.new('El gorron del umbral', 13, p5, bc5)
    
    # H.P munchcraft
    p6 = Prize.new(2, 1)
    bc6 = BadConsequence.newLevelSpecificTreasures("Pierdes la armadura visible", 0, [TreasureKind::ARMOR], [])
    @@monsters << Monster.new('H.P. Munchcraft', 6, p6, bc6)
    
    # Necrofago
    p7 = Prize.new(1, 1)
    bc7 = BadConsequence.newLevelSpecificTreasures("Sientes bichos bajo la ropa. Descarta la armadura visible", 0, [TreasureKind::ARMOR], [])
    @@monsters << Monster.new('Necrofago', 13, p7, bc7)
    
    # El rey rosado
    price = Prize.new(3,2)
    badConsequence = BadConsequence.newLevelNumberOfTreasures('Pierdes 5 niveles y 3 tesoros visibles',5 , 3, 0)
    @@monsters << Monster.new('El rey rosado',11,price, badConsequence)
    
    # Flecher
    p8 = Prize.new(1, 1)
    bc8 = BadConsequence.newLevelNumberOfTreasures("Toses los pulmones y pierdes 2 niveles", 2, 0, 0)
    @@monsters << Monster.new('Flecher', 2, p8, bc8)
    
    # los hondos
    p9 = Prize.new(2, 1)
    bc9 = BadConsequence.newDeath('Estos monstruos resultab bastante superficiales y te aburren mortalmente. Estas muerto')
    @@monsters << Monster.new('los hondos', 8, p9, bc9)
    
    # semillas cthulhu
    p10 = Prize.new(2, 1)
    bc10 = BadConsequence.newLevelNumberOfTreasures('Pierdes 2 niveles y 2 tesoros ocultos', 2, 0, 2)
    @@monsters << Monster.new('Semillas Cthulhu', 4, p10, bc10)
    
    # Dameargo
    p11 = Prize.new(2, 1)
    bc11 = BadConsequence.newLevelSpecificTreasures('Te intentas escaquear. Pierdes una mano visible', 0, [TreasureKind::ONEHAND], [])
    @@monsters << Monster.new('Dameargo', 1, p11, bc11)
    
    # Pollipolipo volante
    p12 = Prize.new(2, 1)
    bc12 = BadConsequence.newLevelNumberOfTreasures('Da mucho asquito. Pierdes 3 niveles', 3, 0, 0)
    @@monsters << Monster.new('Pollipolipo volante', 3, p12, bc12)
    
    # Yskhtihyssg-Goth
    p13 = Prize.new(3, 1)
    bc13 = BadConsequence.newDeath('No le hace gracia que pronuncien mal su nombre. Estas muerto')
    @@monsters << Monster.new('Yskhtihyssg-Goth', 14, p13, bc13)
    
    # Familia Feliz
    p14 = Prize.new(3, 1)
    bc14 = BadConsequence.newDeath('La familia te atrapa. Estas muerto')
    @@monsters << Monster.new('Familia feliz', 1, p14, bc14)
    
    # Roboggoth
    p15 = Prize.new(2, 1)
    bc15 = BadConsequence.newLevelSpecificTreasures('La quinta directiva primitiva te obliga a perder 2 niveles y un tesoro 2 manos visible',2,[TreasureKind::BOTHHANDS],[])
    @@monsters << Monster.new('Robbogoth', 8, p15, bc15)
    
    # El Espía sordo
    p16 = Prize.new(1, 1)
    bc16 = BadConsequence.newLevelSpecificTreasures('Te asusta en la noche.Pierdes un casco visible',0,[TreasureKind::HELMET],[])
    @@monsters << Monster.new('El espia sordo', 5, p16, bc16)
    
    #Tongue
    p17 = Prize.new(2, 1)
    bc17 = BadConsequence.newLevelNumberOfTreasures('Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles.', 2, 5, 0)
    @@monsters << Monster.new('Tongue', 19, p17, bc17)
    
    #Bicéfalo
    p18 = Prize.new(2, 1)
    bc18 = BadConsequence.newLevelNumberOfTreasures('Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos.', 3, [TreasureKind::ONEHAND,TreasureKind::ONEHAND,TreasureKind::BOTHHANDS], 0)
    @@monsters << Monster.new('Bicefalo', 21, p18, bc18)

  end
  
  def levelHigherThan10()
    @@monsters.each do |a| 
      puts a if a.combatLevel > 10
    end
  end

  def onlyLoseLevels()
    @@monsters.each do |a| 
        if a.badConsequence.nLevels != 0 and a.badConsequence.nVisibleTreasures == 0 and a.badConsequence.nHiddenTreasures == 0 and a.badConsequence.specificVisibleTreasures == Array.new and a.badConsequence.specificHiddenTreasures == Array.new and a.badConsequence.death == false
        then
        puts a
        end
        end
  end
  
  def gainMoreThan1Level()
    @@monsters.each do |a| 
      if a.prize.levels > 1
        then
        puts a
      end
    end
  end
  
  def loseSpecificTreasures()
    @@monsters.each do|a| 
      if a.badConsequence.specificVisibleTreasures != Array.new or a.badConsequence.specificHiddenTreasures != Array.new 
        then
        puts a 
      end
    end
  end
end

puts "Hola"
prueba1 = PruebaNapakalaki.new

puts "Funcion 1\n"
prueba1.levelHigherThan10
puts "Funcion 2\n"

prueba1.onlyLoseLevels
puts "Funcion 3\n"
prueba1.gainMoreThan1Level
puts "Funcion 4\n"
prueba1.loseSpecificTreasures

puts "Adios"
