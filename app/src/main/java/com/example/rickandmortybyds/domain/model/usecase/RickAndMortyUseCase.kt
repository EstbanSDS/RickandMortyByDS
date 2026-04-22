package com.example.rickandmortybyds.domain.model.usecase


import com.example.rickandmortybyds.domain.model.RAMCharacterResponse
import com.example.rickandmortybyds.domain.model.RickAndMortyDetailResponse
import com.example.rickandmortybyds.utils.application.ApiServiceState
import kotlinx.coroutines.flow.Flow

interface RickAndMortyUseCase {
    suspend fun getRickAndMortyDetail():Flow<ApiServiceState<RickAndMortyDetailResponse>>
    suspend fun getAllCharactersDB(): Flow<ApiServiceState<RickAndMortyDetailResponse>>
    suspend fun getCharacterByIdDB(characterId: Int): Flow<ApiServiceState<RAMCharacterResponse>>
}