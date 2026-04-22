package com.example.rickandmortybyds.core.room

import androidx.room.TypeConverter
import com.example.rickandmortybyds.domain.model.Location
import com.example.rickandmortybyds.domain.model.Origin
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RAMConverters {
    @TypeConverter
    fun fromStringList(value: List<String>): String = Json.encodeToString(value)

    @TypeConverter
    fun toStringList(value: String): List<String> = Json.decodeFromString(value)

    @TypeConverter
    fun fromLocationList(value: List<Location>): String = Json.encodeToString(value)

    @TypeConverter
    fun toLocationList(value: String): List<Location> = Json.decodeFromString(value)

    @TypeConverter
    fun fromOriginList(value: List<Origin>): String = Json.encodeToString(value)

    @TypeConverter
    fun toOriginList(value: String): List<Origin> = Json.decodeFromString(value)
}