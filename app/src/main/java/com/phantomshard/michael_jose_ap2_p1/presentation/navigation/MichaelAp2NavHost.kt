package com.phantomshard.michael_jose_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.phantomshard.michael_jose_ap2_p1.presentation.cerveza.CervezaListScreen
import com.phantomshard.michael_jose_ap2_p1.presentation.cerveza.CervezaEditScreen
import androidx.navigation.toRoute

@Composable
fun MichaelAp2NavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.CervezaList
    ) {
        composable<Screen.CervezaList> {
            CervezaListScreen(
                onAddCerveza = { navController.navigate(Screen.CervezaEdit()) },
                onEditCerveza = { id -> navController.navigate(Screen.CervezaEdit(id)) }
            )
        }
        composable<Screen.CervezaEdit> { backStackEntry ->
            val args = backStackEntry.toRoute<Screen.CervezaEdit>()
            CervezaEditScreen(
                cervezaId = args.cervezaId,
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}
