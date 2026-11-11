package dario.cortez.pokedex.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dario.cortez.pokedex.R
import dario.cortez.pokedex.model.domain.User
import dario.cortez.pokedex.ui.theme.PokedexTheme
import dario.cortez.pokedex.ui.theme.Typography
import dario.cortez.pokedex.ui.theme.Water

@Composable
fun ProfileScreen(innerPadding: PaddingValues, currentUser: User?) {
    val name = currentUser?.name ?: ""
    val email = currentUser?.email ?: ""
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = innerPadding)
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(name, style = Typography.displayLarge, color = Water)
        Text(email, style = Typography.labelMedium)
        Spacer(Modifier.size(80.dp))

        Text("Mi Compañero", style = Typography.titleLarge)
        Image(painterResource(R.drawable.charmander), contentDescription = "compañero")
        Text("Charmander", style = Typography.labelLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    PokedexTheme {
        ProfileScreen(
            innerPadding = PaddingValues(0.dp),
            currentUser = User(email = "dariocortez@correo.com", password = "1234", name = "Dario Cortez")
        )
    }
}
