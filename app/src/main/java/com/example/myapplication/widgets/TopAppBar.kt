package com.example.myapplication.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    title: String? = "Top App Bar",
    containerColor: Color?,
    titleContentColor: Color?,
    navigationButton: (@Composable() () -> Unit)?,
    actions: (@Composable() () -> Unit)?
) {
    val showDropDownMenu = remember { mutableStateOf(false) }
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor!!,
            titleContentColor = titleContentColor!!,
        ),
        title = {
            Text(
                title!!,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            navigationButton!!()
        },
        actions = {
            actions!!()
        },
        scrollBehavior = scrollBehavior,
    )
}