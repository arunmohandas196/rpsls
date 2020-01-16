/** */
package com.rpsls.dto.player;

import com.rpsls.dto.hand.Hand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/** Helper class for communicating via command line. */
class CommandLineIO {

  public void printChoices(List<Hand> choices) {
    StringBuffer message = new StringBuffer("Please select {");
    for (Hand hand : choices) {
      message.append(hand.name()).append(" ");
    }
    message.append("}");
    System.out.println(message);
  }

  public void printHelpMessage(List<Hand> choices) {
    System.out.println("Wrong choice. Please try again.");
    printChoices(choices);
  }

  public String readLine() throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    return reader.readLine();
  }

  public void printAskForName() {
    System.out.println("Your name?");
  }

  public void print(String message) {
    System.out.println(message);
  }
}
