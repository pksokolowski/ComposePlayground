package com.github.pksokolowski.composeplayground.samples.scaffold.destination2

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.destination2Screen() {
    composable(ROUTE) {

        Destination2Screen(

        )
    }
}

private const val ROUTE = "scaffold/destination2"