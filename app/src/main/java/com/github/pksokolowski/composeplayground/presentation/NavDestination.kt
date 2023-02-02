package com.github.pksokolowski.composeplayground.presentation

import com.github.pksokolowski.featurea.FEATURE_A_PATH

sealed class NavDestination(val path: String) {
    object MainScreen: NavDestination("main")
    object FeatureAScreen: NavDestination(FEATURE_A_PATH)
    object FeatureAConstraintsScreen: NavDestination("constraints")
    object SamplesScreen: NavDestination("samples")
}