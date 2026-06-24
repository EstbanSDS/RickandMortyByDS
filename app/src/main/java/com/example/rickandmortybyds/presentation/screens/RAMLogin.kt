package com.example.rickandmortybyds.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.Navigation

@Composable
fun RAMLogin(

navigateToRAMAllCharacters: () -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Usuario"
        )

        TextField(
            value = "Usuario",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Contraseña"
        )

        TextField(
            value = "Contraseña",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { navigateToRAMAllCharacters() }
        ) { Text("Iniciar Sesión") }


    }

}