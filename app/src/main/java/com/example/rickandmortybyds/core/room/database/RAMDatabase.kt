package com.example.rickandmortybyds.core.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmortybyds.core.room.RAMConverters
import com.example.rickandmortybyds.core.room.dao.RAMDao
import com.example.rickandmortybyds.core.room.entity.CharactersEntity

@Database(
    entities = [CharactersEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    RAMConverters::class
)
abstract class RAMDatabase : RoomDatabase() {
    abstract fun ramDao(): RAMDao
}