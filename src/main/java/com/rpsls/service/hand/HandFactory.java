/** */
package com.rpsls.service.hand;

import com.rpsls.dto.hand.Hand;
import com.rpsls.exception.hand.HandChoiceException;

import java.util.Arrays;
import java.util.List;

public class HandFactory {

  private List<Hand> possibleHands;

  public HandFactory() {
    possibleHands = Arrays.asList(Hand.values());
  }

  public Hand createHand(String choice) throws HandChoiceException {
    if (choice == null) {
      throw new HandChoiceException("Choice was null");
    }

    Hand chosen = null;
    for (Hand h : possibleHands) {
      if (choice.compareToIgnoreCase(h.name()) == 0) {
        chosen = h;
      }
    }

    if (chosen == null) {
      throw new HandChoiceException("No choice found for " + choice);
    }

    return chosen;
  }

  public List<Hand> choices() {
    return possibleHands;
  }
}
