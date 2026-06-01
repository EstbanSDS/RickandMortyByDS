package com.example.rickandmortybyds.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmortybyds.presentation.screens.RAMACharacterDetailScreen
import com.example.rickandmortybyds.presentation.screens.RAMAllCharactersScreen
import com.example.rickandmortybyds.presentation.screens.RAMEpisodeDetailScreen

@Composable
fun NavigationWrapper(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = RAMAllCharactersRoute
    ) {
// cambio de pc
        composable<RAMAllCharactersRoute> {

            RAMAllCharactersScreen(
                navigateToCharacterDetail = { characterId ->
                    navController.navigate(RAMCharacterDetailRoute(characterId))
                }
            )
        }

        composable<RAMCharacterDetailRoute> { backStackEntry ->
            RAMACharacterDetailScreen(
                navigationBack = { navController.navigate(RAMAllCharactersRoute) },

                navigateToEpisodeDetail = { episodeNumber ->
                    navController.navigate(
                        RAMEpisodeRoute(episodeNumber)
                    )
                }
            )
        }

        composable<RAMEpisodeRoute> { backStackEntry ->

            RAMEpisodeDetailScreen(
                navigateToRAMCharacterDetail = { characterId ->
                    navController.navigate(RAMCharacterDetailRoute(characterId))
                }
            )
        }
    }
}