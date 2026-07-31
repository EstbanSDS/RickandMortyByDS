package com.example.rickandmortybyds.model.viewmodel

import androidx.lifecycle.ViewModel
import com.example.rickandmortybyds.presentation.CharacterResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.rickandmortybyds.presentation.EpisodeResult
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SharedViewModel : ViewModel() {

    private val _characterResult = MutableStateFlow(CharacterResult())

    val characterResult: StateFlow<CharacterResult> = _characterResult.asStateFlow()

    fun saveCharacterInfo(
        id: Int,
        characterName: String,
        status: String
    ) {
        _characterResult.update { currentCharacter ->
            currentCharacter.copy(
                id = id,
                characterName = characterName,
                status = status
            )
        }
    }

    fun saveLocation(
        originName: String,
        nameLocation: String
    ) {
        _characterResult.update { currentCharacter ->
            currentCharacter.copy(
                originName = originName,
                nameLocation = nameLocation
            )
        }
    }

    fun saveEpisodes(
        episodes: List<EpisodeResult>
    ) {
        _characterResult.update { currentCharacter ->
            currentCharacter.copy(
                episodes = episodes
            )
        }
    }

    fun updateModificationDate() {

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        val currentDate = LocalDateTime.now().format(formatter)

        _characterResult.update { currentCharacter ->
            currentCharacter.copy(
                fechaModificacion = currentDate
            )
        }
    }
}
