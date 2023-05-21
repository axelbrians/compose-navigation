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
import androidx.navigation.NavGraphBuilder
import com.example.mini_tech_talk.navigation.Screen
import com.example.mini_tech_talk.navigation.composable

fun NavGraphBuilder.addHomeScreen(
    modifier: Modifier = Modifier,
    navigateToStackScreen: () -> Unit,
    navigateToManualScreen: () -> Unit,
    navigateToNavParamScreen: () -> Unit
) {
    composable(screen = Screen.HomeScreen) {
        HomeScreen(
            modifier = modifier,
            navigateToStackScreen = navigateToStackScreen,
            navigateToManualScreen = navigateToManualScreen,
            navigateToNavParamScreen = navigateToNavParamScreen
        )
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToStackScreen: () -> Unit,
    navigateToManualScreen: () -> Unit,
    navigateToNavParamScreen: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        Button(onClick = navigateToStackScreen) {
            Text(text = "Stack Screen")
        }

        Button(onClick = navigateToManualScreen) {
            Text(text = "2nd Activity")
        }

        Button(onClick = navigateToNavParamScreen) {
            Text(text = "NavParam Screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_HomeScreen(modifier: Modifier = Modifier) {
    HomeScreen(
        modifier = Modifier.fillMaxSize(),
        navigateToStackScreen = { },
        navigateToManualScreen = { },
        navigateToNavParamScreen = { }
    )
}
