package dario.cortez.pokedex.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dario.cortez.pokedex.components.FavoritesRow
import dario.cortez.pokedex.components.PokedexGrid
import dario.cortez.pokedex.model.data.getFavoritePokemons
import dario.cortez.pokedex.model.data.pokemonList
import dario.cortez.pokedex.ui.theme.PokedexTheme

@Composable
fun MenuPokedexScreen(
    innerPadding: PaddingValues,
    onPokemonClick: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Text(
            text = "Mis Favoritos",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )
        FavoritesRow(
            favoriteList = getFavoritePokemons(),
            onPokemonClick = { onPokemonClick(it.number) }
        )
        Text(
            text = "Todos mis pokemones",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )
        PokedexGrid(
            pokemons = pokemonList,
            modifier = Modifier.weight(1f),
            onPokemonClick = { onPokemonClick(it.number) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPokedexScreenPreview() {
    PokedexTheme {
        MenuPokedexScreen(innerPadding = PaddingValues(0.dp))
    }
}
