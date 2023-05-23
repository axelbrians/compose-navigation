package com.example.mini_tech_talk.presentation.navparam

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import com.example.mini_tech_talk.navigation.Screen.ArgumentScreen
import com.example.mini_tech_talk.navigation.composable

fun NavGraphBuilder.addArgumentScreen(modifier: Modifier = Modifier) {
    composable(ArgumentScreen) {
        val navArg = ArgumentScreen.getNavArg(it)
        Log.d("puyo", "navArg: $navArg")
        ArgumentScreen(
            navArg = navArg,
            modifier = modifier
        )
    }
}


@Composable
fun ArgumentScreen(
    navArg: ArgumentScreen.ArgumentArg,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "int: ${navArg.int}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "float: ${navArg.float}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "string: ${navArg.string}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}