package com.ouday.cryptowalletsample.creditcards

import com.ouday.cryptowalletsample.creditcards.data.model.CreditCardInfo
import com.ouday.cryptowalletsample.creditcards.data.repository.CreditCardRepository
import com.ouday.cryptowalletsample.creditcards.usecase.CreditCardUseCaseImp
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CreditCardUseCaseImpTest {

    @Mock
    private lateinit var creditCardRepository: CreditCardRepository

    private lateinit var creditCardUseCase: CreditCardUseCaseImp

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        creditCardUseCase = CreditCardUseCaseImp(creditCardRepository)
    }

    @Test
    fun `getCreditCards returns list of credit cards`() = runTest {
        val creditCards =
            listOf(CreditCardInfo("VISA", "**** **** **** 1234", "10/22", "$1,000", "Paid"))
        Mockito.`when`(creditCardRepository.getCreditCards()).thenReturn(flowOf(creditCards))

        val result = creditCardUseCase.getCreditCards().toList()
        assert(result[0] == creditCards)
    }
}