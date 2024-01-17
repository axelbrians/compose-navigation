package com.example.mini_tech_talk.state_remember_recomposition

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

@Composable
fun StepState(
    counter: State<Int>,
    modifier: Modifier = Modifier
) {
    Text(
        text = "counter: ${counter.value}",
        modifier = modifier
    )
}

class MyState(initial: Int): MutableState<Int> {
    private var holder = initial

    override var value: Int
        get() = holder
        set(value) { holder = value }

    override fun component1(): Int = holder

    override fun component2(): (Int) -> Unit = { holder = it }
}

@Composable
fun StepRemember(modifier: Modifier = Modifier) {
    val functionInvocationValue = getNumber()

    Column(modifier = modifier) {
        var state by remember { mutableIntStateOf(0) }

        Text(text = "state: $state")
        Text(text = "function invocation value: $functionInvocationValue")

        LaunchedEffect(key1 = Unit) {
            while (true) {
                state += 1
                delay(1.seconds)
            }
        }
    }
}

private fun getNumber(): Int {
    return Random.nextInt(until = 100)
}
