package com.example.rickandmortybyds.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmortybyds.core.room.dao.RAMDao
import com.example.rickandmortybyds.core.room.database.RAMDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RAMDatabaseModule {
    @Provides
    @Singleton
    fun providesDatabases(@ApplicationContext context: Context): RAMDatabase {
        return Room.databaseBuilder(
            context,
            RAMDatabase::class.java,
            "ram_characters"
        ).build()
    }
        @Provides
        fun provideCharacterDao(db: RAMDatabase): RAMDao = db.ramDao()

}