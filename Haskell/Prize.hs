

module Prize
  (

    Prize(..),
    getTreasures,
    getLevels,
    toString

  ) where

type Treasures = Int
type Levels = Int
data Prize = Prize Treasures Levels

  deriving Show -- Salida por defecto.

getTreasures :: Prize -> Treasures
  getTreasures (Prize x _) = x

getLevels :: Prize -> Levels
  getLevels (Prize _ x) = x

toString :: Prize -> String
  toString (Prize x y) = "Treasures: " ++ (show x) ++ "\nLevels: " ++ (show y)


