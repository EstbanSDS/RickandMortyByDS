package com.example.rickandmortybyds.model.viewmodel

import androidx.lifecycle.ViewModel
import com.example.rickandmortybyds.presentation.CharacterResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel : ViewModel() {

    private val _characterResult = MutableStateFlow(CharacterResult())

    val characterResult: StateFlow<CharacterResult> =
        _characterResult.asStateFlow()
}