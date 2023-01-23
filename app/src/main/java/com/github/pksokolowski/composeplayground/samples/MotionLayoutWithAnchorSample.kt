package com.github.pksokolowski.composeplayground.samples

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.github.pksokolowski.composeplayground.samples.setup.Sample
import com.github.pksokolowski.featurea.R

/**
 * Uses json5 format for motionScene description, see
 * https://github.com/androidx/constraintlayout/wiki/Compose-MotionLayout-JSON-Syntax
 * for details on the syntax.
 */
class MotionLayoutWithAnchorSample : Sample {
    @OptIn(
        ExperimentalMotionApi::class, ExperimentalMaterialApi::class,
        ExperimentalComposeUiApi::class
    )
    @Composable
    override fun Present() {
        var listHeight by remember { mutableStateOf(1000f) }
        val swipeableState = rememberSwipeableState("Bottom")
        val anchors = mapOf(0f to "Bottom", listHeight to "Top")

        val progress = (swipeableState.offset.value / listHeight)

        val scene = MotionScene(
            """{ConstraintSets: {
                  start: {
                    ball: {
                      top: ['parent', 'top', 0],
                      start: ['parent', 'start', 0],
                    },
                    text: {
                      bottom: ['parent', 'bottom', 0],
                      start: ['parent', 'start', 0],
                    },
                    list: {
                      height: 250,
                      width: 'spread',
                      bottom: ['parent', 'bottom', 0],
                      start: ['parent', 'start', 0],
                      end: ['parent', 'end', 0],
                    }
                  },
                  end: {
                    ball: {
                      bottom: ['parent', 'bottom', 0],
                      end: ['parent', 'end', 0],
                    },
                    text: {
                      top: ['parent', 'top', 0],
                      end: ['parent', 'end', 0],
                    },
                    list: {
                      height: 350,
                      width: 'spread',
                      bottom: ['parent', 'bottom', 0],
                      start: ['parent', 'start', 0],
                      end: ['parent', 'end', 0],
                    }
                  }
                },
                  Transitions: {
                    default: {
                      from: 'start',
                      to: 'end',
                      pathMotionArc: 'startHorizontal',
                      KeyFrames: {
                        KeyAttributes: [
                          {
                            target: ['ball'],
                            frames: [40, 60, 80],
                            translationX: [0, -110, 0],
                            rotationZ: [0, 180, 360],
                          },
                        ]
                      }
                    }
                  }
                }
            """.trimIndent()
        )

        MotionLayout(
            motionScene = scene,
            progress = progress,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .layoutId(REF_LIST)
                    .background(Color.DarkGray)
                    .swipeable(
                        state = swipeableState,
                        anchors = anchors,
                        resistance = ResistanceConfig(0.2f, 0.5f, 0.8f),
                        reverseDirection = true,
                        thresholds = { _, _ -> FractionalThreshold(0.3f) },
                        orientation = Orientation.Vertical
                    )
                    .onSizeChanged { size ->
                        listHeight = size.height.toFloat()
                    }
            ) {
                LazyColumn {
                    items(150) {
                        Box(modifier = Modifier.clip(CutCornerShape(8.dp))) {
                            Text(text = "item $it")
                        }
                    }
                }
            }
            BasketBall(modifier = Modifier.layoutId(REF_BALL))
            Text(
                stringResource(id = R.string.accessibility_ball),
                modifier = Modifier.layoutId(REF_TEXT)
            )
        }
    }
}

private const val REF_BALL = "ball"
private const val REF_TEXT = "text"
private const val REF_LIST = "list"

@Composable
private fun BasketBall(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.baseline_sports_baseball_24),
        contentDescription = stringResource(id = R.string.accessibility_ball),
        modifier = modifier
    )
}
