package dario.cortez.pokedex.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dario.cortez.pokedex.components.NumberChip
import dario.cortez.pokedex.model.data.getPokemonByNumber
import dario.cortez.pokedex.ui.theme.PokedexTheme
import dario.cortez.pokedex.utilities.getColorByType

@Composable
fun PokemonDetailsScreen(innerPadding: PaddingValues, pokemon: Int) {
    val pokemon = getPokemonByNumber(pokemon)
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
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonDetailsScreenPreview() {
    PokedexTheme {
        PokemonDetailsScreen(innerPadding = PaddingValues(0.dp), pokemon = 25)
    }
}
