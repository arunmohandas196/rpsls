/** */
package com.rpsls.service.game.reporter;

import com.rpsls.dto.GameResult;

public class ConsoleResultReporter extends ResultReporter {

  public ConsoleResultReporter(GameResult gameResult) {
    super(gameResult);
  }

  public void summarizeGame() {

    super.summarizeGame();
    gameResult.getSummary().forEach(System.out::println);
    printChangeLog();
  }

  private void printChangeLog() {
    System.out.println("Changelog:");
    gameResult.getChangeLog().forEach(System.out::println);
  }
}
