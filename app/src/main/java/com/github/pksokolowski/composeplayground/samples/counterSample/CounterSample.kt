package com.github.pksokolowski.composeplayground.samples.counterSample

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.pksokolowski.composeplayground.samples.Sample
import com.github.pksokolowski.designsystem.theme.ComposePlaygroundTheme

class CounterSample : Sample {
    @Composable
    override fun Present() {
        var count by remember { mutableStateOf(8) }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            AnimatedDigits(value = count)
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { count++ }) {
                    Text("+")
                }
                Button(onClick = { count-- }) {
                    Text("-")
                }
            }
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { count *= 10 }) {
                    Text("*10")
                }
                Button(onClick = { count /= 10 }) {
                    Text("/10")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    ComposePlaygroundTheme {
        AnimatedDigits(
            value = 10,
            modifier = Modifier.fillMaxSize()
        )
    }
}