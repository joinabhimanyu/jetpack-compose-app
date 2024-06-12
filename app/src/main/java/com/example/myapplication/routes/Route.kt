package com.example.myapplication.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

public sealed class Route(
    val route: String?,
    val label: String?,
    val description: String?,
    val icon: ImageVector?=null,
    val hasNavigationMenu: Boolean?=true,
    val argument: String?=null,
) {
    companion object {
        data object UserListRoute:Route("users","Users", "Users route", Icons.Filled.Home);
        data object UserEditRoute:Route("user_edit/{userId}","Edit User","Edit User route", null, false,"userId");
        data object SettingsRoute: Route("settings","Settings", "Settings route", Icons.Filled.Settings);
        data object EmailRoute: Route("email","Email","Email route", Icons.Filled.Email);
        data object PhoneRoute: Route("phone","Phone","Phone route", Icons.Filled.Phone);
    }
}