package com.example.mini_tech_talk.state_remember_recomposition

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
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
fun StepRemember(modifier: Modifier = Modifier) {
    val functionInvocationValue = getNumber()

    var counter by remember {
        mutableIntStateOf(0)
    }

    Column(modifier = modifier) {
        Text(text = "function invocation value: $functionInvocationValue")

        Text(text = "frequent update value: $counter")
    }

    LaunchedEffect(key1 = Unit) {
        while (true) {
            counter += 1
            delay(1.seconds)
        }
    }
}

private fun getNumber(): Int {
    return Random.nextInt(until = 100)
}

data class MyState(
    var value: Int
)

@Composable
fun StepStateHolder(modifier: Modifier = Modifier) {
    val counterState = remember {
        mutableIntStateOf(0)
    }

    val counter = remember {
        MyState(0)
    }

    Column(modifier = modifier) {
        Text(text = "state: ${counterState.intValue}")

        Text(text = "non-state: $counter")

        Row {
            Button(onClick = {
                val temp = counterState.intValue
                counterState.intValue = temp + 1
            }) {
                Text(text = "Increment state")
            }

            Button(onClick = {
                val temp = counter.value
                counter.value = temp + 1
            }) {
                Text(text = "Increment non-state")
            }
        }
    }
}
