package dario.cortez.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dario.cortez.pokedex.model.data.Users
import dario.cortez.pokedex.navigation.Navigation
import dario.cortez.pokedex.ui.theme.PokedexTheme
import dario.cortez.pokedex.viewmodel.UserViewModel

class PokedexList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Users necesita un Context para leer las SharedPreferences; la Activity lo tiene
        val users = Users(context = applicationContext)
        val userViewModel = UserViewModel(users)
        setContent {
            PokedexTheme {
                Navigation(userViewModel)
            }
        }
    }
}
