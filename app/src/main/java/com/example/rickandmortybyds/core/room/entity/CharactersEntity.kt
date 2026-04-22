package com.example.rickandmortybyds.core.room.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortybyds.domain.model.Location
import com.example.rickandmortybyds.domain.model.Origin
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ram_characters")
data class CharactersEntity(
    @PrimaryKey val id: Int? = null,
    val created: String? = null,
    val episode: List<String>? = null,
    @SerializedName("gender") val genero: String? = null,
    val image: String? = null,
    @Embedded("location_")
    val location: Location? = null,
    val name: String? = null,
    @Embedded("origin_")
    val origin: Origin? = null,
    @SerializedName("species") val especie: String? = null,
    val status: String? = null,
    val type: String? = null,
    val url: String? = null,
)
