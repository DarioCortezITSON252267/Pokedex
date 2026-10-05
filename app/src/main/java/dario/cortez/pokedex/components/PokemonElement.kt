package dario.cortez.pokedex.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dario.cortez.pokedex.data.bulbasar
import dario.cortez.pokedex.domain.Pokemon
import dario.cortez.pokedex.ui.theme.Green
import dario.cortez.pokedex.ui.theme.PokedexTheme

@Composable
fun PokemonRow(pokemon: Pokemon) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(pokemon.image),
            contentDescription = "${pokemon.name} image",
            modifier = Modifier
                .width(80.dp)
                .padding(10.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = pokemon.description,
                fontSize = 10.sp
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(0.85f)
            ) {
                Text(
                    text = "${pokemon.height} m",
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = "${pokemon.weight} kg",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
        Text(
            text = "#${pokemon.number}",
            modifier = Modifier
                .align(Alignment.Top)
                .background(Green)
                .padding(horizontal = 5.dp, vertical = 2.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonRowPreview() {
    PokedexTheme {
        PokemonRow(pokemon = bulbasar)
    }
}
