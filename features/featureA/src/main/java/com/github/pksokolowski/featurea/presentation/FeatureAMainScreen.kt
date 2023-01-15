package com.github.pksokolowski.featurea.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.pksokolowski.designsystem.theme.ComposablePlaygroundSurface
import com.github.pksokolowski.designsystem.theme.ComposePlaygroundTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun FeatureAMainScreen(
    viewModel: FeatureAViewModel = koinViewModel()
) {
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