package com.github.pksokolowski.composeplayground.presentation

sealed class NavDestination(val path: String) {
    object MainScreen: NavDestination("main")
    object FeatureAScreen: NavDestination("featureA")
}