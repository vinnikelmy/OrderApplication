package com.example.orderapplication.order_feature.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import com.example.orderapplication.order_feature.presentation.components.DelivererUiListItem
import com.example.orderapplication.ui.theme.gray
import com.example.orderapplication.ui.theme.orange
import com.example.orderapplication.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderChooseDelivererScreen(
    navController: NavController,
    orderChooseDelivererViewModel: OrderChooseDelivererViewModel = hiltViewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Deliverer selection",
                        color = white
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = orange)
            )
        }
    ){ values ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gray)
                .padding(values),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TextField(
                value = orderChooseDelivererViewModel.delivererSearchQuery,
                onValueChange = {
                    orderChooseDelivererViewModel.onSearchQueryChange(it)
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
                    Text("Search deliverer")
                },
                singleLine = true
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(top = 20.dp)
            ){
                items(
                    orderChooseDelivererViewModel.deliverersToShow,
                    key = {delivererListItem ->
                        delivererListItem.delivererId
                    }
                ){
                    DelivererUiListItem(
                        it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .border(1.dp, color = white, RoundedCornerShape(10.dp))
                            .clickable {
                                    navController.navigate(ScreenRoutes.OrderChooseProductsScreen.route + "/${it.delivererId}")
                            }
                            .padding(15.dp)

                    )
                }
            }
        }
    }

}