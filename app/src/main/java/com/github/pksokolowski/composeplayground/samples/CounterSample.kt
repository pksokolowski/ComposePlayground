package com.github.pksokolowski.composeplayground.samples

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.github.pksokolowski.composeplayground.samples.setup.Sample
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
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedDigits(
    value: Int,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.h1,
    fontSize: TextUnit = 50.sp,
    fontWeight: FontWeight = FontWeight.Bold,
) {
    var digits by remember { mutableStateOf(value.toDigitsString()) }
    LaunchedEffect(value) {
        digits = value.toDigitsString()
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        repeat(MAX_INT_DIGITS_LEN) { i ->
            val digit = digits[i]
            AnimatedContent(targetState = digit, transitionSpec = {
                slideIntoContainer(
                    animationSpec = tween(500),
                    towards = AnimatedContentScope.SlideDirection.Up
                ) with slideOutOfContainer(
                    animationSpec = tween(500),
                    towards = AnimatedContentScope.SlideDirection.Up
                )
            }) { digitToDisplay ->
                Text(
                    text = if (digitToDisplay == EMPTY_CHAR_MARKER) "" else digitToDisplay.toString(),
                    style = style,
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.animateContentSize()
                )
            }
        }
    }
}

private const val MAX_INT_DIGITS_LEN = Int.MAX_VALUE.toString().length
private const val EMPTY_CHAR_MARKER = '_'

private fun Int.toDigitsString(): String {
    val valueString = this.toString()
    val sb = StringBuilder()
    repeat(MAX_INT_DIGITS_LEN - valueString.length) {
        sb.append(EMPTY_CHAR_MARKER)
    }
    sb.append(valueString)
    return sb.toString()
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