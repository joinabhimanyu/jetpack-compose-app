package com.example.myapplication.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.routes.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class AppDrawerItem(
    val navigationItemIcon: (@Composable () -> Unit)?,
    val navigationItemLabel: String?,
    val navigationItemSelected: Boolean?,
    val path: String?,
    val onNavigationItemClick: (() -> Unit)?
)

data class AppDrawerConfig(
    val navigationItems: List<AppDrawerItem>? = null,
    val navigationHeaderTitle: String? = null,
    val navigationHeader: (@Composable () -> Unit)? = null,
    val allowDefaultNavigationItems: Boolean? = true
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicScaffold(
    modifier: Modifier,
    navController: NavController,
    topAppBarContainerColor: Color?,
    topAppBarTitleContentColor: Color?,

    navigationButtonIconTint: Color?,
    navigationButtonImageVector: ImageVector?,
    navigationButtonDescription: String?,
    navigationButtonOnClick: ((changeShowBottomSheet: (args: Boolean) -> Unit) -> Unit)?,
    showAppDrawerOnNavigationButton: Boolean,

    actionButtonIconTint: Color?,
    actionButtonImageVector: ImageVector?,
    actionButtonDescription: String?,
    actionButtonOnClick: ((changeShowBottomSheet: (args: Boolean) -> Unit) -> Unit)?,
    wantActionButtonDropdown: Boolean?,
    onDismissRequestDropdown: ((changeShowBottomSheet: (args: Boolean) -> Unit) -> Unit)?,
    dropDownOptions: List<DropDownOptions>?,

    floatingActionButtonIconTint: Color?,
    floatingActionButtonImageVector: ImageVector?,
    floatingActionButtonDescription: String?,
    onFloatingActionButtonClick: ((changeShowBottomSheet: (args: Boolean) -> Unit) -> Unit)?,

    bottomSheetHeader: String?,
    bottomSheetOptions: List<BottomSheetOptions>?,
    bottomSheetContent: (@Composable() () -> Unit)?,

    appDrawerConfig: AppDrawerConfig?,

    content: @Composable() (
        showBottomSheet: Boolean,
        changeShowBottomSheet: (args: Boolean) -> Unit,
        scope: CoroutineScope,
        snackBarHostState: SnackbarHostState,
        drawerState: DrawerState
    ) -> Unit
) {

    var showBottomSheet by remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed);
    val changeShowBottomSheet = { args: Boolean -> showBottomSheet = args };

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(300.dp),
                drawerShape = CutCornerShape(0.dp)
            ) {
                if (appDrawerConfig?.navigationHeaderTitle.isNullOrBlank()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(
                            modifier = Modifier
                                .width(50.dp)
                                .fillMaxHeight()
                                .padding(start = 10.dp, end = 10.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "menu",
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = appDrawerConfig?.navigationHeaderTitle!!,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .align(Alignment.CenterVertically),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                } else {
                    appDrawerConfig?.navigationHeader!!()
                }
                Divider()
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    if (appDrawerConfig?.allowDefaultNavigationItems == false && appDrawerConfig.navigationItems?.size!! > 0) {
                        appDrawerConfig.navigationItems.map { appDrawerItem ->
                            NavigationDrawerItem(
                                icon = appDrawerItem.navigationItemIcon,
                                label = { Text(text = appDrawerItem.navigationItemLabel!!) },
                                selected = appDrawerItem.navigationItemSelected!!,
                                onClick = {
                                    scope.launch {
                                        drawerState.close()
                                    }
                                    navController.navigate(appDrawerItem.path!!)
                                    appDrawerItem.onNavigationItemClick?.invoke()
                                },
//                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                            )
                        }
                    }
                    if (appDrawerConfig?.allowDefaultNavigationItems!!) {
                        Route.Screens.filter { screen -> screen.hasNavigationMenu!! }
                            .map { screen ->
                                NavigationDrawerItem(
                                    icon = { screen.icon },
                                    label = { Text(text = screen.label!!) },
                                    selected = false,
                                    onClick = {
                                        scope.launch {
                                            drawerState.close()
                                        }
                                        navController.navigate(screen.path!!)
                                    },
//                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                )
                            }
                    }
                }
            }
        }) {

        Scaffold(

            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState)
            },
            topBar = {
                TopAppBar(
                    scrollBehavior = scrollBehavior,
                    containerColor = topAppBarContainerColor,
                    titleContentColor = topAppBarTitleContentColor,
                    navigationButton = {
                        IconButton(onClick = {
                            if (showAppDrawerOnNavigationButton) {
                                // show app drawer
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            } else
                                navigationButtonOnClick!!.invoke(changeShowBottomSheet)
                        }) {
                            Icon(
                                tint = navigationButtonIconTint!!,
                                imageVector = navigationButtonImageVector!!,
                                contentDescription = navigationButtonDescription!!
                            )
                        }
                    },
                    actions = {
                        ActionButtonWithDropdown(
                            iconTint = actionButtonIconTint!!,
                            imageVector = actionButtonImageVector!!,
                            contentDescription = actionButtonDescription!!,
                            iconButtonOnClick = { actionButtonOnClick!!.invoke(changeShowBottomSheet) },
                            wantDropdown = wantActionButtonDropdown!!,
                            onDismissRequestDropdown = {
                                onDismissRequestDropdown!!.invoke(
                                    changeShowBottomSheet
                                )
                            },
                            dropDownOptions = dropDownOptions!!
                        )
                    }
                )
            },
            floatingActionButton = {
                SmallFloatingActionButton(
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp),
                    onClick = { onFloatingActionButtonClick!!.invoke(changeShowBottomSheet) }
                ) {
                    Icon(
                        floatingActionButtonImageVector!!,
                        contentDescription = floatingActionButtonDescription!!,
                        tint = floatingActionButtonIconTint!!
                    )
                }
            }
        ) { contentPadding ->
            // bottom modal sheet
            if (showBottomSheet) {
                BottomSheet(
                    onChangeShowBottomSheet = { args -> showBottomSheet = args },
                    bottomSheetHeader!!
                ) {
                    if (bottomSheetOptions!!.size > 0)
                        BottomSheetContent(bottomSheetOptions = bottomSheetOptions)
                    else
                        bottomSheetContent!!()
                }
            }
            Surface(
                modifier = modifier.padding(contentPadding),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = modifier,
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // screen content
                    content(
                        showBottomSheet,
                        changeShowBottomSheet,
                        scope,
                        snackBarHostState,
                        drawerState
                    )
                }
            }
        }
    }

}