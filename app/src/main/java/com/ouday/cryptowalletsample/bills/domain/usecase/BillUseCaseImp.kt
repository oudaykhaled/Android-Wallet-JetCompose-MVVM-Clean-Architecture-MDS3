package com.ouday.cryptowalletsample.bills.domain.usecase

import com.ouday.cryptowalletsample.bills.data.repository.BillRepository
import javax.inject.Inject

class BillUseCaseImp @Inject constructor(val repository: BillRepository): BillUseCase {
    override fun getBills() = repository.getBills()
}