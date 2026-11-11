package dario.cortez.pokedex.model.domain

import kotlinx.serialization.Serializable

@Serializable
data class Companion(
    val number: Int,
    val selectedAt: Long // milisegundos (System.currentTimeMillis())
)
