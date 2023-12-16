package com.ouday.cryptowalletsample

import com.ouday.cryptowalletsample.bills.data.model.Bill
import com.ouday.cryptowalletsample.bills.data.model.History
import com.ouday.cryptowalletsample.bills.domain.usecase.BillUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MockBillUseCase : BillUseCase {
    // Implement the methods with mock behavior
    override fun getBills(): Flow<List<Bill>> = flow {
        val history = listOf(
            History("2022-02-24", 313.08, true),
            History("2022-02-24", 313.08, true),
            History("2022-02-24", 313.08, true),
            History("2022-02-24", 313.08, true),
            History("2022-02-24", 313.08, true),
        )
        val bills = listOf(
            Bill(1, "Electricity Bill", "electricity.svg", history),
            Bill(1, "Electricity Bill", "electricity.svg", history),
            Bill(1, "Electricity Bill", "electricity.svg", history),
            Bill(1, "Electricity Bill", "electricity.svg", history)
        )
        emit(bills)
    }.catch { e -> throw e }
}
