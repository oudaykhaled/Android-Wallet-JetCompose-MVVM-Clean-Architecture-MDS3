package com.ouday.cryptowalletsample

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import com.ouday.cryptowalletsample.bills.data.model.Bill
import com.ouday.cryptowalletsample.bills.domain.usecase.BillUseCase
import com.ouday.cryptowalletsample.creditcards.data.model.CreditCardInfo
import com.ouday.cryptowalletsample.creditcards.usecase.CreditCardUseCase
import com.ouday.cryptowalletsample.home.ui.view.HomeScreen
import com.ouday.cryptowalletsample.home.ui.viewmodel.HomeViewModel
import com.ouday.cryptowalletsample.subscriptions.data.model.Subscription
import com.ouday.cryptowalletsample.subscriptions.usecase.SubscriptionUseCase
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
class HomeScreenTest {

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
    fun homeScreen_DisplayedCorrectly() = testScope.runTest {
        val billsState = flow {
            emit(
                listOf(
                    Bill(1, "Electricity", "icon_url", emptyList())
                )
            )
        }
        
        val creditCardsState = flow {
            emit(
                listOf(
                    CreditCardInfo("Visa", "**** 1111", "12/25", "$1,000", "Paid")
                )
            )
        }

        val subscriptionState = flow {
            emit(
                listOf(
                    Subscription("Price: $100", "Model X", "Next Billing: 2023-12-01", "Billing Date: 2023-11-01", "60%", 0.6, "Car Image", "car_image_url")
                )
            )
        }

        val viewModel = HomeViewModel(
            object : BillUseCase {
                override fun getBills() = billsState
            },
            object : CreditCardUseCase {
                override fun getCreditCards() = creditCardsState
            },
            object : SubscriptionUseCase {
                override fun getCreditCards() = subscriptionState
            }
        )

        composeTestRule.setContent {
            HomeScreen(viewModel = viewModel)
        }

        advanceUntilIdle()
        advanceTimeBy(1000)

        composeTestRule.onNodeWithText("Electricity").assertIsDisplayed()
        composeTestRule.onNodeWithText("**** 1111").assertIsDisplayed()
        composeTestRule.onNodeWithText("Model X").assertIsDisplayed()
    }

    @Test
    fun homeScreen_LoadingState() = testScope.runTest {
        // Simulate loading state
        val billsState = flow<List<Bill>> {  }
        val creditCardsState = flow<List<CreditCardInfo>> {  }
        val subscriptionState = flow<List<Subscription>> {  }

        val viewModel = HomeViewModel(
            object : BillUseCase {
                override fun getBills() = billsState
            },
            object : CreditCardUseCase {
                override fun getCreditCards() = creditCardsState
            },
            object : SubscriptionUseCase {
                override fun getCreditCards() = subscriptionState
            }
        )

        composeTestRule.setContent {
            HomeScreen(viewModel = viewModel)
        }

        advanceUntilIdle()
        advanceTimeBy(1000)

        // Check for the existence of loading indicators
        composeTestRule.onAllNodesWithTag("LoadingIndicator")[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("LoadingIndicator")[1].performScrollTo()
        composeTestRule.onAllNodesWithTag("LoadingIndicator")[1].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("LoadingIndicator")[2].performScrollTo()
        composeTestRule.onAllNodesWithTag("LoadingIndicator")[2].assertIsDisplayed()
    }

    @Test
    fun homeScreen_ErrorState() = testScope.runTest {
        val errorMessage = "Network Error"
        // Simulate error state
        val billsState = flow<List<Bill>> { throw Exception(errorMessage) }
        val creditCardsState = flow<List<CreditCardInfo>> { throw Exception(errorMessage) }
        val subscriptionState = flow<List<Subscription>> { throw Exception(errorMessage) }

        val viewModel = HomeViewModel(
            object : BillUseCase {
                override fun getBills() = billsState
            },
            object : CreditCardUseCase {
                override fun getCreditCards() = creditCardsState
            },
            object : SubscriptionUseCase {
                override fun getCreditCards() = subscriptionState
            }
        )

        composeTestRule.setContent {
            HomeScreen(viewModel = viewModel)
        }

        advanceUntilIdle()
        advanceTimeBy(1000)

        // Check for the existence of error messages
        composeTestRule.onAllNodesWithText(errorMessage)[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithText(errorMessage)[1].assertIsDisplayed()
        composeTestRule.onAllNodesWithText(errorMessage)[2].assertIsDisplayed()
    }
}
