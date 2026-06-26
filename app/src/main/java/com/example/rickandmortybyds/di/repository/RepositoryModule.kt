package com.example.rickandmortybyds.di.repository

import com.example.rickandmortybyds.domain.model.repository.LoginRepository
import com.example.rickandmortybyds.domain.model.repository.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindLoginRepository(
        impl: LoginRepositoryImpl
    ): LoginRepository
}