package com.example.rickandmortybyds.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val DarkColorScheme = darkColorScheme(

    primary = Primary,
    secondary = Secondary,

    background = Background,
    surface = Surface,

    onPrimary = TextOnPrimary,
    onSecondary = TextOnSecondary,

    onBackground = TextPrimary,
    onSurface = TextPrimary

)


private val LightColorScheme = lightColorScheme(

    primary = PrimaryDark,
    secondary = Secondary,

    background = TextPrimary,
    surface = TextPrimary,

    onPrimary = TextOnPrimary,
    onSecondary = TextOnSecondary,

    onBackground = Background,
    onSurface = Background

)


@Composable
fun RickandMortyByDSTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit,
) {

    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}