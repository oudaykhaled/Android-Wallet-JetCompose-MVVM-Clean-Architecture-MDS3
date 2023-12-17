package com.ouday.cryptowalletsample.creditcards

import com.ouday.cryptowalletsample.creditcards.data.model.CreditCardInfo
import com.ouday.cryptowalletsample.creditcards.data.remote.CreditCardApiService
import com.ouday.cryptowalletsample.creditcards.data.repository.CreditCardRepositoryImp
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class CreditCardRepositoryImpTest {

    @Mock
    private lateinit var creditCardApiService: CreditCardApiService

    private lateinit var creditCardRepository: CreditCardRepositoryImp

    @Before
    fun setUp() = runTest {
        MockitoAnnotations.openMocks(this)
        creditCardApiService = Mockito.mock(CreditCardApiService::class.java)
        creditCardRepository = CreditCardRepositoryImp(creditCardApiService)

        val creditCards = listOf(
            CreditCardInfo("VISA", "**** **** **** 3854", "10th Oct", "$5,001.86", "EARLY"),
            CreditCardInfo("MASTERCARD", "**** **** **** 1234", "15th Nov", "$3,500.00", "ON TIME"),
            CreditCardInfo("AMEX", "**** **** **** 5678", "20th Dec", "$7,250.99", "LATE")
        )
        Mockito.`when`(creditCardApiService.getCreditCards())
            .thenReturn(Response.success(creditCards))
    }

    @Test
    fun `getCreditCards returns list of credit cards`() = runTest {
        val result = creditCardRepository.getCreditCards().toList()
        val creditCards = result[0]

        assert(creditCards.isNotEmpty())
        assert(creditCards[0].cardType == "VISA")
        assert(creditCards[1].cardNumber == "**** **** **** 1234")
        assert(creditCards[2].dueDate == "20th Dec")
    }

    @Test(expected = Exception::class)
    fun `getCreditCards throws exception on API failure`() = runTest {
        Mockito.`when`(creditCardApiService.getCreditCards())
            .thenThrow(RuntimeException("Network Error"))

        creditCardRepository.getCreditCards().toList()
    }
}