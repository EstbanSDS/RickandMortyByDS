package com.example.rickandmortybyds.domain.model.repository

import com.example.rickandmortybyds.core.model.login.LoginUser
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(      // Iniciar sesión
        email: String,
        password: String
    ): LoginUser?

    suspend fun logout()        //Cerrar sesión

    fun getUserRole(): Flow<String>     //Obtener el rol del usuario

    fun isLoggedIn(): Flow<Boolean>     //Saber si existe una sesión activa
}