package com.ouday.cryptowalletsample.subscription

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ouday.cryptowalletsample.common.FlowState
import com.ouday.cryptowalletsample.subscriptions.data.model.Subscription
import com.ouday.cryptowalletsample.subscriptions.ui.viewmodel.SubscriptionViewModel
import com.ouday.cryptowalletsample.subscriptions.usecase.SubscriptionUseCase
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
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SubscriptionViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var subscriptionUseCase: SubscriptionUseCase

    private lateinit var viewModel: SubscriptionViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = SubscriptionViewModel(subscriptionUseCase)
    }

    @Test
    fun `triggerFetchSubscription emits loading and then success state`() = runTest {
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
        Mockito.`when`(subscriptionUseCase.getCreditCards()).thenReturn(flowOf(subscriptions))

        val job = launch {
            viewModel.triggerFetchSubscription()
        }

        val states = viewModel.subscriptions.take(2).toList()

        Assert.assertTrue("First state should be Loading", states[0] is FlowState.Loading)
        Assert.assertTrue(
            "Second state should be Success",
            states[1] is FlowState.Success && (states[1] as FlowState.Success).data == subscriptions
        )
        job.cancel()
    }

    @Test
    fun `triggerFetchSubscription emits loading and then error state`() = runTest {
        val errorMessage = "Error occurred"
        val errorFlow = flow<List<Subscription>> {
            throw Exception(errorMessage)
        }

        Mockito.`when`(subscriptionUseCase.getCreditCards()).thenReturn(errorFlow)

        val job = launch {
            viewModel.triggerFetchSubscription()
        }

        val states = viewModel.subscriptions.take(2).toList()
        advanceUntilIdle()
        advanceTimeBy(1000)

        Assert.assertTrue("First state should be Loading", states[0] is FlowState.Loading)
        Assert.assertTrue(
            "Second state should be Error",
            states[1] is FlowState.Error && (states[1] as FlowState.Error).message == errorMessage
        )
        job.cancel()
    }
}