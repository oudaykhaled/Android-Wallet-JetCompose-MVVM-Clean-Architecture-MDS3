package com.ouday.cryptowalletsample.bills.di

import com.ouday.cryptowalletsample.bills.data.remote.BillApiService
import com.ouday.cryptowalletsample.bills.data.repository.BillRepository
import com.ouday.cryptowalletsample.bills.data.repository.BillRepositoryImp
import com.ouday.cryptowalletsample.bills.domain.usecase.BillUseCase
import com.ouday.cryptowalletsample.bills.domain.usecase.BillUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class BillModule {

    @Binds
    abstract fun bindBillUseCase(billUseCaseImp: BillUseCaseImp): BillUseCase

    @Binds
    abstract fun bindBillRepository(billUseCaseImp: BillRepositoryImp): BillRepository

    companion object{
        @Provides
        fun provideBillApiService(retrofit: Retrofit): BillApiService {
            return retrofit.create(BillApiService::class.java)
        }
    }

}