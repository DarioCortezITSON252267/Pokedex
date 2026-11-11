package dario.cortez.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexScreen()
        }
    }
}

@Composable
fun PokedexScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.pokedex_yellow))
            .statusBarsPadding()
    ) {
        PokemonHeader()
        PokemonCard(modifier = Modifier.padding(top = 240.dp))
        // Se dibuja al final para que quede por encima de la tarjeta blanca
        Image(
            painter = painterResource(R.drawable.pikachu),
            contentDescription = stringResource(R.string.cd_pokemon_image),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 90.dp)
                .offset(x = (-30).dp)
                .size(170.dp)
        )
    }
}

@Composable
fun PokemonHeader() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(R.drawable.ic_pokeball),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp)
                .offset(x = 60.dp)
                .size(260.dp)
        )
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(start = 24.dp, top = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.pokemon_name),
                color = colorResource(R.color.white),
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.pokemon_number),
                color = colorResource(R.color.text_secondary),
                fontSize = 22.sp
            )
        }
        Image(
            painter = painterResource(R.drawable.ic_star),
            contentDescription = stringResource(R.string.cd_favorite),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(36.dp)
        )
    }
}

@Composable
fun PokemonCard(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(
                color = colorResource(R.color.white),
                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
            )
            .navigationBarsPadding()
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        TypeChip()
        Spacer(modifier = Modifier.height(16.dp))
        PokemonStats()
        Text(
            text = stringResource(R.string.pokemon_description),
            color = colorResource(R.color.text_primary),
            fontSize = 17.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(32.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        PokemonNavigation()
    }
}

@Composable
fun TypeChip() {
    Text(
        text = stringResource(R.string.pokemon_type),
        color = colorResource(R.color.text_primary),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .background(
                color = colorResource(R.color.type_electric),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp, vertical = 4.dp)
    )
}

@Composable
fun PokemonStats() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            StatRow(
                label = stringResource(R.string.label_height),
                value = stringResource(R.string.pokemon_height)
            )
            StatRow(
                label = stringResource(R.string.label_weight),
                value = stringResource(R.string.pokemon_weight)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .weight(1f)
                .padding(start = 24.dp)
        ) {
            StatLabel(text = stringResource(R.string.label_ability))
            StatValue(text = stringResource(R.string.pokemon_ability))
        }
    }
}

@Composable
fun StatRow(label: String, value: String) {
    Row {
        // Ancho fijo para que "Altura" y "Peso" queden alineados a la derecha
        StatLabel(text = label, modifier = Modifier.width(80.dp))
        Spacer(modifier = Modifier.width(24.dp))
        StatValue(text = value, modifier = Modifier.width(80.dp))
    }
}

@Composable
fun StatLabel(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = colorResource(R.color.pokedex_red),
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.End,
        modifier = modifier
    )
}

@Composable
fun StatValue(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = colorResource(R.color.text_primary),
        fontSize = 22.sp,
        modifier = modifier
    )
}

@Composable
fun PokemonNavigation() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.morsa),
                contentDescription = stringResource(R.string.cd_previous_image),
                modifier = Modifier.size(96.dp)
            )
            Image(
                painter = painterResource(R.drawable.raichu),
                contentDescription = stringResource(R.string.cd_next_image),
                modifier = Modifier.size(96.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp, bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = stringResource(R.string.cd_previous),
                modifier = Modifier.size(24.dp)
            )
            NavLabel(text = stringResource(R.string.previous_pokemon))
            Spacer(modifier = Modifier.weight(1f))
            NavLabel(text = stringResource(R.string.next_pokemon))
            Image(
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = stringResource(R.string.cd_next),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun NavLabel(text: String) {
    Text(
        text = text,
        color = colorResource(R.color.text_primary),
        fontSize = 12.sp,
        modifier = Modifier.padding(horizontal = 4.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PokedexScreenPreview() {
    PokedexScreen()
}
