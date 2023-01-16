package com.github.pksokolowski.featurea.di

import com.github.pksokolowski.featurea.presentation.animation.AnimationsViewModel
import com.github.pksokolowski.featurea.presentation.constraints.ConstraintsViewModel
import com.github.pksokolowski.featurea.presentation.main.FeatureAViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureAModule = module() {
    viewModel { FeatureAViewModel() }
    viewModel { AnimationsViewModel() }
    viewModel { ConstraintsViewModel() }
}