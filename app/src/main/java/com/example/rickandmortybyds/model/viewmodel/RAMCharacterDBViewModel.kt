package com.example.rickandmortybyds.model.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortybyds.core.model.ramCharacters.RAMCharacterUiState
import com.example.rickandmortybyds.domain.model.repository.LoginRepository
import com.example.rickandmortybyds.domain.model.usecase.RickAndMortyUseCase
import com.example.rickandmortybyds.utils.application.ApiServiceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RAMCharacterDBViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,  // Contenedor de estado que android entrega al VM (guardar y recuperar: arg de navegacion, estado temporal, datos necesaris para reconstruir el VM)
    private val rickAndMortyUseCase: RickAndMortyUseCase,
    private val loginRepository: LoginRepository,
) : ViewModel() {

    private val _ramCharacter = MutableStateFlow(RAMCharacterUiState())
    val ramCharacter: StateFlow<RAMCharacterUiState> = _ramCharacter.asStateFlow()

    private val characterID =
        checkNotNull(savedStateHandle.get<Int>("characterId")) // “Busca dentro del SavedStateHandle un valor llamado "characterId" de tipo Int”

    val userRole = loginRepository.getUserRole().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    init {
        getRAMCharacterByIdDB(characterID)
    }

    fun getRAMCharacterByIdDB(characterID: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            rickAndMortyUseCase.getCharacterByIdDB(characterID).collect { response ->

                when (response) {
                    ApiServiceState.Loading -> {
                        _ramCharacter.update {
                            it.copy(
                                loading = true
                            )
                        }
                    }

                    is ApiServiceState.Success -> {
                        response.data?.let { data ->
                            _ramCharacter.update {
                                it.copy(
                                    loading = false,
                                    rickAndMortyDetail = data
                                )
                            }
                        }
                    }

                    is ApiServiceState.Error -> {
                        _ramCharacter.update {
                            it.copy(
                                loading = false,
                                rickAndMortyDetail = null
                            )
                        }
                    }
                }
            }
        }
    }

    fun closeAlertDialog() {
        _ramCharacter.update {
            it.copy(showErrorDialog = false)
        }
    }
}