package com.example.mini_tech_talk.presentation.stack

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StackComponent(
    route: String,
    remember: Int,
    rememberSaveable: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(text = route, fontSize = 16.sp)
        Text(text = "remember: $remember")
        Text(text = "rememberSaveable: $rememberSaveable")
    }
}