package com.example.rickandmortybyds.di

import com.example.rickandmortybyds.domain.model.repository.RickAndMortyRepository
import com.example.rickandmortybyds.domain.model.repository.RickAndMortyRepositoryImpl
import com.example.rickandmortybyds.domain.model.usecase.RickAndMortyUseCase
import com.example.rickandmortybyds.domain.model.usecase.RickAndMortyUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {
    @Binds
    abstract fun providesRickAndMortyUseCase(rickAndMortyUseCase: RickAndMortyUseCaseImpl): RickAndMortyUseCase
    @Binds
    abstract fun providesRickAndMortyRepository(rickAndMortyRepository: RickAndMortyRepositoryImpl): RickAndMortyRepository
}