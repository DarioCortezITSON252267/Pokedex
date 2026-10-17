package dario.cortez.pokedex.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dario.cortez.pokedex.model.data.bulbasar
import dario.cortez.pokedex.model.domain.Pokemon
import dario.cortez.pokedex.ui.theme.OffWhite
import dario.cortez.pokedex.ui.theme.PokedexTheme
import dario.cortez.pokedex.utilities.getColorByType

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
        NumberChip(
            text = pokemon.number.toString(),
            modifier = Modifier.align(Alignment.Top),
            colors = getColorByType(pokemon.type)
        )
    }
}

@Composable
fun FavoritePokemon(pokemon: Pokemon, onClick: () -> Unit = {}) {
    val colors = getColorByType(pokemon.type)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = 15.dp)
    ) {
        Box {
            Box(
                modifier = Modifier.border(
                    border = BorderStroke(
                        width = 5.dp,
                        brush = Brush.sweepGradient(
                            listOf(OffWhite, colors.first, OffWhite, colors.first, OffWhite)
                        )
                    ),
                    shape = RectangleShape
                )
            ) {
                Image(
                    painter = painterResource(pokemon.image),
                    contentDescription = "${pokemon.name} image",
                    modifier = Modifier
                        .padding(5.dp)
                        .width(75.dp)
                        .aspectRatio(1f)
                )
            }
            NumberChip(
                text = pokemon.number.toString(),
                modifier = Modifier.align(Alignment.TopEnd),
                colors = colors
            )
        }
        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun PokemonCell(pokemon: Pokemon, onClick: () -> Unit = {}) {
    val colors = getColorByType(pokemon.type)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(bottom = 5.dp)
    ) {
        Box {
            Image(
                painter = painterResource(pokemon.image),
                contentDescription = "${pokemon.name} image",
                modifier = Modifier
                    .size(150.dp)
                    .padding(10.dp)
            )
            NumberChip(
                text = pokemon.number.toString(),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(3.dp),
                colors = colors
            )
        }
        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.labelMedium
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

@Preview(showBackground = true)
@Composable
fun FavoritePokemonPreview() {
    PokedexTheme {
        FavoritePokemon(pokemon = bulbasar)
    }
}

@Preview(showBackground = true, widthDp = 130)
@Composable
fun PokemonCellPreview() {
    PokedexTheme {
        PokemonCell(pokemon = bulbasar)
    }
}
