package com.example.rickandmortybyds.core.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortybyds.core.room.entity.CharactersEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RAMDao {
    @Query("SELECT * FROM ram_characters")
    fun getAllCharacters(): Flow<List<CharactersEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<CharactersEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharactersEntity)

    @Query("SELECT * FROM ram_characters WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharactersEntity?
}
