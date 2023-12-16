package com.ouday.cryptowalletsample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ouday.cryptowalletsample.common.FlowState
import com.ouday.cryptowalletsample.creditcards.data.model.CreditCardInfo
import com.ouday.cryptowalletsample.creditcards.ui.viewmodel.CreditCardViewModel
import com.ouday.cryptowalletsample.creditcards.usecase.CreditCardUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CreditCardViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var creditCardUseCase: CreditCardUseCase

    private lateinit var viewModel: CreditCardViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = CreditCardViewModel(creditCardUseCase)
    }

    @Test
    fun `triggerFetchCreditCards emits loading and then success state`() = runTest {
        val creditCards = listOf(
            CreditCardInfo(
                "Visa",
                "**** **** **** 1234",
                "12/24",
                "$100.00",
                "Paid"
            )
        )
        `when`(creditCardUseCase.getCreditCards()).thenReturn(flowOf(creditCards))

        val job = launch {
            viewModel.triggerFetchCreditCards()
        }

        val states = viewModel.creditCards.take(2).toList()

        assertTrue("First state should be Loading", states[0] is FlowState.Loading)
        assertTrue("Second state should be Success", states[1] is FlowState.Success && (states[1] as FlowState.Success).data == creditCards)
        job.cancel()
    }

    @Test
    fun `triggerFetchCreditCards emits loading and then error state`() = runTest {
        val errorMessage = "Error occurred"
        val errorFlow = flow<List<CreditCardInfo>> {
            throw Exception(errorMessage)
        }

        `when`(creditCardUseCase.getCreditCards()).thenReturn(errorFlow)

        val job = launch {
            viewModel.triggerFetchCreditCards()
        }

        val states = viewModel.creditCards.take(2).toList()
        advanceUntilIdle()
        advanceTimeBy(1000)

        assertTrue("First state should be Loading", states[0] is FlowState.Loading)
        assertTrue("Second state should be Error", states[1] is FlowState.Error && (states[1] as FlowState.Error).message == errorMessage)
        job.cancel()
    }
}
