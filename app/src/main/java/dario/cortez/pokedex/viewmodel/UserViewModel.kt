package dario.cortez.pokedex.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dario.cortez.pokedex.model.data.Users
import dario.cortez.pokedex.model.domain.User

class UserViewModel(users: Users) : ViewModel() {
    var loginError by mutableStateOf(false)
        private set
    var errorDescription by mutableStateOf("")
        private set
    var showAsList by mutableStateOf(users.getPokemonDisplay())
        private set

    val users = users

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

    fun isLoggedIn(): Boolean {
        return users.isLoggedIn()
    }

    fun logout() {
        users.logout()
    }

    fun changePokemonDisplay(showAsList: Boolean) {
        this.showAsList = showAsList
        users.savePokemonDisplay(showAsList)
    }
}
