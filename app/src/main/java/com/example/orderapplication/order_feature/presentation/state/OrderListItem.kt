package com.example.orderapplication.order_feature.presentation.state

data class OrderListItem(
    val orderId: String,
    val delivererName: String,
    val totalAmount: Double,
    val orderDate: String
)
