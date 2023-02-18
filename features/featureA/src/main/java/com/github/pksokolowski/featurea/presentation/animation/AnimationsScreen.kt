package com.github.pksokolowski.featurea.presentation.animation

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pksokolowski.designsystem.theme.ComposablePlaygroundSurface
import com.github.pksokolowski.designsystem.theme.ComposePlaygroundTheme
import org.koin.androidx.compose.koinViewModel

/**
 * See [AnimationsViewModel] for details of the state hoisting method chosen here
 */
@Composable
fun AnimationsScreen(
    viewModel: AnimationsViewModel = koinViewModel(),
) {
    AnimationsScreenContent(
        showRegularProducts = viewModel.showLeftPane.value,
        onToggleShowRegularProducts = viewModel::toggleProductLineup
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationsScreenContent(
    showRegularProducts: Boolean,
    onToggleShowRegularProducts: () -> Unit
) {
    ComposablePlaygroundSurface {
        Column(modifier = Modifier.fillMaxSize()) {
            Button(
                onClick = onToggleShowRegularProducts,
            ) {
                Text(text = "Toggle product category")
            }
            AnimatedContent(targetState = showRegularProducts,
                modifier = Modifier.fillMaxWidth(),
                transitionSpec = {
                    slideInHorizontally(initialOffsetX = { -it }) with
                            slideOutHorizontally(targetOffsetX = { it })
                }) {
                if (it) {
                    Box(
                        Modifier
                            .background(Color.Gray)
                            .height(64.dp)
                    ) {
                        Text(text = "Regular products :)")
                    }
                } else {
                    Box(
                        Modifier
                            .background(Color.DarkGray)
                            .height(64.dp)
                    ) {
                        Text(
                            text = "Alternative products :)",
                            modifier = Modifier
                                .rotate(12f)
                                .padding(top = 16.dp),
                            color = Color.Magenta
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun regularProductsPreview() {
    ComposePlaygroundTheme {
        AnimationsScreenContent(
            showRegularProducts = true,
            onToggleShowRegularProducts = {}
        )
    }
}

@Preview
@Preview(
    name = "preview2, within the same function",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun alternativeProductsPreview() {
    ComposePlaygroundTheme {
        AnimationsScreenContent(
            showRegularProducts = false,
            onToggleShowRegularProducts = {}
        )
    }
}