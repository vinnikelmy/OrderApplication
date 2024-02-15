package com.example.orderapplication.deliverer_feature.data.mapper

import com.example.orderapplication.core.data.local.entities.DelivererEntity
import com.example.orderapplication.core.domain.model.Deliverer

fun Deliverer.toDelivererEntity(): DelivererEntity {
    return DelivererEntity(
        delivererId = delivererId,
        name = name
    )
}