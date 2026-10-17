package dario.cortez.pokedex.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dario.cortez.pokedex.screens.MenuPokedexScreen
import dario.cortez.pokedex.screens.PokemonDetailsScreen
import dario.cortez.pokedex.screens.PokemonHuntScreen
import dario.cortez.pokedex.screens.ProfileScreen

@Composable
fun MyApp(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController, startDestination = PokemonList) {
        composable<PokemonList> {
            MenuPokedexScreen(
                innerPadding = innerPadding,
                onPokemonClick = { number -> navController.navigate(PokemonDetail(number)) }
            )
        }
        composable<PokemonDetail> { backStackEntry ->
            val detail = backStackEntry.toRoute<PokemonDetail>()
            PokemonDetailsScreen(innerPadding = innerPadding, pokemon = detail.pokemon)
        }
        composable<PokemonHunt> {
            PokemonHuntScreen(innerPadding = innerPadding)
        }
        composable<Profile> {
            ProfileScreen(innerPadding)
        }
    }
}
