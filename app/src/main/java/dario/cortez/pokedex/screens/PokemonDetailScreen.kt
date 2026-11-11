package dario.cortez.pokedex.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dario.cortez.pokedex.R
import dario.cortez.pokedex.components.NumberChip
import dario.cortez.pokedex.model.data.bulbasar
import dario.cortez.pokedex.model.domain.Pokemon
import dario.cortez.pokedex.ui.theme.PokedexTheme
import dario.cortez.pokedex.utilities.getColorByType
import dario.cortez.pokedex.viewmodel.PokemonDetailViewModel

@Composable
fun PokemonDetailsScreen(
    innerPadding: PaddingValues,
    pokemonNumber: Int,
    pokemonDetailViewModel: PokemonDetailViewModel
) {
    LaunchedEffect(pokemonNumber) { pokemonDetailViewModel.loadPokemon(pokemonNumber) }

    val companionNumber by pokemonDetailViewModel.companionNumber.collectAsStateWithLifecycle()
    val context = LocalContext.current
    LaunchedEffect(pokemonDetailViewModel.companionSaved) {
        if (pokemonDetailViewModel.companionSaved) {
            Toast.makeText(context, "Compañero seleccionado", Toast.LENGTH_SHORT).show()
            pokemonDetailViewModel.companionMessageShown()
        }
    }

    val pokemon = pokemonDetailViewModel.pokemon
    if (pokemon != null && pokemon.number == pokemonNumber) {
        PokemonDetailContent(
            innerPadding = innerPadding,
            pokemon = pokemon,
            isCompanion = pokemon.number == companionNumber,
            onSelectCompanion = { pokemonDetailViewModel.saveCompanion() }
        )
    }
}

@Composable
fun PokemonDetailContent(
    innerPadding: PaddingValues,
    pokemon: Pokemon,
    isCompanion: Boolean,
    onSelectCompanion: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(20.dp)
    ) {
        Text(text = pokemon.name, style = MaterialTheme.typography.labelLarge)
        NumberChip(
            text = pokemon.number.toString(),
            colors = getColorByType(pokemon.type)
        )
        Image(
            painter = painterResource(pokemon.image),
            contentDescription = "${pokemon.name} image",
            modifier = Modifier.height(200.dp)
        )
        Text(text = pokemon.type, style = MaterialTheme.typography.labelMedium)
        Text(text = pokemon.description, textAlign = TextAlign.Center)
        Text(
            text = "${pokemon.height} m   ${pokemon.weight} kg",
            style = MaterialTheme.typography.labelMedium
        )
        Text(text = pokemon.ability, style = MaterialTheme.typography.labelMedium)
        if (isCompanion) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.estrellita),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = " Es tu compañero",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        } else {
            Button(onClick = onSelectCompanion) {
                Text("Seleccionar como compañero")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonDetailsScreenPreview() {
    PokedexTheme {
        PokemonDetailContent(
            innerPadding = PaddingValues(0.dp),
            pokemon = bulbasar,
            isCompanion = false,
            onSelectCompanion = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonDetailsCompanionPreview() {
    PokedexTheme {
        PokemonDetailContent(
            innerPadding = PaddingValues(0.dp),
            pokemon = bulbasar,
            isCompanion = true,
            onSelectCompanion = {}
        )
    }
}
