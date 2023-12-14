package com.ouday.cryptowalletsample

import com.ouday.cryptowalletsample.subscriptions.data.model.Subscription
import com.ouday.cryptowalletsample.subscriptions.data.repository.SubscriptionRepository
import com.ouday.cryptowalletsample.subscriptions.usecase.SubscriptionUseCaseImp
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SubscriptionUseCaseImpTest {

    @Mock
    private lateinit var subscriptionRepository: SubscriptionRepository

    private lateinit var subscriptionUseCase: SubscriptionUseCaseImp

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        subscriptionUseCase = SubscriptionUseCaseImp(subscriptionRepository)
    }

    @Test
    fun `getSubscriptions returns list of subscriptions`() = runTest {
        val subscriptions = listOf(
            Subscription("$399/M", "Model X", "NEXT", "5th Oct", "48/60", 0.8, "Car Image", "ic_car")
        )
        `when`(subscriptionRepository.getSubscriptions()).thenReturn(flowOf(subscriptions))

        val result = subscriptionUseCase.getCreditCards().toList()
        assert(result[0] == subscriptions)
    }
}
