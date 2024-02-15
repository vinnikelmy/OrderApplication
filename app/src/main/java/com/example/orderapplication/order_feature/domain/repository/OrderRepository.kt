package com.example.orderapplication.order_feature.domain.repository

import com.example.orderapplication.core.domain.model.Deliverer
import com.example.orderapplication.core.domain.model.Product
import com.example.orderapplication.order_feature.domain.model.Order

interface OrderRepository {

    suspend fun insertOrder(order: Order)

    suspend fun getOrders(): List<Order>

    suspend fun getDeliverers(): List<Deliverer>

    suspend fun getProductsForDeliverers(delivererId: String): List<Product>

    suspend fun getDelivererNameById(delivererId: String): String
}