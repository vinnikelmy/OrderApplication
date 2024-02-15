package com.example.orderapplication.deliverer_feature.domain.repository


import com.example.orderapplication.core.domain.model.Deliverer
import com.example.orderapplication.core.domain.model.Product


interface DelivererRepository {

    suspend fun insertDeliverers(list:List<Deliverer>)

    suspend fun insertProducts(list:List<Product>, delivererId:String)

}