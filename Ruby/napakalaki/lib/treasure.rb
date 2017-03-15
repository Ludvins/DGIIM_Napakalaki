# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

class Treasure
  
  def initialize(name, bonus, tk)
    
    @name = name
    @bonus = bonus
    @type = tk
    
  end
  
  attr_reader :name, :bonus, :type
end
