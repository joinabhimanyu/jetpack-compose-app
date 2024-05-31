package com.example.myapplication.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

/*
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
 */

data class RowLayoutProps(
    val imageThumbnailUrl: String?,
    val imageContentDescription: String?,
    val titleText: String?,
    val titleTextStyle: TextStyle?
)

@Composable
fun RowLayoutLazyColumn(rowLayoutProps: RowLayoutProps, color: Color) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 10.dp, 10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = color,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp, 10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .padding(5.dp, 0.dp, 15.dp, 5.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .border(2.dp, SolidColor(Color.White), shape = RoundedCornerShape(15.dp))
            ) {
                AsyncImage(
                    model = rowLayoutProps.imageThumbnailUrl!!,
                    contentDescription = rowLayoutProps.imageContentDescription!!,
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Row {
                    Text(
                        text = rowLayoutProps.titleText!!, style = rowLayoutProps.titleTextStyle!!
                    )
                }
            }
        }
    }
}