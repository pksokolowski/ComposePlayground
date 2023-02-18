package com.github.pksokolowski.composeplayground.samples.scaffold.destination2

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.pksokolowski.composeplayground.R
import com.github.pksokolowski.composeplayground.samples.scaffold.ScaffoldViewState

fun NavController.navigateToDestination2Screen() {
    navigate(ROUTE)
}

fun NavGraphBuilder.destination2Screen(
    onSetScaffoldViewState: (ScaffoldViewState) -> Unit,
) {
    composable(ROUTE) {
        onSetScaffoldViewState(ScaffoldViewState(screenTitle = R.string.scaffold_screen_2_title))

        Destination2Screen()
    }
}

private const val ROUTE = "scaffold/destination2"