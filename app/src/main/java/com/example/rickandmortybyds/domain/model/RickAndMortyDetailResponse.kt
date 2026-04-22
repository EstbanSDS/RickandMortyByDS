package com.example.rickandmortybyds.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class RickAndMortyDetailResponse(
    val info: Info? = null,
    val results: List<Result>? = null,
)

data class Info(
    val count: Int? = null,
    val next: String? = null,
    val pages: Int? = null,
    val prev: Any? = null,
)

data class Result(
    val created: String? = null,
    val episode: List<String>? = null,
    @SerializedName("gender") val genero: String? = null,
    val id: Int? = null,
    val image: String? = null,
    val location: Location? = null,
    val name: String? = null,
    val origin: Origin? = null,
    @SerializedName("species") val especie: String? = null,
    val status: String? = null,
    val type: String? = null,
    val url: String? = null,
)

@Serializable
data class Location(
    val name: String? = null,
    val url: String? = null,
)

@Serializable
data class Origin(
    val name: String? = null,
    val url: String? = null,
)