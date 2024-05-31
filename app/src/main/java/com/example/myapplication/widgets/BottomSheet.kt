package com.example.myapplication.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

data class BottomSheetOptions(
    val iconTint: Color?,
    val imageVector: ImageVector?,
    val contentDescription: String?,
    val optionText: String?,
    val onOptionClick: (() -> Unit)?
)

@Composable
fun BottomSheetContent(bottomSheetOptions: List<BottomSheetOptions>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        (items(count = bottomSheetOptions.size) { index ->
            val option = bottomSheetOptions[index];
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 20.dp)
                    .clickable { option.onOptionClick!! },
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier
                        .width(50.dp)
                        .align(Alignment.CenterVertically),
                    horizontalAlignment = Alignment.Start
                ) {
                    Icon(
                        tint = option.iconTint!!,
                        imageVector = option.imageVector!!,
                        contentDescription = option.contentDescription!!
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = option.optionText!!,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onChangeShowBottomSheet: (args: Boolean) -> Unit, header: String?,
    content: (@Composable() () -> Unit)?
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val headerString = if (header.isNullOrBlank()) "Bottom Sheet Header" else header;
    ModalBottomSheet(
        onDismissRequest = {
            onChangeShowBottomSheet.invoke(false)
        },
        sheetState = sheetState,
    ) {
        // Sheet content
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(10.dp, 0.dp, 10.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .width(200.dp)
                        .height(60.dp)
                        .padding(start = 15.dp)
                ) {
                    Text(
                        text = headerString, style = TextStyle(
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    IconButton(
                        onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    onChangeShowBottomSheet.invoke(false)
                                }
                            }
                        }, modifier = Modifier
                            .align(alignment = Alignment.End)
                            .padding(end = 10.dp),
                        content = {
                            Icon(
                                tint = Color.Black,
                                imageVector = Icons.Rounded.Close,
                                contentDescription = "Localized description"
                            )
                        }
                    )
                }
            }
            Divider()
            content!!()
        }
    }
}