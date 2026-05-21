package com.example.rickandmortybyds.model.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortybyds.core.model.ramCharacters.RAMEpisodeNumberUiState
import com.example.rickandmortybyds.domain.model.usecase.RickAndMortyUseCase
import com.example.rickandmortybyds.utils.application.ApiServiceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RAMEpisodeVM @Inject constructor(
    private val rickAndMortyUseCase: RickAndMortyUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val episodeNumber =
        checkNotNull(savedStateHandle.get<Int>("episodeNumber"))

    init {
        getRAMEpisodeNumber(episodeNumber)
    }

    private val _ramEpisodeNumber = MutableStateFlow(RAMEpisodeNumberUiState())
    val ramEpisodeNumber: StateFlow<RAMEpisodeNumberUiState> = _ramEpisodeNumber.asStateFlow()

    fun getRAMEpisodeNumber(episodeNumber: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            rickAndMortyUseCase.getEpisodeByNumber(episodeNumber).collect { response ->

                when (response) {
                    ApiServiceState.Loading -> {
                        _ramEpisodeNumber.update {
                            it.copy(
                                loading = true
                            )
                        }
                    }

                    is ApiServiceState.Success -> {
                        response.data?.let { data ->
                            _ramEpisodeNumber.update {
                                it.copy(
                                    loading = false,
                                    ramEpisodeNumberDetail = data
                                )
                            }
                        }
                    }

                    is ApiServiceState.Error -> {
                        _ramEpisodeNumber.update {
                            it.copy(
                                loading = false,
                                ramEpisodeNumberDetail = null
                            )
                        }
                    }
                }
            }
        }
    }
}