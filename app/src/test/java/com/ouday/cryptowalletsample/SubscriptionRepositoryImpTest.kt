package com.ouday.cryptowalletsample

import com.ouday.cryptowalletsample.subscriptions.data.model.Subscription
import com.ouday.cryptowalletsample.subscriptions.data.remote.SubscriptionApiService
import com.ouday.cryptowalletsample.subscriptions.data.repository.SubscriptionRepositoryImp
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

class SubscriptionRepositoryImpTest {

    @Mock
    private lateinit var subscriptionApiService: SubscriptionApiService

    private lateinit var subscriptionRepository: SubscriptionRepositoryImp

    @Before
    fun setUp() = runTest {
        MockitoAnnotations.openMocks(this)
        subscriptionApiService = mock(SubscriptionApiService::class.java)
        subscriptionRepository = SubscriptionRepositoryImp(subscriptionApiService)

        val subscriptions = listOf(
            Subscription("$399/M", "Model X", "NEXT", "5th Oct", "48/60", 0.8, "Car Image", "ic_car")
        )
        `when`(subscriptionApiService.getSubscriptions()).thenReturn(Response.success(subscriptions))
    }

    @Test
    fun `getSubscriptions returns list of subscriptions`() = runTest {
        val result = subscriptionRepository.getSubscriptions().toList()
        val subscriptions = result[0]

        assert(subscriptions.isNotEmpty())
        assert(subscriptions[0].priceText == "$399/M")
        assert(subscriptions[0].modelText == "Model X")
        assert(subscriptions[0].nextText == "NEXT")
        assert(subscriptions[0].billingDateText == "5th Oct")
        assert(subscriptions[0].progressText == "48/60")
        assert(subscriptions[0].progressFraction == 0.8)
        assert(subscriptions[0].imageContentDescription == "Car Image")
        assert(subscriptions[0].imageResourceId == "ic_car")
    }

    @Test(expected = Exception::class)
    fun `getSubscriptions throws exception on API failure`() = runTest {
        `when`(subscriptionApiService.getSubscriptions()).thenThrow(RuntimeException("Network Error"))

        subscriptionRepository.getSubscriptions().toList()
    }
}
