package com.github.pksokolowski.composeplayground.samples.scaffold.destionation1

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.destination1Screen() {
    composable(DESTINATION1_ROUTE) {

        Destination1Screen(

        )
    }
}

const val DESTINATION1_ROUTE = "scaffold/destination1"