package com.example.orderapplication.order_feature.data.repository

import com.example.orderapplication.core.data.local.DelivererDao
import com.example.orderapplication.core.data.local.OrderDao
import com.example.orderapplication.core.data.local.ProductDao
import com.example.orderapplication.core.data.local.entities.OrderProductEntity
import com.example.orderapplication.core.data.mapper.toDeliverer
import com.example.orderapplication.core.data.mapper.toProduct
import com.example.orderapplication.core.domain.model.Deliverer
import com.example.orderapplication.core.domain.model.Product
import com.example.orderapplication.order_feature.data.mapper.toOrder
import com.example.orderapplication.order_feature.data.mapper.toOrderEntity
import com.example.orderapplication.order_feature.domain.model.Order
import com.example.orderapplication.order_feature.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao,
    private val delivererDao: DelivererDao,
    private val productDao: ProductDao,
): OrderRepository {
    override suspend fun insertOrder(order: Order) {
        orderDao.insertOrder(order.toOrderEntity(order.delivererName))
        val orderProductEntities = order.products.map { boughtProducts ->
            OrderProductEntity(order.orderId, boughtProducts.productId, boughtProducts.amount)
        }
        orderDao.insertOrderProductEntities(orderProductEntities)
    }

    override suspend fun getOrders(): List<Order> {
        return orderDao.getOrderWithProducts().map {
            it.toOrder()
        }
    }

    override suspend fun getDeliverers(): List<Deliverer> {
        return delivererDao.getDeliverers().map {
            it.delivererEntity.toDeliverer(
                it.products.map { productEntity ->
                    productEntity.toProduct()
                }
            )
        }
    }

    override suspend fun getProductsForDeliverers(delivererId: String): List<Product> {
        return productDao.getProductsForDeliverer(delivererId).map { productEntity ->
            productEntity.toProduct()
        }
    }

    override suspend fun getDelivererNameById(delivererId: String): String {
        return delivererDao.getDelivererNameById(delivererId)
    }
}