package dario.cortez.pokedex.model.domain

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val name: String,
    val number: Int,
    val type: String,
    val description: String,
    val height: Float,
    val weight: Float,
    val favorite: Boolean,
    val ability: String,
    val image: Int
)
