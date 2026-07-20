package com.example.rickandmortybyds.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.rickandmortybyds.core.model.login.UserRole
import com.example.rickandmortybyds.model.viewmodel.RAMAllCharactersVM
import com.example.rickandmortybyds.ui.components.buttons.RAMButton
import com.example.rickandmortybyds.utils.dialogs.AlertCommonDialog
import com.example.rickandmortybyds.ui.components.scaffold.RAMScreen

@Composable
fun RAMAllCharactersScreen(
    viewModel: RAMAllCharactersVM = hiltViewModel(),

    navigateToCharacterDetail: (Int) -> Unit,

    navigateToLogin: () -> Unit,
) {
    val ramData by viewModel.rickAndMortyData.collectAsState()

    val characterList by viewModel.ramCharactersDB.collectAsState()

    val logoutEvent by viewModel.logoutEvent.collectAsState()

    val userRole by viewModel.userRole.collectAsState()

    LaunchedEffect(logoutEvent) {
        if (logoutEvent) {
            navigateToLogin()
        }
    }
    // Surface es el componente base de Material Design

    RAMScreen {

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),

                ) {

                AlertCommonDialog(
                    showAlertDialog = ramData.showErrorDialog,
                    title = ramData.codeError ?: "Error inesperado",
                    message = ramData.errorMessage ?: "Error inesperado",
                    onAccept = {},
                    onDismiss = { viewModel.resetErrorDialog() }
                )

                AlertCommonDialog(
                    showAlertDialog = ramData.showErrorDialog,
                    title = "¡Espera!",
                    message = "Por primera vez debes iniciar con una conección a internet",
                    onAccept = {},
                    onDismiss = { viewModel.resetErrorDialog() }
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Rol: $userRole",
                    textAlign = TextAlign.Center
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Por primera vez debes iniciar con una conección a internet",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                RAMButton(
                    text = "Cerrar sesión",
                    modifier = Modifier
                        .height(40.dp),
                    enabled = true,     // userRole == UserRole.SUPER_USER.name,
                    loading = ramData.loading,
                    onClick = {
                        viewModel.logout()
                    }
                )

                LazyColumn(
                    modifier = Modifier.weight(1f)
                )
                {
                    items(characterList) { character ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    if (userRole != UserRole.USER.name) {
                                        character.id?.let { id ->
                                            navigateToCharacterDetail(id)
                                        }
                                    }
                                },
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            AsyncImage(
                                model = character.image,
                                contentDescription = character.name,
                                modifier = Modifier.size(150.dp)
                            )

                            Text(character.name ?: "Sin nombre")
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                    }
                }
            }

            if (ramData.loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
