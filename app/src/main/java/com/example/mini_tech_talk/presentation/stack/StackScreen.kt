package com.example.mini_tech_talk.presentation.stack

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mini_tech_talk.navigation.Screen
import com.example.mini_tech_talk.navigation.composable
import kotlin.random.Random

fun NavGraphBuilder.addStackScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit
) {
    composable(Screen.StackScreen) {
        StackScreen(
            modifier = modifier,
            navigateUp = navigateUp
        )
    }
}

@Composable
fun StackScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit
) {
    val navHostController = rememberNavController()
    val backStackList = navHostController.currentBackStack.collectAsStateWithLifecycle()

    StackScreen(
        modifier = modifier.fillMaxSize(),
        navHostController = navHostController,
        backStackList = backStackList.value.asReversed()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun StackScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    backStackList: List<NavBackStackEntry>
) {
//    Log.d("puyo", "list: $backStackList")
//    Log.d("puyo", "currentBackStack: ${navHostController.currentBackStackEntry?.destination}")
//    Log.d("puyo", "prevBackStack: ${navHostController.previousBackStackEntry?.destination}")
    Scaffold(modifier = modifier) { scaffoldPadding ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(scaffoldPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(5f)
                    .fillMaxWidth()
                    .background(Color.Yellow.copy(alpha = 0.3f))
                    .padding(12.dp),
                reverseLayout = false,
                verticalArrangement = Arrangement.Bottom
            ) {
                itemsIndexed(
                    items = backStackList,
                    key = { index, item ->  item.id }
                ) { index: Int, item ->
                    Text(text = "[$index]@" +
                        "${item.id.substringBefore("-")} " +
                        "${item.destination.route}"
                    )
                }
            }

            NavHost(
                navController = navHostController,
                startDestination = "route-a",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
                    .background(Color.Cyan.copy(alpha = 0.3f))
            ) {
                composable(route = "route-a") {
                    val rememberValue = remember { mutableStateOf(Random.nextInt(99)) }
                    val saveableValue = rememberSaveable { mutableStateOf(Random.nextInt(99)) }

                    StackComponent(
                        route = "route A",
                        remember = rememberValue.value,
                        rememberSaveable = saveableValue.value
                    )
                }

                composable(route = "route-b") {
                    val rememberValue = remember { mutableStateOf(Random.nextInt(99)) }
                    val saveableValue = rememberSaveable { mutableStateOf(Random.nextInt(99)) }

                    StackComponent(
                        route = "route B",
                        remember = rememberValue.value,
                        rememberSaveable = saveableValue.value
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp, start = 12.dp, end = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                Column (horizontalAlignment = Alignment.CenterHorizontally) {

                    Button(onClick = {
                        navHostController.navigate("route-a")
                    }) {
                        Text(text = "Route A")
                    }

                    Button(onClick = {
                        navHostController.navigate("route-a") {
                            this.restoreState = true
                        }
                    }) {
                        Text(text = "Route A Restore")
                    }

                    Button(onClick = {
                        navHostController.navigate("route-a") {
                            navHostController.currentDestination?.route?.let { route ->
                                popUpTo(route) { inclusive = true }
                            }
                        }
                    }) {
                        Text(text = "Route A PopSelf")
                    }
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Button(onClick = {
                        navHostController.navigate("route-b")
                    }) {
                        Text(text = "Route B")
                    }

                    Button(onClick = {
                        navHostController.navigate("route-b") {
                            this.restoreState = true
                        }
                    }) {
                        Text(text = "Route B Restore")
                    }

                    Button(onClick = {
                        navHostController.navigate("route-b") {
                            navHostController.currentDestination?.route?.let { route ->
                                popUpTo(route) { inclusive = true }
                            }
                        }

//                        navHostController.popBackStack(
//                            "route-c",
//                            true
//                        )

//                        navHostController.navigate("route-c") {
//                            popUpTo("route-a")
//                        }
//
//                        navHostController.navigate("route-b") {
//                            popUpTo("route-b") {
//                                inclusive = true
//                            }
//                        }
                    }) {
                        Text(text = "Route B PopSelf")
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp, start = 12.dp, end = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {


                Button(onClick = {
                    if (navHostController.previousBackStackEntry != null) {
                        navHostController.navigateUp()
                    }
                }) {
                    Text(text = "Pop")
                }


                Button(onClick = {
                    if (navHostController.previousBackStackEntry != null) {
                        navHostController.popBackStack()
                    }
                }) {
                    Text(text = "Pop BackStack")
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    if (navHostController.previousBackStackEntry != null) {
                        with(navHostController.currentDestination?.route) {
                            if (this != null) {
                                navHostController.popBackStack(
                                    route = this,
                                    inclusive = true,
                                    saveState = true
                                )
                            } else {
                                navHostController.navigateUp()
                            }
                        }
                    }
                }) {
                    Text(text = "Pop SaveState")
                }

                Button(onClick = {
                    while (navHostController.previousBackStackEntry != null) {
                        navHostController.navigateUp()
                    }
                }) {
                    Text(text = "Clear")
                }
            }
        }
    }
}

@Preview(device = "id:Nexus 4")
@Composable
private fun Preview_StackScreen() {
    StackScreen(
        modifier = Modifier.fillMaxSize(),
        navHostController = rememberNavController(),
        backStackList = emptyList()
    )
}