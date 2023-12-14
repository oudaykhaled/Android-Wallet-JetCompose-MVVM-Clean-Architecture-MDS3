package com.ouday.cryptowalletsample.creditcards.di

import com.ouday.cryptowalletsample.creditcards.data.remote.CreditCardApiService
import com.ouday.cryptowalletsample.creditcards.data.repository.CreditCardRepository
import com.ouday.cryptowalletsample.creditcards.data.repository.CreditCardRepositoryImp
import com.ouday.cryptowalletsample.creditcards.usecase.CreditCardUseCase
import com.ouday.cryptowalletsample.creditcards.usecase.CreditCardUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class CreditCardModule {

    @Binds
    abstract fun bindCreditCardUseCase(creditCardUseCaseImp: CreditCardUseCaseImp): CreditCardUseCase

    @Binds
    abstract fun bindCreditCardRepository(creditCardRepositoryImp: CreditCardRepositoryImp): CreditCardRepository

    companion object {
        @Provides
        fun provideCreditCardApiService(retrofit: Retrofit): CreditCardApiService {
            return retrofit.create(CreditCardApiService::class.java)
        }
    }
}
