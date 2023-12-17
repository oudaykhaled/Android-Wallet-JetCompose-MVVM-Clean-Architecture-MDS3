package com.ouday.cryptowalletsample.bills

import com.ouday.cryptowalletsample.bills.data.model.Bill
import com.ouday.cryptowalletsample.bills.data.repository.BillRepository
import com.ouday.cryptowalletsample.bills.domain.usecase.BillUseCaseImp
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class BillUseCaseImpTest {

    @Mock
    private lateinit var billRepository: BillRepository

    private lateinit var billUseCase: BillUseCaseImp

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        billUseCase = BillUseCaseImp(billRepository)
    }

    @Test
    fun `getBills returns list of bills`() = runTest {
        val bills = listOf(Bill(1, "Electricity", "icon_url", emptyList()))
        Mockito.`when`(billRepository.getBills()).thenReturn(flowOf(bills))

        val result = billUseCase.getBills().toList()
        assert(result[0] == bills)
    }
}