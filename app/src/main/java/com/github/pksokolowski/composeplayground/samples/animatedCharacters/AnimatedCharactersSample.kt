package com.github.pksokolowski.composeplayground.samples.animatedCharacters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.github.pksokolowski.composeplayground.samples.Sample
import com.github.pksokolowski.designsystem.theme.ComposePlaygroundTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

class AnimatedCharactersSample : Sample {

    @Composable
    override fun Present() {
        var value by remember { mutableStateOf("initial value") }
        LaunchedEffect(key1 = Unit) {
            val texts = listOf(
                "kopkop",
                "abc",
                "ala ma kota",
                "ala ma kota a kot ma ale",
                "ala ma kota a kot ma psa",
                "parasol",
                "ala ma kota a kot ma parasol",
            )
            var i = 0
            while (isActive) {
                delay(1000)
                value = texts[i++ % texts.size]
            }
        }
        ComposePlaygroundTheme {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxSize()
            ) {
                AnimatedCharacters(capacity = 30, value = value, fontSize = 18.sp)
            }
        }
    }
}