package dario.cortez.pokedex.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dario.cortez.pokedex.screens.LoginScreen
import dario.cortez.pokedex.screens.MenuPokedexScreen
import dario.cortez.pokedex.screens.PokemonDetailsScreen
import dario.cortez.pokedex.screens.PokemonHuntScreen
import dario.cortez.pokedex.screens.ProfileScreen
import dario.cortez.pokedex.viewmodel.PokemonDetailViewModel
import dario.cortez.pokedex.viewmodel.UserViewModel

@Composable
fun MyApp(
    navController: NavHostController,
    innerPadding: PaddingValues,
    userViewModel: UserViewModel,
    pokemonDetailViewModel: PokemonDetailViewModel
) {
    NavHost(navController, startDestination = Login) {
        composable<Login> {
            LoginScreen(
                innerPadding = innerPadding,
                onNavigateToHome = {
                    // Se quita el Login de la pila para que "atrás" no regrese a él
                    navController.navigate(PokemonList) {
                        popUpTo<Login> { inclusive = true }
                        launchSingleTop = true
                    }
                },
                userViewModel = userViewModel
            )
        }
        composable<PokemonList> {
            MenuPokedexScreen(
                innerPadding = innerPadding,
                showAsList = userViewModel.showAsList,
                onDisplayChange = { userViewModel.changePokemonDisplay(it) },
                onLogout = {
                    userViewModel.logout()
                    navController.navigate(Login) {
                        popUpTo(navController.graph.id) { inclusive = true }
                    }
                },
                onCatchNew = { navController.navigate(PokemonHunt) },
                onPokemonClick = { number -> navController.navigate(PokemonDetail(number)) },
                onNavigateToProfile = { navController.navigate(Profile) { launchSingleTop = true } }
            )
        }
        composable<PokemonDetail> { backStackEntry ->
            val detail = backStackEntry.toRoute<PokemonDetail>()
            PokemonDetailsScreen(
                innerPadding = innerPadding,
                pokemonNumber = detail.pokemon,
                pokemonDetailViewModel = pokemonDetailViewModel
            )
        }
        composable<PokemonHunt> {
            PokemonHuntScreen(innerPadding = innerPadding)
        }
        composable<Profile> {
            ProfileScreen(innerPadding = innerPadding, userViewModel = userViewModel)
        }
    }
}
