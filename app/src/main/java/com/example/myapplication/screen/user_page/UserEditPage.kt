package com.example.myapplication.screen.user_page

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun UserEditPage(modifier: Modifier = Modifier) {
    val firstname = remember {
        mutableStateOf("")
    };
    val lastname = remember {
        mutableStateOf("")
    };
    val address = remember {
        mutableStateOf("")
    };

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            modifier = modifier, verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = firstname.value, onValueChange = { firstname.value = it },
                placeholder = {
                    Text(text = "Please enter firstname")
                }, modifier = Modifier.padding(top = 10.dp)
            )
            OutlinedTextField(
                value = lastname.value, onValueChange = { lastname.value = it },
                placeholder = {
                    Text(text = "Please enter lastname")
                }, modifier = Modifier.padding(top = 10.dp)
            )

            OutlinedTextField(
                value = address.value, onValueChange = { address.value = it },
                placeholder = {
                    Text(text = "Please enter address")
                }, modifier = Modifier
                    .padding(top = 10.dp)
                    .height(120.dp), singleLine = false
            )

            ElevatedButton(
                onClick = {
                    println("firstname: ${firstname.value}, lastname: ${lastname.value}, address: ${address.value}")
                }, modifier = Modifier.padding(top = 10.dp),
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(50)
            ) {
                Text(text = "Click me")
            }
        }
    }
}