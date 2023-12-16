package com.ouday.cryptowalletsample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ouday.cryptowalletsample.common.FlowState
import com.ouday.cryptowalletsample.bills.data.model.Bill
import com.ouday.cryptowalletsample.bills.data.model.History
import com.ouday.cryptowalletsample.bills.domain.usecase.BillUseCase
import com.ouday.cryptowalletsample.common.asFlowState
import com.ouday.cryptowalletsample.creditcards.data.model.CreditCardInfo
import com.ouday.cryptowalletsample.creditcards.usecase.CreditCardUseCase
import com.ouday.cryptowalletsample.subscriptions.data.model.Subscription
import com.ouday.cryptowalletsample.subscriptions.usecase.SubscriptionUseCase
import com.ouday.cryptowalletsample.home.ui.viewmodel.HomeViewModel
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
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var billUseCase: BillUseCase

    @Mock
    lateinit var creditCardUseCase: CreditCardUseCase

    @Mock
    lateinit var subscriptionUseCase: SubscriptionUseCase

    private lateinit var viewModel: HomeViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(billUseCase, creditCardUseCase, subscriptionUseCase)
    }

    @Test
    fun `triggerFetchBills emits loading and then success state`() = runTest {
        val history = listOf(
            History("2022-02-24", 313.08, true),
        )
        val bills = listOf(Bill(1, "Electricity Bill", "electricity.svg", history))
        `when`(billUseCase.getBills()).thenReturn(flowOf(bills))

        val job = launch {
            viewModel.triggerFetchBills()
        }

        val states = viewModel.bills.take(2).toList()

        assertTrue("First state should be Loading", states[0] is FlowState.Loading)
        advanceUntilIdle()

        assertTrue("Second state should be Success", states[1] is FlowState.Success && (states[1] as FlowState.Success).data == bills)
        job.cancel()
    }

    @Test
    fun `triggerFetchBills emits loading and then error state`() = runTest {
        val errorMessage = "Error occurred"
        val errorFlow = flow<List<Bill>> {
            throw Exception(errorMessage)
        }

        `when`(billUseCase.getBills()).thenReturn(errorFlow)

        val job = launch {
            viewModel.triggerFetchBills()
        }

        val states = viewModel.bills.take(2).toList()
        advanceUntilIdle()
        advanceTimeBy(1000)

        assertTrue("First state should be Loading", states[0] is FlowState.Loading)
        assertTrue("Second state should be Error", states[1] is FlowState.Error && (states[1] as FlowState.Error).message == errorMessage)
        job.cancel()
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

    @Test
    fun `triggerFetchSubscriptions emits loading and then success state`() = runTest {
        val subscriptions = listOf(
            Subscription(
                "Price",
                "Model",
                "Next",
                "Billing Date",
                "Progress",
                0.5,
                "Content Description",
                "Image Resource ID"
            )
        )
        `when`(subscriptionUseCase.getCreditCards()).thenReturn(flowOf(subscriptions))

        val job = launch {
            viewModel.triggerFetchSubscriptions()
        }

        val states = viewModel.subscriptions.take(2).toList()

        assertTrue("First state should be Loading", states[0] is FlowState.Loading)
        assertTrue("Second state should be Success", states[1] is FlowState.Success && (states[1] as FlowState.Success).data == subscriptions)
        job.cancel()
    }

    @Test
    fun `triggerFetchSubscriptions emits loading and then error state`() = runTest {
        val errorMessage = "Error occurred"
        val errorFlow = flow<List<Subscription>> {
            throw Exception(errorMessage)
        }

        `when`(subscriptionUseCase.getCreditCards()).thenReturn(errorFlow)

        val job = launch {
            viewModel.triggerFetchSubscriptions()
        }

        val states = viewModel.subscriptions.take(2).toList()
        advanceUntilIdle()
        advanceTimeBy(1000)

        assertTrue("First state should be Loading", states[0] is FlowState.Loading)
        assertTrue("Second state should be Error", states[1] is FlowState.Error && (states[1] as FlowState.Error).message == errorMessage)
        job.cancel()
    }
}
