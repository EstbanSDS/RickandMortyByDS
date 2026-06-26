package com.example.rickandmortybyds.core.model.login

data class LoginUser(
    val email: String,

    val password: String,

    val name: String,

    val role: UserRole
)
