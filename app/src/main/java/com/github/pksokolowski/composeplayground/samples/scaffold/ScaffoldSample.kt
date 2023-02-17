package com.github.pksokolowski.composeplayground.samples.scaffold

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.github.pksokolowski.composeplayground.samples.Sample
import com.github.pksokolowski.composeplayground.samples.scaffold.destination2.destination2Screen
import com.github.pksokolowski.composeplayground.samples.scaffold.destionation1.DESTINATION1_ROUTE
import com.github.pksokolowski.composeplayground.samples.scaffold.destionation1.destination1Screen

class ScaffoldSample : Sample {
    @Composable
    override fun Present() {
        val navController = rememberNavController()
        val title by rememberSaveable { mutableStateOf("") }

        Scaffold(
            topBar = {
                TopAppBar(
                    elevation = 0.dp,
                    backgroundColor = Color.Gray
                ) {
                    Text("title")
                }
            }
        ) {
            NavHost(
                navController = navController,
                modifier = Modifier.padding(it),
                startDestination = DESTINATION1_ROUTE
            ) {
                destination1Screen()
                destination2Screen()
            }
        }
    }
}
