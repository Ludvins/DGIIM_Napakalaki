

module Monster
  (
    Monster(..)
    
  ) where

import Prize
import BadConsequence

type Name = String
type Level = Int


data Monster = Monster { name::String, level::Int, prize::Prize, badConsequence::BadConsequence}

instance Show Monster
show (Monster n l p c) = "Name: " ++ (show n) ++ "\nLevel: " ++ (show l) ++ "\nPrize: " ++ (show p) ++ "\nBadConsequence: " ++ (show c)


