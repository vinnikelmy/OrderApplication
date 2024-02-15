package com.example.orderapplication.order_feature.presentation.mapper

import com.example.orderapplication.core.domain.model.Deliverer
import com.example.orderapplication.order_feature.presentation.state.DelivererListItem

fun Deliverer.toDelivererListItem(): DelivererListItem {
    return DelivererListItem(
        delivererId = delivererId,
        name = name
    )
}