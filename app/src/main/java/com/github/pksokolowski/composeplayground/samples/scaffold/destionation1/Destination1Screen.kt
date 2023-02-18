package com.github.pksokolowski.composeplayground.samples.scaffold.destionation1

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Destination1Screen(
    onShowDetails: () -> Unit,
) {
    Column {
        Text("hello 1")
        Button(onClick = onShowDetails) {
            Text(text = "Click me")
        }
    }
}
