package com.example.rickandmortybyds.core.network

import com.example.rickandmortybyds.utils.application.Constants.GET_ALL_CHARACTERS
import com.example.rickandmortybyds.domain.model.RickAndMortyDetailResponse
import com.example.rickandmortybyds.domain.model.usecase.RAMEpisodeResponse
import com.example.rickandmortybyds.utils.application.Constants.GET_EPISODE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceRickAndMorty {
    @GET(GET_ALL_CHARACTERS)
    suspend fun getRickAndMortyInformationDetail(): Response<RickAndMortyDetailResponse>

    @GET(GET_EPISODE)
    suspend fun getRAMEpisodeByNumber (
        @Path("id") id: Int
    ): RAMEpisodeResponse
}