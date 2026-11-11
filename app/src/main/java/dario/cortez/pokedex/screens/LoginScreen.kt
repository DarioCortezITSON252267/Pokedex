package dario.cortez.pokedex.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dario.cortez.pokedex.ui.theme.PokedexTheme
import dario.cortez.pokedex.viewmodel.UserViewModel

@Composable
fun LoginScreen(
    innerPadding: PaddingValues,
    onNavigateToHome: () -> Unit,
    userViewModel: UserViewModel
) {
    if (userViewModel.isLoggedIn()) {
        LaunchedEffect(Unit) { onNavigateToHome() }
    } else {
        LoginForm(
            innerPadding = innerPadding,
            loginError = userViewModel.loginError,
            errorDescription = userViewModel.errorDescription,
            onLogin = { mail, password ->
                userViewModel.login(mail, password)
                if (!userViewModel.loginError) {
                    onNavigateToHome()
                }
            }
        )
    }
}

@Composable
fun LoginForm(
    innerPadding: PaddingValues,
    loginError: Boolean,
    errorDescription: String,
    onLogin: (String, String) -> Unit
) {
    var mail by remember { mutableStateOf("") }
    val password = rememberTextFieldState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(paddingValues = innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = mail,
            onValueChange = { mail = it },
            label = { Text("Correo electrónico") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(Modifier.size(15.dp))
        SecureTextField(
            state = password,
            label = { Text("Contraseña") }
        )
        Spacer(Modifier.size(25.dp))
        Button(onClick = { onLogin(mail.trim(), password.text.toString()) }) {
            Text("Iniciar sesión")
        }
        if (loginError) {
            Spacer(Modifier.size(10.dp))
            Text(
                text = errorDescription,
                color = Color.Red,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    PokedexTheme {
        LoginForm(
            innerPadding = PaddingValues(0.dp),
            loginError = true,
            errorDescription = "Usuario o contraseña incorrectos",
            onLogin = { _, _ -> }
        )
    }
}
//Para acceder a la aplicacion
//dariocortez@correo.com	1234
