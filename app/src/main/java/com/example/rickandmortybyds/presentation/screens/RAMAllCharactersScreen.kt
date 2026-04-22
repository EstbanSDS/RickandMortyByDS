package com.example.rickandmortybyds.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmortybyds.model.viewmodel.RickAndMortyViewModel

@Composable
fun RAMAllCharactersScreen(
    viewModel: RickAndMortyViewModel = hiltViewModel(),
) {

    val ramData by viewModel.rickAndMortyData.collectAsState()
    val ramDataDBState = viewModel.ramCharacterDB.collectAsState()
    LaunchedEffect(Unit) {
//        viewModel.getRickAndMortyDetail()
    }


    Column(modifier = Modifier.fillMaxSize()) {
        val name = ramDataDBState.value

        if (ramData.loading) {
            Text("Si se ve")
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(name) { movie ->    // crea un item por cada pelicula ( movie es una pelicula, no una lista)
                Text(movie.name ?: "nada")
            }
        }
        Text("Datos: }")
        Button(
            onClick = { viewModel.getRickAndMortyDetail() }
        ) { Text("hola") }

    }
}

