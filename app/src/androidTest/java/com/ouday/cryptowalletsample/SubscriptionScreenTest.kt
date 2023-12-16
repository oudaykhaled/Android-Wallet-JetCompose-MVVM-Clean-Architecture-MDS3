package com.ouday.cryptowalletsample

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.ouday.cryptowalletsample.subscriptions.data.model.Subscription
import com.ouday.cryptowalletsample.subscriptions.ui.view.SubscriptionScreen
import com.ouday.cryptowalletsample.subscriptions.ui.viewmodel.SubscriptionViewModel
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
class SubscriptionScreenTest {

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
    fun subscriptionScreen_DisplayedCorrectly() = testScope.runTest {
        val subscriptionState = flow {
            emit(
                listOf(
                    Subscription("Price: $100", "Model X", "Next Billing: 2023-12-01", "Billing Date: 2023-11-01", "60%", 0.6, "Car Image", "car_image_url")
                )
            )
        }

        val subscriptionUseCase = object : SubscriptionUseCase {
            override fun getCreditCards() = subscriptionState
        }

        val viewModel = SubscriptionViewModel(subscriptionUseCase)
        composeTestRule.setContent {
            SubscriptionScreen(viewModel = viewModel)
        }

        advanceUntilIdle()
        advanceTimeBy(1000)

        composeTestRule.onNodeWithText("Model X").assertIsDisplayed()
        composeTestRule.onNodeWithText("Price: $100").assertIsDisplayed()
        composeTestRule.onNodeWithText("Next Billing: 2023-12-01").assertIsDisplayed()
        composeTestRule.onNodeWithText("Billing Date: 2023-11-01").assertIsDisplayed()
        composeTestRule.onNodeWithText("60%").assertIsDisplayed()
    }

    @Test
    fun subscriptionScreen_LoadingState() = testScope.runTest {
        val subscriptionState = flow<List<Subscription>> {
            // Don't emit anything to simulate loading state
        }

        val subscriptionUseCase = object : SubscriptionUseCase {
            override fun getCreditCards() = subscriptionState
        }

        val viewModel = SubscriptionViewModel(subscriptionUseCase)
        composeTestRule.setContent {
            SubscriptionScreen(viewModel = viewModel)
        }

        // Check for the existence of a loading indicator
        composeTestRule.onNodeWithTag("LoadingIndicator").assertIsDisplayed()

        advanceUntilIdle()
        advanceTimeBy(1000)
    }

    @Test
    fun subscriptionScreen_ErrorState() = testScope.runTest {
        val errorMessage = "Network Error"
        val subscriptionState = flow<List<Subscription>> {
            throw Exception(errorMessage)
        }

        val subscriptionUseCase = object : SubscriptionUseCase {
            override fun getCreditCards() = subscriptionState
        }

        val viewModel = SubscriptionViewModel(subscriptionUseCase)
        composeTestRule.setContent {
            SubscriptionScreen(viewModel = viewModel)
        }

        advanceUntilIdle()
        advanceTimeBy(1000)

        // Check for the existence of an error message
        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
        // Check for the existence of a retry button
        composeTestRule.onNodeWithText("Retry").assertIsDisplayed()
    }
}
