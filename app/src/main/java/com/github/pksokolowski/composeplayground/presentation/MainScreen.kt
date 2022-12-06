package com.github.pksokolowski.composeplayground.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.github.pksokolowski.designsystem.theme.ComposablePlaygroundSurface
import com.github.pksokolowski.designsystem.theme.ComposePlaygroundTheme
import kotlinx.coroutines.delay
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

    val message = viewModel.message.collectAsState()
    MainScreenContent(message = message.value)
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