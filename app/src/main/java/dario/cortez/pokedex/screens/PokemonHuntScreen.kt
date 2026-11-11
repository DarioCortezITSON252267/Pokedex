package dario.cortez.pokedex.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dario.cortez.pokedex.components.PokemonRow
import dario.cortez.pokedex.ui.theme.PokedexTheme
import dario.cortez.pokedex.viewmodel.PokemonViewModel

@Composable
fun PokemonHuntScreen(innerPadding: PaddingValues, viewModel: PokemonViewModel = viewModel()) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Button(onClick = { viewModel.searchPokemon() }) {
            Text("Buscar pokemon en la hierba")
        }
        Spacer(Modifier.size(18.dp))
        viewModel.wildPokemon?.let { pokemon ->
            Text("Apareció un ${pokemon.name} salvaje!")
            Image(
                painter = painterResource(pokemon.image),
                contentDescription = "${pokemon.name} image",
                modifier = Modifier
                    .height(90.dp)
                    .padding(0.dp, 15.dp)
            )
            Spacer(Modifier.size(20.dp))
            Button(onClick = { viewModel.capturePokemon() }) {
                Text("Capturar a ${pokemon.name}!")
            }
        }
        if (viewModel.gonePokemon) {
            Text("El pokemon se escapó!")
        }
        Spacer(Modifier.size(20.dp))
        Text("Pokemones capturados: ${viewModel.capturedPokemons.size}")
        // Apilados en vertical, con el último capturado arriba
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.capturedPokemons.asReversed()) { pokemon ->
                PokemonRow(pokemon)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonHuntScreenPreview() {
    PokedexTheme {
        PokemonHuntScreen(innerPadding = PaddingValues(15.dp))
    }
}
