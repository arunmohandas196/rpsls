package com.rpsls.service.hand;

import com.rpsls.dto.hand.Hand;
import com.rpsls.dto.hand.HandCompareResult;

public interface HandService {
    HandCompareResult beats(Hand playerHand, Hand opponentHand);
}
