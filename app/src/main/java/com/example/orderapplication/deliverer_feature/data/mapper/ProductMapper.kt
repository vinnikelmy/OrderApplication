package com.example.orderapplication.deliverer_feature.data.mapper

import com.example.orderapplication.core.data.local.entities.ProductEntity
import com.example.orderapplication.core.domain.model.Product


fun Product.toProductEntity(delivererId:String): ProductEntity {
    return ProductEntity(
        productId = productId,
        name = name,
        pricePerAmount = pricePerAmount,
        belongsToDeliverer = delivererId
    )
}