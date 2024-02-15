package com.example.orderapplication.deliverer_feature.data.repository

import com.example.orderapplication.core.data.local.DelivererDao
import com.example.orderapplication.core.data.local.ProductDao
import com.example.orderapplication.core.domain.model.Deliverer
import com.example.orderapplication.core.domain.model.Product
import com.example.orderapplication.deliverer_feature.data.mapper.toDelivererEntity
import com.example.orderapplication.deliverer_feature.data.mapper.toProductEntity
import com.example.orderapplication.deliverer_feature.domain.repository.DelivererRepository
import javax.inject.Inject

class DelivererRepositoryImpl @Inject constructor(
    private val delivererDao: DelivererDao,
    private val productDao: ProductDao
): DelivererRepository {

    override suspend fun insertDeliverers(list: List<Deliverer>) {
        list.forEach { deliverer ->
            delivererDao.insertDeliverer(deliverer.toDelivererEntity())
            insertProducts(deliverer.products, deliverer.delivererId)
        }
    }

    override suspend fun insertProducts(list: List<Product>, delivererId:String) {
        list.forEach { product ->
            productDao.insertProduct(product.toProductEntity(delivererId))
        }
    }

}