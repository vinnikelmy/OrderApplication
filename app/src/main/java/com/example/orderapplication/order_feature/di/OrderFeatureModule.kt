package com.example.orderapplication.order_feature.di

import com.example.orderapplication.core.data.local.DelivererDao
import com.example.orderapplication.core.data.local.OrderDao
import com.example.orderapplication.core.data.local.ProductDao
import com.example.orderapplication.order_feature.data.repository.OrderRepositoryImpl
import com.example.orderapplication.order_feature.domain.repository.OrderRepository
import com.example.orderapplication.order_feature.domain.use_case.ConfirmOrderUseCase
import com.example.orderapplication.order_feature.domain.use_case.FilterListByNameUseCase
import com.example.orderapplication.order_feature.domain.use_case.SortListByNameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderFeatureModule {

    @Provides
    @Singleton
    fun provideOrderRepository(
        orderDao: OrderDao,
        productDao: ProductDao,
        delivererDao: DelivererDao
    ): OrderRepository {
        return OrderRepositoryImpl(orderDao, delivererDao, productDao)
    }

    @Provides
    @Singleton
    fun provideFilterListByNameUseCase(): FilterListByNameUseCase {
        return FilterListByNameUseCase()
    }

    @Provides
    @Singleton
    fun provideSortListByNameUseCase(): SortListByNameUseCase {
        return SortListByNameUseCase()
    }

    @Provides
    @Singleton
    fun provideConfirmOrderUseCase(orderRepository: OrderRepository): ConfirmOrderUseCase {
        return ConfirmOrderUseCase(orderRepository)
    }
}