package com.example.orderapplication.order_feature.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.orderapplication.core.presentation.ScreenRoutes
import com.example.orderapplication.order_feature.presentation.components.OrderDetailDialog
import com.example.orderapplication.order_feature.presentation.components.OrderUiListItem
import com.example.orderapplication.ui.theme.gray
import com.example.orderapplication.ui.theme.orange
import com.example.orderapplication.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderScreen(
    navController: NavController,
    orderViewModel: OrderViewModel = hiltViewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(ScreenRoutes.OrderChooseDelivererScreen.route) },
                containerColor = orange,
                contentColor = white
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add new order"
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Order overview",
                        color = white
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = orange)
            )
        }

    ){ values ->
        if (orderViewModel.orderList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text("There are no orders yet")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gray)
                    .padding(values)
            ){
                items(
                    orderViewModel.orderList,
                    key = {orderListItem ->
                        orderListItem.orderId
                    }
                ){
                    OrderUiListItem(
                        it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .border(1.dp, color = white, RoundedCornerShape(10.dp))
                            .clickable {
                                orderViewModel.onOrderClick(it.orderId)
                            }
                    )
                }
            }
        }
    }

    if (orderViewModel.isOrderDialogShown && orderViewModel.clickedOrderItem != null) {
        OrderDetailDialog(
            onDismiss = {
                        orderViewModel.onDismissOrderDialog()
            },
            orderDetailListItem = orderViewModel.clickedOrderItem!!
        )
    }

}