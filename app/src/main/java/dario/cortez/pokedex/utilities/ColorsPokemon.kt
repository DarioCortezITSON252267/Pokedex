package dario.cortez.pokedex.utilities

import androidx.compose.ui.graphics.Color
import dario.cortez.pokedex.ui.theme.Bug
import dario.cortez.pokedex.ui.theme.DarkGray
import dario.cortez.pokedex.ui.theme.Electric
import dario.cortez.pokedex.ui.theme.Fairy
import dario.cortez.pokedex.ui.theme.Fight
import dario.cortez.pokedex.ui.theme.Fire
import dario.cortez.pokedex.ui.theme.Flying
import dario.cortez.pokedex.ui.theme.Ghost
import dario.cortez.pokedex.ui.theme.Grass
import dario.cortez.pokedex.ui.theme.Ground
import dario.cortez.pokedex.ui.theme.Normal
import dario.cortez.pokedex.ui.theme.OffWhite
import dario.cortez.pokedex.ui.theme.Poison
import dario.cortez.pokedex.ui.theme.Psych
import dario.cortez.pokedex.ui.theme.Rock
import dario.cortez.pokedex.ui.theme.Water

fun getColorByType(type: String): Pair<Color, Color> {
    // Los pokemones con dos tipos ("Grass/Poison") toman el color del primero
    val mainType = type.substringBefore("/").trim().lowercase()
    return when {
        mainType == "normal" -> Pair(Normal, OffWhite)
        mainType == "water" -> Pair(Water, OffWhite)
        mainType == "fire" -> Pair(Fire, OffWhite)
        mainType.startsWith("psych") -> Pair(Psych, OffWhite)
        mainType == "ghost" -> Pair(Ghost, OffWhite)
        mainType == "bug" -> Pair(Bug, OffWhite)
        mainType == "poison" -> Pair(Poison, OffWhite)
        mainType == "grass" -> Pair(Grass, OffWhite)
        mainType == "ground" -> Pair(Ground, OffWhite)
        mainType == "rock" -> Pair(Rock, OffWhite)
        mainType == "electric" -> Pair(Electric, DarkGray)
        mainType == "fairy" -> Pair(Fairy, DarkGray)
        mainType.startsWith("fight") -> Pair(Fight, DarkGray)
        mainType == "flying" -> Pair(Flying, DarkGray)
        else -> Pair(Normal, OffWhite)
    }
}
