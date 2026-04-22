package com.example.rickandmortybyds.domain.model.repository

import com.example.rickandmortybyds.core.room.entity.CharactersEntity
import com.example.rickandmortybyds.domain.model.RAMCharacterResponse
import com.example.rickandmortybyds.domain.model.RickAndMortyDetailResponse
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {
    suspend fun getRickAndMortyDetail(): RickAndMortyDetailResponse

    fun getCharactersStream(): Flow<List<CharactersEntity>>

    suspend fun getAllCharactersDB(): RickAndMortyDetailResponse

    suspend fun getCharacterByIdDB(characterId: Int): RAMCharacterResponse
}