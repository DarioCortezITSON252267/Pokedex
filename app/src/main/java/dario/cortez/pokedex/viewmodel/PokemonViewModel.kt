package dario.cortez.pokedex.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dario.cortez.pokedex.model.data.pokemonList
import dario.cortez.pokedex.model.domain.Pokemon

class PokemonViewModel : ViewModel() {
    var wildPokemon by mutableStateOf<Pokemon?>(null)
        private set

    var capturedPokemons by mutableStateOf(listOf<Pokemon>())
        private set

    var gonePokemon by mutableStateOf(false)
        private set

    fun searchPokemon() {
        wildPokemon = pokemonList.random()
        gonePokemon = false
    }

    fun capturePokemon() {
        wildPokemon?.let {
            val isCaptured = (1..2).random()
            if (isCaptured == 1) {
                capturedPokemons = capturedPokemons + it
                gonePokemon = false
                wildPokemon = null
            } else {
                gonePokemon = true
                wildPokemon = null
            }
        }
    }
}
