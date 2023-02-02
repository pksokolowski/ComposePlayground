package com.github.pksokolowski.featurea

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.pksokolowski.featurea.presentation.main.FeatureAMainScreen
import com.github.pksokolowski.featurea.presentation.main.FeatureAViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.featureAScreen() {
    composable(FEATURE_A_PATH) {
        val viewModel: FeatureAViewModel = koinViewModel()
        val message by viewModel.message.collectAsState()

        FeatureAMainScreen(
            message = message
        )
    }
}

const val FEATURE_A_PATH = "featureA"