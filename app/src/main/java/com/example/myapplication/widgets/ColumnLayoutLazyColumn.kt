package com.example.myapplication.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

/*
            ColumnLayoutLazyColumn(columnLayoutProps = ColumnLayoutProps(
                    imageThumbnailUrl = user.thumbnailUrl,
                    imageContentDescription = user.title,
                    imageModifier = Modifier.width(150.dp),
                    titleText = user.title,
                    titleTextStyle = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                titleTextModifier = Modifier.padding(10.dp, 10.dp, 10.dp, 5.dp),
                titleTextAlign = TextAlign.Center
            ), MaterialTheme.colorScheme.tertiary)
*/

data class ColumnLayoutProps(
    val imageThumbnailUrl: String?,
    val imageContentDescription: String?,
    val imageModifier: Modifier?,
    val titleText: String?,
    val titleTextStyle: TextStyle?,
    val titleTextModifier: Modifier?,
    val titleTextAlign: TextAlign?
)

@Composable
fun ColumnLayoutLazyColumn(columnLayoutProps: ColumnLayoutProps, color: Color) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .padding(10.dp, 10.dp, 10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = color,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp, 10.dp)
                .height(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .padding(5.dp, 0.dp, 15.dp, 10.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .border(
                        2.dp,
                        SolidColor(Color.White),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .align(Alignment.CenterHorizontally)
            ) {
                AsyncImage(
                    model = columnLayoutProps.imageThumbnailUrl!!,
                    contentDescription = columnLayoutProps.imageContentDescription!!,
                    modifier = columnLayoutProps.imageModifier!!
                )
            }
            Text(
                text = columnLayoutProps.titleText!!, style = columnLayoutProps.titleTextStyle!!,
                modifier = columnLayoutProps.titleTextModifier!!,
                textAlign = columnLayoutProps.titleTextAlign!!
            )
        }
    }
}