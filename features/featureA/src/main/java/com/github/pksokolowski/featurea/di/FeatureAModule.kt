package com.github.pksokolowski.featurea.di

import com.github.pksokolowski.featurea.presentation.FeatureAViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureAModule = module() {
    viewModel { FeatureAViewModel() }
}