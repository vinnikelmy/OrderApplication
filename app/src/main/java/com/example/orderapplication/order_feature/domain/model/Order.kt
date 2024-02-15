package com.example.orderapplication.order_feature.domain.model

data class Order(
    val orderId: String,
    val date: String,
    val delivererTime: String,
    val delivererName: String,
    val products: List<BoughtProduct>
)
