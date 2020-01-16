package com.rpsls.dto.player;

import com.rpsls.dto.hand.Hand;
import com.rpsls.service.hand.HandFactory;

import java.util.Random;

public class ComputerPlayer implements Player {

  protected Hand chosenHand;
  protected HandFactory handFactory;
  private Random rand;
  private String name;

  public ComputerPlayer(String name) {
    rand = new Random();
    handFactory = new HandFactory();
    this.name = name;
  }

  public void initialise() {}

  public void choose() {
    this.chosenHand = handFactory.choices().get(rand.nextInt(handFactory.choices().size()));
  }

  public Hand draw() {
    return this.chosenHand;
  }

  public String getName() {
    return this.name;
  }
}
