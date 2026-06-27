package com.example.rickandmortybyds.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object RAMAllCharactersRoute

@Serializable
data class RAMCharacterDetailRoute(
    val characterId : Int = -1
)

@Serializable
data class RAMEpisodeRoute(
    val episodeNumber: Int
)

@Serializable
object RAMLoginRoute
