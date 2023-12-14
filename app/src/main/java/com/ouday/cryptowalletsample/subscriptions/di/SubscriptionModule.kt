package com.ouday.cryptowalletsample.subscriptions.di

import com.ouday.cryptowalletsample.subscriptions.data.remote.SubscriptionApiService
import com.ouday.cryptowalletsample.subscriptions.data.repository.SubscriptionRepository
import com.ouday.cryptowalletsample.subscriptions.data.repository.SubscriptionRepositoryImp
import com.ouday.cryptowalletsample.subscriptions.usecase.SubscriptionUseCase
import com.ouday.cryptowalletsample.subscriptions.usecase.SubscriptionUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class SubscriptionModule {

    @Binds
    abstract fun bindSubscriptionUseCase(subscriptionUseCaseImp: SubscriptionUseCaseImp): SubscriptionUseCase

    @Binds
    abstract fun bindSubscriptionRepository(subscriptionRepositoryImp: SubscriptionRepositoryImp): SubscriptionRepository

    companion object {
        @Provides
        fun provideSubscriptionApiService(retrofit: Retrofit): SubscriptionApiService {
            return retrofit.create(SubscriptionApiService::class.java)
        }
    }
}