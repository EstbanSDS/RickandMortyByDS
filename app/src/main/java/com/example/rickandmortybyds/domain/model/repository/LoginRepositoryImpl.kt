package com.example.rickandmortybyds.domain.model.repository

import com.example.rickandmortybyds.core.model.login.LoginUser
import com.example.rickandmortybyds.core.model.login.UserRole
import com.example.rickandmortybyds.data.datastore.SessionManager
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(

    private val sessionManager: SessionManager,

    ) : LoginRepository {

    private val users = listOf( //simulacion de base de datos

        LoginUser(
            email = "admin",
            password = "1234",
            name = "Administrador",
            role = UserRole.ADMIN
        ),

        LoginUser(
            email = "user",
            password = "1234",
            name = "Usuario",
            role = UserRole.USER
        ),

        LoginUser(
            email = "superuser",
            password = "1234",
            name = "Super Usuario",
            role = UserRole.SUPER_USER
        )
    )

    override suspend fun login(
        email: String,
        password: String,
    ): LoginUser? {

        val user = users.find { // recorre toda la lista

            it.email == email &&
                    it.password == password

        }

        user?.let {     // si user no es nulo, ejecuta este bloque (funcion de extencion)

            sessionManager.saveUser(
                email = it.email,
                name = it.name,
                role = it.role.name
            )
        }

        return user
    }

    override suspend fun logout() {

        sessionManager.clearSession()

    }
}