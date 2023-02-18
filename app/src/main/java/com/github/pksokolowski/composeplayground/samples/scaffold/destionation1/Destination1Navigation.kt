package com.github.pksokolowski.composeplayground.samples.scaffold.destionation1

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.pksokolowski.composeplayground.R
import com.github.pksokolowski.composeplayground.samples.scaffold.ScaffoldViewState

fun NavController.navigateToDestination1Screen() {
    navigate(DESTINATION1_ROUTE)
}

fun NavGraphBuilder.destination1Screen(
    onSetScaffoldViewState: (ScaffoldViewState) -> Unit,
    onShowDetails: () -> Unit,
) {
    composable(DESTINATION1_ROUTE) {
        onSetScaffoldViewState(ScaffoldViewState(screenTitle = R.string.scaffold_screen_1_title))

        Destination1Screen(
            onShowDetails = onShowDetails,
        )
    }
}

const val DESTINATION1_ROUTE = "scaffold/destination1"