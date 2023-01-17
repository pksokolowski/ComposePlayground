package com.github.pksokolowski.featurea.presentation.constraints

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import com.github.pksokolowski.designsystem.theme.ComposablePlaygroundSurface
import com.github.pksokolowski.designsystem.theme.ComposePlaygroundTheme
import com.github.pksokolowski.featurea.R
import com.github.pksokolowski.featurea.presentation.constraints.ConstraintsViewModel.*
import org.koin.androidx.compose.koinViewModel

@Composable
fun ConstraintLayoutScreen(
    viewModel: ConstraintsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    ConstraintLayoutScreenContent(state = state)
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMotionApi::class)
@Composable
private fun ConstraintLayoutScreenContent(
    state: ConstraintsViewModel.State
) {
    ComposablePlaygroundSurface {
        AnimatedContent(state) {
            when (it) {
                is Loading -> {
                    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                        val (progressBar) = createRefs()

                        CircularProgressIndicator(modifier = Modifier.constrainAs(progressBar) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        })
                    }
                }
                is Loaded -> {
                    // a little animation to add life to the sample
                    val transition = rememberInfiniteTransition()
                    val verticalBias by transition.animateFloat(
                        initialValue = 0f,
                        targetValue = 300f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(1000),
                            repeatMode = RepeatMode.Reverse
                        )
                    )

                    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                        val (ball) = createRefs()
                        BasketBall(modifier = Modifier.constrainAs(ball) {
                            top.linkTo(parent.top, margin = verticalBias.dp)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        })
                    }
                }
                is SeparatedConstraints -> {
                    val constraints = ConstraintSet {
                        val ball = createRefFor(REF_BALL)

                        constrain(ball) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    }

                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize(),
                        constraintSet = constraints
                    ) {
                        BasketBall(modifier = Modifier.layoutId(REF_BALL))
                    }
                }
                is ConstraintsTransition -> {
                    val constraintsStart = ConstraintSet {
                        val ball = createRefFor(REF_BALL)
                        val text = createRefFor(REF_TEXT)

                        constrain(ball) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                        constrain(text) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        }
                    }
                    val constraintsEnd = ConstraintSet {
                        val ball = createRefFor(REF_BALL)
                        val text = createRefFor(REF_TEXT)

                        constrain(ball) {
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        }
                        constrain(text) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                    }

                    val transition = rememberInfiniteTransition()
                    val progress by transition.animateFloat(
                        initialValue = 0f,
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(2000),
                            repeatMode = RepeatMode.Reverse
                        )
                    )

                    MotionLayout(
                        start = constraintsStart, end = constraintsEnd, progress = progress,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        BasketBall(modifier = Modifier.layoutId(REF_BALL))
                        Text(
                            stringResource(id = R.string.accessibility_ball),
                            modifier = Modifier.layoutId(REF_TEXT)
                        )
                    }
                }
            }
        }
    }
}

private const val REF_BALL = "ball"
private const val REF_TEXT = "text"

@Composable
private fun BasketBall(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.baseline_sports_baseball_24),
        contentDescription = stringResource(id = R.string.accessibility_ball),
        modifier = modifier
    )
}

@Composable
@Preview
fun LoadingPreview() {
    ComposePlaygroundTheme {
        ConstraintLayoutScreenContent(
            state = Loading
        )
    }
}

@Composable
@Preview
fun LoadedPreview() {
    ComposePlaygroundTheme {
        ConstraintLayoutScreenContent(
            state = Loaded
        )
    }
}

@Composable
@Preview
fun SeparatedConstraintsPreview() {
    ComposePlaygroundTheme {
        ConstraintLayoutScreenContent(
            state = SeparatedConstraints
        )
    }
}