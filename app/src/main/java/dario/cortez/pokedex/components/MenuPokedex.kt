package dario.cortez.pokedex.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items as gridItems
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dario.cortez.pokedex.model.data.getFavoritePokemons
import dario.cortez.pokedex.model.data.pokemonList
import dario.cortez.pokedex.model.domain.Pokemon
import dario.cortez.pokedex.ui.theme.PokedexTheme

@Composable
fun MenuPokedex(pokemons: List<Pokemon>) {
    LazyColumn {
        items(pokemons) { pokemon ->
            PokemonRow(pokemon)
        }
    }
}

@Composable
fun FavoritesRow(favoriteList: List<Pokemon>, onPokemonClick: (Pokemon) -> Unit = {}) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(favoriteList) { pokemon ->
            FavoritePokemon(pokemon = pokemon, onClick = { onPokemonClick(pokemon) })
        }
    }
}

@Composable
fun PokedexGrid(
    pokemons: List<Pokemon>,
    modifier: Modifier = Modifier,
    onPokemonClick: (Pokemon) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 5.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier
    ) {
        gridItems(pokemons) { pokemon ->
            PokemonCell(pokemon = pokemon, onClick = { onPokemonClick(pokemon) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPokedexPreview() {
    PokedexTheme {
        MenuPokedex(pokemons = pokemonList)
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesRowPreview() {
    PokedexTheme {
        FavoritesRow(favoriteList = getFavoritePokemons())
    }
}

@Preview(showBackground = true)
@Composable
fun PokedexGridPreview() {
    PokedexTheme {
        PokedexGrid(pokemons = pokemonList)
    }
}
