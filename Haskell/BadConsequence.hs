


module BadConsequence
  (
    BadConsequence(..),
    getText,
    getLevels,
    getNVisible,
    getNHidden,
    getDeath

  ) where

type Text = String
type Levels = Int
type NVisibleTreasures = Int
type NHiddenTreasures = Int
type Death = Bool
type SpecificHidden = [TreasureKind]
type SpecificVisible = [TreasureKind]

data BadConsequence = TextLevelsItems Text Levels NVisibleTreasures NHiddenTreasures | TextDeath Text Death | TextLevelsSpecific Text Levels SpecificHidden SpecificVisible

data TreasureKind = ARMOR | ONEHAND | BOTHHANDS | HELMET | SHOES

deriving Show

getText :: Prize -> Text
getText (Prize x _ _ _ _) = x

getLevels :: Prize -> Levels
getLevels (Prize _ x _ _ _) = x

getNVisible :: Prize -> NVisibleTreasures
getNVisible (Prize _ _ x _ _) = x

getNHidden :: Prize -> NHiddenTreasures
getNHidden (Prize _ _ _ x _) = x

getDeath :: Prize -> Death
getDeath (Prize _ _ _ _ x) = x


