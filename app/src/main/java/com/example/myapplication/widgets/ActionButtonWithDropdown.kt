package com.example.myapplication.widgets

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class DropDownOptions(
    val text: String,
    val leadingIconImageVector: ImageVector,
    val contentDescription: String,
    val onClickOption: () -> Unit
)

@Composable
fun ActionButtonWithDropdown(
    iconTint: Color,
    imageVector: ImageVector,
    contentDescription: String,
    iconButtonOnClick: () -> Unit,
    wantDropdown: Boolean,
    onDismissRequestDropdown: () -> Unit?,
    dropDownOptions: List<DropDownOptions>?
) {
    val showDropDownMenu = remember { mutableStateOf(false) }

    IconButton(onClick = {
        iconButtonOnClick.invoke();
        if (wantDropdown) showDropDownMenu.value = true;
    }) {
        Icon(
            tint = iconTint,
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
    if (wantDropdown) {
        DropdownMenu(
            expanded = showDropDownMenu.value,
            onDismissRequest = {
                onDismissRequestDropdown.invoke();
                showDropDownMenu.value = false;
            }
            // offset = DpOffset((-102).dp, (-64).dp),
        ) {
            dropDownOptions?.map { dropDownOptions ->
                DropdownMenuItem(text = { Text(text = dropDownOptions.text) }, leadingIcon = {
                    Icon(
                        imageVector = dropDownOptions.leadingIconImageVector,
                        contentDescription = dropDownOptions.contentDescription
                    )
                }, onClick = {
                    dropDownOptions.onClickOption.invoke();
                    showDropDownMenu.value = false;
                })
            }
        }
    }
}