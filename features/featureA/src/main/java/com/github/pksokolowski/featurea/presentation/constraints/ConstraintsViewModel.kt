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
        val states = listOf(Loaded, SeparatedConstraints, ConstraintsTransition)
        var i = 0
        viewModelScope.launch {
            delay(1500)
            while (isActive) {
                _screenState.value = states[i++ % states.size]
                delay(4000)
            }
        }
    }

    sealed class State
    object Loading : State()
    object Loaded : State()
    object SeparatedConstraints : State()
    object ConstraintsTransition : State()
}

