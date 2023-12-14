package com.ouday.cryptowalletsample.subscriptions.usecase

import com.ouday.cryptowalletsample.subscriptions.data.repository.SubscriptionRepository
import javax.inject.Inject

class SubscriptionUseCaseImp @Inject constructor(val subscriptionRepository: SubscriptionRepository): SubscriptionUseCase {
    override fun getCreditCards() = subscriptionRepository.getSubscriptions()
}