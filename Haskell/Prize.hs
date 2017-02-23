

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

instance Show Prize
show (Prize t l) = "Treasures: " ++ (show t) ++ "\nLevels: " ++ (show l)

getTreasures :: Prize -> Treasures
  getTreasures (Prize x _) = x

getLevels :: Prize -> Levels
  getLevels (Prize _ x) = x


