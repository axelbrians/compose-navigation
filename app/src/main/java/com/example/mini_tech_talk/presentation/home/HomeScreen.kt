package com.example.mini_tech_talk.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import com.example.mini_tech_talk.navigation.Screen
import com.example.mini_tech_talk.navigation.composable
import kotlin.random.Random

fun NavGraphBuilder.addHomeScreen(
    modifier: Modifier = Modifier,
    navigateToStackScreen: () -> Unit,
    navigateToManualScreen: () -> Unit
) {
    composable(Screen.HomeScreen) {
        val counter = rememberSaveable { mutableStateOf(Random.nextInt(99)) }

        HomeScreen(
            viewModel = viewModel(),
            modifier = modifier,
            counter = counter.value,
            navigateToStackScreen = navigateToStackScreen,
            navigateToManualScreen = navigateToManualScreen
        )
    }
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    counter: Int,
    modifier: Modifier = Modifier,
    navigateToStackScreen: () -> Unit,
    navigateToManualScreen: () -> Unit
) {
    HomeScreen(
        counter = counter,
        modifier = modifier,
        navigateToStackScreen = navigateToStackScreen,
        navigateToManualScreen = navigateToManualScreen
    )
}

@Composable
internal fun HomeScreen(
    counter: Int,
    modifier: Modifier = Modifier,
    navigateToStackScreen: () -> Unit,
    navigateToManualScreen: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(text = "rememberSaveable: $counter")

        Button(onClick = navigateToStackScreen) {
            Text(text = "Stack Screen")
        }

        Button(onClick = navigateToManualScreen) {
            Text(text = "Manual Screen")
        }
    }
}

@Preview
@Composable
private fun Preview_HomeScreen(modifier: Modifier = Modifier) {
    HomeScreen(
        counter = 10,
        modifier = Modifier.fillMaxSize(),
        navigateToStackScreen = { },
        navigateToManualScreen = { }
    )
}