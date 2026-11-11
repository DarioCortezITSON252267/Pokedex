package dario.cortez.pokedex.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dario.cortez.pokedex.model.domain.Destination
import dario.cortez.pokedex.viewmodel.PokemonDetailViewModel
import dario.cortez.pokedex.viewmodel.UserViewModel

@Composable
fun Navigation(
    userViewModel: UserViewModel,
    pokemonDetailViewModel: PokemonDetailViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    val currentEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentEntry?.destination
    val showBottomBar = currentDestination != null && !currentDestination.hasRoute(Login::class)

    Scaffold(modifier = modifier.fillMaxSize(), bottomBar = {
        if (showBottomBar) NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
            Destination.entries.forEach { destination ->
                val route = destination.toRoute()
                NavigationBarItem(
                    selected = currentDestination?.hasRoute(route::class) == true,
                    onClick = {
                        navController.navigate(route) {
                            popUpTo<PokemonList> { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(destination.icon),
                            contentDescription = destination.contentDescription
                        )
                    },
                    label = { Text(destination.label) }
                )
            }
        }
    }) { innerPadding ->
        MyApp(
            navController = navController,
            innerPadding = innerPadding,
            userViewModel = userViewModel,
            pokemonDetailViewModel = pokemonDetailViewModel
        )
    }
}

private fun Destination.toRoute(): Any = when (this) {
    Destination.HOME -> PokemonList
    Destination.HUNT -> PokemonHunt
    Destination.PROFILE -> Profile
}
