package com.example.orderapplication.order_feature.data.mapper

import com.example.orderapplication.core.data.local.entities.OrderEntity
import com.example.orderapplication.order_feature.domain.model.Order

fun Order.toOrderEntity(delivererName: String): OrderEntity {
    return OrderEntity(
        orderId = orderId,
        date = date,
        delivererTime = delivererTime,
        delivererName = delivererName
    )
}