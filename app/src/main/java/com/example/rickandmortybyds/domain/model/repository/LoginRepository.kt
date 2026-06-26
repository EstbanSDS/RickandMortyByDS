package com.example.rickandmortybyds.domain.model.repository

import com.example.rickandmortybyds.core.model.login.LoginUser

interface LoginRepository {
    suspend fun login(
        email: String,
        password: String
    ): LoginUser?
    suspend fun logout()
}