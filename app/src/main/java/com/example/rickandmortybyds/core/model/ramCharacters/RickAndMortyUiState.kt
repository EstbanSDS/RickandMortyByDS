package com.example.rickandmortybyds.core.model.ramCharacters

import com.example.rickandmortybyds.domain.model.RickAndMortyDetailResponse

data class RickAndMortyUiState(
    val loading: Boolean = false,
    val codeError: String? = null,
    val errorMessage: String? = null,
    val showErrorDialog: Boolean = false,
    val rickAndMortyDetail: RickAndMortyDetailResponse? = null,
)