package com.example.rickandmortybyds.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmortybyds.presentation.screens.RAMACharacterDetailScreen
import com.example.rickandmortybyds.presentation.screens.RAMAllCharactersScreen
import com.example.rickandmortybyds.presentation.screens.RAMEpisodeDetailScreen
import com.example.rickandmortybyds.presentation.screens.RAMLogin
import com.example.rickandmortybyds.presentation.screens.RAMSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rickandmortybyds.model.viewmodel.SharedViewModel

@Composable
fun NavigationWrapper(navController: NavHostController) {

    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = RAMSplashRoute
    ) {

        composable<RAMSplashRoute> {
            RAMSplashScreen(
                navigateToLogin = {
                    navController.navigate(RAMLoginRoute) {
                        popUpTo(RAMSplashRoute) {
                            inclusive = true
                        }
                    }
                },

                navigateToAllCharacters = {
                    navController.navigate(RAMAllCharactersRoute) {
                        popUpTo(RAMSplashRoute) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<RAMAllCharactersRoute> {
            RAMAllCharactersScreen(
                sharedViewModel = sharedViewModel,


                navigateToCharacterDetail = { characterId ->
                    navController.navigate(RAMCharacterDetailRoute(characterId))
                },

                navigateToLogin = {
                    navController.navigate(RAMLoginRoute)
                }
            )
        }

        composable<RAMCharacterDetailRoute> { backStackEntry ->
            RAMACharacterDetailScreen(

                sharedViewModel = sharedViewModel,

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

                sharedViewModel = sharedViewModel,

                navigateToRAMCharacterDetail = { characterId ->
                    navController.navigate(RAMCharacterDetailRoute(characterId))
                },
                navigateToRAMAllCharacters = {
                    navController.navigate(RAMAllCharactersRoute)
                }
            )
        }

        composable<RAMLoginRoute> { backStackEntry ->
            RAMLogin(
                navigateToRAMAllCharacters = {
                    navController.navigate(RAMAllCharactersRoute)
                }
            )
        }
    }
}