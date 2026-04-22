package com.example.rickandmortybyds.domain.model.usecase

import com.example.rickandmortybyds.core.network.NetWork
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
}