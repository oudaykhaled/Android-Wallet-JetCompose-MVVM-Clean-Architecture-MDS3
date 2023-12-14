package com.ouday.cryptowalletsample.subscriptions.usecase

import com.ouday.cryptowalletsample.subscriptions.data.model.Subscription
import kotlinx.coroutines.flow.Flow

interface SubscriptionUseCase {
    fun getCreditCards(): Flow<List<Subscription>>
}