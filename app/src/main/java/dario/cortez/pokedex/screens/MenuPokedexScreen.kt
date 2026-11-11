package dario.cortez.pokedex.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dario.cortez.pokedex.R
import dario.cortez.pokedex.components.FavoritesRow
import dario.cortez.pokedex.components.MenuPokedex
import dario.cortez.pokedex.components.PokedexGrid
import dario.cortez.pokedex.model.data.getFavoritePokemons
import dario.cortez.pokedex.model.data.pokemonList
import dario.cortez.pokedex.ui.theme.PokedexTheme

@Composable
fun MenuPokedexScreen(
    innerPadding: PaddingValues,
    showAsList: Boolean = false,
    onDisplayChange: (Boolean) -> Unit = {},
    onLogout: () -> Unit = {},
    onCatchNew: () -> Unit = {},
    onPokemonClick: (Int) -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            IconButton(onClick = onNavigateToProfile) {
                Icon(
                    painter = painterResource(R.drawable.baseline_account_circle_24),
                    contentDescription = "Mi perfil"
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "Mis Favoritos",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.weight(1f)
                )
                OutlinedButton(onClick = onLogout) {
                    Text("Cerrar sesión")
                }
            }
            FavoritesRow(
                favoriteList = getFavoritePokemons(),
                onPokemonClick = { onPokemonClick(it.number) }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "Todos mis pokemones",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.weight(1f)
                )

                Switch(
                    checked = showAsList,
                    onCheckedChange = onDisplayChange,
                    thumbContent = {
                        Icon(
                            painter = painterResource(R.drawable.ic_list),
                            contentDescription = "Mostrar como lista",
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    }
                )
            }
            if (showAsList) {
                MenuPokedex(
                    pokemons = pokemonList,
                    modifier = Modifier.weight(1f),
                    onPokemonClick = { onPokemonClick(it.number) }
                )
            } else {
                PokedexGrid(
                    pokemons = pokemonList,
                    modifier = Modifier.weight(1f),
                    onPokemonClick = { onPokemonClick(it.number) }
                )
            }
        }
        ExtendedFloatingActionButton(
            onClick = onCatchNew,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text("Atrapar nuevo")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPokedexScreenPreview() {
    PokedexTheme {
        MenuPokedexScreen(innerPadding = PaddingValues(0.dp), showAsList = true)
    }
}
