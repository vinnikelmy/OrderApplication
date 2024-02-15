package com.example.orderapplication.core.domain.model

import com.example.orderapplication.core.domain.SelectAndSortableByName

data class Deliverer(
    val delivererId: String,
    override val name: String,
    val products: List<Product>
): SelectAndSortableByName
