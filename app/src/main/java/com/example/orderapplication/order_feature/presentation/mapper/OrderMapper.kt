package com.example.orderapplication.order_feature.presentation.mapper

import com.example.orderapplication.order_feature.domain.model.Order
import com.example.orderapplication.order_feature.presentation.state.OrderDetailListItem
import com.example.orderapplication.order_feature.presentation.state.OrderListItem
import com.example.orderapplication.order_feature.presentation.state.ProductListItem

fun Order.toOrderDetailListItem(): OrderDetailListItem {
    return OrderDetailListItem(
        orderId = orderId,
        delivererName = delivererName,
        orderDate = date,
        products = products.map { boughtProducts ->
            boughtProducts.toProductListItem()
        }
    )
}

fun Order.toOrderListItem(): OrderListItem {
    return OrderListItem(
        orderId = orderId,
        delivererName = delivererName,
        orderDate = date,
        totalAmount = products.sumOf { (it.amount * it.pricePerAmount).toDouble()}
    )
}