package dario.cortez.pokedex.model.domain

import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import dario.cortez.pokedex.R

enum class Destination(val route: String, val label: String, val icon: Int, val contentDescription: String) {
    HOME(route = "PokemonList", label = "Home", icon = R.drawable.home, contentDescription = "home"),
    HUNT(route = "PokemonHunt", label="New Pokemon", icon= R.drawable.hunt, contentDescription = "hunt"),
    PROFILE(route = "Profile", label = "Profile", icon = R.drawable.account, contentDescription = "profile")
}