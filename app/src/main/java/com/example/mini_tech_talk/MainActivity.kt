package com.example.mini_tech_talk

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.mini_tech_talk.navigation.Screen
import com.example.mini_tech_talk.navigation.Screen.ArgumentScreen
import com.example.mini_tech_talk.navigation.Screen.BundleScreen
import com.example.mini_tech_talk.navigation.Screen.HomeScreen
import com.example.mini_tech_talk.navigation.Screen.StackScreen
import com.example.mini_tech_talk.navigation.composable
import com.example.mini_tech_talk.presentation.home.HomeViewModel
import com.example.mini_tech_talk.presentation.home.addHomeScreen
import com.example.mini_tech_talk.presentation.navparam.NavParamStartScreen
import com.example.mini_tech_talk.presentation.navparam.addBundleScreen
import com.example.mini_tech_talk.presentation.stack.addStackScreen

class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

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
                    navigateToStackScreen = { navController.navigate(StackScreen.route) },
                    navigateToManualScreen = {
                        val intent = Intent(this@MainActivity, SecondActivity::class.java)
                        startActivity(intent)
                    },
                    navigateToNavParamScreen = {
                        navController.navigate(Screen.NavParamStartScreen.route)
                    }
                )

                addStackScreen(
                    modifier = Modifier.fillMaxSize(),
                    navigateUp = { navController.navigateUp() }
                )


                navigation(
                    startDestination = Screen.NavParamStartScreen.route,
                    route = Screen.NavParamScreen.route,
                ) {
                    composable(Screen.NavParamStartScreen) {
                        NavParamStartScreen(
                            navigateToBundleScreen = {
                                navController.navigate(
                                    BundleScreen.createRoute(it)
                                )
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    composable(ArgumentScreen) {
                        val navArg = ArgumentScreen.getNavArg(it)

                    }

                    addBundleScreen(modifier = Modifier.fillMaxSize())
                }


            }
        }
    }

    @Composable
    fun ScreenA(
        modifier: Modifier = Modifier,
        navigateToB: () -> Unit,
        back: () -> Unit
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
    fun ScreenD(
        backTwice: () -> Unit,
        modifier: Modifier = Modifier
    ) {

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("puyo", "Activity onDestroy()")
    }

    override fun onStart() {
        super.onStart()
        Log.d("puyo", "Activity onStart()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("puyo", "Activity onStop()")
    }
}
