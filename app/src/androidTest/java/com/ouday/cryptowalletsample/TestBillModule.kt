package com.ouday.cryptowalletsample

import com.ouday.cryptowalletsample.bills.data.model.Bill
import com.ouday.cryptowalletsample.bills.data.repository.BillRepository
import com.ouday.cryptowalletsample.bills.data.repository.BillRepositoryImp
import com.ouday.cryptowalletsample.bills.di.BillModule
import com.ouday.cryptowalletsample.bills.domain.usecase.BillUseCase
import com.ouday.cryptowalletsample.bills.domain.usecase.BillUseCaseImp
import com.ouday.cryptowalletsample.bills.ui.viewmodel.BillViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [BillModule::class]
)
object TestBillModule {

    @Provides
    fun bindBillRepository(): BillRepository = object : BillRepository{
        override fun getBills(): Flow<List<Bill>> {
            return flow{}
        }
    }

    @Provides
    fun provideMockBillUseCase(): BillUseCase {
        return MockBillUseCase()
    }

    @Provides
    fun provideMockViewModel(useCase: BillUseCase): BillViewModel {
        return BillViewModel(useCase)
    }

}

