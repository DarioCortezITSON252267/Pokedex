package dario.cortez.pokedex.model.data

import android.content.Context
import dario.cortez.pokedex.model.domain.User
import dario.cortez.pokedex.model.preferences.ARCHIVO
import dario.cortez.pokedex.model.preferences.POKEMON_DISPLAY
import dario.cortez.pokedex.model.preferences.USER
import dario.cortez.pokedex.model.preferences.USERNAME

class Users(context: Context) {
    val validUsers = listOf(
        User(email = "dariocortez@correo.com", password = "1234", name = "Dario Cortez"),
        User(email = "marcoantonio@correo.com", password = "4561", name = "Marco Antonio"),
        User(email = "juliocorrales@correo.com", password = "1452", name = "Julio Corrales")
    )

    val shared = context.getSharedPreferences(ARCHIVO, Context.MODE_PRIVATE)

    fun getUserByMail(mail: String): User? {
        val users = validUsers.filter { it.email.equals(mail, ignoreCase = true) }
        return if (users.isEmpty()) null else users.first()
    }

    fun loginService(email: String, password: String): User? {
        val user = getUserByMail(email)
        if (user != null && user.password == password) {
            saveUser(user.email, user.name)
            return user
        }
        return null
    }

    fun saveUser(user: String, name: String) {
        val editor = shared.edit()
        editor.putString(USER, user)
        editor.putString(USERNAME, name)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        val user = shared.getString(USER, null)
        val username = shared.getString(USERNAME, null)
        if (user != null && username != null) {
            return true
        }
        return false
    }

    fun logout() {
        val editor = shared.edit()
        editor.putString(USER, null)
        editor.putString(USERNAME, null)
        editor.apply()
    }

    fun getCurrentUser(): User? {
        val user = shared.getString(USER, null)
        if (user != null) {
            return getUserByMail(user)
        }
        return null
    }

    // true = lista, false = cuadrícula
    fun savePokemonDisplay(showAsList: Boolean) {
        val editor = shared.edit()
        editor.putBoolean(POKEMON_DISPLAY, showAsList)
        editor.apply()
    }

    fun getPokemonDisplay(): Boolean {
        return shared.getBoolean(POKEMON_DISPLAY, false)
    }
}
