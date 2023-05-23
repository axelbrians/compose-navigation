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
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mini_tech_talk.navigation.Screen
import com.example.mini_tech_talk.navigation.Screen.ArgumentScreen
import com.example.mini_tech_talk.navigation.Screen.BundleScreen
import com.example.mini_tech_talk.navigation.Screen.HomeScreen
import com.example.mini_tech_talk.navigation.Screen.StackScreen
import com.example.mini_tech_talk.navigation.composable
import com.example.mini_tech_talk.presentation.home.HomeViewModel
import com.example.mini_tech_talk.presentation.home.addHomeScreen
import com.example.mini_tech_talk.presentation.navparam.NavParamStartScreen
import com.example.mini_tech_talk.presentation.navparam.addArgumentScreen
import com.example.mini_tech_talk.presentation.navparam.addBundleScreen
import com.example.mini_tech_talk.presentation.navparam.addNavParamStartScreen
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
                    addNavParamStartScreen(
                        navigateToBundleScreen = {
                            homeViewModel.start()
                            navController.navigate(BundleScreen.createRoute(it))

//                            val bundle = Bundle().apply {
//                                putParcelable("myExtra", it)
//                            }
//                            navController.navigate(BundleScreen.route, bundle)
                        },
                        navigateToArgumentScreen = {
                            navController.navigate(ArgumentScreen.createRoute(it))
                        },
                        modifier = Modifier.fillMaxSize()
                    )

                    addArgumentScreen(modifier = Modifier.fillMaxSize())

                    addBundleScreen(
                        stopMeasureTime = homeViewModel::stop,
                        getMeasuredTime = {
                            homeViewModel.endTimeInMillis - homeViewModel.startTimeInMillis
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }


                composable(
                    route = "route-a/{id}",
                    arguments = listOf(
                        navArgument(name = "id") {
                            this.type = NavType.IntType
                            this.nullable = false
                            this.defaultValue = 0
                        }
                    )
                ) {

                    navController.navigate("ini-route-a")

                }

                composable(
                    route = "ini-route-a/{yourDataName}/{data-2}/{data-3}/{data-4}",
                    arguments = listOf(
                        navArgument("yourDataName") {
                            type = NavType.IntType
                            nullable = false
                            defaultValue = 0
                        }
                    )
                ) { }

                composable(
                    route = "ini-route-b"
                ) { }
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










fun NavController.navigate(route: String, bundle: Bundle, popUpToRoute: String? = null) {
    val callback = NavController.OnDestinationChangedListener { _, destination, arguments ->
//        Log.d("puyo", "callbackCalled")
        if (destination.route == route) {
//            Log.d("puyo", "intercepting Bundle")
            arguments?.putAll(bundle)


            val bundleArg = arguments?.getParcelable("myExtra")
                ?: BundleScreen.BundleArg(emptyList(), 10)

//            Log.d("puyo", "intercepted Bundle: $bundleArg")
        }
    }
    addOnDestinationChangedListener(callback)
    if (popUpToRoute != null) {
        navigate(route, NavOptions.Builder().setPopUpTo(popUpToRoute, false, false).build())
    } else {
        navigate(route)
    }
    removeOnDestinationChangedListener(callback)
}
