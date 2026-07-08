package com.example.rickandmortybyds.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmortybyds.model.viewmodel.LoginViewModel
import com.example.rickandmortybyds.ui.components.buttons.RAMButton

@Composable
fun RAMLogin(
    navigateToRAMAllCharacters: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsState().value

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(text = "Usuario")

        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Contraseña")

        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (uiState.loading) {
            CircularProgressIndicator()
        }

        uiState.errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }

        RAMButton(
            text = "Iniciar Sesión",
            onClick = { viewModel.login(email, password) },
        )

        LaunchedEffect(uiState.isLoginSuccess) {
            if (uiState.isLoginSuccess) {
                navigateToRAMAllCharacters()
            }
        }
    }
}