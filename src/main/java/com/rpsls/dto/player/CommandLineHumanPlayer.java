package com.rpsls.dto.player;

import com.rpsls.dto.hand.Hand;
import com.rpsls.exception.hand.HandChoiceException;
import com.rpsls.service.hand.HandFactory;

import java.io.IOException;

public class CommandLineHumanPlayer implements Player {

  protected Hand chosenHand;

  protected HandFactory handFactory;

  protected CommandLineIO commandLineIO;

  private String name;

  public CommandLineHumanPlayer() {
    handFactory = new HandFactory();
    commandLineIO = new CommandLineIO();
    name = "player1";
  }

  public void initialise() {
    commandLineIO.printAskForName();
    try {
      this.name = commandLineIO.readLine();
    } catch (IOException e) {
      commandLineIO.print("I didn't catch that, so I'm going to call you player1");
      this.name = "player1";
    }
  }

  public void choose() throws PlayerException {
    this.chosenHand = null;

    while (this.chosenHand == null) {
      commandLineIO.printChoices(handFactory.choices());
      try {
        String choice = commandLineIO.readLine();
        this.chosenHand = handFactory.createHand(choice);
      } catch (HandChoiceException e) {
        commandLineIO.printHelpMessage(handFactory.choices());
        continue;
      } catch (IOException e) {
        throw new PlayerException("Failed to read choice", e);
      }
    }
  }

  public Hand draw() {
    return this.chosenHand;
  }

  public String getName() {
    return this.name;
  }
}
