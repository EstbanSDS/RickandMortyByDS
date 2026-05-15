package com.example.rickandmortybyds.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.rickandmortybyds.model.viewmodel.RAMAllCharactersVM
import com.example.rickandmortybyds.utils.dialogs.CommonDialog
import androidx.compose.foundation.lazy.items

@Composable
fun RAMAllCharactersScreen(
    viewModel: RAMAllCharactersVM = hiltViewModel(),

    navigateToCharacterDetail: (Int) -> Unit,
) {

    val ramData by viewModel.rickAndMortyData.collectAsState()

    val characterList by viewModel.ramCharactersDB.collectAsState()

    /*LaunchedEffect(Unit) {
        viewModel.getRAMAllCharacters()
    }*/

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        val context = LocalContext.current

        if (ramData.loading) {
            CircularProgressIndicator()
        }
        if (ramData.showErrorDialog) {
            CommonDialog(
                title = ramData.codeError ?: "Error inesperado",
                message = ramData.errorMessage ?: "Error inesperado",
                onDismiss = { viewModel.resetErrorDialog() }
            )
        }
        if (ramData.showErrorDialog) {
            CommonDialog(
                title = "¡Espera!",
                message = "Por primera vez debes iniciar con una conección a internet",
                onDismiss = { viewModel.resetErrorDialog() }
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Por primera vez debes iniciar con una conección a internet",
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)

        )
        {
            items(characterList) { character ->

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                            character.id?.let { id ->
                                navigateToCharacterDetail(id)
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = character.image,
                        contentDescription = character.name,
                        modifier = Modifier.size(150.dp)
                    )
                    Text(character.name ?: "nada")
                }
                Spacer(modifier = Modifier.height(14.dp))
            }
        }
    }
}
