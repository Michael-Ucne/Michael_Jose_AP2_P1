package com.phantomshard.michael_jose_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.phantomshard.michael_jose_ap2_p1.presentation.navigation.MichaelAp2NavHost
import com.phantomshard.michael_jose_ap2_p1.ui.theme.Michael_Jose_AP2_P1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Michael_Jose_AP2_P1Theme {
                val navController = rememberNavController()
                MichaelAp2NavHost(navController = navController)
            }
        }
    }
}