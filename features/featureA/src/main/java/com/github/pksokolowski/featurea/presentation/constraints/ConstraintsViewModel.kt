package com.github.pksokolowski.featurea.presentation.constraints

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ConstraintsViewModel : ViewModel() {
    private val _screenState = MutableStateFlow<State>(Loading)
    val state = _screenState.asStateFlow()

    init {
        val states = listOf(Loaded, SeparatedConstraints, Loading)
        var i = 0
        viewModelScope.launch {
            while (isActive) {
                delay(4000)
                _screenState.value = states[i++ % states.size]
            }
        }
    }

    sealed class State
    object Loading : State()
    object Loaded : State()
    object SeparatedConstraints : State()
}

