package com.ouday.cryptowalletsample.bills

import com.ouday.cryptowalletsample.bills.data.model.Bill
import com.ouday.cryptowalletsample.bills.data.model.History
import com.ouday.cryptowalletsample.bills.data.remote.BillApiService
import com.ouday.cryptowalletsample.bills.data.repository.BillRepositoryImp
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class BillRepositoryImpTest {

    @Mock
    private lateinit var apiService: BillApiService

    private lateinit var billRepository: BillRepositoryImp

    @Before
    fun setUp() = runTest {
        MockitoAnnotations.openMocks(this)
        apiService = Mockito.mock(BillApiService::class.java)
        billRepository = BillRepositoryImp(apiService)
        billRepository = BillRepositoryImp(apiService)

        val history = listOf(
            History("2022-02-24", 313.08, true),
        )
        val bills = listOf(Bill(1, "Electricity Bill", "electricity.svg", history))
        Mockito.`when`(apiService.getCreditCards()).thenReturn(Response.success(bills))
    }

    @Test
    fun `getBills returns list of bills`() = runTest {
        val result = billRepository.getBills().toList()
        val bills = result[0]

        // Check if the result is not empty
        assert(bills.isNotEmpty())

        // Assertions on the first bill
        val firstBill = bills[0]
        assert(firstBill.id == 1)
        assert(firstBill.billName == "Electricity Bill")
        assert(firstBill.icon == "electricity.svg")

        // Assertions on the history of the first bill
        assert(firstBill.history.isNotEmpty())
        firstBill.history.forEach { historyItem ->
            assert(historyItem.date.isNotBlank())  // Check date is not blank
            assert(historyItem.amount >= 0)        // Check amount is non-negative
            assert(historyItem.paid is Boolean)    // Check paid is a boolean value
        }

    }


    @Test(expected = Exception::class)
    fun `getBills throws exception on API failure`() = runTest {
        Mockito.`when`(apiService.getCreditCards()).thenThrow(RuntimeException("Network Error"))

        billRepository.getBills().toList()
    }
}