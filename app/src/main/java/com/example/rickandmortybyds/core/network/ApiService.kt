package com.example.rickandmortybyds.core.network

import com.example.rickandmortybyds.utils.application.Constants.GET_ALL_CHARACTERS
import com.example.rickandmortybyds.domain.model.RickAndMortyDetailResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceRickAndMorty {
    @GET(GET_ALL_CHARACTERS)
    suspend fun getRickAndMortyInformationDetail(): Response<RickAndMortyDetailResponse>
}