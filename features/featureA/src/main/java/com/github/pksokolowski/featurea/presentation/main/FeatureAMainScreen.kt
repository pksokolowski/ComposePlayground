package com.github.pksokolowski.featurea.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.pksokolowski.designsystem.theme.ComposablePlaygroundSurface
import com.github.pksokolowski.designsystem.theme.ComposePlaygroundTheme
import com.github.pksokolowski.featurea.presentation.animation.AnimationsScreen

@Composable
fun FeatureAMainScreen(
    message: String,
) {
    ComposablePlaygroundSurface {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Important message is: $message")
            AnimationsScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    ComposePlaygroundTheme {
        FeatureAMainScreen(message = "Processing")
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    ComposePlaygroundTheme(darkTheme = true) {
        FeatureAMainScreen(message = "Processing")
    }
}