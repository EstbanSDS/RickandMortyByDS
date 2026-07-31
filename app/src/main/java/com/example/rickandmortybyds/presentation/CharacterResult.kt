package com.example.rickandmortybyds.presentation

data class CharacterResult(
    val id: Int = 0,
    val characterName: String = "",
    val status: String = "",
    val episodes: List<EpisodeResult> = emptyList(),
    val originName: String = "",
    val nameLocation: String = "",
    val fechaCreado: String = "",
    val fechaCreacion: String = "",
    val fechaModificacion: String = ""
)

data class EpisodeResult(
    val episodeName: String,
    val characters: List<String>,
    val airDate: String
)