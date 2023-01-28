package com.github.pksokolowski.composeplayground.samples.counterSample

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedDigits(
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
        digits.forEach { digit ->
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

// adding one for the negative number representations
private const val MAX_INT_DIGITS_LEN = Int.MAX_VALUE.toString().length + 1
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