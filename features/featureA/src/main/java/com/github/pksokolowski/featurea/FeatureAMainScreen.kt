package com.github.pksokolowski.featurea

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.pksokolowski.designsystem.theme.ComposablePlaygroundSurface
import com.github.pksokolowski.designsystem.theme.ComposePlaygroundTheme

@Composable
fun FeatureAMainScreen() {
    FeatureAMainScreenContent("kop")
}

@Composable
private fun FeatureAMainScreenContent(message: String) {
    ComposablePlaygroundSurface {
        Text(text = "Important message is: $message")
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    ComposePlaygroundTheme {
        FeatureAMainScreenContent("Processing")
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    ComposePlaygroundTheme(darkTheme = true) {
        FeatureAMainScreenContent("Processing")
    }
}