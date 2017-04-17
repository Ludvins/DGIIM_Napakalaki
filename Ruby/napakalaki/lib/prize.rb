# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module NapakalakiGame

class Prize
      
  attr_reader :treasures, :levels
  
  
  def initialize(treasures, levels)
    @treasures = treasures
    @levels = levels
    
  end

  
  def to_s
    "\n\tEarned Treasures: #{@treasures} \n\tEarned Levels: #{@levels}\n"
  end
end
end
