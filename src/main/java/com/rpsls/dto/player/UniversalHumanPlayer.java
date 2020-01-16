package com.rpsls.dto.player;

import com.rpsls.dto.hand.Hand;
import com.rpsls.exception.hand.HandChoiceException;
import com.rpsls.service.hand.HandFactory;

public class UniversalHumanPlayer implements Player {
  private final HandFactory handFactory;
  private String name;
  private String choice;
  private Hand chosenHand;

  public UniversalHumanPlayer(String name) {
    this.handFactory = new HandFactory();
    this.name = name;
  }

  @Override
  public void initialise() {}

  @Override
  public void choose() throws PlayerException {
    try {
      this.chosenHand = handFactory.createHand(choice);
    } catch (HandChoiceException e) {
      throw new PlayerException("Invalid choice", e);
    }
  }

  @Override
  public Hand draw() {
    return this.chosenHand;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setChoice(String choice) {
    this.choice = choice;
  }
}
