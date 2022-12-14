package com.github.pksokolowski.composeplayground.di

import com.github.pksokolowski.composeplayground.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val applicationModule = module {
    viewModelOf(::MainViewModel)
}