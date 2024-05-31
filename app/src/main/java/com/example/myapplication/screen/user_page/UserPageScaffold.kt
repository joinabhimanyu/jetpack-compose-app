package com.example.myapplication.screen.user_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.widgets.AppDrawerConfig
import com.example.myapplication.widgets.AppDrawerItem
import com.example.myapplication.widgets.BasicScaffold
import com.example.myapplication.widgets.BottomSheetOptions
import com.example.myapplication.widgets.DropDownOptions

@Composable
fun UserPageScaffold(modifier: Modifier, navController: NavController) {

    BasicScaffold(
        topAppBarContainerColor = MaterialTheme.colorScheme.primary,
        topAppBarTitleContentColor = MaterialTheme.colorScheme.background,
        navigationButtonIconTint = Color.White,
        navigationButtonImageVector = Icons.Filled.Menu,
        navigationButtonDescription = "Localized description",
        navigationButtonOnClick = { changeShowBottomSheet ->  /* TODO */ },
        showAppDrawerOnNavigationButton = true,
        actionButtonIconTint = Color.White,
        actionButtonImageVector = Icons.Filled.MoreVert,
        actionButtonDescription = "Localized description",
        actionButtonOnClick = { changeShowBottomSheet -> /*TODO*/ },
        wantActionButtonDropdown = true,
        onDismissRequestDropdown = { changeShowBottomSheet -> /*TODO*/ },
        dropDownOptions = listOf(
            DropDownOptions(
                "Menu 1",
                contentDescription = "Call",
                leadingIconImageVector = Icons.Filled.Home,
                onClickOption = { /*TODO*/ }
            ), DropDownOptions(
                "Menu 2",
                contentDescription = "Call",
                leadingIconImageVector = Icons.Filled.Settings,
                onClickOption = { /*TODO*/ }
            ), DropDownOptions(
                "Menu 3",
                contentDescription = "Call",
                leadingIconImageVector = Icons.Filled.Info,
                onClickOption = { /*TODO*/ }
            ), DropDownOptions(
                "Menu 4",
                contentDescription = "Call",
                leadingIconImageVector = Icons.Filled.Build,
                onClickOption = { /*TODO*/ }
            )),
        floatingActionButtonIconTint = Color.Black,
        floatingActionButtonImageVector = Icons.Filled.Add,
        floatingActionButtonDescription = "Add",
        onFloatingActionButtonClick = { changeShowBottomSheet -> changeShowBottomSheet.invoke(true) },
        bottomSheetHeader = "More Options...",
        bottomSheetOptions = listOf(
            BottomSheetOptions(
                iconTint = Color.Black,
                imageVector = Icons.Filled.Email,
                contentDescription = "Gmail",
                optionText = "Share with Gmail",
                onOptionClick = { /*TODO*/ }
            ),
            BottomSheetOptions(
                iconTint = Color.Black,
                imageVector = Icons.Filled.Share,
                contentDescription = "whatsapp",
                optionText = "Share with Whatsapp",
                onOptionClick = { /*TODO*/ }
            ),
            BottomSheetOptions(
                iconTint = Color.Black,
                imageVector = Icons.Filled.Settings,
                contentDescription = "settings",
                optionText = "Manage settings",
                onOptionClick = { /*TODO*/ }
            ),
            BottomSheetOptions(
                iconTint = Color.Black,
                imageVector = Icons.Filled.Build,
                contentDescription = "about",
                optionText = "About this application",
                onOptionClick = { /*TODO*/ }
            ),
            BottomSheetOptions(
                iconTint = Color.Black,
                imageVector = Icons.Filled.Edit,
                contentDescription = "report",
                optionText = "Report this",
                onOptionClick = { /*TODO*/ }
            ),
            BottomSheetOptions(
                iconTint = Color.Black,
                imageVector = Icons.Filled.Info,
                contentDescription = "feedback",
                optionText = "Send Feedback",
                onOptionClick = { /*TODO*/ }
            )
        ),
        bottomSheetContent = null,
        appDrawerConfig = AppDrawerConfig(
            navigationHeader = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Drawer title",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically),
                        textAlign = TextAlign.Center
                    )
                }
            },
            navigationItems = listOf(
                AppDrawerItem(navigationItemIcon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "item 1")}
                    ,navigationItemLabel = "Item 1", navigationItemSelected = false,
                    onNavigationItemClick = { /*TODO*/ }),
                AppDrawerItem(navigationItemIcon = { Icon(imageVector = Icons.Filled.Settings, contentDescription = "item 2")}
                    ,navigationItemLabel = "Item 2", navigationItemSelected = false,
                    onNavigationItemClick = { /*TODO*/ }),
                AppDrawerItem(navigationItemIcon = { Icon(imageVector = Icons.Filled.Email, contentDescription = "item 3")}
                    ,navigationItemLabel = "Item 3", navigationItemSelected = false,
                    onNavigationItemClick = { /*TODO*/ }),
                AppDrawerItem(navigationItemIcon = { Icon(imageVector = Icons.Filled.Phone, contentDescription = "item 4")}
                    ,navigationItemLabel = "Item 4", navigationItemSelected = false,
                    onNavigationItemClick = { /*TODO*/ })
            )
        )
    ) { contentPadding, showBottomSheet, changeShowBottomSheet, scope, snackBarHostState, drawerState ->
        Surface(
            modifier = modifier.padding(contentPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UsersListPage()
            }
        }
    }
}
