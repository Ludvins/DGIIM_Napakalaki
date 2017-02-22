# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

class Prize
  def initialize(treasures, levels)
    @treasures = treasures
    @levels = levels
    
  end
  
  attr_reader :treasures, :levels
  
  def to_s
    "Treasures earned: #{treasures} \nLevels earned: #{levels}"
  end
end
