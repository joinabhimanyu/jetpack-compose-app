package com.example.myapplication.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.screen.user_page.UserEditPageScaffold
import com.example.myapplication.screen.user_page.UserPageScaffold
import androidx.navigation.compose.composable
import androidx.navigation.createGraph

public sealed class Route(
    val path: String?,
    val label: String?,
    val icon: ImageVector? = null,
    val composable: (@Composable (modifier: Modifier, navController: NavController, args: String?) -> Unit)? = null,
    val argument: String? = null,
    val hasNavigationMenu: Boolean? = true,
) {
    companion object {
        val Screens = listOf(
            UserListRoute,
            UserEditRoute,
            SettingsRoute,
            EmailRoute,
            PhoneRoute
        )

        data object UserListRoute : Route(
            "users",
            "Users",
            Icons.Filled.Home,
            composable = { modifier: Modifier, navController: NavController, _ ->
                UserPageScaffold(
                    modifier,
                    navController
                )
            }
        );
        data object UserEditRoute : Route(
            "user_edit/{userId}", "Edit User", null,
            composable = { modifier: Modifier, navController: NavController, args: String? ->
                UserEditPageScaffold(
                    modifier,
                    navController,
                    args!!
                )
            },
            argument = "userId",
            hasNavigationMenu = false
        );
        data object SettingsRoute : Route("settings", "Settings", Icons.Filled.Settings,
            composable = { modifier: Modifier, navController: NavController, _ ->
                UserPageScaffold(
                    modifier,
                    navController
                )
            }
        );
        data object EmailRoute : Route("email", "Email", Icons.Filled.Email,
            composable = { modifier: Modifier, navController: NavController, _ ->
                UserPageScaffold(
                    modifier,
                    navController
                )
            }
        );
        data object PhoneRoute : Route("phone", "Phone", Icons.Filled.Phone,
            composable = { modifier: Modifier, navController: NavController, _ ->
                UserPageScaffold(
                    modifier,
                    navController
                )
            }
        );
    }
}