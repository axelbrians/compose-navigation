package com.example.mini_tech_talk.presentation.stack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
        Text(
            text = route,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "remember")
                Text(text = "$remember", fontSize = 18.sp)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "rememberSaveable")
                Text(text = "$rememberSaveable", fontSize = 18.sp)
            }
        }
    }
}

@Preview(device = "id:Nexus 4", showBackground = true)
@Composable
private fun Preview_StackComponent() {
    StackComponent(
        route = "route",
        remember = 10,
        rememberSaveable = 20,
        modifier = Modifier.fillMaxSize()
    )
}
