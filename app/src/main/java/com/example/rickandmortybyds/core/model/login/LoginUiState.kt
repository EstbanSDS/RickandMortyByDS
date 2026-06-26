package com.example.rickandmortybyds.core.model.login

data class LoginUiState(
    val loading: Boolean = false,

    val email: String = "",

    val role: UserRole? = null,

    val isLoginSuccess: Boolean = false,

    val errorMessage: String? = null
)
