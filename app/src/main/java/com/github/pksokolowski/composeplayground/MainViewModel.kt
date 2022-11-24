package com.github.pksokolowski.composeplayground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _message = MutableStateFlow("Processing")
    val message = _message as StateFlow<String>

    init {
        viewModelScope.launch {
            delay(2000)
            _message.value = "Done!"
        }
    }
}