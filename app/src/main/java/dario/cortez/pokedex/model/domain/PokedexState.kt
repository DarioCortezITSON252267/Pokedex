package dario.cortez.pokedex.model.domain

data class PokedexState(
    val team: List<Pokemon> = emptyList()
)
