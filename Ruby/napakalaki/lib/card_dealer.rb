#encoding: utf-8

module NapakalakiGame

require "singleton"
require_relative "treasure.rb"
require_relative "monster.rb"
require_relative "prize.rb"
require_relative "bad_consequence.rb"
require_relative 'treasure_kind.rb'
require_relative 'cultist.rb'

class CardDealer
  
  include Singleton
    
  attr_reader :usedMonsters, :unusedMonsters, :usedTreasures, :unusedTreasures
  
  private
  def initTreasureDeck
    
    @usedTreasures = Array.new
    @unusedTreasures = Array.new
    
    @unusedTreasures <<  Treasure.new("Linterna a 2 manos", 3, TreasureKind::BOTHHANDS)
    @unusedTreasures <<  Treasure.new("Necronomicon", 5, TreasureKind::BOTHHANDS)
    @unusedTreasures <<  Treasure.new("Gaita", 4, TreasureKind::BOTHHANDS)
    @unusedTreasures <<  Treasure.new("Botas de lluvia acida", 1, TreasureKind::BOTHHANDS)
    @unusedTreasures <<  Treasure.new("Lanzallamas", 4, TreasureKind::BOTHHANDS)
    @unusedTreasures <<  Treasure.new("Escopeta de 3 cañones", 3, TreasureKind::BOTHHANDS)
    @unusedTreasures <<  Treasure.new("Ametralladora ACME", 4, TreasureKind::BOTHHANDS)
    @unusedTreasures <<  Treasure.new("Shogulador", 1, TreasureKind::BOTHHANDS)
        
    @unusedTreasures <<  Treasure.new("Necrotelecom", 2, TreasureKind::HELMET)
    @unusedTreasures <<  Treasure.new("Casco minero", 2, TreasureKind::HELMET)
    @unusedTreasures <<  Treasure.new("Tentáculo de pega", 2, TreasureKind::HELMET)
    @unusedTreasures <<  Treasure.new("Capucha de Cthulhu", 3, TreasureKind::HELMET)
    @unusedTreasures <<  Treasure.new("Fez alopodo", 3, TreasureKind::HELMET)
    @unusedTreasures <<  Treasure.new("¡Si mi amo!", 4, TreasureKind::HELMET)
        
    @unusedTreasures <<  Treasure.new("Camiseta de la ETSIIT", 1, TreasureKind::ARMOR)
    @unusedTreasures <<  Treasure.new("La rebecametalica", 2, TreasureKind::ARMOR)
    @unusedTreasures <<  Treasure.new("A prueba de babas", 2, TreasureKind::ARMOR)
    @unusedTreasures <<  Treasure.new("El aparato del Pr. Tesla", 4, TreasureKind::ARMOR)
        
    @unusedTreasures <<  Treasure.new("Garabato mistico", 2, TreasureKind::ONEHAND)
    @unusedTreasures <<  Treasure.new("Necrocomicon", 1, TreasureKind::ONEHAND)
    @unusedTreasures <<  Treasure.new("Necroplayboycon", 3, TreasureKind::ONEHAND)
    @unusedTreasures <<  Treasure.new("Hacha prehistorica", 2, TreasureKind::ONEHAND)
    @unusedTreasures <<  Treasure.new("Mazo de los antiguos", 3, TreasureKind::ONEHAND)
    @unusedTreasures <<  Treasure.new("Clavo de rail ferroviario", 3, TreasureKind::ONEHAND)
    @unusedTreasures <<  Treasure.new("Porra preternatural", 1, TreasureKind::ONEHAND)
    @unusedTreasures <<  Treasure.new("Cuchillo de sushi arcano", 2, TreasureKind::ONEHAND)
    @unusedTreasures <<  Treasure.new("Varita de atizamiento", 3, TreasureKind::ONEHAND)
    @unusedTreasures <<  Treasure.new("Necrognomicon", 2, TreasureKind::ONEHAND)
    @unusedTreasures <<  Treasure.new("Insecticida", 2, TreasureKind::ONEHAND)
        
    @unusedTreasures <<  Treasure.new("Zapato deja-amigos", 1, TreasureKind::SHOES)
    @unusedTreasures <<  Treasure.new("botas de investigacion", 3, TreasureKind::SHOES)
  end
  
  
  def initMonsterDeck
    
    @usedMonsters = Array.new
    @unusedMonsters = Array.new
    
    # 3 byakhees de bonanza
    p = Prize.new(2, 1)
    b = BadConsequence.newLevelSpecificTreasures('Pierdes tu armadura visible y otra oculta', 0, [TreasureKind::ARMOR], [TreasureKind::ARMOR])
    @unusedMonsters << Monster.newStandard('3 byakhees de bonanza', 8, p, b)
    
    # Tenochtitlan
    p = Prize.new(1, 1)
    b = BadConsequence.newLevelSpecificTreasures('Embobados con el primigenio te descartas de tu casco visible.', 0, [] , [TreasureKind::HELMET])
    @unusedMonsters << Monster.newStandard('Tenochtitlan', 2, p, b)
    
    # El sopor de Dunwich
    p = Prize.new(1, 1)
    b = BadConsequence.newLevelSpecificTreasures('El primordial bostezo contagioso, pierdes el calzado visible', 0, [TreasureKind::SHOES], [])
    @unusedMonsters << Monster.newStandard('El sopor de Dunwich', 2, p, b)
    
    # Demonios de magaluf
    p = Prize.new(4, 1)
    b = BadConsequence.newLevelSpecificTreasures("Te atrapan para llevarte de fiesta y te dejan caer en mitad de vuelo. Descarta 1 mano visible y 1 mano oculta.", 0, [TreasureKind::ONEHAND], [TreasureKind::ONEHAND])
    @unusedMonsters << Monster.newStandard('Demonios de magaluf', 2, p, b)
    
    # El gorron en el umbral
    p = Prize.new(3, 1)
    b = BadConsequence.newLevelNumberOfTreasures("pierdes todos tus tesoros visibles", 0, 10, 0)
    @unusedMonsters << Monster.newStandard('El gorron del umbral', 13, p, b)
    
    # H.P munchcraft
    p = Prize.new(2, 1)
    b = BadConsequence.newLevelSpecificTreasures("Pierdes la armadura visible", 0, [TreasureKind::ARMOR], [])
    @unusedMonsters << Monster.newStandard('H.P. Munchcraft', 6, p, b)
    
    # Necrofago
    p = Prize.new(1, 1)
    b = BadConsequence.newLevelSpecificTreasures("Sientes bichos bajo la ropa. Descarta la armadura visible", 0, [TreasureKind::ARMOR], [])
    @unusedMonsters << Monster.newStandard('Necrofago', 13, p, b)
    
    # El rey rosado
    p = Prize.new(3,2)
    b = BadConsequence.newLevelNumberOfTreasures('Pierdes 5 niveles y 3 tesoros visibles',5 , 3, 0)
    @unusedMonsters << Monster.newStandard('El rey rosado',11, p, b)
    
    # Flecher
    p = Prize.new(1, 1)
    b = BadConsequence.newLevelNumberOfTreasures("Toses los pulmones y pierdes 2 niveles", 2, 0, 0)
    @unusedMonsters << Monster.newStandard('Flecher', 2, p, b)
    
    # los hondos
    p = Prize.new(2, 1)
    b = BadConsequence.newDeath('Estos monstruos resultan bastante superficiales y te aburren mortalmente. Estas muerto')
    @unusedMonsters << Monster.newStandard('Los hondos', 8, p, b)
    
    # semillas cthulhu
    p = Prize.new(2, 1)
    b = BadConsequence.newLevelNumberOfTreasures('Pierdes 2 niveles y 2 tesoros ocultos', 2, 0, 2)
    @unusedMonsters << Monster.newStandard('Semillas Cthulhu', 4, p, b)
    
    # Dameargo
    p = Prize.new(2, 1)
    b = BadConsequence.newLevelSpecificTreasures('Te intentas escaquear. Pierdes una mano visible', 0, [TreasureKind::ONEHAND], [])
    @unusedMonsters << Monster.newStandard('Dameargo', 1, p, b)
    
    # Pollipolipo volante
    p = Prize.new(2, 1)
    b = BadConsequence.newLevelNumberOfTreasures('Da mucho asquito. Pierdes 3 niveles', 3, 0, 0)
    @unusedMonsters << Monster.newStandard('Pollipolipo volante', 3, p, b)
    
    # Yskhtihyssg-Goth
    p = Prize.new(3, 1)
    b = BadConsequence.newDeath('No le hace gracia que pronuncien mal su nombre. Estas muerto')
    @unusedMonsters << Monster.newStandard('Yskhtihyssg-Goth', 14, p, b)
    
    # Familia Feliz
    p = Prize.new(3, 1)
    b = BadConsequence.newDeath('La familia te atrapa. Estas muerto')
    @unusedMonsters << Monster.newStandard('Familia feliz', 1, p, b)
    
    # Roboggoth
    p = Prize.new(2, 1)
    b = BadConsequence.newLevelSpecificTreasures('La quinta directiva primitiva te obliga a perder 2 niveles y un tesoro 2 manos visible',2,[TreasureKind::BOTHHANDS],[])
    @unusedMonsters << Monster.newStandard('Robbogoth', 8, p, b)
    
    # El Espía sordo
    p = Prize.new(1, 1)
    b = BadConsequence.newLevelSpecificTreasures('Te asusta en la noche.Pierdes un casco visible',0,[TreasureKind::HELMET],[])
    @unusedMonsters << Monster.newStandard('El espia sordo', 5, p, b)
    
    #Tongue
    p = Prize.new(2, 1)
    b = BadConsequence.newLevelNumberOfTreasures('Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles.', 2, 5, 0)
    @unusedMonsters << Monster.newStandard('Tongue', 19, p, b)
    
    #Bicéfalo
    p = Prize.new(2, 1)
    b = BadConsequence.newLevelSpecificTreasures('Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos.', 3, [TreasureKind::ONEHAND,TreasureKind::ONEHAND,TreasureKind::BOTHHANDS], [])
    @unusedMonsters << Monster.newStandard('Bicefalo', 21, p, b)
    
    
    
    #El mal indecible impronunciable
    p = Prize.new(3, 1)
    b = BadConsequence.newLevelSpecificTreasures('Pierdes una mano visible.', 0, [TreasureKind::ONEHAND], [])
    @unusedMonsters << Monster.newCultist('El mal indecible impronunciable', 10, p, b, -2)
    
    #Testigos oculates
    p = Prize.new(2, 1)
    b = BadConsequence.newLevelNumberOfTreasures('Pierdes tus tesoros visibles. Jajaja', 0, 5, 0)
    @unusedMonsters << Monster.newCultist('Testigos oculates', 6, p, b, 2)
    
    
    #El gran Cthulhu
    p = Prize.new(2, 5)
    b = BadConsequence.newDeath('Hoy no es tu dia de suerte. Mueres.')
    @unusedMonsters << Monster.newCultist('El gran Cthulhu', 20, p, b, 4)
    
    
    #Serpiente politico
    p = Prize.new(2, 1)
    b = BadConsequence.newLevelNumberOfTreasures('Tu gobierno te recorta 2 niveles.', 2, 0, 0)
    @unusedMonsters << Monster.newCultist('El mal indecible impronunciable', 8, p, b, -2)
    
    
    #Felpuggoth
    p = Prize.new(1, 1)
    b = BadConsequence.newLevelSpecificTreasures('Pierdes tu casco y tu armadura visible. Pierdes tus manos ocultas.', 0, [TreasureKind::HELMET, TreasureKind::ARMOR], [TreasureKind::ONEHAND, TreasureKind::ONEHAND, TreasureKind::BOTHHANDS])
    @unusedMonsters << Monster.newCultist('Felpuggoth', 2, p, b, 5)
    
    #Shoggoth
    p = Prize.new(4, 2)
    b = BadConsequence.newLevelNumberOfTreasures('Pierdes 2 niveles.', 2, 0, 0)
    @unusedMonsters << Monster.newCultist('Shoggoth', 16, p, b, -4)
    
    #Lolitagooth
    p = Prize.new(1, 1)
    b = BadConsequence.newLevelNumberOfTreasures('Pintalabios negro. Pierdes 2 niveles.', 2, 0, 0)
    @unusedMonsters << Monster.newCultist('Lolitagooth', 2, p, b, 3)

  end
  
  def initCultistsDeck
    @usedCultists = Array.new
    @unusedCultists = Array.new
    
    @unusedCultists << Cultist.new(1)
    @unusedCultists << Cultist.new(1)
    @unusedCultists << Cultist.new(1)
    @unusedCultists << Cultist.new(1)
    @unusedCultists << Cultist.new(2)
    @unusedCultists << Cultist.new(2)

  end
  
  def shuffleCultists
    
    @unusedCultists.shuffle!
    
  end
  
  def shuffleTreasures
    
    @unusedTreasures.shuffle!
    
  end
  
  def shuffleMonsters
    
    @unusedMonsters.shuffle!
    
  end
  
  public
  
  def nextCultist 
    
    t = @unusedCultists.at(0)
    
    @usedCultists << t
    
    @unusedCultists.delete(t)
    
    return t
    
  end
  
  
  def nextTreasure
    
    if (@unusedTreasures.empty?)
      
      @usedTreasures.each do |t|
        
        @unusedTreasures << t
        
      end
      
      @usedTreasures.clear
      
      shuffleTreasures
   
    end
    
    t = @unusedTreasures.at(0)
    
    @usedTreasures << t
    
    @unusedTreasures.delete(t)
    
    return t
    
  end
  
  def nextMonster
    
    if (@unusedMonsters.empty?)
      
      @usedMonsters.each do |m|
        
        @unusedMonsters << m
      end
      
      @usedMonsters.clear
      
      shuffleMonsters
      
    end
    
    m = @unusedMonsters.at(0)
    
    @usedMonsters << m
    
    @unusedMonsters.delete_at(0)
    
    return m
    
  end
  
  def giveTreasureBack(t)
    
    @usedTreasures << t
    
  end
  
  def giveMonsterBack(m)
    
    @usedMonster << m
  end
  
  def initCards
    
    initMonsterDeck
    initTreasureDeck
    initCultistsDeck
    
    shuffleCultists
    shuffleMonsters
    shuffleTreasures
    
  end
  
end

end