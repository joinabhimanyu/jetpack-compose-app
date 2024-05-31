package com.example.myapplication.screen.user_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun UserEditPageScaffold(modifier: Modifier, navController: NavController, userId: String) {
    Scaffold { contentPadding ->
        Surface(
            modifier = modifier.padding(contentPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UserEditPage()
            }
        }
    }
}