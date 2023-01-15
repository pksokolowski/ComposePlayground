package com.github.pksokolowski.composeplayground.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.github.pksokolowski.designsystem.theme.ComposablePlaygroundSurface
import com.github.pksokolowski.designsystem.theme.ComposePlaygroundTheme
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = koinViewModel()
) {
    LaunchedEffect(true) {
        viewModel.destination
            .filterNotNull()
            .onEach { navController.navigate(it.path) }
            .launchIn(this)
    }

    val message by viewModel.message.collectAsState()
    MainScreenContent(message = message)
}

@Composable
private fun MainScreenContent(message: String) {
    ComposablePlaygroundSurface {
        Text(text = "Important message is: $message")
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    ComposePlaygroundTheme {
        MainScreenContent("Processing")
    }
}

@Preview(showBackground = true)
@Composable
private fun DarkPreview() {
    ComposePlaygroundTheme(darkTheme = true) {
        MainScreenContent("Processing")
    }
}