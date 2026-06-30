package com.example.rickandmortybyds.presentation.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.rickandmortybyds.core.model.login.UserRole
import com.example.rickandmortybyds.model.viewmodel.RAMCharacterDBViewModel
import com.example.rickandmortybyds.utils.dialogs.AlertCommonDialog


@Composable
fun RAMACharacterDetailScreen(
    viewModel: RAMCharacterDBViewModel = hiltViewModel(),
    navigationBack: () -> Unit,
    navigateToEpisodeDetail: (Int) -> Unit,
) {

    val ramCharacterDB = viewModel.ramCharacter.collectAsState()
    val character = ramCharacterDB.value.rickAndMortyDetail
    val context = LocalContext.current

    var showEpisodes by remember {
        mutableStateOf(false)
    }

    val userRole by viewModel.userRole.collectAsState()

    LaunchedEffect(character?.id) {
        character?.let {
            Toast.makeText(context, "${it.id}", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        if (character != null) {
            Text(character.name ?: "Sin nombre")

            Spacer(modifier = Modifier.height(14.dp))

            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(14.dp))


            Text(
                text = "Locación: ${character.location?.name ?: "Desconocida"}"
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Origen: ${character.origin?.name ?: "Desconocido"}"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (showEpisodes)
                    "Ocultar episodios ▲"
                else
                    "Mostrar episodios ▼",

                modifier = Modifier.clickable {
                    if (userRole != UserRole.ADMIN.name) {
                        showEpisodes = !showEpisodes
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))

            AnimatedVisibility(
                visible = showEpisodes
            ) {
                LazyColumn {
                    items(character.episode ?: emptyList()) { episode ->

                        val episodeNumber = episode.substringAfterLast("/")

                        Text(
                            text = "Episodio: $episodeNumber",
                            modifier = Modifier.clickable {
                                navigateToEpisodeDetail(
                                    episodeNumber.toInt()
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }

            }
        }
    }
    AlertCommonDialog(
        showAlertDialog = ramCharacterDB.value.showErrorDialog,
        title = "Algo salio mal",
        message = "Intenta mas tarde...",
        textOnAccept = "cancelar",
        onAccept = {
            viewModel.closeAlertDialog()
        }
    ) { viewModel.closeAlertDialog() }

}
