package com.example.mini_tech_talk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.mini_tech_talk.navigation.Screen
import com.example.mini_tech_talk.navigation.Screen.ArgumentScreen
import com.example.mini_tech_talk.navigation.Screen.BundleScreen
import com.example.mini_tech_talk.navigation.Screen.HomeScreen
import com.example.mini_tech_talk.navigation.Screen.ManualScreen
import com.example.mini_tech_talk.navigation.Screen.StackScreen
import com.example.mini_tech_talk.navigation.composable
import com.example.mini_tech_talk.presentation.home.addHomeScreen
import com.example.mini_tech_talk.presentation.stack.addStackScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = HomeScreen.route,
                modifier = Modifier.fillMaxSize()
            ) {
                addHomeScreen(
                    modifier = Modifier.fillMaxSize(),
                    navigateToStackScreen = { navController.navigate(StackScreen.route) }
                )

                addStackScreen(
                    modifier = Modifier.fillMaxSize(),
                    navigateUp = { navController.navigateUp() }
                )

                composable(ArgumentScreen) {
                    val navArg = ArgumentScreen.getNavArg(it)

                }

                composable(ManualScreen) {

                    val currentScreen = remember { mutableStateOf("screen A") }
                    when (currentScreen.value) {
                        "screen A" -> ScreenA(navigateToB = {
                            currentScreen.value = "screen B"
                        })
                        "screen B" -> ScreenB(navigateToA = {
                            currentScreen.value = "screen A"
                        })
                        "screen C" -> ScreenC()
                        "screen D" -> ScreenD()
//                       .....
                    }


                }

                composable(BundleScreen) {
                    val bundleArg = BundleScreen.getNavArg(it)

                }
            }
        }
    }

    @Composable
    fun ScreenA(
        modifier: Modifier = Modifier,
        navigateToB: () -> Unit
    ) {
        Text(text = "its A")

        Button(onClick = navigateToB) {
            Text(text = "Navigate")
        }
    }

    @Composable
    fun ScreenB(
        modifier: Modifier = Modifier,
        navigateToA: () -> Unit
    ) {
        Text(text = "its B")

        Button(onClick = navigateToA) {
            Text(text = "Navigate")
        }
    }

    @Composable
    fun ScreenC(modifier: Modifier = Modifier) {

    }

    @Composable
    fun ScreenD(modifier: Modifier = Modifier) {

    }
}
