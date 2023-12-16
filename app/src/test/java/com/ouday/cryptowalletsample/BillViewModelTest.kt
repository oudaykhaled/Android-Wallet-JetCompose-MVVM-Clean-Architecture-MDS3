package com.ouday.cryptowalletsample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ouday.cryptowalletsample.bills.data.model.Bill
import com.ouday.cryptowalletsample.bills.data.model.History
import com.ouday.cryptowalletsample.bills.domain.usecase.BillUseCase
import com.ouday.cryptowalletsample.bills.ui.viewmodel.BillViewModel
import com.ouday.cryptowalletsample.common.FlowState
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
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
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class BillViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var billUseCase: BillUseCase

    private lateinit var viewModel: BillViewModel

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = BillViewModel(billUseCase)
    }

    @Test
    fun `triggerFetchBills emits loading and then success state`() = testScope.runTest {
        val historyList = listOf(History("2023-01-01", 100.0, true))
        val billList = listOf(Bill(1, "Electricity", "icon_url", historyList))

        `when`(billUseCase.getBills()).thenReturn(flowOf(billList))

        val collectedStates = mutableListOf<FlowState<List<Bill>>>()
        val job = launch {
            viewModel.bills.collect { collectedStates.add(it) }
        }

        viewModel.triggerFetchBills()

        val states = viewModel.bills.take(2).toList()

        Assert.assertTrue("First state should be Loading", states[0] is FlowState.Loading)
        advanceUntilIdle()
        advanceTimeBy(1000)
        Assert.assertTrue("Second state should be Success", states.size > 1 && states[1] is FlowState.Success && (states[1] as FlowState.Success).data == billList)

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

}
