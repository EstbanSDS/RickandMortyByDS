package com.example.rickandmortybyds.presentation.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmortybyds.model.viewmodel.RAMEpisodeVM

@Composable
fun RAMEpisodeDetailScreen(
    viewModel: RAMEpisodeVM = hiltViewModel(),
    ) {

    val ramEpisode = viewModel.ramEpisodeNumber.collectAsState()
    val episode = ramEpisode.value.ramEpisodeNumberDetail

    Text(
        text = "Episodio ${episode?.id} \n" +
                "Nombre del episodio: ${episode?.name}"
    )


}