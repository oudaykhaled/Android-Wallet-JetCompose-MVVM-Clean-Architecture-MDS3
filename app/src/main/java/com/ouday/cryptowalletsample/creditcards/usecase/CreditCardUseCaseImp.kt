package com.ouday.cryptowalletsample.creditcards.usecase

import com.ouday.cryptowalletsample.creditcards.data.repository.CreditCardRepository
import javax.inject.Inject

class CreditCardUseCaseImp @Inject constructor(val creditCardRepository: CreditCardRepository): CreditCardUseCase {
    override fun getCreditCards() = creditCardRepository.getCreditCards()
}