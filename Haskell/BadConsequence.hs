


module BadConsequence
  (
    BadConsequence(..),
    fromTextLevelsItems,
    fromTextDeath,
    fromTextLevelsSpecific
  ) where

data BadConsequence = BadConsequence {text::String, levels::Int, nVisibleTreasures::Int, nHiddenTreasures::Int, death::Bool specificVisible::[TreasureKind], SpecificHidden::[TreasureKind]{

fromTextLevelsItems :: Text -> Levels -> NVisibleTreasures -> NHiddenTreasures -> BadConsequence
fromTextLevelsItems t l v h = BadConsequence t l v h False [] []

fromTextDeath :: Text -> Death -> BadConsequence
fromTextDeath t d = BadConsequence t 0 0 0 True [] []

fromTextLevelsSpecific :: Text -> Levels -> SpecificVisible -> SpecificHidden -> BadConsequence
fromTextLevelsSpecific t l v h = BadConsequence t l 0 0 False v h

data TreasureKind = ARMOR | ONEHAND | BOTHHANDS | HELMET | SHOES

instance Show BadConsequence
show (Prize t l nv nh d sv sh ) = "Text: " ++ (show t) ++ "\nLevels: " ++ (show l) ++ "\nVisible Treasures Lost: " ++ (show nv) ++ "\nHidden Treasures Lost: " ++ (show nh) ++ "\nDeath: " ++ (show d) ++ "\nSpecific Visible Treasures Lost: " ++ (show sv) ++ "\nSpecific Hidden Treasures Lost: " ++ (show sh)







