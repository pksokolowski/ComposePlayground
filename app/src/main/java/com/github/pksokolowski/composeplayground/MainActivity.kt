package com.github.pksokolowski.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.pksokolowski.composeplayground.presentation.MainScreen
import com.github.pksokolowski.composeplayground.presentation.NavDestination
import com.github.pksokolowski.composeplayground.samples.setup.SamplesScreen
import com.github.pksokolowski.designsystem.theme.ComposePlaygroundTheme
import com.github.pksokolowski.featurea.featureAScreen
import com.github.pksokolowski.featurea.presentation.constraints.ConstraintLayoutScreen
import com.github.pksokolowski.featurea.presentation.main.FeatureAMainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            ComposePlaygroundTheme {
                NavHost(
                    navController = navController,
                    startDestination = NavDestination.SamplesScreen.path
                ) {
                    composable(NavDestination.MainScreen.path) { MainScreen(navController) }
                    featureAScreen()
                    composable(NavDestination.FeatureAConstraintsScreen.path) { ConstraintLayoutScreen() }
                    composable(NavDestination.SamplesScreen.path) { SamplesScreen() }
                }
            }
        }
    }
}
