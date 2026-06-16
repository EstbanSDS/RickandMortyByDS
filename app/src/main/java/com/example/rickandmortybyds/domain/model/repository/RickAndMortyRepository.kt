package com.example.rickandmortybyds.domain.model.repository

import com.example.rickandmortybyds.core.room.entity.CharactersEntity
import com.example.rickandmortybyds.domain.model.RAMCharacterResponse
import com.example.rickandmortybyds.domain.model.RickAndMortyDetailResponse
import com.example.rickandmortybyds.domain.model.usecase.RAMEpisodeResponse
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {
    suspend fun getRickAndMortyDetail(): RickAndMortyDetailResponse

    fun getCharactersStream(): Flow<List<CharactersEntity>>

    suspend fun getAllCharactersDB(): RickAndMortyDetailResponse

    suspend fun getCharacterByIdDB(characterId: Int): RAMCharacterResponse

    suspend fun getCharacterByIdAPI(characterId: Int): RAMCharacterResponse

    suspend fun getEpisodeByNumber(episodeNumber: Int): RAMEpisodeResponse
}