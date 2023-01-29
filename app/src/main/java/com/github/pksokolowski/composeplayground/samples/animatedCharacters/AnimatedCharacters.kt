package com.github.pksokolowski.composeplayground.samples.animatedCharacters

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import kotlin.math.max

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedCharacters(
    value: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {
    var characters by remember { mutableStateOf(generateCharStateList(value)) }
    LaunchedEffect(value) { characters = generateCharStateList(value, characters) }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        characters.forEachIndexed { i, character ->
            AnimatedContent(targetState = character, transitionSpec = {
                slideIntoContainer(
                    animationSpec = tween(500),
                    towards = AnimatedContentScope.SlideDirection.Up
                ) with slideOutOfContainer(
                    animationSpec = tween(500),
                    towards = AnimatedContentScope.SlideDirection.Up
                )
            }) { characterToDisplay ->
                Text(
                    text = characterToDisplay?.toString() ?: "",
                    color = color,
                    fontSize = fontSize,
                    fontStyle = fontStyle,
                    fontWeight = fontWeight,
                    fontFamily = fontFamily,
                    letterSpacing = letterSpacing,
                    textDecoration = textDecoration,
                    textAlign = textAlign,
                    lineHeight = lineHeight,
                    overflow = overflow,
                    softWrap = softWrap,
                    maxLines = maxLines,
                    onTextLayout = onTextLayout,
                    style = style,
                    modifier = Modifier.animateContentSize()
                )
            }
        }
    }
}

private fun generateCharStateList(
    newVal: String,
    oldState: List<Char?> = newVal.toList()
): List<Char?> {
    val naiveState = newVal.toList()

    val largerLen = max(naiveState.size, oldState.size)
    val output = List(largerLen) {
        val c = if (it < naiveState.size) naiveState[it] else null
        c
    }

    return output
}