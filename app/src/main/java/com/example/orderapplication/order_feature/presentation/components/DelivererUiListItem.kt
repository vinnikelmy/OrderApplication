package com.example.orderapplication.order_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.orderapplication.order_feature.presentation.state.DelivererListItem
import com.example.orderapplication.ui.theme.white

@Composable
fun DelivererUiListItem(
    delivererListItem: DelivererListItem,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            delivererListItem.name,
            color = white
        )
        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = "arrow_right",
            tint = white
        )
    }
}