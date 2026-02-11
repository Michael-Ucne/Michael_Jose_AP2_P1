package com.phantomshard.michael_jose_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MichaelAp2NavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.BorrameList
    ) {
        composable<Screen.BorrameList> {
        }
        composable<Screen.BorrameEdit> { backStackEntry ->
        }
    }
}
