package com.example.rickandmortybyds.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortybyds.core.model.login.LoginUiState
import com.example.rickandmortybyds.data.datastore.SessionManager
import com.example.rickandmortybyds.domain.model.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val sessionManager: SessionManager,
) : ViewModel() {

    init {
        checkSession()
    }

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {

            _uiState.value = LoginUiState(loading = true)

            val user = repository.login(email, password)

            _uiState.value = if (user != null) {
                LoginUiState(
                    loading = false,
                    email = user.email,
                    role = user.role,
                    isLoginSuccess = true
                )
            } else {
                LoginUiState(
                    loading = false,
                    errorMessage = "Credenciales incorrectas"
                )
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
            sessionManager.clearSession()
            _uiState.value = LoginUiState()
        }
    }

    private fun checkSession() {
        viewModelScope.launch {

            val logged = sessionManager.isLoggedInOnce()

            if (logged) {
                _uiState.value = LoginUiState(
                    isLoginSuccess = true
                )
            }
        }
    }
}