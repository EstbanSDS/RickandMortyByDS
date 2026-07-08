package com.example.rickandmortybyds.ui.components.scaffold
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RAMScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }

    }

}