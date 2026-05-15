package com.example.rickandmortybyds.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.rickandmortybyds.model.viewmodel.RAMCharacterDBViewModel
import com.example.rickandmortybyds.presentation.screens.RAMACharacterDetailScreen
import com.example.rickandmortybyds.presentation.screens.RAMAllCharactersScreen

@Composable
fun NavigationWrapper(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = RAMAllCharactersRoute
    ) {

        composable<RAMAllCharactersRoute> {

            RAMAllCharactersScreen(

                navigateToCharacterDetail = { characterId ->
                    navController.navigate(RAMCharacterDetailRoute(characterId))
                }
            )
        }

        composable<RAMCharacterDetailRoute> { backStackEntry ->
            val character = backStackEntry.toRoute<RAMCharacterDetailRoute>()
            RAMACharacterDetailScreen(

                characterId = character.characterId,
                navigationBack = { navController.navigate(RAMAllCharactersRoute) }
            )
        }
    }
}