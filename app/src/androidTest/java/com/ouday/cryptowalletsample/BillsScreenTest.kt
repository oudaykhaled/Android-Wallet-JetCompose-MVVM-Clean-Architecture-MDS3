package com.ouday.cryptowalletsample

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.ouday.cryptowalletsample.bills.data.model.Bill
import com.ouday.cryptowalletsample.bills.data.model.History
import com.ouday.cryptowalletsample.bills.domain.usecase.BillUseCase
import com.ouday.cryptowalletsample.bills.ui.view.BillsScreen
import com.ouday.cryptowalletsample.bills.ui.viewmodel.BillViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BillsScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun billsScreen_DisplayedCorrectly() = testScope.runTest {
        val billsState = flow {
            emit(
                listOf(
                    Bill(1, "Electricity", "icon_url", listOf(
                        History("2023-12-01", 100.0, true)
                    ))
                )
            )
        }

        val billUseCase = object : BillUseCase {
            override fun getBills() = billsState
        }

        val viewModel = BillViewModel(billUseCase)
        composeTestRule.setContent {
            BillsScreen(viewModel = viewModel)
        }

        advanceUntilIdle()
        advanceTimeBy(1000)

        composeTestRule.onNodeWithText("Date: 2023-12-01").assertIsDisplayed()
        composeTestRule.onNodeWithText("Amount: $100.0").assertIsDisplayed()

    }

    @Test
    fun billsScreen_LoadingState() = testScope.runTest {
        val billsState = flow<List<Bill>> {
            // Don't emit anything to simulate loading state
        }

        val billUseCase = object : BillUseCase {
            override fun getBills() = billsState
        }

        val viewModel = BillViewModel(billUseCase)
        composeTestRule.setContent {
            BillsScreen(viewModel = viewModel)
        }

        // Check for the existence of a loading indicator
        composeTestRule.onNodeWithTag("LoadingIndicator").assertIsDisplayed()

        advanceUntilIdle()
        advanceTimeBy(1000)
    }

    @Test
    fun billsScreen_ErrorState() = testScope.runTest {
        val errorMessage = "Network Error"
        val billsState = flow<List<Bill>> {
            throw Exception(errorMessage)
        }

        val billUseCase = object : BillUseCase {
            override fun getBills() = billsState
        }

        val viewModel = BillViewModel(billUseCase)
        composeTestRule.setContent {
            BillsScreen(viewModel = viewModel)
        }

        advanceUntilIdle()
        advanceTimeBy(1000)

        // Check for the existence of an error message
        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
        // Check for the existence of a retry button
        composeTestRule.onNodeWithText("Retry").assertIsDisplayed()

    }

}
