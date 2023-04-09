package com.example.mini_tech_talk.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import com.example.mini_tech_talk.navigation.Screen
import com.example.mini_tech_talk.navigation.composable

fun NavGraphBuilder.addHomeScreen(
    modifier: Modifier = Modifier,
    navigateToStackScreen: () -> Unit
) {
    composable(Screen.HomeScreen) {
        HomeScreen(
            viewModel(),
            modifier,
            navigateToStackScreen
        )
    }
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    navigateToStackScreen: () -> Unit
) {
    HomeScreen(
        modifier,
        navigateToStackScreen
    )
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToStackScreen: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(onClick = navigateToStackScreen) {
            Text(text = "Stack Screen")
        }
    }
}

@Preview
@Composable
private fun Preview_HomeScreen(modifier: Modifier = Modifier) {
    HomeScreen(
        modifier = Modifier.fillMaxSize(),
        navigateToStackScreen = { }
    )
}