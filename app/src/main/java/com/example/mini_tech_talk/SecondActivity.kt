package com.example.mini_tech_talk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import kotlin.random.Random

class SecondActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val counter = rememberSaveable { mutableStateOf(Random.nextInt(100)) }
            Text(text = "savedCounter: ${counter.value}")
        }
    }
}