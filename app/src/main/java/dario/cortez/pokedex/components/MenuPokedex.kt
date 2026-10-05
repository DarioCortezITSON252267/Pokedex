package dario.cortez.pokedex.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dario.cortez.pokedex.data.pokemonList
import dario.cortez.pokedex.domain.Pokemon
import dario.cortez.pokedex.ui.theme.PokedexTheme

@Composable
fun MenuPokedex(pokemons: List<Pokemon>, innerPadding: PaddingValues) {
    LazyColumn() {
        items(pokemons) { pokemon ->
            PokemonRow(pokemon)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPokedexPreview() {
    PokedexTheme {
        MenuPokedex(pokemons = pokemonList, innerPadding = PaddingValues(0.dp))
    }
}
