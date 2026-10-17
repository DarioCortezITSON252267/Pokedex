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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dario.cortez.pokedex.model.domain.Destination

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    // La pestaña seleccionada sale de la pantalla actual, así se mantiene bien al pulsar "atrás"
    val currentEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentEntry?.destination

    Scaffold(modifier = modifier.fillMaxSize(), bottomBar = {
        NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
            // Iterar la colección de destinos para crear un botón por cada uno
            Destination.entries.forEach { destination ->
                val route = destination.toRoute()
                NavigationBarItem(
                    selected = currentDestination?.hasRoute(route::class) == true,
                    onClick = {
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
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
        MyApp(navController = navController, innerPadding = innerPadding)
    }
}

// Relaciona cada destino de la barra con su ruta de Routes.kt
private fun Destination.toRoute(): Any = when (this) {
    Destination.HOME -> PokemonList
    Destination.HUNT -> PokemonHunt
    Destination.PROFILE -> Profile
}
