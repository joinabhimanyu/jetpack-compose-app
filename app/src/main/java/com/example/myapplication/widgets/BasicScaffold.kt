package com.example.myapplication.widgets

import androidx.compose.foundation.background
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class AppDrawerItem(
    val navigationItemIcon: (@Composable ()->Unit)?,
    val navigationItemLabel: String?,
    val navigationItemSelected: Boolean?,
    val onNavigationItemClick: (() -> Unit)?
)

data class AppDrawerConfig(
    val navigationItems: List<AppDrawerItem>?,
    val navigationHeader: (@Composable () -> Unit)?
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicScaffold(
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
        contentPadding: PaddingValues, showBottomSheet: Boolean,
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        appDrawerConfig?.navigationHeader!!()
                    }
                }
                Divider()
                Column(modifier= Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                ) {
                    appDrawerConfig?.navigationItems?.map { appDrawerItem ->
                        NavigationDrawerItem(
                            icon = appDrawerItem.navigationItemIcon,
                            label = { Text(text = appDrawerItem.navigationItemLabel!!) },
                            selected = appDrawerItem.navigationItemSelected!!,
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                }
                            },
//                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
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
            // screen content
            content(
                contentPadding,
                showBottomSheet,
                changeShowBottomSheet,
                scope,
                snackBarHostState,
                drawerState
            )
        }
    }

}