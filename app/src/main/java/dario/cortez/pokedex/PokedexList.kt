package dario.cortez.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dario.cortez.pokedex.model.data.CompanionDataStore
import dario.cortez.pokedex.model.data.Users
import dario.cortez.pokedex.navigation.Navigation
import dario.cortez.pokedex.ui.theme.PokedexTheme
import dario.cortez.pokedex.viewmodel.PokemonDetailViewModel
import dario.cortez.pokedex.viewmodel.UserViewModel

class PokedexList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Users necesita un Context para leer las SharedPreferences; la Activity lo tiene
        val users = Users(context = applicationContext)
        val companionDataStore = CompanionDataStore(context = applicationContext)
        val userViewModel = UserViewModel(users, companionDataStore)
        val pokemonDetailViewModel = PokemonDetailViewModel(companionDataStore)
        setContent {
            PokedexTheme {
                Navigation(userViewModel, pokemonDetailViewModel)
            }
        }
    }
}
