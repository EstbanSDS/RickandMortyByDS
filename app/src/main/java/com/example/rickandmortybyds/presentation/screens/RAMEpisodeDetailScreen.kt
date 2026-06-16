package com.example.rickandmortybyds.presentation.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmortybyds.model.viewmodel.RAMEpisodeVM
import com.example.rickandmortybyds.utils.dialogs.AlertCommonDialog

@Composable
fun RAMEpisodeDetailScreen(
    viewModel: RAMEpisodeVM = hiltViewModel(),
    navigateToRAMCharacterDetail: (Int) -> Unit,
) {

    val ramEpisode = viewModel.ramEpisodeNumber.collectAsState()
    val episode = ramEpisode.value.ramEpisodeNumberDetail

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Episodio ${episode?.id} - ${episode?.episode}"
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Nombre del episodio: ${episode?.name}"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Personajes dentro del episodio:"
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {

            items(episode?.characters ?: emptyList()) { character ->
                val characterNumber = character.substringAfterLast("/").toInt()

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navigateToRAMCharacterDetail(characterNumber)
                        }

                ) {

                    Text(
                        text = "Personaje $characterNumber"
                    )
                }
            }
        }
    }
    AlertCommonDialog(
        showAlertDialog = ramEpisode.value.showErrorDialog,
        title = "Algo salio mal",
        message = "Intenta mas tarde...",
//        textOnAccept = "Aceptar",
        onAccept = {
            viewModel.closeAlertDialog()
        }
    ) { viewModel.closeAlertDialog() }
}

