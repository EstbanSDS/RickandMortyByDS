package com.example.rickandmortybyds.core.model.ramCharacters

import com.example.rickandmortybyds.domain.model.RAMCharacterResponse
import com.example.rickandmortybyds.domain.model.RickAndMortyDetailResponse
import com.example.rickandmortybyds.domain.model.usecase.RAMEpisodeResponse

data class RickAndMortyUiState(
    val loading: Boolean = false,
    val codeError: String? = null,
    val errorMessage: String? = null,
    val showErrorDialog: Boolean = false,
    val rickAndMortyDetail: RickAndMortyDetailResponse? = null,
)

data class RAMCharacterUiState(
    val loading: Boolean = false,
    val codeError: String? = null,
    val errorMessage: String? = null,
    val showErrorDialog: Boolean = false,
    val rickAndMortyDetail: RAMCharacterResponse? = null,
)

data class RAMEpisodeNumberUiState(
    val loading: Boolean = false,
    val codeError: String? = null,
    val errorMessage: String? = null,
    val showErrorDialog: Boolean = false,
    val ramEpisodeNumberDetail: RAMEpisodeResponse? = null,
)