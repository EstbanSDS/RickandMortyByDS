package com.example.rickandmortybyds.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortybyds.domain.model.usecase.RickAndMortyUseCase
import com.example.rickandmortybyds.core.model.ramCharacters.RickAndMortyUiState
import com.example.rickandmortybyds.domain.model.repository.RickAndMortyRepository
import com.example.rickandmortybyds.utils.application.ApiServiceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickAndMortyViewModel @Inject constructor(
    private val rickAndMortyUseCase: RickAndMortyUseCase,
    private val rickAndMortyRepository: RickAndMortyRepository,
) : ViewModel() {

    private val _rickAndMortyData = MutableStateFlow(RickAndMortyUiState())
    val rickAndMortyData: StateFlow<RickAndMortyUiState> = _rickAndMortyData.asStateFlow()
    val ramCharacterDB = rickAndMortyRepository.getCharactersStream().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun getRickAndMortyDetail() {
        viewModelScope.launch(Dispatchers.IO) {
//            rickAndMortyUseCase.getRickAndMortyDetail().collect { response ->
            rickAndMortyUseCase.getAllCharactersDB().collect { response ->
                when (response) {
                    ApiServiceState.Loading -> {
                        _rickAndMortyData.update {
                            it.copy(
                                loading = true
                            )
                        }
                    }

                    is ApiServiceState.Success -> {
                        response.data?.let { data ->
                            _rickAndMortyData.update {
                                it.copy(
                                    loading = false,
                                    rickAndMortyDetail = data
                                )
                            }
                        }
                    }

                    is ApiServiceState.Error -> {
                        _rickAndMortyData.update {
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
}