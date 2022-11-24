package com.github.pksokolowski.composeplayground

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.github.pksokolowski.designsystem.theme.ComposePlaygroundTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
    val message = viewModel.message.collectAsState()
    MainScreenContent(message = message.value)
}

@Composable
private fun MainScreenContent(message: String) {
    Text(text = "Important message is: $message")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePlaygroundTheme {
        MainScreenContent("Processing")
    }
}