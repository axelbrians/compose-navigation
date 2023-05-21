package com.example.mini_tech_talk.presentation.navparam

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import com.example.mini_tech_talk.navigation.Screen.BundleScreen
import com.example.mini_tech_talk.navigation.composable

fun NavGraphBuilder.addBundleScreen(
    modifier: Modifier = Modifier
) {
    composable(BundleScreen) {
        val bundleArg = BundleScreen.getNavArg(it)
        BundleScreen(
            navArg = bundleArg,
            modifier = modifier
        )
        Log.d("puyo", bundleArg.toString())
    }
}


@Composable
fun BundleScreen(
    navArg: BundleScreen.BundleArg,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "bundle counter: ${navArg.counter}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(navArg.dataSet) { index, item ->
                Text(
                    text = "${index + 1}. $item",
                    fontSize = 16.sp
                )
            }
        }
    }
}