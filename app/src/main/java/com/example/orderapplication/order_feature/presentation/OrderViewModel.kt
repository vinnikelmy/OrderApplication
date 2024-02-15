package com.example.orderapplication.order_feature.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderapplication.order_feature.domain.model.Order
import com.example.orderapplication.order_feature.domain.repository.OrderRepository
import com.example.orderapplication.order_feature.presentation.mapper.toOrderDetailListItem
import com.example.orderapplication.order_feature.presentation.mapper.toOrderListItem
import com.example.orderapplication.order_feature.presentation.state.OrderDetailListItem
import com.example.orderapplication.order_feature.presentation.state.OrderListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository
): ViewModel() {

    private lateinit var orders: List<Order>

    var orderList by mutableStateOf<List<OrderListItem>>(emptyList())
        private set

    var isOrderDialogShown by mutableStateOf(false)
        private set

    var clickedOrderItem by mutableStateOf<OrderDetailListItem?>(null)
        private set

    init {
        viewModelScope.launch {
            orders = orderRepository.getOrders()
            setupOrderList()
        }
    }

    fun onOrderClick(orderId: String) {
        initOrderForDialog(orderId)
        isOrderDialogShown = true
    }

    private fun initOrderForDialog(orderId: String) {
        clickedOrderItem = orders.firstOrNull { it.orderId == orderId }?.toOrderDetailListItem()
    }

    fun onDismissOrderDialog() {
        isOrderDialogShown = false
        clickedOrderItem = null
    }

    private fun setupOrderList() {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss")
        orderList = orders.map { order ->
            order.toOrderListItem()
        }.sortedByDescending { orderListItem ->
            LocalDateTime.parse(orderListItem.orderDate,formatter)
        }
    }
}