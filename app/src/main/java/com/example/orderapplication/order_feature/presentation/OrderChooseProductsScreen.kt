package com.example.orderapplication.order_feature.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PriceCheck
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.orderapplication.core.presentation.ScreenRoutes
import com.example.orderapplication.order_feature.presentation.components.CheckoutDialog
import com.example.orderapplication.order_feature.presentation.components.ProductUiListItem
import com.example.orderapplication.ui.theme.gray
import com.example.orderapplication.ui.theme.orange
import com.example.orderapplication.ui.theme.white

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderChooseProductsScreen(
    navController: NavController,
    delivererId:String?,
    orderChooseProductsViewModel: OrderChooseProductsViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true){
        if(delivererId != null){
            orderChooseProductsViewModel.initProductList(delivererId)
        }
    }

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Product Section",
                        color = white
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = orange)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { orderChooseProductsViewModel.onCheckoutClick() },
                containerColor = orange,
                contentColor = white
            ) {
                Icon(
                    imageVector = Icons.Filled.PriceCheck,
                    contentDescription = "fab_money",
                    tint = white,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    ){values ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gray)
                .padding(values),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TextField(
                value = orderChooseProductsViewModel.productSearchQuery,
                onValueChange = {
                    orderChooseProductsViewModel.onProductSearchQueryChange(it)
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = white,
                    focusedContainerColor = white,
                    focusedTextColor = gray,
                    unfocusedTextColor = gray,
                    cursorColor = orange,
                    focusedLabelColor = orange,
                    focusedIndicatorColor = orange
                ),
                label = {
                    Text("Search Product")
                },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(16.dp)
            ){
                items(
                    orderChooseProductsViewModel.productsToShow,
                    key = {productListItem ->
                        productListItem.id
                    }
                ){
                    ProductUiListItem(
                        productListItem = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .border(1.dp, color = white, RoundedCornerShape(10.dp))
                            .clickable {
                                orderChooseProductsViewModel.onListItemClick(it.id)
                            }
                            .padding(10.dp),
                        isExpanded = it.isExpanded,
                        onMinusClick = {
                            orderChooseProductsViewModel.onMinusClick(it.id)
                        },
                        onPlusClick = {
                            orderChooseProductsViewModel.onPlusClick(it.id)
                        }
                    )
                }
            }
        }
    }
    if(orderChooseProductsViewModel.isCheckoutDialogShown){
        CheckoutDialog(
            onDismiss = {
                orderChooseProductsViewModel.onDismissCheckoutDialog()
            },
            onConfirm = {
                orderChooseProductsViewModel.onBuy()
                navController.navigate(ScreenRoutes.OrderScreen.route){
                    popUpTo(0)
                }
            },
            selectedProducts = orderChooseProductsViewModel.selectedProducts
        )
    }
}