package com.example.myapplication.screen.user_page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.OffWhite
import com.example.myapplication.viewModel.UserViewModel
import com.example.myapplication.widgets.RowLayoutLazyColumn
import com.example.myapplication.widgets.RowLayoutProps

@Composable
fun UsersListPage(userViewModel: UserViewModel) {
    val users by userViewModel.users.observeAsState()
    val isLoading by userViewModel.isLoading.observeAsState(false)
    val isError by userViewModel.isError.observeAsState(false)
    val error by userViewModel.error.observeAsState(null)
    var refreshCount by remember {
        mutableIntStateOf(1)
    }

    LaunchedEffect(key1 = refreshCount) {
        userViewModel.fetchUsers()
    }
    if (isLoading) {
        Column(
            modifier = Modifier.width(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterVertically))
            }
        }
    }
    if (isError) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp, 10.dp, 10.dp, 10.dp), verticalAlignment = Alignment.Top
        ) {
            Text(
                text = error!!, style = TextStyle(
                    color = Color.Red,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
    Row {
        ElevatedButton(onClick = { refreshCount++ }) {
            Text(text = "Refresh ${users!!.size}")
        }
    }
    LazyColumn {
        (items(count = users!!.size) { index ->
            val user = users!![index]
            RowLayoutLazyColumn(
                rowLayoutProps = RowLayoutProps(
                    imageThumbnailUrl = user.thumbnailUrl,
                    imageContentDescription = user.title,
                    titleText = user.title,
                    titleTextStyle = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                ), OffWhite
            )
        })
    }
}