package com.example.rickandmortybyds.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmortybyds.presentation.screens.RAMAllCharactersScreen

@Composable
fun NavigationWrapper(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = RAMAllCharactersScreen
    ){

        composable<RAMAllCharactersScreen> {
            RAMAllCharactersScreen()
        }

    }

}