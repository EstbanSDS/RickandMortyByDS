package com.example.rickandmortybyds.utils

import com.example.rickandmortybyds.core.room.entity.CharactersEntity
import com.example.rickandmortybyds.domain.model.RAMCharacterResponse
import com.example.rickandmortybyds.domain.model.Result

fun Result.toEntity(): CharactersEntity {
    return CharactersEntity(
        id = this.id,
        created = this.created,
        episode = this.episode,
        genero = this.genero,
        image = this.image,
        location = this.location,
        name = this.name,
        origin = this.origin,
        especie = this.especie,
        status = this.status,
        type = this.type,
        url = this.url,
    )
}

fun CharactersEntity.toEntity(): RAMCharacterResponse{
    return RAMCharacterResponse(
        id = this.id,
        created = this.created,
        episode = this.episode,
        genero = this.genero,
        image = this.image,
        location = this.location,
        name = this.name,
        origin = this.origin,
        especie = this.especie,
        status = this.status,
        type = this.type,
        url = this.url,
    )
}