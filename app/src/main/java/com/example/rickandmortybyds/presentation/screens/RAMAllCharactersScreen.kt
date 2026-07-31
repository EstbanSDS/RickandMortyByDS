package com.example.rickandmortybyds.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.rickandmortybyds.core.model.login.UserRole
import com.example.rickandmortybyds.model.viewmodel.RAMAllCharactersVM
import com.example.rickandmortybyds.ui.components.buttons.RAMButton
import com.example.rickandmortybyds.ui.components.loading.RAMLoading
import com.example.rickandmortybyds.ui.components.scaffold.RAMScreen
import com.example.rickandmortybyds.utils.dialogs.AlertCommonDialog
import com.example.rickandmortybyds.model.viewmodel.SharedViewModel

@Composable
fun RAMAllCharactersScreen(
    viewModel: RAMAllCharactersVM = hiltViewModel(),
    sharedViewModel: SharedViewModel,
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

    RAMScreen {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                AlertCommonDialog(
                    showAlertDialog = ramData.showErrorDialog,
                    title = ramData.codeError
                        ?: "Error inesperado",
                    message = ramData.errorMessage
                        ?: "Error inesperado",
                    onAccept = {},
                    onDismiss = {
                        viewModel.resetErrorDialog()
                    }
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Rick & Morty",
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Sesión: $userRole",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                RAMButton(
                    text = "Cerrar sesión",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                    enabled = true,
                    loading = ramData.loading,
                    onClick = {
                        viewModel.logout()
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                LazyVerticalGrid(
                    // Define que existirán dos columnas fijas
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.weight(1f),

                    // Separación vertical entre filas
                    verticalArrangement = Arrangement.spacedBy(12.dp),

                    // Separación horizontal entre columnas
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(characterList) { character ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    if (userRole != UserRole.USER.name) {
                                        character.id?.let { id ->
                                            navigateToCharacterDetail(id)
                                        }
                                    }
                                }
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                AsyncImage(
                                    model = character.image,
                                    contentDescription = character.name,
                                    modifier = Modifier
                                        .size(120.dp)
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Box(
                                    modifier = Modifier.heightIn(min = 48.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = character.name ?: "Sin nombre",
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }

            RAMLoading(

                visible = ramData.loading
            )
        }
    }
}