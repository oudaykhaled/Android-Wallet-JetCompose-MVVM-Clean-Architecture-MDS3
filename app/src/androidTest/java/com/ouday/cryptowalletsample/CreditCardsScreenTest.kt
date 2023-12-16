package com.ouday.cryptowalletsample

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.ouday.cryptowalletsample.creditcards.data.model.CreditCardInfo
import com.ouday.cryptowalletsample.creditcards.ui.view.CreditCardsScreen
import com.ouday.cryptowalletsample.creditcards.ui.viewmodel.CreditCardViewModel
import com.ouday.cryptowalletsample.creditcards.usecase.CreditCardUseCase
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
class CreditCardsScreenTest {

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
    fun creditCardsScreen_DisplayedCorrectly() = testScope.runTest {
        val creditCardsState = flow {
            emit(
                listOf(
                    CreditCardInfo("Visa", "**** 1111", "12/25", "$1,000", "Paid")
                )
            )
        }

        val creditCardUseCase = object : CreditCardUseCase {
            override fun getCreditCards() = creditCardsState
        }

        val viewModel = CreditCardViewModel(creditCardUseCase)
        composeTestRule.setContent {
            CreditCardsScreen(viewModel = viewModel)
        }

        advanceUntilIdle()
        advanceTimeBy(1000)
        

        composeTestRule.onNodeWithText("Visa").assertIsDisplayed()
        composeTestRule.onNodeWithText("**** 1111").assertIsDisplayed()
        composeTestRule.onNodeWithText("Due Date 12/25").assertIsDisplayed()
        composeTestRule.onNodeWithText("$1,000").assertIsDisplayed()
    }

    @Test
    fun creditCardsScreen_LoadingState() = testScope.runTest {
        val creditCardsState = flow<List<CreditCardInfo>> {
            // Don't emit anything to simulate loading state
        }

        val creditCardUseCase = object : CreditCardUseCase {
            override fun getCreditCards() = creditCardsState
        }

        val viewModel = CreditCardViewModel(creditCardUseCase)
        composeTestRule.setContent {
            CreditCardsScreen(viewModel = viewModel)
        }

        advanceUntilIdle()
        advanceTimeBy(1000)
        

        // Check for the existence of a loading indicator
        composeTestRule.onNodeWithTag("LoadingIndicator").assertIsDisplayed()

    }

    @Test
    fun creditCardsScreen_ErrorState() = testScope.runTest {
        val errorMessage = "Network Error"
        val creditCardsState = flow<List<CreditCardInfo>> {
            throw Exception(errorMessage)
        }

        val creditCardUseCase = object : CreditCardUseCase {
            override fun getCreditCards() = creditCardsState
        }

        val viewModel = CreditCardViewModel(creditCardUseCase)
        composeTestRule.setContent {
            CreditCardsScreen(viewModel = viewModel)
        }

        advanceUntilIdle()
        advanceTimeBy(1000)
        

        // Check for the existence of an error message
        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
        // Check for the existence of a retry button
        composeTestRule.onNodeWithText("Retry").assertIsDisplayed()
    }
}
