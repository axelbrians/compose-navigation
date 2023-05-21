package com.example.mini_tech_talk.presentation.navparam

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mini_tech_talk.navigation.Screen.BundleScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavParamStartScreen(
    navigateToBundleScreen: (BundleScreen.BundleArg) -> Unit,
    modifier: Modifier = Modifier
) {
    var dataSetValue by rememberSaveable { mutableStateOf("") }
    var counterValue by rememberSaveable { mutableStateOf(10) }

    Column(modifier = modifier) {
        Text(
            text = "counter: $counterValue",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { counterValue-- }) {
                Text(text = "decrement")
            }

            Button(onClick = { counterValue++ }) {
                Text(text = "increment")
            }
        }

        OutlinedTextField(
            value = dataSetValue,
            onValueChange = { dataSetValue = it },
            placeholder = {
                Text(
                    text = "coma separated value, eg: one,two,three",
                    color = Color.DarkGray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    val dataSet = dataSetValue.split(",", ignoreCase = true).map { it.trim() }
                    navigateToBundleScreen(
                        BundleScreen.BundleArg(
                            dataSet = dataSet,
                            counter = counterValue
                        )
                    )
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "To Bundle Screen")
            }
            Spacer(modifier = Modifier.width(18.dp))
            Button(
                onClick = {
                    val dataSet = dataSetValue.split(",", ignoreCase = true).map { it.trim() }

                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "To Argument Screen")
            }
        }

    }
}

@Preview(apiLevel = 30, device = "id:pixel_2", showBackground = true)
@Composable
private fun Preview_NavParamStartScreen() {
    NavParamStartScreen(
        navigateToBundleScreen = { },
        modifier = Modifier.fillMaxSize()
    )
}
