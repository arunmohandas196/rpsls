package com.rpsls.service.hand;

import com.rpsls.dto.hand.Hand;
import com.rpsls.dto.hand.HandCompareResult;
import org.springframework.stereotype.Service;

@Service
public class HandServiceImpl implements HandService {
  @Override
  public HandCompareResult beats(Hand playerHand, Hand opponentHand) {
    if (opponentHand == null) {
      throw new IllegalArgumentException("Can't compare. OpponentHand was null");
    }
    if (playerHand == opponentHand) {
      return HandCompareResult.DRAW;
    }
    return playerHand.getBeatableHands().contains(opponentHand)
        ? HandCompareResult.WIN
        : HandCompareResult.LOSE;
  }
}
