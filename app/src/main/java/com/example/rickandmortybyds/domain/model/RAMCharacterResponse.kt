package com.example.rickandmortybyds.domain.model

import com.google.gson.annotations.SerializedName

data class RAMCharacterResponse(

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

