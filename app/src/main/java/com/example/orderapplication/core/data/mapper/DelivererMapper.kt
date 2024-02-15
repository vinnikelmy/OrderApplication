package com.example.orderapplication.core.data.mapper

import com.example.orderapplication.core.data.local.entities.DelivererEntity
import com.example.orderapplication.core.domain.model.Deliverer
import com.example.orderapplication.core.domain.model.Product

fun DelivererEntity.toDeliverer(products: List<Product>): Deliverer {
    return Deliverer(
        delivererId = delivererId,
        name = name,
        products = products
    )
}