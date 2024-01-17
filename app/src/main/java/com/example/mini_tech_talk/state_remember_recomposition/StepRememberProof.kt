package com.example.mini_tech_talk.state_remember_recomposition

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

@Composable
fun StepRememberProof(modifier: Modifier = Modifier) {
    var state by remember { mutableIntStateOf(0) }


    Column(modifier = modifier) {
        Text(text = "state: $state")
    }

    LaunchedEffect(key1 = Unit) {
        while (true) {
            state += 1
            delay(1.seconds)
        }
    }
}

@Composable
fun LambdaProof(modifier: Modifier = Modifier) {

}

private fun getNumber(): Int {
    return Random.nextInt(until = 100)
}