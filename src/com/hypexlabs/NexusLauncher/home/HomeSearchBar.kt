package com.hypexlabs.NexusLauncher.home

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hypexlabs.NexusLauncher.theme.iOSColors

@Composable
fun HomeSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier,
    focused: Boolean = false,
) {
    val bgColor = if (focused) Color.White.copy(alpha = 0.9f) else Color.White.copy(alpha = 0.2f)
    val focusRequester = remember { FocusRequester() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .clickable { onSearch() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(Modifier.width(8.dp))
        Icon(
            imageVector = Icons.Rounded.Search,
            contentDescription = "Search",
            modifier = Modifier.size(18.dp),
            tint = if (focused) Color.Gray else Color.White.copy(alpha = 0.6f),
        )
        Spacer(Modifier.width(6.dp))
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.weight(1f),
            singleLine = true,
            textStyle = TextStyle(
                color = if (focused) Color.Black else Color.White,
                fontSize = 16.sp,
            ),
            cursorBrush = SolidColor(iOSColors.systemBlue),
            decorationBox = { innerTextField ->
                Box {
                    if (query.isEmpty()) {
                        Text(
                            text = "Search",
                            color = if (focused) Color.Gray else Color.White.copy(alpha = 0.5f),
                            fontSize = 16.sp,
                        )
                    }
                    innerTextField()
                }
            },
        )
        if (query.isNotEmpty()) {
            IconButton(onClick = onClear, modifier = Modifier.size(24.dp)) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Clear",
                    modifier = Modifier.size(16.dp),
                    tint = if (focused) Color.Gray else Color.White.copy(alpha = 0.6f),
                )
            }
        } else {
            Spacer(Modifier.width(8.dp))
        }
    }
}
