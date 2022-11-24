package com.github.pksokolowski.designsystem.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pksokolowski.designsystem.theme.ComposePlaygroundTheme
import com.github.pksokolowski.designsystem.theme.Purple200

@Composable
fun FancyButton(onclick: () -> Unit) {
    Button(onClick = onclick) {
        Box(modifier = Modifier
            .size(10.dp)
            .drawBehind {
                drawRect(color = Purple200)
            }
        ) {
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePlaygroundTheme {
        FancyButton {}
    }
}