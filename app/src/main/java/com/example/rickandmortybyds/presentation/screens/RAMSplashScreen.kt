package com.example.rickandmortybyds.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rickandmortybyds.model.viewmodel.RAMSplashVM
import com.example.rickandmortybyds.ui.components.loading.RAMLoading

@Composable
fun RAMSplashScreen(
    viewModel: RAMSplashVM = hiltViewModel(),
    navigateToLogin: () -> Unit,
    navigateToAllCharacters: () -> Unit,
    ) {

    val isLoggedIn by viewModel.isLoggedIn.collectAsStateWithLifecycle()

    LaunchedEffect(isLoggedIn) {

        when (isLoggedIn) {
            true -> navigateToAllCharacters()
            false -> navigateToLogin()
            null -> Unit
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        RAMLoading(
            visible = true
        )
    }
}

/*
            PROBAR LOADING EN LA PANTALLA DE SPLASH
@Composable
fun RAMSplashScreen() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        RAMLoading(
            visible = true
        )
    }
}*/
