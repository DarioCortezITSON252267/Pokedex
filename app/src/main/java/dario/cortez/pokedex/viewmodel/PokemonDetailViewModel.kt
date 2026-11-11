package dario.cortez.pokedex.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dario.cortez.pokedex.model.data.CompanionDataStore
import dario.cortez.pokedex.model.data.getPokemonByNumber
import dario.cortez.pokedex.model.domain.Pokemon
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val companionDataStore: CompanionDataStore
) : ViewModel() {

    // Pokemon cuyo detalle se está mostrando; lo busca el ViewModel, no la pantalla
    var pokemon by mutableStateOf<Pokemon?>(null)
        private set

    // true cuando terminó de guardarse el compañero, para mostrar el mensaje
    var companionSaved by mutableStateOf(false)
        private set

    // Número del compañero actual (-1 si todavía no hay)
    val companionNumber: StateFlow<Int> = companionDataStore.companion()
        .map { it.number }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = -1
        )

    fun loadPokemon(number: Int) {
        pokemon = getPokemonByNumber(number)
        companionSaved = false
    }

    fun saveCompanion() {
        val selected = pokemon ?: return
        viewModelScope.launch {
            companionDataStore.saveCompanion(selected.number)
            // Esta línea corre cuando la corrutina ya guardó el compañero
            companionSaved = true
        }
    }

    fun companionMessageShown() {
        companionSaved = false
    }
}
