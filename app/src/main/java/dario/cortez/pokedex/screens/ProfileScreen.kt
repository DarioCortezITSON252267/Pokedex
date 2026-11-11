package dario.cortez.pokedex.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dario.cortez.pokedex.R
import dario.cortez.pokedex.model.data.bulbasar
import dario.cortez.pokedex.model.domain.User
import dario.cortez.pokedex.ui.theme.PokedexTheme
import dario.cortez.pokedex.ui.theme.Typography
import dario.cortez.pokedex.ui.theme.Water
import dario.cortez.pokedex.viewmodel.CompanionInfo
import dario.cortez.pokedex.viewmodel.UserViewModel

@Composable
fun ProfileScreen(innerPadding: PaddingValues, userViewModel: UserViewModel) {
    // Se actualiza solo cada vez que se guarda un compañero nuevo en el DataStore
    val companion by userViewModel.companion.collectAsStateWithLifecycle()
    ProfileContent(
        innerPadding = innerPadding,
        currentUser = userViewModel.currentUser,
        companion = companion
    )
}

// Solo dibuja la pantalla; así se puede ver en el Preview sin el ViewModel
@Composable
fun ProfileContent(innerPadding: PaddingValues, currentUser: User?, companion: CompanionInfo?) {
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
        if (companion == null) {
            Image(
                painter = painterResource(R.drawable.wtp),
                contentDescription = "sin compañero",
                modifier = Modifier.height(200.dp)
            )
            Text("Aún no eliges compañero", style = Typography.labelLarge)
        } else {
            Image(
                painter = painterResource(companion.pokemon.image),
                contentDescription = "compañero",
                modifier = Modifier.height(200.dp)
            )
            Text(companion.pokemon.name, style = Typography.labelLarge)
            Text(companionDaysText(companion.days), style = Typography.labelMedium)
        }
    }
}

private fun companionDaysText(days: Long): String = when (days) {
    0L -> "Es tu compañero desde hoy"
    1L -> "Lleva 1 día siendo tu compañero"
    else -> "Lleva $days días siendo tu compañero"
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    PokedexTheme {
        ProfileContent(
            innerPadding = PaddingValues(0.dp),
            currentUser = User(email = "dariocortez@correo.com", password = "1234", name = "Dario Cortez"),
            companion = CompanionInfo(pokemon = bulbasar, days = 5)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileWithoutCompanionPreview() {
    PokedexTheme {
        ProfileContent(
            innerPadding = PaddingValues(0.dp),
            currentUser = User(email = "dariocortez@correo.com", password = "1234", name = "Dario Cortez"),
            companion = null
        )
    }
}
