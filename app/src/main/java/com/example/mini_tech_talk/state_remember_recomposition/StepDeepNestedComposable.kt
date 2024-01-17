package com.example.mini_tech_talk.state_remember_recomposition

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

@Composable
fun StepDeepNestedComposable(modifier: Modifier = Modifier) {
    val counter = remember {
        mutableIntStateOf(0)
    }

    Row {
        Text(text = "sengaja consume state: ${counter.intValue}")

//        NoStateLevel2(counter = counter.intValue)
    }

    LaunchedEffect(key1 = Unit) {
        while (true) {
            counter.intValue += 1
            delay(1.seconds)
        }
    }
}

@Composable
private fun Level2(
    counter: MutableState<Int>,
    modifier: Modifier = Modifier
) {
    val remember = remember {
        Random.nextInt(until = 100)
    }

    Column {
        Icon(imageVector = Icons.Default.Home, contentDescription = null)
        Text(text = "remember di child $remember")
        Spacer(modifier = modifier)
        Level3(counter)
    }
}

@Composable
private fun Level3(
    counter: MutableState<Int>,
    modifier: Modifier = Modifier
) {
    Column {
        Icon(imageVector = Icons.Default.Home, contentDescription = null)
        Text(text = "3")
        Spacer(modifier = modifier)
        Level4(counter)
    }
}

@Composable
private fun Level4(
    counter: MutableState<Int>,
    modifier: Modifier = Modifier
) {
    Column {
        Icon(imageVector = Icons.Default.Home, contentDescription = null)
        Text(text = "4")
        Spacer(modifier = modifier)
        Text(text = "counter value: ${counter.value}")
    }
}


@Composable
private fun NoStateLevel2(
    counter: Int,
    modifier: Modifier = Modifier
) {
    Column {
        Icon(imageVector = Icons.Default.Home, contentDescription = null)
        Text(text = "2")
        Spacer(modifier = modifier)
        NoStateLevel3(counter)
    }
}

@Composable
private fun NoStateLevel3(
    counter: Int,
    modifier: Modifier = Modifier
) {
    Column {
        Icon(imageVector = Icons.Default.Home, contentDescription = null)
        Text(text = "3")
        Spacer(modifier = modifier)
        NoStateLevel4(counter)
    }
}

@Composable
private fun NoStateLevel4(
    counter: Int,
    modifier: Modifier = Modifier
) {
    Column {
        Icon(imageVector = Icons.Default.Home, contentDescription = null)
        Text(text = "4")
        Spacer(modifier = modifier)
        Text(text = "counter value: $counter")
    }
}
