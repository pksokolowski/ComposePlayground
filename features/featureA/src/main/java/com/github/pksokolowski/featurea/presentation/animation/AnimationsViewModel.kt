package com.github.pksokolowski.featurea.presentation.animation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * Here trying out exposing Compose's own State type from the VM, just to see how it works in
 * a bit of practice
 */
class AnimationsViewModel : ViewModel() {
    private val _showLeft = mutableStateOf(true)
    val showLeftPane: State<Boolean> = _showLeft

    fun toggleProductLineup() {
        _showLeft.value = !_showLeft.value
    }
}