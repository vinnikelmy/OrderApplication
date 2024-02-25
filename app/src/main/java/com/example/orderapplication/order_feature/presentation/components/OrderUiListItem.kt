package com.example.orderapplication.order_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orderapplication.order_feature.presentation.state.OrderListItem
import com.example.orderapplication.ui.theme.white

@Composable
fun OrderUiListItem(
    orderListItem: OrderListItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = orderListItem.delivererName,
                fontWeight = FontWeight.Bold,
                color = white,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
            Text(
                "%.2f".format(orderListItem.totalAmount)+" $",
                fontWeight = FontWeight.Bold,
                color = white,
                fontSize = 20.sp
            )
        }
        Divider(color = white)
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = orderListItem.orderDate.replace('.', '/'),
                color = white,
                fontSize = 16.sp
            )
        }
    }
}