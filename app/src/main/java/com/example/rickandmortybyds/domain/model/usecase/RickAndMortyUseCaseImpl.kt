package com.example.rickandmortybyds.domain.model.usecase

import com.example.rickandmortybyds.core.network.NetWork
import com.example.rickandmortybyds.domain.model.RAMCharacterResponse
import com.example.rickandmortybyds.domain.model.RickAndMortyDetailResponse
import com.example.rickandmortybyds.domain.model.repository.RickAndMortyRepository
import com.example.rickandmortybyds.utils.application.ApiServiceState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RickAndMortyUseCaseImpl @Inject constructor(
    private val network: NetWork,
    private val repository: RickAndMortyRepository
) : RickAndMortyUseCase {
    override suspend fun getRickAndMortyDetail(): Flow<ApiServiceState<RickAndMortyDetailResponse>> {
        return network.executeEndPoint {
            repository.getRickAndMortyDetail()
        }
    }

    override suspend fun getAllCharactersDB(): Flow<ApiServiceState<RickAndMortyDetailResponse>> {
        return network.executeEndPoint {
            repository.getAllCharactersDB()
        }
    }

    /*override suspend fun getCharacterByIdDB(characterId: Int): Flow<ApiServiceState<RAMCharacterResponse>> {
        return network.executeEndPoint {
            repository.getCharacterByIdDB(characterId)
        }
    }*/

    override suspend fun getCharacterByIdDB(characterId: Int): Flow<ApiServiceState<RAMCharacterResponse>> {
        return network.executeRoomQuery {
            repository.getCharacterByIdDB(characterId)
        }
    }

    override suspend fun getEpisodeByNumber(episodeNumber: Int): Flow<ApiServiceState<RAMEpisodeResponse>> {
        return network.executeEndPoint {
            repository.getEpisodeByNumber(episodeNumber)
        }
    }
}