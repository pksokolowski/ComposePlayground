package com.github.pksokolowski.composeplayground.samples.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.pksokolowski.designsystem.theme.ComposablePlaygroundSurface
import org.koin.androidx.compose.get

@Composable
fun SamplesScreen(
    samples: List<Sample> = get()
) {
    var currentSample: Sample? by remember { mutableStateOf(null) }

    ComposablePlaygroundSurface {
        Column {
            Box(
                modifier = Modifier.fillMaxHeight(0.8f)
            ) {
                currentSample?.Present()
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.LightGray)
            ) {
                items(samples.size) {
                    val sample = samples[it]

                    Card(elevation = 6.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .clickable {
                                currentSample = sample
                            }) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = sample::class.simpleName.toString(),
                                modifier = Modifier
                                    .padding(12.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .width(8.dp)
                                    .height(8.dp)
                                    .background(if (sample == currentSample) Color.Magenta else Color.Gray)
                            )
                        }
                    }

                }
            }
        }

    }
}