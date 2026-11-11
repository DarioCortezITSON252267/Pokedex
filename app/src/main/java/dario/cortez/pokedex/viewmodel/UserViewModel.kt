package dario.cortez.pokedex.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dario.cortez.pokedex.model.data.CompanionDataStore
import dario.cortez.pokedex.model.data.Users
import dario.cortez.pokedex.model.data.getPokemonByNumber
import dario.cortez.pokedex.model.domain.Pokemon
import dario.cortez.pokedex.model.domain.User
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit

// Lo que necesita el perfil: el pokemon compañero y cuántos días lleva siéndolo
data class CompanionInfo(val pokemon: Pokemon, val days: Long)

class UserViewModel(
    private val users: Users,
    private val companionDataStore: CompanionDataStore) : ViewModel() {

    var loginError by mutableStateOf(false)
        private set

    var errorDescription by mutableStateOf("")
        private set

    var showAsList by mutableStateOf(users.getPokemonDisplay())
        private set

    // null mientras no se haya elegido compañero
    val companion: StateFlow<CompanionInfo?> = companionDataStore.companion()
        .map { saved ->
            getPokemonByNumber(saved.number)?.let { pokemon ->
                CompanionInfo(pokemon = pokemon, days = daysSince(saved.selectedAt))
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val currentUser: User?
        get() = users.getCurrentUser()

    fun login(user: String, password: String) {
        val userRetrieved = users.loginService(user, password)
        if (userRetrieved == null) {
            loginError = true
            errorDescription = "Usuario o contraseña incorrectos"
        } else {
            loginError = false
            errorDescription = ""
        }
    }

    fun isLoggedIn(): Boolean = users.isLoggedIn()

    fun logout() {
        users.logout()
    }

    fun changePokemonDisplay(showAsList: Boolean) {
        this.showAsList = showAsList
        users.savePokemonDisplay(showAsList)
    }

    // Días de calendario entre la fecha en que se eligió y hoy
    private fun daysSince(millis: Long): Long {
        val zone = ZoneId.systemDefault()
        val selectedDay = Instant.ofEpochMilli(millis).atZone(zone).toLocalDate()
        return ChronoUnit.DAYS.between(selectedDay, LocalDate.now(zone))
    }
}
