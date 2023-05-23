package com.example.mini_tech_talk.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn

class HomeViewModel: ViewModel() {

    private val _flowList = List<Flow<Int>>(1) {
        flow<Int> {
            var counter = 0
            while (true) {
                counter++
                emit(counter)
                delay(1_000)
            }
        }
        .shareIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2_000)
        )
    }
    val flowList = _flowList


    var startTimeInMillis: Long = 0
    var endTimeInMillis: Long = 0

    fun start() {
        startTimeInMillis = System.currentTimeMillis()
    }

    fun stop() {
        endTimeInMillis = System.currentTimeMillis()
    }

}