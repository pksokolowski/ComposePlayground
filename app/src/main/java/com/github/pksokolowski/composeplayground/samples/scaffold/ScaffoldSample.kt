package com.github.pksokolowski.composeplayground.samples.scaffold

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.github.pksokolowski.composeplayground.samples.Sample
import com.github.pksokolowski.composeplayground.samples.scaffold.destination2.destination2Screen
import com.github.pksokolowski.composeplayground.samples.scaffold.destination2.navigateToDestination2Screen
import com.github.pksokolowski.composeplayground.samples.scaffold.destionation1.DESTINATION1_ROUTE
import com.github.pksokolowski.composeplayground.samples.scaffold.destionation1.destination1Screen

class ScaffoldSample : Sample {
    @Composable
    override fun Present() {
        val navController = rememberNavController()
        var scaffoldViewState by remember {
            mutableStateOf(ScaffoldViewState())
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    elevation = 0.dp,
                    backgroundColor = Color.Gray
                ) {
                    scaffoldViewState.screenTitle?.let { title ->
                        Text(stringResource(id = title))
                    }
                }
            }
        ) {
            NavHost(
                navController = navController,
                modifier = Modifier.padding(it),
                startDestination = DESTINATION1_ROUTE
            ) {
                destination1Screen(
                    onSetScaffoldViewState = { scaffoldViewState = it },
                    onShowDetails = { navController.navigateToDestination2Screen() }
                )
                destination2Screen(
                    onSetScaffoldViewState = { scaffoldViewState = it },
                )
            }
        }
    }
}

@Immutable
data class ScaffoldViewState(
    @StringRes val screenTitle: Int? = null,
)