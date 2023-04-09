package com.example.mini_tech_talk.presentation.stack

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class StackScreenViewModel: ViewModel() {

    private val _counter: MutableState<Int> = mutableStateOf(0)
    val counter: State<Int> = _counter


    fun increment() {
        _counter.value++
    }

    fun reset() {
        _counter.value = 0
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("puyo", "onClear with: ${_counter.value}")
    }
}