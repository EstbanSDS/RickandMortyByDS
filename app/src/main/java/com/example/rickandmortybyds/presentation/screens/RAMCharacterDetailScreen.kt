package com.example.rickandmortybyds.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmortybyds.model.viewmodel.RickAndMortyViewModel


@Composable
fun RAMACharacterDetailScreen(
    viewModel: RickAndMortyViewModel,
    characterId: Int,
    navigationBack: () -> Unit,
) {

    val ramCharacterDB = viewModel.ramCharacterDB.collectAsState()
    val character = ramCharacterDB.value.rickAndMortyDetail
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getRAMCharacterById(characterId)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        if (character != null) {
            Text(character.name ?: "Sin nombre")

            Spacer(modifier = Modifier.height(14.dp))

            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(14.dp))

            character.episode?.let {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                )
                {
                    items(character.episode) { episode ->
                        Text(episode)
                    }
                }
            }
        }
    }
}