package com.github.pksokolowski.featurea.presentation.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FeatureAViewModel : ViewModel() {
    private val _message = MutableStateFlow("kopkop")
    val message = _message.asStateFlow()
}